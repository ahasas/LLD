package ParkingLot.ParkingSpot;

import ParkingLot.Vehicle.Vehicle;

import java.util.UUID;

public class ParkingSpot {
    private UUID id;
    private boolean isOccupied;
    private String spotType;

    public String getSpotType() {
        return spotType;
    }

    public void setSpotType(String spotType) {
        this.spotType = spotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    private Vehicle vehicle;

    public ParkingSpot(UUID id, String spotType) {
        this.id = id;
        this.isOccupied = false;
        this.spotType = spotType;
    }
    public boolean isParkingSpotAvailable(){
        return this.isOccupied;
    }
    public void parkVehicle(Vehicle vehicle){
        if(isOccupied){
            throw new IllegalStateException("SPOT is Occupied");
        }
        this.vehicle=vehicle;
        isOccupied=true;
    }
    public void vacate(){
        this.isOccupied=false;
        this.vehicle=null;
        System.out.println("Spot is vacated");
    }


}
