package co.edu.uniandes.tianguix.conciliator.rest;

import feign.RequestLine;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */

public interface SlackClient {

	@RequestLine("POST /")
	void sendMessage(String message);
}
