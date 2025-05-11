package ParkingLot.Payment.Concrete;

import ParkingLot.Payment.PaymentStratergy;

public class CashStratergy implements PaymentStratergy {


    @Override
    public double processPayment(double amount) {
        System.out.println("Cash. amount"+ amount);
        return amount;
    }
}
