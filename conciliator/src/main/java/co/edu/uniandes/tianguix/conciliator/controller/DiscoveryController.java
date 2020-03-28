package co.edu.uniandes.tianguix.conciliator.controller;

import co.edu.uniandes.tianguix.conciliator.service.EurekaDiscoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/instances")
public class DiscoveryController {

	// -----------------------------------------------------------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------------------------------------------------------

	private final EurekaDiscoveryService service;

	// -----------------------------------------------------------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------------------------------------------------------

	@GetMapping
	public String getMatchingEngineInstances() {

        var instances = service.getMatchingEngineInstances();
        return String.format("INSTANCES: %d", instances);
	}
}
