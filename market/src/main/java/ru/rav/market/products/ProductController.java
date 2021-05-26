package ru.rav.market.products;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.rav.market.exceptions.ResourceNotFoundException;
import ru.rav.market.products.dto.ProductRequest;
import ru.rav.market.products.dto.ProductResponse;
import ru.rav.market.products.specifications.ProductSpecifications;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductSpecifications productSpecifications;

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException(
                "Can't find product with that id"
        ));
    }

    @GetMapping
    public Page<ProductResponse> getProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "pSize", defaultValue = "10") Integer pageSize
    ) {
        return productService.getProducts(productSpecifications.build(params), page, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse saveProduct(@RequestBody @Valid ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }

    @PutMapping
    public ProductResponse updateProduct(@RequestBody @Valid ProductRequest updateRequest) {
        return productService.updateProduct(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
