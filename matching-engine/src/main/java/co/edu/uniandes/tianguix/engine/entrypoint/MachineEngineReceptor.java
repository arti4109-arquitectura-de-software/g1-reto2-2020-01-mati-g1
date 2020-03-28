package co.edu.uniandes.tianguix.engine.entrypoint;

import co.edu.uniandes.tianguix.engine.model.OrderDTO;
import co.edu.uniandes.tianguix.engine.useCases.MatchResultSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/machine-engine/v1/perform-match")
public class MachineEngineReceptor {

    // -----------------------------------------------------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------------------------------------------------
    static int processedOrderId = 0;
    MatchResultSender matchResultSender;
    @Value("eureka.instance.instance-id")
    String instanceId;


    // -----------------------------------------------------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------------------------------------------------

    public MachineEngineReceptor(MatchResultSender matchResultSender) {
        this.matchResultSender = matchResultSender;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity processOrder(@RequestBody OrderDTO arrivedOrder) {
        arrivedOrder.setOrderId(processedOrderId++);
        System.out.println("MATCHING-ENGINE: " + instanceId);
//        matchResultSender.sendResultToCollector();
        return ResponseEntity.ok().body(arrivedOrder);
    }
}
