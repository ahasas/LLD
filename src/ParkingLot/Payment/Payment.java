package ParkingLot.Payment;

public class Payment {
    private double amount;
    private PaymentStratergy paymentStratergy;

    public Payment(double amount, PaymentStratergy paymentStratergy) {
        this.amount = amount;
        this.paymentStratergy = paymentStratergy;
    }

    public void process(){
        if(amount>0){
            paymentStratergy.processPayment(amount);
        }
        else{
            System.out.println("Invalid payment amount.");
        }
    }
}
