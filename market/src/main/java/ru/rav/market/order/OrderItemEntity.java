package ru.rav.market.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.rav.market.products.ProductEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private long pricePerProduct;

    @Column(name = "price")
    private long price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public OrderItemEntity(ProductEntity product) {
        this.product = product;
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = this.pricePerProduct;
    }

    public void incrementQuantity() {
        quantity++;
        price = quantity * pricePerProduct;
    }

    public void decrementQuantity() {
        quantity--;
        price = quantity * pricePerProduct;
    }

}
