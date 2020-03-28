package co.edu.uniandes.tianguix.conciliator.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@FeignClient("slack.notifications")
public interface SlackClient {

	@PostMapping
	void sendMessage(String message);
}
