package ru.rav.market.products.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import ru.rav.market.products.ProductEntity;

@Component
public class ProductSpecifications {

    private Specification<ProductEntity> priceGreaterOrEqualsThan(long minPrice) {
        return (Specification<ProductEntity>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    private Specification<ProductEntity> priceLesserOrEqualsThan(long maxPrice) {
        return (Specification<ProductEntity>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    private Specification<ProductEntity> titleLike(String titlePart) {
        return (Specification<ProductEntity>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }

    public Specification<ProductEntity> build(MultiValueMap<String, String> params) {
        Specification<ProductEntity> spec = Specification.where(null);
        if (params.containsKey("minPrice") && !params.getFirst("minPrice").isBlank()) {
            spec = spec.and(priceGreaterOrEqualsThan(Long.parseLong(params.getFirst("minPrice"))));
        }
        if (params.containsKey("maxPrice") && !params.getFirst("maxPrice").isBlank()) {
            spec = spec.and(priceLesserOrEqualsThan(Long.parseLong(params.getFirst("maxPrice"))));
        }
        if (params.containsKey("title") && !params.getFirst("title").isBlank()) {
            spec = spec.and(titleLike(params.getFirst("title")));
        }
        return spec;
    }

}
