package dev.jossh.orders.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class ProductService implements IProductService {

    private final RestClient restClient;
    private final String baseUrl;

    public ProductService(@Value("${product-service.url}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.restClient = RestClient.create();
    }

    @Override
    public int getQuantity(int productId) {
        Map<?, ?> product = getProduct(productId);
        Object qty = product.get("quantity");
        return ((Number) qty).intValue();
    }

    @Override
    public double getPrice(int productId) {
        Map<?, ?> product = getProduct(productId);
        Object price = product.get("price");
        return ((Number) price).doubleValue();
    }

    @Override
    public String getName(int productId) {
        Map<?, ?> product = getProduct(productId);
        Object name = product.get("name");
        return name == null ? "" : name.toString();
    }

    @SuppressWarnings("unchecked")
    private Map<?, ?> getProduct(int productId) {
        return restClient.get()
                .uri(baseUrl + "/api/products/" + productId)
                .retrieve()
                .body(Map.class);
    }
}