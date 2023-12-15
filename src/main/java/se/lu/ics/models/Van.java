package se.lu.ics.models;

import java.time.LocalDate;

public class Van extends Vehicle {

    private static final double MAX_CARGO_CAPACITY = 2500; // Maximum cargo capacity for Van
    private double cargoCapacity;

    public Van(String make, String model, String brand, int year, String currentLocation, int currentMileage, LocalDate lastServiceDate, double cargoCapacity, int lastServiceDistance) {
        super(make, model, brand, year, currentLocation, currentMileage, lastServiceDate, cargoCapacity, lastServiceDistance);
        this.cargoCapacity = Math.min(cargoCapacity, MAX_CARGO_CAPACITY);
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    

    
}

   

   
