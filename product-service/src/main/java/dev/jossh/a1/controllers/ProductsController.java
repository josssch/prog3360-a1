package dev.jossh.a1.controllers;

import dev.jossh.a1.entity.Product;
import dev.jossh.a1.services.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.jossh.a1.services.FeatureFlagService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final IProductService productService;
    private final FeatureFlagService featureFlagService;

    public ProductsController(IProductService productService,
            FeatureFlagService featureFlagService) {
        this.productService = productService;
        this.featureFlagService = featureFlagService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/premium")
    public List<Product> getPremiumProducts() {
        List<Product> products = productService.getAllProducts();

        if (!featureFlagService.isPremiumPricingEnabled()) {
            return products;
        }

        return products.stream()
                .map(p -> {
                    Product copy = new Product();
                    copy.setId(p.getId());
                    copy.setName(p.getName());
                    copy.setPrice(p.getPrice() * 0.9);
                    return copy;
                })
                .toList();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.status(201).body(productService.create(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Optional<Product> product = productService.getById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        Optional<Product> product = productService.deleteById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
