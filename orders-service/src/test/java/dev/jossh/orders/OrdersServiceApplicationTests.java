package dev.jossh.orders;

import dev.jossh.orders.entity.Order;
import dev.jossh.orders.repository.IOrderRepository;
import dev.jossh.orders.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrdersServiceApplicationTests {

    @Mock
    IOrderRepository repository;

    @InjectMocks
    IOrderRepository service;

    @Test
    void getOrderById_found() {
        Order order = new Order();
        order.setId(1);
        order.setStatus("PENDING");
        when(repository.findById(1)).thenReturn(Optional.of(order));

        var result = service.findById(1);

        assertTrue(result.isPresent());
        assertEquals("PENDING", result.get().getStatus());
    }
    @Test
    void getOrderById_not_found() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        var result = service.findById(1);

        assertTrue(result.isEmpty());
    }

    @Test
    void getOrders_found() {
        Order order = new Order();
        order.setId(1);
        order.setStatus("PENDING");
        when(repository.findAll()).thenReturn(List.of(order));

        var result = service.findAll();

        assertFalse(result.isEmpty());
    }
}
