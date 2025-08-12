package org.example.Ecommerce.model;

import org.example.Ecommerce.CouponStratergy.ICoupon;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class NormalCart {
    private ConcurrentHashMap<String, CartItems> items;
    private String userID;
    private String cartId;
    private List<Coupon> appliedcoupons;

    public NormalCart(String userID, String cartId) {
        this.userID = userID;
        this.cartId = Objects.requireNonNull(cartId);
        items=new ConcurrentHashMap<>();
        appliedcoupons=new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public String getCartId() {
        return cartId;
    }
    public void putItem(CartItems item){
        items.put(item.getProductId(),item);
    }
    CartItems findItem(String key){
        return items.get(key);
    }
    public CartItems getItems(String itid){
        CartItems t=items.get(itid);
        if(t!=null)return t;
        return null;
    }
    public void removeItemByKey(String key){
        items.remove(key);
    }
    public void clearItems(){
        items.clear();
    }

    public BigDecimal subtotal(){
        BigDecimal s=BigDecimal.ZERO;
        for(CartItems it: items.values())s=s.add(it.getUnitPrice().multiply(BigDecimal.valueOf(it.getQuantity())));
        return s;
    }

    public BigDecimal totalWithDiscount(){
        BigDecimal sub=subtotal();
        if(appliedcoupons==null)return sub;
        for(Coupon i:appliedcoupons){
            sub=i.getStratergy().apply(sub,i);
        }
        return sub;

    }

    public void setCoupon(Coupon coupon){
        appliedcoupons.add(coupon);
    }

}
