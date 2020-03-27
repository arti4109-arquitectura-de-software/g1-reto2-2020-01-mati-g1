package co.edu.uniandes.tianguix.engine.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @With private int orderId;
    @With private OrderType type;
    @With private String asset;
    @With private Integer amount;

}
