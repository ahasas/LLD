package ParkingLot;

import ParkingLot.ParkingSpot.ParkingSpot;
import ParkingLot.Vehicle.Vehicle;

import java.util.List;

public class ParkingLot {

    private List<ParkingSpot> parkingSpotList;

    public ParkingLot(List<ParkingSpot> parkingSpotList) {
        this.parkingSpotList = parkingSpotList;
    }

    public ParkingSpot findParkingSpot(VehicleType vehicleType){
        for(ParkingSpot p:parkingSpotList){
            if (!p.isOccupied()&&p.getSpotType().equals(vehicleType.toString())){
                return p ;

            }
        }
        return null;
    }
    public ParkingSpot parkVehicle(Vehicle vehicle){
        ParkingSpot spot=findParkingSpot(vehicle.getType());
        if(spot!=null){
            spot.parkVehicle(vehicle);
            System.out.println("Vehicle parked Succesfully");
            return spot;
        }
        else{
            System.out.println("SPot is not available for vehicle type:"+vehicle.getType());
        }
        return null;
    }

    public void vacateSpot(Vehicle vehicle,ParkingSpot spot){
        if(spot!=null&&spot.isOccupied()){
            spot.vacate();
        }
    }


}
