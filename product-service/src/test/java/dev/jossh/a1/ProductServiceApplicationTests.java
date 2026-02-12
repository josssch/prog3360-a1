package dev.jossh.a1;

import dev.jossh.a1.entity.Product;
import dev.jossh.a1.repository.IProductRepository;
import dev.jossh.a1.services.IProductService;
import dev.jossh.a1.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductServiceApplicationTests {

    @Mock
    IProductRepository repository;

    @InjectMocks
    IProductService service;

    @Test
    void getProductById_found() {
        Product p = new Product();
        p.setId(1);
        p.setName("Product 1");
        when(repository.findById(1)).thenReturn(Optional.of(p));

        var result = service.getById(1);

        assertTrue(result.isPresent());
        assertEquals("Product 1", result.get().getName());
    }
    @Test
    void getProductById_not_found() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        var result = service.getById(1);

        assertFalse(result.isPresent());
    }

    @Test
    void getAllProducts_found() {
        Product p = new Product();
        p.setId(1);
        p.setName("Product 1");
        when(repository.findAll()).thenReturn(List.of(p));

        var result = service.getAllProducts();

        assertFalse(result.isEmpty());
    }
}
