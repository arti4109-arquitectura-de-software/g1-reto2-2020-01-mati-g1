package co.edu.uniandes.tianguix.engine.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @With private int processId;
    @With private String orderId;
    @With private OrderType type;
    @With private String asset;
    @With private Integer amount;

}
