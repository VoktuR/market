package ru.rav.market.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.rav.market.order.dto.OrderDto;
import ru.rav.market.security.users.PersonEntity;
import ru.rav.market.exceptions.ResourceNotFoundException;
import ru.rav.market.security.users.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public List<OrderDto> getCurrentUserCart(Principal principal) {
        return orderService.findAllOrdersByOwnerName(principal.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrderFromCart(Principal principal) {
        PersonEntity user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createFromUserCart(user);
    }

}
