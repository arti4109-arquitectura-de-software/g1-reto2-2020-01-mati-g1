package co.edu.uniandes.tianguix.engine.useCases;

import co.edu.uniandes.tianguix.engine.model.*;
import co.edu.uniandes.tianguix.engine.rest.ConciliatorClient;
import co.edu.uniandes.tianguix.engine.service.DiscoveryService;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class SendMatchingResult {

    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient eurekaClient;
    private final ConciliatorClient conciliatorClient;

    public SendMatchingResult(ConciliatorClient conciliatorClient, DiscoveryService discoveryService) {
        this.conciliatorClient = conciliatorClient;
    }


    public ResponseEntity sendResultToCollector(Order order){
        MatchingEngineResponse matchingResponse = createResponseObject(order);
        System.out.println("Matching Response Object = " + matchingResponse);
        System.out.println("Sending order to conciliator");
        ResponseEntity responseEntity = conciliatorClient.processOrder(matchingResponse);
        return responseEntity;

    }

    private MatchingEngineResponse createResponseObject(Order order) {
        int enginesConsensusGroups = 1;
        if( TianguixFeature.FAILURE.isActive() ) {
            enginesConsensusGroups = (int) (Math.random() * ((9) + 1) + 1);
        }
        Match[] answers = new Match[enginesConsensusGroups];
        Collection<Integer> numbersConsensus = new ArrayList<Integer>();
        for (int i = 0; i < enginesConsensusGroups; i++) {
            String[] ordersId = createMatches(order, numbersConsensus);
            numbersConsensus.add(ordersId.length);
            short votes;
            if (i == 0) {
                votes = (short) (10 / enginesConsensusGroups + 10 % enginesConsensusGroups);
            } else {
                votes = (short) (10 / enginesConsensusGroups);
            }
            Match answerItem = new Match(order.getOrderId(), order.getType(), ordersId, votes);
            answers[i] = answerItem;
        }
        MatchingEngineResponse matchingResponseDTO = new MatchingEngineResponse(eurekaClient.getApplicationInfoManager().getInfo().getInstanceId(), answers);

        return matchingResponseDTO;
    }

    private String[] createMatches(Order order, Collection<Integer> numberConsensus) {
        int matchedOrders;
        do {
            matchedOrders = (int) (Math.random() * ((9) + 1) + 1);
        }
        while (numberConsensus.contains(Integer.valueOf(matchedOrders)) && numberConsensus.size() < 10);
        String[] ordersId = new String[matchedOrders];
        for (int j = 0; j < matchedOrders; j++) {
            String prefix;
            if (order.getType() == OrderType.PURCHASE) {
                prefix = "SALE_";
            } else {
                prefix = "PURCHASE_";
            }
            ordersId[j] = prefix + j;
        }
        return ordersId;
    }
}
