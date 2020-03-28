package co.edu.uniandes.tianguix.conciliator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.Collection;

/**
 * A model that represents a Match retrieved by the Matching Engine,
 * the model should have the requested order and all the orders that can
 * solve the incoming request, the model should have the match votes as well
 *
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {

	/**
	 * The identifier for the requested operation
	 */
	@With private String orderID;

	/**
	 * The requested operation type, for instance SALE or PURCHASE
	 */
	@With private OrderType orderType;

	/**
	 * A Collection with the identifiers for all the orders
	 * that do match with the {@link Match#orderID}
	 */
	@With private Collection<String> matchesIds;

	/**
	 * Indicates how many Match Algorithm Runners return
	 * the current match, in order to be used in the Voting process
	 */
	@With private Short votes;

}
