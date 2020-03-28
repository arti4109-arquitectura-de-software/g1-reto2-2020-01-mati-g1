package co.edu.uniandes.tianguix.engine.service;

import com.netflix.appinfo.InstanceInfo;

import java.util.List;

public interface DiscoveryService {

    List<InstanceInfo> getMatchingEngineInstances();
}
