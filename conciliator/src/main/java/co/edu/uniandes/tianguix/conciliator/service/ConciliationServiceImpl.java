package co.edu.uniandes.tianguix.conciliator.service;

import co.edu.uniandes.tianguix.conciliator.model.Conciliation;
import co.edu.uniandes.tianguix.conciliator.model.FailureNotification;
import co.edu.uniandes.tianguix.conciliator.model.Match;
import co.edu.uniandes.tianguix.conciliator.model.MatchingEngineResponse;
import co.edu.uniandes.tianguix.conciliator.model.Response;
import co.edu.uniandes.tianguix.conciliator.model.Type;
import co.edu.uniandes.tianguix.conciliator.repository.ConciliationRepository;
import co.edu.uniandes.tianguix.conciliator.repository.ResponsesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:daniel.bellon@payulatam.com"> Daniel Bellón </a>
 * @since 0.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConciliationServiceImpl implements ConciliationService {

	// -----------------------------------------------------------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------------------------------------------------------

	private final ResponsesRepository responsesRepository;
	private final ConciliationRepository conciliationRepository;
	private final DiscoveryService discoveryService;
	private final NotificationService notificationService;

	// -----------------------------------------------------------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void conciliate(MatchingEngineResponse response) {

		conciliationRepository.addResponse(response);

		var orderId = response.getMatches().stream().findFirst().map(Match::getOrderID).orElse("");
		var optionalConciliation = conciliationRepository.getConciliation(orderId);
		var matchingEngineInstances = discoveryService.getMatchingEngineInstances();

		optionalConciliation.ifPresent(conciliation -> {
			if (matchingEngineInstances == conciliation.getResponsesToReconcile().size()) {
				doReconciliation(conciliation);
			}
		});
	}

	// -----------------------------------------------------------------------------------------------------------------
	// Inner logic
	// -----------------------------------------------------------------------------------------------------------------

	private void doReconciliation(Conciliation conciliation) {

		var response = new Response().withLocalDateTime(LocalDateTime.now());

		if (conciliation.thereWasConsensus()) {
			responsesRepository.save(response.withType(Type.SUCCESS));
		} else {
			responsesRepository.save(response.withType(Type.FAIL));
			notifyConciliationFailure(conciliation);
		}
	}

	private void notifyConciliationFailure(Conciliation conciliation) {

		conciliation.getResponsesWithoutConsensus().forEach(
				response -> notificationService.notify(makeFailureNotification(response)));
	}

	private FailureNotification makeFailureNotification(MatchingEngineResponse response) {

		var optionalOrderId = response.getMatches().stream().findFirst().map(Match::getOrderID);
		var orderId = optionalOrderId.orElse("No order Id retrieved");

		return new FailureNotification()
				.withLocalDateTime(LocalDateTime.now())
				.withOrderId(orderId)
				.withMatchingEngineId(response.getMatchingEngineId());
	}
}
