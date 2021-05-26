package ru.rav.market.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.rav.market.cart.Cart;
import ru.rav.market.security.users.PersonEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItemEntity> items;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private PersonEntity owner;

    @Column(name = "price")
    private long price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public OrderEntity(Cart cart, PersonEntity user) {
        this.items = new ArrayList<>();
        this.owner = user;
        this.price = cart.getTotalPrice();
        cart.getCustomerCart().get(owner).stream().forEach((oi) -> {
            oi.setOrder(this);
            items.add(oi);
        });
    }
}
