package dev.jossh.a1.services;

import dev.jossh.a1.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();

    Optional<Product> getById(int id);

    Product create(Product product);

    Optional<Product> deleteById(int id);
}
