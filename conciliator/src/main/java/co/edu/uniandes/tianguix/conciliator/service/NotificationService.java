package co.edu.uniandes.tianguix.conciliator.service;

import co.edu.uniandes.tianguix.conciliator.model.FailureNotification;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
public interface NotificationService {

	void notify(FailureNotification notification);
}
