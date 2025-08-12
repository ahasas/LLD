package org.example.InventoryManagement.service;

import org.example.InventoryManagement.model.OrderItem;

import java.util.List;

public interface InventoryInterface {
    public void createProduct(String name);
    public boolean blockInventory(String productId,int qty);
    public boolean handlePaymentFailure(List<OrderItem> item);
    public boolean handlePaymentSuccess(List<OrderItem> item);
    public boolean addInventory(String productId,int qty);
}
