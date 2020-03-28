package co.edu.uniandes.tianguix.conciliator.service;

import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EurekaDiscoveryService implements DiscoveryService {

	@Lazy private final EurekaClient eurekaClient;

	public EurekaDiscoveryService(EurekaClient eurekaClient) {

		this.eurekaClient = eurekaClient;
	}

	@Override
	public Integer getMatchingEngineInstances() {

		var optionalEngine = Optional.ofNullable(eurekaClient.getApplication("MATCHING-ENGINE"));
		return optionalEngine.map(engine -> engine.getInstances().size()).orElse(0);
	}

}
