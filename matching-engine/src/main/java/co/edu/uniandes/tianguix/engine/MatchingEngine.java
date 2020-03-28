package co.edu.uniandes.tianguix.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class MatchingEngine {

	public static void main(String[] args) {

		SpringApplication.run(MatchingEngine.class, args);
	}

}
