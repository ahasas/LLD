package org.example.InventoryManagement.service;

import org.example.InventoryManagement.Repository.InventoryImpl;
import org.example.InventoryManagement.model.Inventory;
import org.example.InventoryManagement.model.OrderItem;
import org.example.InventoryManagement.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class InventoryService implements InventoryInterface {
    private final InventoryImpl repo=new InventoryImpl();
    private final Map<String, ReentrantLock> productLocks = new ConcurrentHashMap<>();
    @Override
    public void createProduct(String name) {
        Optional<Product> opt=repo.getbyName(name);
        if(opt.isPresent()) throw new IllegalArgumentException("Product already exist");
        String productId=UUID.randomUUID().toString();
        Product p=new Product(productId,name);
        repo.saveProduct(p);


    }
    public String getProductbyName(String name){
        Optional<Product> p=repo.getbyName(name);
        if(p.isPresent())return p.get().getProductId();
        return null;

    }

    @Override
    public boolean blockInventory(String productId, int qty) {
        ReentrantLock lock=productLocks.computeIfAbsent(productId,k->new ReentrantLock());
        lock.lock();
        try{
            Inventory inv=repo.findByProduct(productId);
            if(inv!=null){
                if(inv.getAvailable()>=qty){
                    inv.setBlocked(qty);
                    inv.setAvailable(inv.getAvailable()-qty);
                    repo.saveInventory(inv);
                    return true;
                }

                return false;
            }
            else{
                return false;
            }
        }
        finally {
            lock.unlock();
        }

    }

    @Override
    public boolean handlePaymentFailure(List<OrderItem> item) {
        boolean released=false;
        for(OrderItem i:item){
            ReentrantLock l=productLocks.computeIfAbsent(i.getProductId(),k->new ReentrantLock());
            l.lock();
            try{
                Inventory inv=repo.findByProduct(i.getProductId());
                if(inv!=null && inv.getBlocked()>=i.getQty()){

                        inv.setBlocked(inv.getBlocked()-i.getQty());
                        inv.setAvailable(inv.getAvailable()+i.getQty());
                    released=true;
                    repo.saveInventory(inv);
                }
            }
            finally {
                l.unlock();
            }
        }
        return released;


    }

    @Override
    public boolean handlePaymentSuccess(List<OrderItem> item) {
        boolean released=false;
        for(OrderItem i:item){
            ReentrantLock l=productLocks.computeIfAbsent(i.getProductId(),k->new ReentrantLock());
            l.lock();
            try{
                Inventory inv=repo.findByProduct(i.getProductId());
                if(inv!=null && inv.getBlocked()>=i.getQty()){

                    inv.setBlocked(inv.getBlocked()-i.getQty());
                    repo.saveInventory(inv);
                    released=true;
                }
            }
            finally {
                l.unlock();
            }
        }
        return released;


    }


    @Override
    public boolean addInventory(String productId, int qty) {


            ReentrantLock l=productLocks.computeIfAbsent(productId,k->new ReentrantLock());
            l.lock();
            try{
                Inventory inv=repo.findByProduct(productId);
                if(inv==null){
                    inv = new Inventory(0,productId);

                }

                inv.setAvailable(inv.getAvailable()+qty);
                repo.saveInventory(inv);
                return true;
            }
            finally {
                l.unlock();
            }



    }
}
