package co.edu.uniandes.tianguix.engine.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@PropertySource("classpath:application.properties")
@Service
public class MatchResultSender {
    @Autowired
    private Environment environment;
    public void sendResultToCollector(){
        try {
            URL url = new URL(environment.getRequiredProperty("api.collector.url"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";

            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }


            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("An error at consume Collector API");
            } else {
                System.out.println("Response HTTP 200");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
