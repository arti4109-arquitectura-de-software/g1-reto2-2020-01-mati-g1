package co.edu.uniandes.tianguix.engine.useCases;

import co.edu.uniandes.tianguix.engine.model.*;
import co.edu.uniandes.tianguix.engine.rest.ConciliatorClient;
import co.edu.uniandes.tianguix.engine.service.DiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@PropertySource("classpath:application.properties")
@Service
public class SendMatchingResult {
    @Autowired
    private Environment environment;
    private final ConciliatorClient conciliatorClient;

    public SendMatchingResult(ConciliatorClient conciliatorClient, DiscoveryService discoveryService) {
        this.conciliatorClient = conciliatorClient;
    }


    public MatchingResponse sendResultToCollector(Order order){
        MatchingResponse matchingResponse = createResponseObject(order);
        System.out.println("Matching Response Object = " + matchingResponse);
        System.out.println("Sending order to conciliator");
        conciliatorClient.processOrder(matchingResponse);
        return matchingResponse;

    }

    private MatchingResponse createResponseObject(Order order) {
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
        MatchingResponse matchingResponseDTO = new MatchingResponse(environment.getRequiredProperty("engineId"), answers);

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
