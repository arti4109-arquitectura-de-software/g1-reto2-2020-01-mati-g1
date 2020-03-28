package co.edu.uniandes.tianguix.engine.entrypoint;

import co.edu.uniandes.tianguix.engine.model.CommunicationException;
import co.edu.uniandes.tianguix.engine.model.MatchingResponse;
import co.edu.uniandes.tianguix.engine.model.Order;
import co.edu.uniandes.tianguix.engine.useCases.SendMatchingResult;
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
        arrivedOrder.setProcessId(processedOrderId++);
        String result = null;
        try {
            result = sendMatchingResult.sendResultToCollector(arrivedOrder);
            return ResponseEntity.ok().body(result);
        } catch (CommunicationException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PostMapping("/mockCollector")
    public ResponseEntity collectAnswers(@RequestBody MatchingResponse response){
        return ResponseEntity.ok().body(response);
    }
}
