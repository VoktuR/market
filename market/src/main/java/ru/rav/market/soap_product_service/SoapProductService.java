package ru.rav.market.soap_product_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rav.market.products.ProductEntity;
import ru.rav.market.products.ProductRepository;
import ru.rav.market.soap_product_service.soap.products.Product;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoapProductService {

    private final ProductRepository productRepository;

    private Function<ProductEntity, Product> functionEntityToSoap = productEntity -> {
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setTitle(productEntity.getTitle());
        product.setCategory(productEntity.getCategory());
        product.setTitle(productEntity.getTitle());
        return product;
    };

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

}
