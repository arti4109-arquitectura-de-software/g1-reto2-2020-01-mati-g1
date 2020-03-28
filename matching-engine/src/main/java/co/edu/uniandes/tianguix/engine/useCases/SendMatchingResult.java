package co.edu.uniandes.tianguix.engine.useCases;

import co.edu.uniandes.tianguix.engine.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@PropertySource("classpath:application.properties")
@Service
public class SendMatchingResult {
    @Autowired
    private Environment environment;
    @Value("$")
    private String url;

    public String sendResultToCollector(Order order) throws CommunicationException {
        try {
            URL url = new URL(environment.getRequiredProperty("api.collector.url"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            String jsonInputString = createResponseObject(order);
            System.out.println("Matching Response Object = " + jsonInputString);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("An error at consume Collector API, Error Code: " + conn.getResponseCode());
            } else {
                System.out.println("Response HTTP 200");
            }
            return jsonInputString;
        } catch (UnsupportedEncodingException e) {
            throw new CommunicationException(e.getCause());
        } catch (ProtocolException e) {
            throw new CommunicationException(e.getCause());
        } catch (MalformedURLException e) {
            throw new CommunicationException(e.getCause());
        } catch (IOException e) {
            throw new CommunicationException(e.getCause());
        }
    }

    private String createResponseObject(Order order) {
        ObjectMapper mapper = new ObjectMapper();
        boolean failureIsEnabled = Boolean.parseBoolean(environment.getRequiredProperty("failureIsEnabled"));
        int enginesConsensusGroups = 1;
        if (failureIsEnabled) {
            enginesConsensusGroups = (int) (Math.random() * ((9) + 1)+1);
        }
        Match[] answers = new Match[enginesConsensusGroups];
        for (int i = 0; i < enginesConsensusGroups; i++) {
            String[] ordersId = createMatches(order);
            short votes;
            if(i==0){
                votes= (short) (10/enginesConsensusGroups + 10%enginesConsensusGroups);
            }else{
                votes = (short) (10/enginesConsensusGroups);
            }
            Match answerItem = new Match(order.getOrderId(),order.getType(), ordersId,votes);
            answers[i]=answerItem;
        }
        MatchingResponse matchingResponseDTO = new MatchingResponse(environment.getRequiredProperty("engineId"),answers);
        String json="";
        try {
            json=mapper.writeValueAsString(matchingResponseDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    private String[] createMatches(Order order) {
        int matchedOrders = (int) (Math.random() * ((4) + 1)+1);
        String[] ordersId = new String[matchedOrders];
        for (int j = 0; j < matchedOrders; j++) {
            String prefix;
            if(order.getType()== OrderType.PURCHASE){
                prefix = "SALE_";
            }
            else{
                prefix = "PURCHASE_";
            }
            ordersId [j]= prefix + j;
        }
        return ordersId;
    }
}
