package co.edu.uniandes.tianguix.engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchingEngineResponse {
    @With private String matchingEngineId;
    @With private Match matches[];
}
