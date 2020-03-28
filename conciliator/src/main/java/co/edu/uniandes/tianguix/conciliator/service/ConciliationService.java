package co.edu.uniandes.tianguix.conciliator.service;

import co.edu.uniandes.tianguix.conciliator.model.MatchingEngineResponse;

/**
 * @author <a href="mailto:d.bellonc@uniandew.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@FunctionalInterface
public interface ConciliationService {

	void conciliate(MatchingEngineResponse response);
}
