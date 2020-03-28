package co.edu.uniandes.tianguix.orders.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@EnableEurekaClient
@SpringBootApplication
public class OrdersManager {

	public static void main(String[] args) {

		SpringApplication.run(OrdersManager.class, args);
	}
}
