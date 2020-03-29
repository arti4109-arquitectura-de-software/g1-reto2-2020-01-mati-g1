package co.edu.uniandes.tianguix.conciliator.controller;

import co.edu.uniandes.tianguix.conciliator.model.MatchingEngineResponse;
import co.edu.uniandes.tianguix.conciliator.repository.ResponsesRepository;
import co.edu.uniandes.tianguix.conciliator.service.ConciliationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
@RequestMapping("/conciliation")
public class ConciliationController {

	// -----------------------------------------------------------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------------------------------------------------------

	private final ConciliationService conciliationService;

	// -----------------------------------------------------------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------------------------------------------------------

	@PostMapping
	public ResponseEntity doConciliation(@RequestBody MatchingEngineResponse response) {

		conciliationService.conciliate(response);
		return ResponseEntity.ok().build();
	}
}
