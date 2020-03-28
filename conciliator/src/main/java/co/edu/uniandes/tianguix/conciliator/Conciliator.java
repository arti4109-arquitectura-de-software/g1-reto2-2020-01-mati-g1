package co.edu.uniandes.tianguix.conciliator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@Slf4j
@EnableEurekaClient
@SpringBootApplication
public class Conciliator {

	public static void main(String[] args) {

		var context = SpringApplication.run(Conciliator.class, args);
		context.addApplicationListener(applicationEvent -> log.error("OFF --> {}", applicationEvent.toString()));
	}

}
