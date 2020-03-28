package co.edu.uniandes.tianguix.orders.manager.entrypoint;

import co.edu.uniandes.tianguix.orders.manager.model.OrderDTO;
import co.edu.uniandes.tianguix.orders.manager.rest.MatchingEngineRestClient;
import co.edu.uniandes.tianguix.orders.manager.service.DiscoveryService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/v1/orders")
public class OrderReceptor {

    // -----------------------------------------------------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------------------------------------------------
    static int processedOrderId = 0;
    private final MatchingEngineRestClient client;
    private final DiscoveryService discoveryService;


    // -----------------------------------------------------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------------------------------------------------
    public OrderReceptor(MatchingEngineRestClient client, DiscoveryService discoveryService) {
        this.client = client;
        this.discoveryService = discoveryService;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity processOrder(@RequestBody OrderDTO arrivedOrder) {
        List<InstanceInfo> matchingEngineInstances = discoveryService.getMatchingEngineInstances();
        matchingEngineInstances.forEach(instanceInfo -> {
            System.out.println("Sending order to " + instanceInfo.getInstanceId());
            client.processOrder(arrivedOrder);
        });
        return ResponseEntity.ok().body(arrivedOrder);
    }
}
