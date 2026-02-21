package dev.jossh.orders.services;

public interface IProductService {
    int getQuantity(int id);

    double getPrice(int productId);

    String getName(int productId);
}
