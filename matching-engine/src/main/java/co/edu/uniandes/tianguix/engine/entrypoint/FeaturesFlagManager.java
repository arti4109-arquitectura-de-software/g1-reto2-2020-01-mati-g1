package co.edu.uniandes.tianguix.engine.entrypoint;

import co.edu.uniandes.tianguix.engine.model.TianguixFeature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.togglz.core.manager.FeatureManager;

@Controller
public class FeaturesFlagManager {
    private FeatureManager manager;

    public FeaturesFlagManager(FeatureManager manager){
        this.manager=manager;
    }

    @RequestMapping("/")
    public ResponseEntity<?> index() {
        if (TianguixFeature.FAILURE.isActive()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok("HOLA esta es una falla");

    }
}
