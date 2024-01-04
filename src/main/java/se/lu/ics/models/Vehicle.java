package se.lu.ics.models;

import java.time.LocalDate;
import java.util.UUID;

public class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private int year;
    private double cargoCapacity; // Cargo capacity
    private int currentMileage;
    private LocalDate lastServiceDate;
    private String currentLocation;
    private MaintenanceSchedule maintenanceSchedule;
    private int lastServiceDistance;

    public Vehicle() {
    }

    public Vehicle(String model, String brand, int year, String currentLocation, int currentMileage, LocalDate lastServiceDate, double cargoCapacity, int lastServiceDistance) {
        this.vehicleId = generateVehicleId();
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.currentMileage = currentMileage;
        this.lastServiceDate = lastServiceDate;
        this.currentLocation = currentLocation;
        this.cargoCapacity = cargoCapacity;
        this.lastServiceDistance = lastServiceDistance;

        // Initialize MaintenanceSchedule using its default constructor
        this.maintenanceSchedule = new MaintenanceSchedule();
        this.maintenanceSchedule.setLastServiceDistance(lastServiceDistance);
        this.maintenanceSchedule.setLastServiceDate(lastServiceDate);
    }

    private String generateVehicleId() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString().toUpperCase().replaceAll("-", "");
        return randomUUIDString.substring(0, 6);
    }

    // Getters and Setters

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public int getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;
        this.maintenanceSchedule.performRegularService(currentMileage);
    }

    public LocalDate getLastServiceDate() {
        return lastServiceDate;
    }

    
    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;

    }

    public MaintenanceSchedule getMaintenanceSchedule() {
        return maintenanceSchedule;
    }

    public void setMaintenanceSchedule(MaintenanceSchedule maintenanceSchedule) {
        this.maintenanceSchedule = maintenanceSchedule;
    }

    public int getLastServiceDistance() {
        return lastServiceDistance;
    }

    public void setLastServiceDistance(int lastServiceDistance) {
        this.lastServiceDistance = lastServiceDistance;
        this.maintenanceSchedule.performRegularService(lastServiceDistance);
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
    public ServiceHistory getServiceHistory() {
        return this.maintenanceSchedule.getServiceHistory();
    }

    public void setServiceHistory(ServiceHistory serviceHistory) {
        this.maintenanceSchedule.setServiceHistory(serviceHistory);
    }

    public WorkShop getMaintenanceWorkshop() {
        return this.maintenanceSchedule.getMaintenanceWorkshop();
    }

    public void setMaintenanceWorkshop(WorkShop maintenanceWorkshop) {
        this.maintenanceSchedule.setMaintenanceWorkshop(maintenanceWorkshop);
    }

    public static int getRegularServiceDistanceThreshold() {
        return MaintenanceSchedule.getRegularServiceDistanceThreshold();
    }

    public static int getRegularServiceTimeThreshold() {
        return MaintenanceSchedule.getRegularServiceTimeThreshold();
    }



    public String getVehicleType() {
        return "Vehicle"; // Default type
    }
    
    

   
}
