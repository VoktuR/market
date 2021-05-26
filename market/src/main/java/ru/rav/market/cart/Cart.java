package ru.rav.market.cart;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.rav.market.order.OrderItemEntity;
import ru.rav.market.exceptions.ResourceNotFoundException;
import ru.rav.market.products.ProductRepository;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.*;

@Component
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
@Data
public class Cart {

    private final ProductRepository productRepository;

    private Map<String, List<OrderItemEntity>> customerCart;
    private long totalPrice;

    @PostConstruct
    private void init() {
        customerCart = new HashMap<>();
    }

    public void addToCart(Long productId, Principal principal) {
        if (customerCart.containsKey(principal.getName())) {
            for (OrderItemEntity o : customerCart.get(principal.getName())) {
                if (o.getProduct().getId().equals(productId)) {
                    o.incrementQuantity();
                    recalculateCart(principal.getName());
                    return;
                }
            }
        }

        OrderItemEntity product = productRepository.findById(productId).map(OrderItemEntity::new)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Can't add product to cart. There is no product using that id"
        ));
        List<OrderItemEntity> list = customerCart.computeIfAbsent(principal.getName(), k -> new ArrayList<>());
        list.add(product);
        customerCart.put(principal.getName(), list);

        recalculateCart(principal.getName());
    }

    public void deleteFromCart(Long productId, Principal principal) {
        for (OrderItemEntity o : customerCart.get(principal.getName())) {
            if (o.getProduct().getId().equals(productId)) {
                if (o.getQuantity() > 1) {
                    o.decrementQuantity();
                } else {
                    customerCart.get(principal.getName()).remove(o);
                    return;
                }
                recalculateCart(principal.getName());
                return;
            }
        }

        OrderItemEntity product = productRepository.findById(productId).map(OrderItemEntity::new)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Can't remove product from cart. There is no such product there"
        ));

        List<OrderItemEntity> list = customerCart.get(principal.getName());
        if (list == null) {
            return;
        }
        list.remove(product);
        customerCart.put(principal.getName(), list);

        recalculateCart(principal.getName());
    }


    public void recalculateCart(String user) {
        totalPrice = 0;
        for (OrderItemEntity o : customerCart.get(user)) {
            totalPrice += o.getPrice();
        }
    }

    public void clearCart(String user) {
        customerCart.clear();
        recalculateCart(user);
    }

}
