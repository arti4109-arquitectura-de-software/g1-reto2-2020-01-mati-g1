package co.edu.uniandes.tianguix.engine.rest;

import co.edu.uniandes.tianguix.engine.model.MatchingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("MATCHING-ENGINE")
public interface ConciliatorClient {

    @PostMapping("/machine-engine/v1/match/mockCollector")
    MatchingResponse processOrder(MatchingResponse matchingResponse);
}
