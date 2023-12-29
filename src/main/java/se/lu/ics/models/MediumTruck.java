package se.lu.ics.models;

import java.time.LocalDate;

public class MediumTruck extends Vehicle {

    private static final double MAX_CARGO_CAPACITY = 5000; // Maximum cargo capacity for MediumTruck

    public MediumTruck(String model, String brand, int year, String currentLocation, int currentMileage, LocalDate lastServiceDate, double currentCargo, int lastServiceDistance) {
        // Call to the super constructor of Vehicle
        super(model, brand, year, currentLocation, currentMileage, lastServiceDate, MAX_CARGO_CAPACITY, currentCargo, lastServiceDistance);
    }

    
    @Override
    public void setCurrentCargo(double currentCargo) {
        if (currentCargo <= MAX_CARGO_CAPACITY) {
            super.setCurrentCargo(currentCargo);
        } else {
            System.out.println("Current cargo exceeds maximum capacity for Medium Truck, setting to maximum capacity.");
            super.setCurrentCargo(MAX_CARGO_CAPACITY);
        }
    }

    @Override
    protected String getVehicleType() {
        return "MediumTruck";
    }

}

