package co.edu.uniandes.tianguix.conciliator.contrller;

import co.edu.uniandes.tianguix.conciliator.service.EurekaDiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/instances")
public class DiscoveryController {

    @Autowired
    private EurekaDiscoveryService service;

    @GetMapping("/instances")
    public String home() {
        return "INSTANCES: " + service.getMatchingEngineInstances();
    }
}
