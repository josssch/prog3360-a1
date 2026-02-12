package dev.jossh.orders.repository;

import dev.jossh.orders.entity.Order;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface IOrderRepository extends JpaRepository<Order, Integer> {
}
