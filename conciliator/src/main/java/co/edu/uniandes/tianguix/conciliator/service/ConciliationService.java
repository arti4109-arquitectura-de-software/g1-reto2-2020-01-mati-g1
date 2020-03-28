package co.edu.uniandes.tianguix.conciliator.service;

import co.edu.uniandes.tianguix.conciliator.model.MatchingEngineResponse;

/**
 * @author <a href="mailto:d.bellonc@uniandew.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@FunctionalInterface
public interface ConciliationService {

	/**
	 * The method should receive a matching engine response and verify if all
	 * the conditions are fulfilled in order to proceed with the order materialization
	 *
	 * @param response an incoming {@link MatchingEngineResponse}
	 */
	void conciliate(MatchingEngineResponse response);
}
