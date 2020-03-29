package co.edu.uniandes.tianguix.engine.rest;

import co.edu.uniandes.tianguix.engine.model.MatchingEngineResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("CONCILIATOR")
public interface ConciliatorClient {

    @PostMapping("/conciliation")
    ResponseEntity processOrder(MatchingEngineResponse matchingResponse);
}
