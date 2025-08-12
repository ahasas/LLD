package org.example.InventoryManagement.Repository;

import org.example.InventoryManagement.model.Inventory;
import org.example.InventoryManagement.model.Product;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryImpl implements InventoryRepo{
    Map<String, Product>productMap=new ConcurrentHashMap<>();
    Map<String,Inventory> InventoryMap =new ConcurrentHashMap<>();
    @Override
    public Inventory findByProduct(String product) {
        Inventory temp= InventoryMap.get(product);
        if(temp==null)return null;
        return temp;
    }

    @Override
    public void saveInventory(Inventory inv) {
        InventoryMap.put(inv.getProduct(),inv);

    }

    public void saveProduct(Product product){
        productMap.put(product.getProductId(),product);
    }

    public Optional<Product> getbyName(String name){
        Optional<Product> existing =productMap.values().stream()
                .filter(p->p.getName().equals(name))
                .findFirst();
        return existing;
    }
}
