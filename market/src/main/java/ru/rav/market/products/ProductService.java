package ru.rav.market.products;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.rav.market.products.dto.ProductRequest;
import ru.rav.market.products.dto.ProductResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<ProductResponse> getProduct(Long id) {
        return productRepository.findById(id).map(ProductResponse::new);
    }

    public Page<ProductResponse> getProducts(Specification<ProductEntity> spec, Integer page, Integer pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductResponse::new);
    }

    public ProductResponse saveProduct(ProductRequest saveRequest) {
        saveRequest.setId(null);
        return new ProductResponse(productRepository.save(new ProductEntity(saveRequest)));
    }

    public ProductResponse updateProduct(ProductRequest updateRequest) {
        return new ProductResponse(productRepository.save(new ProductEntity(updateRequest)));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
