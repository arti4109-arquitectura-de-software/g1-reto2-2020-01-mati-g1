package co.edu.uniandes.tianguix.conciliator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@SpringBootApplication
@EnableEurekaClient
public class Conciliator {

	public static void main(String[] args) {

		SpringApplication.run(Conciliator.class, args);
	}

}
