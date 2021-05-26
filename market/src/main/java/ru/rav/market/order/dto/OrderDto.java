package ru.rav.market.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rav.market.order.OrderEntity;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private String username;
    private long totalPrice;
    private String creationDateTime;

    public OrderDto(OrderEntity order) {
        this.id = order.getId();
        this.username = order.getOwner().getUsername();
        this.totalPrice = order.getPrice();
        this.creationDateTime = order.getCreatedAt().toString();
    }

}
