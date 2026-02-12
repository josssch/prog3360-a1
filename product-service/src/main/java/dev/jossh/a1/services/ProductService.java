package dev.jossh.a1.services;

import dev.jossh.a1.entity.Product;
import dev.jossh.a1.repository.IProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class ProductService implements IProductService {
    private final IProductRepository repository;

    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public Product create(Product product) {
        product.setId(null);
        return repository.save(product);
    }

    @Override
    public Optional<Product> deleteById(int id) {
        Optional<Product> product = getById(id);
        product.ifPresent(repository::delete);
        return product;
    }
}
