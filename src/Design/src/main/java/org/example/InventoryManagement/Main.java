package org.example.InventoryManagement;


/*
Design a Problem Statement: Design an inventory management service with:
	•	Add Product functionality
	•	Add inventory to products
	•	Block inventory for orders
	•	Handle payment scenarios (success/failure)
	•	Inventory release on payment failure
 */



import org.example.InventoryManagement.Repository.InventoryImpl;
import org.example.InventoryManagement.model.OrderItem;
import org.example.InventoryManagement.model.Product;
import org.example.InventoryManagement.service.InventoryInterface;
import org.example.InventoryManagement.service.InventoryService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        InventoryService inventoryService = new InventoryService();


        System.out.println("=== Inventory Management System Demo ===\n");

        // 1. Create Product - returns actual IDs to avoid mismatch issues
        String laptopId = createProductUseCase(inventoryService, "Laptop");
        String phoneId = createProductUseCase(inventoryService, "Phone");

        // 2. Add Inventory
        addInventoryUseCase(inventoryService, laptopId, 100);
        addInventoryUseCase(inventoryService, phoneId, 50);

        // 3. Block Inventory (Successful and failed cases)
        blockInventoryUseCase(inventoryService, laptopId, 30);
        blockInventoryUseCase(inventoryService, laptopId, 500); // Insufficient stock
        blockInventoryUseCase(inventoryService, "non-existing-id", 10); // Non-existing product

        // 4. Handle Payment Success
        handlePaymentSuccessUseCase(inventoryService, laptopId, 20);

        // 5. Handle Payment Failure
        handlePaymentFailureUseCase(inventoryService, laptopId, 10);

        // 6. Concurrent Inventory Blocking
        concurrentBlockingUseCase(inventoryService);

        // 7. Edge Cases
        edgeCasesUseCase(inventoryService, laptopId);

        System.out.println("\n✅ All use cases completed successfully.");
    }

    private static String createProductUseCase(InventoryService service, String productName) {
        try {

            service.createProduct(productName);
            //System.out.println("Created product '" + productName + "' with ID: " + productId);
            return service.getProductbyName(productName);
        } catch (IllegalArgumentException e) {
            System.out.println("Product creation failed (duplicate?): " + e.getMessage());
            return null;
        }
    }

    private static void addInventoryUseCase(InventoryService service, String productId, int qty) {
        boolean added = service.addInventory(productId, qty);
        System.out.println("Added inventory for product " + productId + ": " + qty + " units - Success: " + added);
    }

    private static void blockInventoryUseCase(InventoryService service, String productId, int qty) {
        boolean blocked = service.blockInventory(productId, qty);
        System.out.println("Block " + qty + " units for product " + productId + " - Success: " + blocked);
    }

    private static void handlePaymentSuccessUseCase(InventoryService service, String productId, int qty) {
        List<OrderItem> items = Arrays.asList(new OrderItem(productId, qty));
        boolean success = service.handlePaymentSuccess(items);
        System.out.println("Handle payment success for " + qty + " units of product " + productId + " - Success: " + success);
    }

    private static void handlePaymentFailureUseCase(InventoryService service, String productId, int qty) {
        // First block inventory to simulate order placement
        service.blockInventory(productId, qty);
        List<OrderItem> items = Arrays.asList(new OrderItem(productId, qty));
        boolean failureHandled = service.handlePaymentFailure(items);
        System.out.println("Handle payment failure for " + qty + " units of product " + productId + " - Restored: " + failureHandled);
    }

    private static void concurrentBlockingUseCase(InventoryService service) throws InterruptedException {
        System.out.println("Starting concurrent blocking test...");
        InventoryImpl repo=new InventoryImpl();
        service.createProduct("ConcurrentItem");
        Optional< Product > p=repo.getbyName("ConcurrentItem");
        service.addInventory(service.getProductbyName("ConcurrentItem"), 500);

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                boolean result = service.blockInventory(service.getProductbyName("ConcurrentItem"), 50);
                System.out.println(Thread.currentThread().getName() + " blocked 50 units: " + result);
            }, "Thread-" + i);
        }
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println("Concurrent blocking test completed.\n");
    }

    private static void edgeCasesUseCase(InventoryService service, String productId) {
        System.out.println("Testing edge cases:");

        try {
            boolean result = service.addInventory(productId, 0);
            System.out.println("Add zero quantity: " + result);
        } catch (Exception e) {
            System.out.println("Add zero quantity error: " + e.getMessage());
        }

        try {
            boolean result = service.addInventory(productId, -10);
            System.out.println("Add negative quantity: " + result);
        } catch (Exception e) {
            System.out.println("Add negative quantity error: " + e.getMessage());
        }

        try {
            boolean result = service.blockInventory(productId, 0);
            System.out.println("Block zero quantity: " + result);
        } catch (Exception e) {
            System.out.println("Block zero quantity error: " + e.getMessage());
        }

        try {
            boolean result = service.blockInventory(null, 10);
            System.out.println("Block null product ID: " + result);
        } catch (Exception e) {
            System.out.println("Block null product ID error: " + e.getMessage());
        }

        try {
            boolean result = service.handlePaymentSuccess(null);
            System.out.println("Handle payment success with null order list: " + result);
        } catch (Exception e) {
            System.out.println("Handle payment success with null order list error: " + e.getMessage());
        }

        try {
            boolean result = service.handlePaymentFailure(Arrays.asList());
            System.out.println("Handle payment failure with empty order list: " + result);
        } catch (Exception e) {
            System.out.println("Handle payment failure with empty order list error: " + e.getMessage());
        }
    }
}
