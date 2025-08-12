package org.example.InventoryManagement.Repository;

import org.example.InventoryManagement.model.Inventory;

import java.util.Map;

public interface InventoryRepo {
    //CRUD
    public Inventory findByProduct(String product);
    public void saveInventory(Inventory inv);
}
