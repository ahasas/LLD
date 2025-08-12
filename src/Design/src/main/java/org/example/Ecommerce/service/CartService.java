package org.example.Ecommerce.service;

import org.example.Ecommerce.model.CartItems;
import org.example.Ecommerce.model.Coupon;
import org.example.Ecommerce.model.ICart;
import org.example.Ecommerce.model.NormalCart;
import org.example.Ecommerce.repository.CartRepo;
import org.example.Ecommerce.repository.CouponRepo;
import org.example.RateLimiter.Error.UserNotFoundException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CartService {
    private final CartRepo repo;
    private final CouponRepo couponRepo;

    public CartService(CartRepo repo, CouponRepo couponRepo) {
        this.repo = repo;
        this.couponRepo = couponRepo;
    }

    public void addItem(String userId, CartItems item){
        Optional<NormalCart> cart=repo.findCart(userId);
        if(cart.isEmpty())throw new UserNotFoundException("User not exits");
        CartItems exist=cart.get().getItems(item.getProductId());
        if(exist==null){
            cart.get().putItem(item);
        }
        else{
            exist.setQuantity(exist.getQuantity()+item.getQuantity());
        }
        repo.saveCart(cart.get(),userId);

    }

    public void removeItem(CartItems item,String user){
        Optional<NormalCart> cart=repo.findCart(user);
        if(cart.isEmpty())throw new UserNotFoundException("User not exits");
        CartItems exist=cart.get().getItems(item.getProductId());
        if(exist!=null){
            cart.get().removeItemByKey(item.getProductId());
        }
        repo.saveCart(cart.get(),user);

    }

    public void update(String productId,int quantity,String user){
        Optional<NormalCart> cart=repo.findCart(user);
        if(cart.isEmpty())throw new UserNotFoundException("User not exits");
        CartItems exist=cart.get().getItems(productId);
        if(exist==null) throw new NoSuchElementException("item does not exits");
        CartItems items=cart.get().getItems(productId);
        items.setQuantity(quantity);
        repo.saveCart(cart.get(),user);


    }

    public void applyCoupon(String couponId,String user){
        Optional<NormalCart> cart=repo.findCart(user);
        if(cart.isEmpty())throw new UserNotFoundException("User not exits");
        Optional<Coupon> temp=couponRepo.findByCode(couponId);
        if(temp.isEmpty())throw new NoSuchElementException("Coupon does not Exist");
        cart.get().setCoupon(temp.get());
        repo.saveCart(cart.get(),user);
    }

    public BigDecimal checkout(String user){
        Optional<NormalCart> cart=repo.findCart(user);
        if(cart.isEmpty())throw new UserNotFoundException("Cart is Empty");
        return cart.get().totalWithDiscount();

    }


}
