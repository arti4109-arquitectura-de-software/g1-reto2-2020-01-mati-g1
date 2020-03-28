package co.edu.uniandes.tianguix.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@EnableEurekaServer
@SpringBootApplication
public class Registry {

	public static void main(String[] args) {

		SpringApplication.run(Registry.class, args);
	}

}
