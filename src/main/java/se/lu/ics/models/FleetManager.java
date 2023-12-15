package se.lu.ics.models;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FleetManager {
    private ObservableList<Vehicle> vehicles;
    private Map<Vehicle, ServiceHistory> vehicleServiceHistories;
    private ObservableList<WorkShop> workshops;

    public FleetManager() {
        this.vehicles = FXCollections.observableArrayList();
        this.vehicleServiceHistories = new HashMap<>();
        this.workshops = FXCollections.observableArrayList(); 
    }

    // CRUD operations for workshops

    public void addWorkShop(WorkShop workshop) {
        workshops.add(workshop);
    }
    public void removeWorkShop(WorkShop workshop) {
        workshops.remove(workshop);
    }
    public void printWorkshops() {
        workshops.forEach(System.out::println);
    }
    public void updateWorkshopName(WorkShop workshop, String newName) {
        if (workshops.contains(workshop)) {
            workshop.setName(newName);
        }
    }
    
    public void updateWorkshopAddress(WorkShop workshop, String newAddress) {
        if (workshops.contains(workshop)) {
            workshop.setAddress(newAddress);
        }
    }
    public void updateWorkshopInternal(WorkShop workshop, boolean newInternal) {
        if (workshops.contains(workshop)) {
            workshop.setInternal(newInternal);
        }
    }
    

    // CRUD operations for vehicles


    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        vehicleServiceHistories.put(vehicle, new ServiceHistory());
    }

    public void addVehicles(Vehicle... vehicles) {
        for (Vehicle vehicle : vehicles) {
            addVehicle(vehicle); // Reuse the addVehicle method
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
        vehicleServiceHistories.remove(vehicle); // Also remove its service history
    }

    public void printVehicles() {
        vehicles.forEach(System.out::println);
    }

    public void printVehiclesByModel(String model) {
        vehicles.stream()
                .filter(vehicle -> vehicle.getModel().equals(model))
                .forEach(System.out::println);
    }

    public void updateVehicle(Vehicle oldVehicle, Vehicle newVehicle) {
        if (vehicles.contains(oldVehicle)) {
            vehicles.remove(oldVehicle);
            vehicles.add(newVehicle);
            // Update the service history map
            ServiceHistory history = vehicleServiceHistories.remove(oldVehicle);
            vehicleServiceHistories.put(newVehicle, history);
        } else {
            System.out.println("Old vehicle not found in the fleet.");
        }
    }




// Method to print the service history for a specific workshop
public void printServiceHistoryForWorkshop(WorkShop workshop) {
    System.out.println("Service History for Workshop: " + workshop.getName());
    boolean serviceFound = false;

    for (Vehicle vehicle : vehicles) {
        ServiceHistory history = vehicleServiceHistories.get(vehicle);
        if (history != null) {
            for (Service service : history.getServices()) {
                if (service.getWorkShop().equals(workshop)) {
                    System.out.println("Vehicle ID: " + vehicle.getVehicleId() + " - " + service);
                    serviceFound = true;
                }
            }
        }
    }

    if (!serviceFound) {
        System.out.println("No services found for this workshop.");
    }
}

    public void updateServiceDetails(Vehicle vehicle, Service oldService, Service newService) {
        ServiceHistory history = vehicleServiceHistories.get(vehicle);
        if (history != null && history.getServices().contains(oldService)) {
            history.deleteService(oldService);
            history.addService(newService);
        }
    }
    
    public void removeServiceFromHistory(Vehicle vehicle, Service service) {
        ServiceHistory history = vehicleServiceHistories.get(vehicle);
        if (history != null) {
            history.deleteService(service);
        }
    }

    public void printWorkshopsForVehicle(Vehicle vehicle) {
        Set<WorkShop> servicedWorkshops = new HashSet<>();
        ServiceHistory history = vehicleServiceHistories.get(vehicle);

        if (history != null) {
            for (Service service : history.getServices()) {
                servicedWorkshops.add(service.getWorkShop());
            }
        }

        if (servicedWorkshops.isEmpty()) {
            System.out.println("No workshops found for this vehicle.");
        } else {
            System.out.println("Workshops for Vehicle ID: " + vehicle.getVehicleId());
            for (WorkShop workshop : servicedWorkshops) {
                System.out.println(workshop.getName());

                }
            }
        }
            


    public ObservableList<Vehicle> getVehicles() {
        return FXCollections.unmodifiableObservableList(this.vehicles);
    }

    // Method to get the service history for a specific vehicle
    public ServiceHistory getServiceHistory(Vehicle vehicle) {
        return vehicleServiceHistories.get(vehicle);
    }
    public ObservableList<WorkShop> getWorkshops() {
        return FXCollections.unmodifiableObservableList(this.workshops);
    }
    public void setWorkshops(ObservableList<WorkShop> workshops) {
        this.workshops = workshops;
    }
    public void setServiceHistory(Vehicle vehicle, ServiceHistory serviceHistory) {
        vehicleServiceHistories.put(vehicle, serviceHistory);
    }

    // test

    public Service getMostExpensiveMaintenanceJob() {
        Service mostExpensiveService = null;
        double maxCost = 0;

        for (Map.Entry<Vehicle, ServiceHistory> entry : vehicleServiceHistories.entrySet()) {
            for (Service service : entry.getValue().getServices()) {
                double totalCost = service.getServiceCost() + service.getPartCost();
                if (totalCost > maxCost) {
                    maxCost = totalCost;
                    mostExpensiveService = service;
                }
            }
        }

        return mostExpensiveService;
    }

    public void printMostExpensiveWorkshop() {
        Map<WorkShop, Double> workshopCosts = new HashMap<>();

        for (ServiceHistory history : vehicleServiceHistories.values()) {
            for (Service service : history.getServices()) {
                WorkShop workshop = service.getWorkShop();
                double cost = service.getServiceCost() + service.getPartCost(); // Assuming these methods return the cost

                workshopCosts.put(workshop, workshopCosts.getOrDefault(workshop, 0.0) + cost);
            }
        }

        double maxCost = 0;
        WorkShop mostExpensiveWorkshop = null;

        for (Map.Entry<WorkShop, Double> entry : workshopCosts.entrySet()) {
            if (entry.getValue() > maxCost) {
                maxCost = entry.getValue();
                mostExpensiveWorkshop = entry.getKey();
            }
        }

        if (mostExpensiveWorkshop != null) {
            System.out.println("Most Expensive Workshop: " + mostExpensiveWorkshop.getName());
            System.out.println("Total Cost: " + maxCost);
        } else {
            System.out.println("No workshops found.");
        }
    }


}
