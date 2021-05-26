package ru.rav.market.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rav.market.order.OrderItemEntity;

@NoArgsConstructor
@Data
public class OrderItemDto {
    private String productTitle;
    private int quantity;
    private long pricePerProduct;
    private long price;

    public OrderItemDto(OrderItemEntity orderItem) {
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
    }
}
