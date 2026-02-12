package dev.jossh.orders.services;

import dev.jossh.orders.entity.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    List<Order> getAllOrders();

    Optional<Order> getById(int id);

    Optional<Order> placeOrder(Order order);
}
