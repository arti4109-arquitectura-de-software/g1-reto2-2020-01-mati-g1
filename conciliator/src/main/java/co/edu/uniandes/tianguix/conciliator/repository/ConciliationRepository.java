package co.edu.uniandes.tianguix.conciliator.repository;

import co.edu.uniandes.tianguix.conciliator.model.MatchingEngineResponse;

import java.util.Collection;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
public interface ConciliationRepository {

	void addResponse(MatchingEngineResponse response);

	Collection<MatchingEngineResponse> getResponsesForOrderId(String orderId);

}
