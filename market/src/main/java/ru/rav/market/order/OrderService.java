package ru.rav.market.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rav.market.cart.Cart;
import ru.rav.market.order.dto.OrderDto;
import ru.rav.market.security.users.PersonEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;

    public OrderEntity createFromUserCart(PersonEntity user) {
        OrderEntity order = new OrderEntity(cart, user);
        order = orderRepository.save(order);
        cart.clearCart(user.getUsername());
        return order;
    }

    public List<OrderDto> findAllOrdersByOwnerName(String username) {
        return orderRepository.findAllByOwnerUsername(username).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
