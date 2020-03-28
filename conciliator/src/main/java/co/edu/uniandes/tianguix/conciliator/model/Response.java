package co.edu.uniandes.tianguix.conciliator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:d.bellonc@uniandes.edu.co"> Daniel Bellón </a>
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

	@With private LocalDateTime localDateTime;
	@With private Type type;
}
