package co.edu.uniandes.tianguix.conciliator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@Slf4j
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class Conciliator {

	public static void main(String[] args) {
		SpringApplication.run(Conciliator.class, args);
	}
}
