package se.lu.ics.models;

import java.time.LocalDate;

public class MediumTruck extends Vehicle {

    private static final double MAX_CARGO_CAPACITY = 5000; // Maximum cargo capacity for MediumTruck
    private double cargoCapacity;
   

    public MediumTruck(String make, String model, String brand, int year, String currentLocation, int currentMileage, LocalDate lastServiceDate, double cargoCapacity, int lastServiceDistance) {
        super(make, model, brand, year, currentLocation, currentMileage, lastServiceDate, cargoCapacity, lastServiceDistance);
        this.cargoCapacity = Math.min(cargoCapacity, MAX_CARGO_CAPACITY);
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

   

    
}

