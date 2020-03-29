package co.edu.uniandes.tianguix.engine.entrypoint;

import co.edu.uniandes.tianguix.engine.model.MatchingEngineResponse;
import co.edu.uniandes.tianguix.engine.model.Order;
import co.edu.uniandes.tianguix.engine.useCases.SendMatchingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/machine-engine/v1/match")
public class MachineEngineReceptor {

    // -----------------------------------------------------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------------------------------------------------
    static int processedOrderId = 0;
    SendMatchingResult sendMatchingResult;
    @Autowired private Environment environment;


    // -----------------------------------------------------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------------------------------------------------

    public MachineEngineReceptor(SendMatchingResult sendMatchingResult) {
        this.sendMatchingResult = sendMatchingResult;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity processOrder(@RequestBody Order arrivedOrder){
        System.out.println("INSTANCE ID:" + environment.getProperty("eureka.instance.instance-id"));
        arrivedOrder.setProcessId(processedOrderId++);
        ResponseEntity result = null;
        try {
            result = sendMatchingResult.sendResultToCollector(arrivedOrder);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PostMapping("/mockCollector")
    public ResponseEntity collectAnswers(@RequestBody MatchingEngineResponse response){
        return ResponseEntity.ok().body(response);
    }
}
