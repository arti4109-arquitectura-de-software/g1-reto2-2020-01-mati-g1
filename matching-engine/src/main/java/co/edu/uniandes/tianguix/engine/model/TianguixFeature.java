package co.edu.uniandes.tianguix.engine.model;

import org.springframework.context.annotation.Bean;
import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;

public enum TianguixFeature implements Feature {
    @EnabledByDefault
    @Label("FAILURE")
    FAILURE;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }

    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(TianguixFeature.class);
    }
}
