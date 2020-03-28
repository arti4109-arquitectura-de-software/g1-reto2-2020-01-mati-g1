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

		var instances = Optional.ofNullable(eurekaClient.getApplication("MATCHING-ENGINE").getInstances());
		return instances.map(List::size).orElse(0);
	}

}
