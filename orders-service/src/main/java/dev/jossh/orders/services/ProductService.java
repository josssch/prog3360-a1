package dev.jossh.orders.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Primary
public class ProductService implements IProductService {
    private final ProductClient productClient;

    public ProductService(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    public int getQuantity(int id) {
        Map<String, Object> response = productClient.getProduct(id);
        return response != null ? (int) response.getOrDefault("quantity", 0) : 0;
    }
}
