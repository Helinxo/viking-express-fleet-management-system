package se.lu.ics.models;

import java.time.LocalDate;

public class LargeTruck extends Vehicle {

    private static final double MAX_CARGO_CAPACITY = 10000; // Maximum cargo capacity for large truck

    public LargeTruck(String model, String brand, int year, String currentLocation, int currentMileage, LocalDate lastServiceDate, int lastServiceDistance, double cargoCapacity) {
        // Call to the super constructor of Vehicle
        super(model, brand, year, currentLocation, currentMileage, lastServiceDate, MAX_CARGO_CAPACITY, lastServiceDistance);
    }

    @Override
    public void setCargoCapacity(double cargoCapacity) {
        if (cargoCapacity <= MAX_CARGO_CAPACITY) {
            super.setCargoCapacity(cargoCapacity);
        } else {
            System.out.println("Cargo capacity exceeds maximum capacity for Large Truck, setting to maximum capacity.");
            super.setCargoCapacity(MAX_CARGO_CAPACITY);
        }
    }

    @Override
    public String getVehicleType() {
        return "LargeTruck";
    }
}




