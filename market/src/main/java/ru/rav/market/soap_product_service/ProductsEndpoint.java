package ru.rav.market.soap_product_service;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.rav.market.soap_product_service.soap.products.GetAllProductsRequest;
import ru.rav.market.soap_product_service.soap.products.GetAllProductsResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductsEndpoint {

    private static final String NAMESPACE_URI = "http://www.rav.ru/app/ws/products";

    private final SoapProductService soapProductService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllStudents(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        soapProductService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }

}
