package ParkingLot.ParkingFeeStratergy.ConcreteStartergy;

import ParkingLot.DurationType;
import ParkingLot.ParkingFeeStratergy.ParkingFeeStartergy;
import ParkingLot.Payment.PaymentStratergy;
import ParkingLot.VehicleType;

public class BaseRateStratergyImplementation implements ParkingFeeStartergy {

    @Override
    public double calculateFees(VehicleType vehicleType, int duration, DurationType dt) {
        switch (vehicleType){
            case Car:
                return dt==DurationType.HOURS?duration*10:duration*10*24;
            case Bike:
                return dt==DurationType.HOURS?duration*7:duration*7*24;
            default:
                return dt==DurationType.HOURS?duration*15:duration*15*24;
        }
    }
}
