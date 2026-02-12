package dev.jossh.a1.repository;

import dev.jossh.a1.entity.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface IProductRepository extends JpaRepository<Product, Integer> {
}
