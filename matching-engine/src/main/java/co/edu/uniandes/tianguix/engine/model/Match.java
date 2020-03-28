package co.edu.uniandes.tianguix.engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @With private String orderID;
    @With OrderType orderType;
    @With private String matchesIds[];
    @With private short votes;


}
