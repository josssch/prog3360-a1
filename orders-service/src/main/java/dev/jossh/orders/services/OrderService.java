package dev.jossh.orders.services;

import dev.jossh.orders.entity.Order;
import dev.jossh.orders.repository.IOrderRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class OrderService implements IOrderService {
    private final IOrderRepository repository;
    private final IProductService productService;

    public OrderService(IOrderRepository repository, IProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Optional<Order> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Order> placeOrder(Order order) {
        order.setId(null);

        int onHand = productService.getQuantity(order.getProductId());
        if (order.getQuantity() <= 0 || order.getQuantity() > onHand) {
            return Optional.empty();
        }

        return Optional.of(repository.save(order));
    }
}
