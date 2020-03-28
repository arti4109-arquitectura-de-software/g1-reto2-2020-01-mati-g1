package co.edu.uniandes.tianguix.engine.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EurekaDiscoveryService implements DiscoveryService {


    private final EurekaClient eurekaClient;

    public EurekaDiscoveryService(@Qualifier("eurekaClient") EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Override
    public List<InstanceInfo> getMatchingEngineInstances() {
        return eurekaClient.getApplication("MATCHING-ENGINE").getInstances();
    }
}
