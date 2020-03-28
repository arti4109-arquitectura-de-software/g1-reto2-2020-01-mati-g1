package co.edu.uniandes.tianguix.conciliator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@EnableEurekaClient
@SpringBootApplication
public class Conciliator {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(Conciliator.class, args);
		run.addApplicationListener(applicationEvent -> System.out.printf("OFF --- " + applicationEvent.toString()));

	}

}
