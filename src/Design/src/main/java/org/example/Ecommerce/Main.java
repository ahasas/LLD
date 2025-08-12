package org.example.Ecommerce;

import org.example.Ecommerce.CouponStratergy.FixedDiscount;
import org.example.Ecommerce.CouponStratergy.PercentageImplementation;
import org.example.Ecommerce.model.CartItems;
import org.example.Ecommerce.model.Coupon;
import org.example.Ecommerce.model.NormalCart;
import org.example.Ecommerce.repository.CartRepo;
import org.example.Ecommerce.repository.CouponRepo;
import org.example.Ecommerce.repository.InMemoryCoupon;
import org.example.Ecommerce.repository.Inmemory;
import org.example.Ecommerce.service.CartService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    //2. Cart and Coupon Management System (2024)
    //Problem Statement: Design and implement an e-commerce cart system with:
    //	•	Add items to cart (name, size, quantity, price)
    //	•	Remove and update cart items
    //	•	Apply apply coupons (percentage and fixed amount)
    //	•	Calculate final amount after discounts
    //  •	Handle coupon validation and expiry
     //
    // Entities: Cart, apply -> strategy , Item,
    // Cart: should be able to add ,delete , update items
    //Coupon : can be applied , can we apply one coupon or multiple coupon
    //
    public static void main(String[] args) {

        // Create repositories
        CartRepo cartRepo = new Inmemory();
        CouponRepo couponRepo = new InMemoryCoupon();

        // Create a cart for a test user
        String userId = "user123";
        NormalCart cart = new NormalCart(userId,"cart123");
        cartRepo.saveCart(cart, userId);

        // Add some coupons in couponRepo
        couponRepo.save(new Coupon("SAVE10",new PercentageImplementation(), LocalDateTime.now().plusDays(2), 3,BigDecimal.valueOf(10)));
        couponRepo.save(new Coupon("FLAT50",new FixedDiscount(), LocalDateTime.now().plusDays(2),2,BigDecimal.valueOf(50)));
        couponRepo.save(new Coupon("FLAT250",new FixedDiscount(), LocalDateTime.now().plusDays(2),2,BigDecimal.valueOf(250)));

        // Create CartService
        CartService cartService = new CartService(cartRepo, couponRepo);

        // Add items
        cartService.addItem(userId, new CartItems("P001", 1, "Laptop",BigDecimal.valueOf(200) ));
        //cartService.addItem(userId, new CartItems("P002", 2, "Mouse", BigDecimal.valueOf(50)));

        System.out.println("Cart before coupon:");
        System.out.println(cartRepo.findCart(userId).get());

        // Apply coupon
        cartService.applyCoupon("FLAT250", userId);

        // Checkout
        BigDecimal finalAmount = cartService.checkout(userId);

        System.out.println("Final amount after coupon: " + finalAmount);
    }


}
