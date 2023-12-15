package se.lu.ics.models;

import java.time.LocalDate;
import java.util.UUID;


public class Vehicle{
    private String vehicleId;
    private String model;
    private String make;
    private String brand;
    private int year;
    private double cargoCapacity;
    private int currentMileage;
    private LocalDate lastServiceDate;
    private String currentLocation;
    private MaintenanceSchedule maintenanceSchedule;
    private int lastServiceDistance;


    public Vehicle(String make, String model, String brand, int year, String currentLocation, int currentMileage, LocalDate lastServiceDate, double cargoCapacity, int lastServiceDistance) {
        this.vehicleId = generateVehicleId();
        this.make = make;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.currentMileage = currentMileage;
        this.lastServiceDate = lastServiceDate; // Make sure this is provided
        this.currentLocation = currentLocation;
        this.cargoCapacity = cargoCapacity;
        this.maintenanceSchedule = new MaintenanceSchedule(lastServiceDistance, lastServiceDate); // Correct order of parameters
    }
    private String generateVehicleId() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString().toUpperCase().replaceAll("-", "");
        return randomUUIDString.substring(0, 6);
    }

// Getters and Setters

public int getLastServiceDistance() {
    return lastServiceDistance;
}

public void setLastServiceDistance(int lastServiceDistance) {
    this.lastServiceDistance = lastServiceDistance;
}

public MaintenanceSchedule getMaintenanceSchedule() {
    return maintenanceSchedule;
}

public void setMaintenanceSchedule(MaintenanceSchedule maintenanceSchedule) {
    this.maintenanceSchedule = maintenanceSchedule;
}



public String getCurrentLocation() {
    return currentLocation;
}

public void setCurrentLocation(String currentLocation) {
    this.currentLocation = currentLocation;
}



public String getVehicleId() {
    return vehicleId;
}

public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
}


public String getMake() {
    return make;
}

public void setMake(String make) {
    this.make = make;
}

public String getModel() {
    return model;
}

public void setModel(String model) {
    this.model = model;
}

public String getBrand() {
    return brand;
}

public void setBrand(String brand) {
    this.brand = brand;
}

public int getYear() {
    return year;
}

public void setYear(int year) {
    this.year = year;
}



public int getCurrentMileage() {
    return currentMileage;
}

public void setCurrentMileage(int currentMileage) {
    this.currentMileage = currentMileage;
}

public LocalDate getLastServiceDate() {
    return lastServiceDate;
}

public void setLastServiceDate(LocalDate lastServiceDate) {
    this.lastServiceDate = lastServiceDate;
}

public double getCargoCapacity() {
    return cargoCapacity;
}

public void setCargoCapacity(double cargoCapacity) {
    this.cargoCapacity = cargoCapacity;
}

@Override
public String toString() {
    return String.format("Vehicle{\n" +
            "\tvehicleId='%s',\n" +
            "\tmake='%s',\n" +
            "\tmodel='%s',\n" +
            "\tbrand='%s',\n" +
            "\tyear='%s',\n" +
            "\tcurrentMilage='%s Km',\n" +
            "\tlastServiceDate='%s',\n" +
            "\tcurrentLocation='%s',\n" +
            "\tcargoCapacity='%s Kg'\n" +
            '}',
            vehicleId, make, model, brand, year,currentMileage, lastServiceDate, currentLocation, cargoCapacity);
}
}






