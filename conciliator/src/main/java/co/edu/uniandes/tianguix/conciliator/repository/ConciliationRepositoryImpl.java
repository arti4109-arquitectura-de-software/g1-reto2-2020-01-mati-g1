package co.edu.uniandes.tianguix.conciliator.repository;

import co.edu.uniandes.tianguix.conciliator.model.Conciliation;
import co.edu.uniandes.tianguix.conciliator.model.ConciliationStatus;
import co.edu.uniandes.tianguix.conciliator.model.Match;
import co.edu.uniandes.tianguix.conciliator.model.MatchingEngineResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@Slf4j
@Component
public class ConciliationRepositoryImpl implements ConciliationRepository {

	// -----------------------------------------------------------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------------------------------------------------------

	private static Map<String, Conciliation> conciliationMap;

	// -----------------------------------------------------------------------------------------------------------------
	// Init Methods
	// -----------------------------------------------------------------------------------------------------------------

	static {
		conciliationMap = new HashMap<>();
	}

	// -----------------------------------------------------------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public void addResponse(MatchingEngineResponse response) {

		response.getMatches()
				.stream()
				.findFirst()
				.map(Match::getOrderID)
				.ifPresent(orderId -> addResponseToConciliation(orderId, response));
	}

	@Override
	public Optional<Conciliation> getConciliation(String orderId) {

		return Optional.ofNullable(conciliationMap.get(orderId));
	}

	// -----------------------------------------------------------------------------------------------------------------
	// Inner logic
	// -----------------------------------------------------------------------------------------------------------------

	private void addResponseToConciliation(String orderId, MatchingEngineResponse response) {

		log.debug("Adding response from matching engine with id: '{}' to order with id: '{}'",
				  response.getMatchingEngineId(),
				  orderId);

		getConciliation(orderId).ifPresentOrElse(
				conciliation -> conciliation.getResponsesToReconcile().add(response),
				() -> conciliationMap.put(orderId, makeConciliationForResponse(orderId, response)));
	}

	private Conciliation makeConciliationForResponse(String orderId, MatchingEngineResponse response) {

		log.debug("Creating conciliation for order id: '{}'", orderId);

		var responses = new ArrayList<MatchingEngineResponse>();
		responses.add(response);

		return new Conciliation()
				.withOrderId(orderId)
				.withStatus(ConciliationStatus.CREATED)
				.withLocalDateTime(LocalDateTime.now())
				.withResponsesToReconcile(responses);
	}
}
