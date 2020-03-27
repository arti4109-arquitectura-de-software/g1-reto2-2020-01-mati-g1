package co.edu.uniandes.tianguix.conciliator.service;

import com.netflix.discovery.EurekaClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EurekaDiscoveryService implements DiscoveryService {

    @Lazy
    private final EurekaClient eurekaClient;

    public EurekaDiscoveryService(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Override
    public Integer getMatchingEngineInstances() {
        List instances = eurekaClient.getApplication("MATCHING-ENGINE").getInstances();
        System.out.printf("INSTANCES: " + instances.size());
        return instances.size();
    }

}
