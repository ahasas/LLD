package ParkingLot.Payment.Concrete;

import ParkingLot.Payment.PaymentStratergy;

public class CardStartergy implements PaymentStratergy {
    @Override
    public double processPayment(double amount) {
        System.out.println("Card Stratergy . Payment completed for amount:"+ amount);
        return amount;
    }
}
