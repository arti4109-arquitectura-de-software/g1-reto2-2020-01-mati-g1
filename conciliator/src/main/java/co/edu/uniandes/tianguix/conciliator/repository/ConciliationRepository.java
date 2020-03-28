package co.edu.uniandes.tianguix.conciliator.repository;

import co.edu.uniandes.tianguix.conciliator.model.Conciliation;
import co.edu.uniandes.tianguix.conciliator.model.MatchingEngineResponse;

import java.util.Optional;

/**
 * Repository in charge of the {@link Conciliation} management
 *
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
public interface ConciliationRepository {

	/**
	 * Receive a {@link MatchingEngineResponse} and evaluate if there is an already existent
	 * conciliation for the order id that generated the
	 *
	 * @param response a {@link MatchingEngineResponse}
	 */
	void addResponse(MatchingEngineResponse response);

	/**
	 * Retrieve the conciliation for the order with the given identifier
	 *
	 * @param orderId the order identifier for which the conciliation is going to be retrieved
	 * @return an {@link Optional<Conciliation>} instance that will have the retrieved conciliation
	 * if there is a conciliation for the given order id, an optional with no value otherwise
	 */
	Optional<Conciliation> getConciliation(String orderId);

}
