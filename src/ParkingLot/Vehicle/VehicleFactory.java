package ParkingLot.Vehicle;

import ParkingLot.Vehicle.ConcreteClass.Bike;
import ParkingLot.Vehicle.ConcreteClass.Car;
import ParkingLot.Vehicle.ConcreteClass.Others;
import ParkingLot.VehicleType;

public class VehicleFactory {

    public static Vehicle createVehicle(String licensePlate, VehicleType type) {
        return switch (type) {
            case Car -> new Car(licensePlate, type);
            case Bike -> new Bike(licensePlate, type);
            case Others -> new Others(licensePlate, type);
        };

    }
}
