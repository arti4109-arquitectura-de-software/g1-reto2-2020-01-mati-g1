package co.edu.uniandes.tianguix.orders.manager.rest;

import co.edu.uniandes.tianguix.orders.manager.model.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("MATCHING-ENGINE")
public interface MatchingEngineRestClient {

    @PostMapping("/machine-engine/v1/match/")
    OrderDTO processOrder(OrderDTO order);
}
