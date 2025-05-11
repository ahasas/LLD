package ParkingLot;

import ParkingLot.ParkingFeeStratergy.ConcreteStartergy.BaseRateStratergyImplementation;
import ParkingLot.ParkingFeeStratergy.ParkingFeeStartergy;
import ParkingLot.ParkingSpot.ParkingSpot;
import ParkingLot.Payment.Concrete.CardStartergy;
import ParkingLot.Payment.Concrete.CashStratergy;
import ParkingLot.Payment.PaymentStratergy;
import ParkingLot.Vehicle.Vehicle;
import ParkingLot.Vehicle.VehicleFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        List<ParkingSpot> parkingSpot = new ArrayList<>();
        PaymentStratergy p1;
        parkingSpot.add(new ParkingSpot(UUID.randomUUID(), "Car"));
        parkingSpot.add(new ParkingSpot(UUID.randomUUID(), "Bike"));
        parkingSpot.add(new ParkingSpot(UUID.randomUUID(), "Car"));
        parkingSpot.add(new ParkingSpot(UUID.randomUUID(), "Bike"));
        ParkingLot parkingLot = new ParkingLot(parkingSpot);
        ParkingFeeStartergy baseRate = new BaseRateStratergyImplementation();
        Vehicle car1 = VehicleFactory.createVehicle("Car123", VehicleType.Car);
        Vehicle car2 = VehicleFactory.createVehicle("Car456", VehicleType.Car);
        Vehicle bike1 = VehicleFactory.createVehicle("Bike123", VehicleType.Bike);
        Vehicle others = VehicleFactory.createVehicle("Others123", VehicleType.Others);
        ParkingSpot carSpot = parkingLot.parkVehicle(car1);
        Scanner sc = new Scanner(System.in);
        System.out.println("Select payment method for your vehicle:");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash");
        int paymentMethod = sc.nextInt();

        if (carSpot != null) {
            double fee = baseRate.calculateFees(VehicleType.Car, 2, DurationType.HOURS);
            p1=getPaymentStartergy(paymentMethod);
            p1.processPayment(fee);
            parkingLot.vacateSpot(car1,carSpot);

        }
    }

        private  static PaymentStratergy getPaymentStartergy(int paymentMethod)
        {
            switch(paymentMethod){
                case 1:
                     return new CardStartergy();
                case 2:
                   return new CashStratergy();
                default:
                    return new CardStartergy();
            }
        }

    }



