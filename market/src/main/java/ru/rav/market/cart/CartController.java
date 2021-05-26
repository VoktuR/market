package ru.rav.market.cart;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rav.market.cart.Cart;
import ru.rav.market.cart.CartDto;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final Cart cart;

    @GetMapping("/show")
    @ApiOperation("Show the cart for chosen customer")
    public CartDto showCart(Principal p) {
        log.info(p.getName());
        return new CartDto(cart, p);
    }

    @GetMapping("/add/{id}")
    @ApiOperation("Add one product to cart of chosen customer")
    public void addToCart(
            @PathVariable("id") Long productId,
            Principal p
    ) {
        log.info(p.getName());
        cart.addToCart(productId, p);
    }

    @GetMapping("/delete/{id}")
    @ApiOperation("Delete one product from cart of chosen customer")
    public void deleteFromCart(
            @PathVariable("id") Long productId,
            Principal p
    ) {
        cart.deleteFromCart(productId, p);
    }

    @GetMapping("/clear")
    @ApiOperation("Cleat the cart for chosen customer")
    public void clearCart(Principal p) {
        cart.clearCart(p.getName());
    }

}
