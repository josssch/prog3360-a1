package dev.jossh.orders.services;

import dev.jossh.orders.entity.Order;
import dev.jossh.orders.repository.IOrderRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import dev.jossh.orders.services.FeatureFlagService;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class OrderService implements IOrderService {
    private final IOrderRepository repository;
    private final IProductService productService;
    private final FeatureFlagService featureFlagService;

    public OrderService(IOrderRepository repository,
            IProductService productService,
            FeatureFlagService featureFlagService) {
        this.repository = repository;
        this.productService = productService;
        this.featureFlagService = featureFlagService;
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

        double unitPrice = productService.getPrice(order.getProductId());

        int qty = order.getQuantity();
        double total = unitPrice * qty;

        if (featureFlagService.isBulkOrderDiscountEnabled() && qty > 5) {
            total = total * 0.85;
        }

        order.setTotalPrice(total);

        Order saved = repository.save(order);

        
        if (featureFlagService.isOrderNotificationsEnabled()) {
            String name = productService.getName(order.getProductId());
            System.out.println("[NOTIFICATION] Order confirmed id=" + saved.getId()
                    + ", productId=" + saved.getProductId()
                    + ", name=" + name
                    + ", qty=" + saved.getQuantity()
                    + ", total=" + saved.getTotalPrice());
        }

        return Optional.of(saved);
    }
}
