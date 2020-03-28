package co.edu.uniandes.tianguix.conciliator.service;

import co.edu.uniandes.tianguix.conciliator.model.Match;
import co.edu.uniandes.tianguix.conciliator.model.MatchingEngineResponse;
import co.edu.uniandes.tianguix.conciliator.repository.ConciliationRepository;
import co.edu.uniandes.tianguix.conciliator.repository.FailuresRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

	private final ConciliationRepository conciliationRepository;
	private final FailuresRepository failuresRepository;
	private final DiscoveryService discoveryService;

	// -----------------------------------------------------------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------------------------------------------------------

	@Override public void conciliate(MatchingEngineResponse response) {

		conciliationRepository.addResponse(response);

		var orderId = response.getMatches().stream().findFirst().map(Match::getOrderId).orElse("");
		var responses = Optional.ofNullable(conciliationRepository.getResponsesForOrderId(orderId));
		var instances = discoveryService.getMatchingEngineInstances();

		responses.ifPresent(value -> {

			if (instances == value.size()) {

				// TODO: 27/03/20 implement the conciliation logic
			}
		});

	}
}
