package dev.jossh.orders.controllers;

import dev.jossh.orders.entity.Order;
import dev.jossh.orders.services.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private final IOrderService orderService;

    public OrdersController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Optional<Order> newOrder = orderService.placeOrder(order);
        return newOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @GetMapping("{id}")
    public ResponseEntity<Order> getOrder(@PathVariable int id) {
        Optional<Order> order = orderService.getById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
