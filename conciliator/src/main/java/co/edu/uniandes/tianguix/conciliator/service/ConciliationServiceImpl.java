package co.edu.uniandes.tianguix.conciliator.service;

import co.edu.uniandes.tianguix.conciliator.model.Conciliation;
import co.edu.uniandes.tianguix.conciliator.model.FailureNotification;
import co.edu.uniandes.tianguix.conciliator.model.Match;
import co.edu.uniandes.tianguix.conciliator.model.MatchingEngineResponse;
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

		var orderId = response.getMatches().stream().findFirst().map(Match::getOrderId).orElse("");
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

		if (conciliation.thereWasConsensus()) {
			// TODO: 28/03/20 materialize match
		} else {
			// Notifying the failure ...
			notifyConciliationFailure(conciliation);

			// materializing the match with higher voting ...
			var optionalMatch = conciliation.getMatchWithHigherVoting();
			optionalMatch.ifPresentOrElse(
					this::materializeMatch,
					() -> log.error("there was no match to save for order with id '{}'", conciliation.getOrderId()));
		}
	}

	private void materializeMatch(Match match) {

	}

	private void notifyConciliationFailure(Conciliation conciliation) {

		conciliation.getResponsesWithoutConsensus().forEach(
				response -> notificationService.notify(makeFailureNotification(response)));
	}

	private FailureNotification makeFailureNotification(MatchingEngineResponse response) {

		var optionalOrderId = response.getMatches().stream().findFirst().map(Match::getOrderId);
		var orderId = optionalOrderId.orElse("No order Id retrieved");

		return new FailureNotification()
				.withLocalDateTime(LocalDateTime.now())
				.withOrderId(orderId)
				.withMatchingEngineId(response.getMatchingEngineId());
	}

}
