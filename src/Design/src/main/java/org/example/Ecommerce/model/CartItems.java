package org.example.Ecommerce.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class CartItems {
    private final String productId;
    private int quantity;
    private final String name;
    private final BigDecimal unitPrice;

    public CartItems(String productId, int quantity, String name, BigDecimal unitPrice) {
        this.productId = Objects.requireNonNull(productId);
        if(quantity<=0) throw new IllegalArgumentException("quantity should be less than zero");
        this.quantity = quantity;
        this.name = name;
        this.unitPrice = Objects.requireNonNull(unitPrice);
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
