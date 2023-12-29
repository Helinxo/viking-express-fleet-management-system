package se.lu.ics.models;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
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
    
        System.out.println("----- Workshops Servicing Vehicle -----");
        System.out.println("Vehicle ID: " + vehicle.getVehicleId() + " (" + vehicle.getBrand() + " " + vehicle.getModel() + ", " + vehicle.getYear() + ")");
        
        if (history != null) {
            for (Service service : history.getServices()) {
                servicedWorkshops.add(service.getWorkShop());
            }
        }
    
        if (servicedWorkshops.isEmpty()) {
            System.out.println("No workshops found for this vehicle.");
        } else {
            int count = 1;
            for (WorkShop workshop : servicedWorkshops) {
                System.out.println(count + ". " + workshop.getName() + " - Address: " + workshop.getAddress());
                count++;
            }
        }
        System.out.println("--------------------------------------");
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

    public void printServiceHistoryForVehicle(Vehicle vehicle) {
        ServiceHistory history = vehicleServiceHistories.get(vehicle);
        if (history != null) {
            history.printServiceHistory();
        } else {
            System.out.println("No service history found for this vehicle.");
        }
    }

    // Method to print the total cost for a specific vehicle

    public void printTotalCostForVehicle(Vehicle vehicle) {
        ServiceHistory history = vehicleServiceHistories.get(vehicle);
        if (history != null) {
            double totalCost = history.calculateTotalCost();
            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
    
            System.out.println("Vehicle: " + vehicle.getModel() + " (VIN: " + vehicle.getVehicleId() + ")");
            System.out.println("Total cost for vehicle service" + formatter.format(totalCost));
        } else {
            System.out.println("No service history found for this vehicle.");
        }
    }
    
    // Method to print the total cost for the entire fleet
    

    public void printTotalCostForFleet() {
        double totalFleetCost = 0;
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
    
        for (Vehicle vehicle : vehicles) {
            ServiceHistory history = vehicleServiceHistories.get(vehicle);
            if (history != null) {
                double totalCost = history.calculateTotalCost();
                totalFleetCost += totalCost;
    
                System.out.println("Vehicle: " + vehicle.getModel() + " (VIN: " + vehicle.getVehicleId() + ")");
                System.out.println("Total cost for vehicle: " + formatter.format(totalCost));
            }
        }
    
        System.out.println("\nTotal cost for entire fleet: " + formatter.format(totalFleetCost));
    }
    
    // Method to print the total n
    public void printMostExpensiveMaintenanceJob() {
        Service mostExpensiveService = null;
        double maxTotalCost = 0;
    
        for (ServiceHistory history : vehicleServiceHistories.values()) {
            for (Service service : history.getServices()) {
                double totalCost = service.getServiceCost() + service.getPartCost();
                if (totalCost > maxTotalCost) {
                    maxTotalCost = totalCost;
                    mostExpensiveService = service;
                }
            }
        }
    
        if (mostExpensiveService != null) {
            System.out.println("Most Expensive Maintenance Job:");
            System.out.println(mostExpensiveService);
            // Optionally, print the total cost as well
            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
            System.out.println("Total Cost: " + formatter.format(maxTotalCost));
        } else {
            System.out.println("No maintenance jobs found.");
        }
    }
    

    public void printMostExpensiveWorkshop() {
    Map<WorkShop, Double> workshopCosts = new HashMap<>();
    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

    for (ServiceHistory history : vehicleServiceHistories.values()) {
        for (Service service : history.getServices()) {
            WorkShop workshop = service.getWorkShop();
            double cost = service.getServiceCost() + service.getPartCost();
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
        System.out.println("Total Cost: " + formatter.format(maxCost));
    } else {
        System.out.println("No workshops found.");
    }
}



}
