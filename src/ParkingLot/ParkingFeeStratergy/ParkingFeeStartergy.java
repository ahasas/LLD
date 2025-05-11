package ParkingLot.ParkingFeeStratergy;

import ParkingLot.DurationType;
import ParkingLot.VehicleType;

public interface ParkingFeeStartergy {

    double calculateFees(VehicleType vehicleType, int duration, DurationType dt);
}
