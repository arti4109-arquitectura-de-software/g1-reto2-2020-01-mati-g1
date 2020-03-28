package co.edu.uniandes.tianguix.conciliator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conciliation {

	// -----------------------------------------------------------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------------------------------------------------------

	@With private String orderId;
	@With private Collection<MatchingEngineResponse> responsesToReconcile;
	@With private ConciliationStatus status;
	@With private LocalDateTime localDateTime;

	// -----------------------------------------------------------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------------------------------------------------------

	public boolean thereWasConsensus() {

		return getDistinctMatches().size() == 1;
	}

	public Optional<Match> getMatchWithHigherVoting() {

		return getDistinctMatches()
				.stream()
				.max(comparingInt(Match::getVotes));
	}

	public List<MatchingEngineResponse> getResponsesWithoutConsensus() {

		return responsesToReconcile
				.stream()
				.filter(response -> !response.thereWasConsensus())
				.collect(Collectors.toList());
	}

	// -----------------------------------------------------------------------------------------------------------------
	// Inner logic
	// -----------------------------------------------------------------------------------------------------------------

	private List<Match> getDistinctMatches() {

		return responsesToReconcile
				.stream()
				.map(MatchingEngineResponse::getMatches)
				.flatMap(Collection::stream)
				.distinct()
				.collect(Collectors.toList());
	}
}
