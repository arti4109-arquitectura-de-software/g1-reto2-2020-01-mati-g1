package co.edu.uniandes.tianguix.orders.manager.service;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EurekaDiscoveryService implements DiscoveryService {


    private final EurekaClient eurekaClient;

    public EurekaDiscoveryService(@Qualifier("eurekaClient") EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Override
    public Integer getMatchingEngineInstances() {
        return eurekaClient.getApplication("MATCHING-ENGINE").getInstances().size();
    }
}
