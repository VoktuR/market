package ru.rav.market.cart;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rav.market.order.dto.OrderItemDto;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class CartDto {
    private List<OrderItemDto> items;
    private long totalPrice;

    public CartDto(Cart cart, Principal principal) {
        this.totalPrice = cart.getTotalPrice();
        this.items = cart.getCustomerCart().get(principal.getName())
                .stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
