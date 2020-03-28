package co.edu.uniandes.tianguix.orders.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @With
    private String orderId;
    @With
    private OrderType type;
    @With
    private String asset;
    @With
    private Integer amount;

}
