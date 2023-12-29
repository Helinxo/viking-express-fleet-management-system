package se.lu.ics.models;

import java.time.LocalDate;
import java.util.UUID;

public class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private int year;
    private double maxCargoCapacity; // Maximum cargo capacity
    private double currentCargo; // Current cargo load
    private int currentMileage;
    private LocalDate lastServiceDate;
    private String currentLocation;
    private MaintenanceSchedule maintenanceSchedule;
    private int lastServiceDistance;

    public Vehicle(String model, String brand, int year, String currentLocation, int currentMileage, LocalDate lastServiceDate, double maxCargoCapacity, double currentCargo, int lastServiceDistance) {
        this.vehicleId = generateVehicleId();
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.currentMileage = currentMileage;
        this.lastServiceDate = lastServiceDate;
        this.currentLocation = currentLocation;
        this.maxCargoCapacity = maxCargoCapacity;
        this.currentCargo = currentCargo;
        this.lastServiceDistance = lastServiceDistance;
        this.maintenanceSchedule = new MaintenanceSchedule(lastServiceDistance, lastServiceDate);
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
    
    public double getMaxCargoCapacity() {
        return maxCargoCapacity;
    }

    public void setMaxCargoCapacity(double maxCargoCapacity) {
        this.maxCargoCapacity = maxCargoCapacity;
    }

    public double getCurrentCargo() {
        return currentCargo;
    }

    public void setCurrentCargo(double currentCargo) {
        if (currentCargo <= this.maxCargoCapacity) {
            this.currentCargo = currentCargo;
        } else {
            System.out.println("Current cargo exceeds maximum capacity, setting to maximum capacity.");
            this.currentCargo = this.maxCargoCapacity;
        }
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getCurrentMileage() {
        return currentMileage;
    }

    public LocalDate getLastServiceDate() {
        return lastServiceDate;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;
    }

    public void setLastServiceDate(LocalDate lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    protected String getVehicleType() {
        return "Vehicle"; // Default type
    }

    @Override
    public String toString() {
        return String.format("%s{\n" +
                "\tvehicleId='%s',\n" +
                "\tbrand='%s',\n" +
                "\tmodel='%s',\n" +
                "\tyear='%s',\n" +
                "\tcurrentMileage='%s Km',\n" +
                "\tlastServiceDate='%s',\n" +
                "\tcurrentLocation='%s',\n" +
                "\tmaxCargoCapacity='%s Kg',\n" +
                "\tcurrentCargo='%s Kg'\n" +
                '}', getVehicleType(), vehicleId, brand, model, year, currentMileage, lastServiceDate, currentLocation, maxCargoCapacity, currentCargo);
    }

    

    
}
