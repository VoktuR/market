package ru.rav.market.products.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.rav.market.products.ProductEntity;

@Data
@NoArgsConstructor
public class ProductResponse {

    private Long id;

    private String title;

    private long price;

    public ProductResponse(ProductEntity p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
    }

}
