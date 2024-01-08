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
    private int lastServiceDistance;
    private MaintenanceSchedule maintenanceSchedule;
    private ServiceHistory serviceHistory;
    private WorkShop assignedWorkshop;

    public Vehicle() {
    }

    public Vehicle(String model, String brand, int year, String currentLocation, 
                   int currentMileage, LocalDate lastServiceDate, 
                   double cargoCapacity, int lastServiceDistance) {
        this.vehicleId = generateVehicleId();
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.currentMileage = currentMileage;
        this.lastServiceDate = lastServiceDate;
        this.currentLocation = currentLocation;
        this.cargoCapacity = cargoCapacity;
        this.lastServiceDistance = lastServiceDistance;
    }
    
    private String generateVehicleId() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString().toUpperCase().replaceAll("-", "");
        return randomUUIDString.substring(0, 6);
    }


        // Other properties and methods
    
        public boolean isLargeTruck() {
            return this instanceof LargeTruck;
        }
        public void setAssignedWorkshop(WorkShop workshop) {
            this.assignedWorkshop = workshop;
        }
    
        public WorkShop getAssignedWorkshop() {
            return this.assignedWorkshop;
        }
    
    

    // Getters and Setters
    public MaintenanceSchedule getMaintenanceSchedule() {
        return maintenanceSchedule;
    }

    public void setMaintenanceSchedule(MaintenanceSchedule maintenanceSchedule) {
        this.maintenanceSchedule = maintenanceSchedule;
    }

    public ServiceHistory getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(ServiceHistory serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

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

    public int getLastServiceDistance() {
        return lastServiceDistance;
    }

    public void setLastServiceDistance(int lastServiceDistance) {
        this.lastServiceDistance = lastServiceDistance;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String id) {
        this.vehicleId = id;
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

    public String getVehicleType() {
        return "Vehicle"; // Default type
    }
}
