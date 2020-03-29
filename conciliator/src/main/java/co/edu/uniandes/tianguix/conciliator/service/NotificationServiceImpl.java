package co.edu.uniandes.tianguix.conciliator.service;

import co.edu.uniandes.tianguix.conciliator.model.FailureNotification;
import co.edu.uniandes.tianguix.conciliator.rest.SlackClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	// -----------------------------------------------------------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------------------------------------------------------

	private final SlackClient slackClient;

	// -----------------------------------------------------------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public void notify(FailureNotification notification) {

		var message = getNotificationMessage(notification);
		slackClient.sendMessage(message);

	}

	// -----------------------------------------------------------------------------------------------------------------
	// Inner logic
	// -----------------------------------------------------------------------------------------------------------------

	private String getNotificationMessage(FailureNotification notification) {

		return getNotificationTemplate()
				.replace("matchingEngineId", notification.getMatchingEngineId())
				.replace("date", notification.getLocalDateTime().toString())
				.replace("orderId", notification.getOrderId());
	}

	private String getNotificationTemplate() {

		var inputStream = getClass().getClassLoader().getResourceAsStream("notification.json");
		var reader = new BufferedReader(new InputStreamReader(inputStream));
		return reader.lines().collect(Collectors.joining(System.lineSeparator()));
	}
}
