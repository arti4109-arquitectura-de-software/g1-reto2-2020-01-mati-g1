package co.edu.uniandes.tianguix.conciliator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.Collection;

/**
 * Model that represents the response returned by the Matching Engine,
 * that's the model that is gonna be used in the Voting algorithm
 *
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchingEngineResponse {

	/**
	 * The identifier of the the Matching engine that generated
	 * the Matching response.
	 */
	@With private Short matchingEngineId;

	/**
	 * A Collection of {@link Match} instances that represents
	 * all the matches retrieved by the Matching Engine. If everything is OK,
	 * the matches collection should have only one match, but if there
	 * was failures in the matching algorithm, the matches collection
	 * will have more than one match.
	 */
	@With private Collection<Match> matches;

}
