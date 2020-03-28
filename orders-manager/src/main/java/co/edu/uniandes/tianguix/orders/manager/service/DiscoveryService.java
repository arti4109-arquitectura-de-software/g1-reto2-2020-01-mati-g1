package co.edu.uniandes.tianguix.orders.manager.service;

import com.netflix.appinfo.InstanceInfo;

import java.util.List;

public interface DiscoveryService {

    List<InstanceInfo> getMatchingEngineInstances();
}
