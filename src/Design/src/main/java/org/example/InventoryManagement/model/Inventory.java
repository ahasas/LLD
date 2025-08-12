package org.example.InventoryManagement.model;

public class Inventory{

    private String product;
    private int available;
    private int blocked;


    public Inventory(int quantity, String product) {
        this.available = quantity;
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }
}

