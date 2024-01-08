package se.lu.ics.models;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FleetManager {
    private ObservableList<Vehicle> vehicles;
    private Map<Vehicle, ServiceHistory> vehicleServiceHistories;
    private ObservableList<WorkShop> workshops;
    private Map<Vehicle, MaintenanceSchedule> maintenanceSchedules;
    
  
    

    public FleetManager() {
        this.vehicles = FXCollections.observableArrayList();
        this.vehicleServiceHistories = new HashMap<>();
        this.workshops = FXCollections.observableArrayList();
        this.maintenanceSchedules = new HashMap<>();
       
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

    public void updateWorkshop(WorkShop oldWorkshop, WorkShop newWorkshop) {
        if (workshops.contains(oldWorkshop)) {
            workshops.remove(oldWorkshop);
            workshops.add(newWorkshop);
        }
    }
    public boolean containsWorkshop(String workshopId) {
        return workshops.stream().anyMatch(w -> w.getWorkshopId().equals(workshopId));
    }

    public WorkShop findWorkShopById(String workshopId) {
        for (WorkShop workshop : workshops) {
            if (workshop.getWorkshopId().equals(workshopId)) {
                return workshop;
            }
        }
        return null; // Return null if no workshop is found
    }
    public void printWorkshopById(String workshopId) {
        WorkShop workshop = findWorkShopById(workshopId);
        if (workshop != null) {
            System.out.println(workshop);
        } else {
            System.out.println("Workshop not found.");
        }
    }    
   


    // CRUD operations for vehicles

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        
        // Initialize ServiceHistory and MaintenanceSchedule for the new vehicle
        ServiceHistory newServiceHistory = new ServiceHistory();
        MaintenanceSchedule newMaintenanceSchedule = new MaintenanceSchedule(vehicle);
    
        // Assuming Vehicle class has methods to set ServiceHistory and MaintenanceSchedule
        vehicle.setServiceHistory(newServiceHistory);
        vehicle.setMaintenanceSchedule(newMaintenanceSchedule);
    
        System.out.println("Vehicle added to FleetManager: " + vehicle);
    }
    
// In FleetManager class
public ObservableList<Service> getAllServicesForFleet() {
    List<Service> allServices = new ArrayList<>();
    for (Vehicle vehicle : vehicles) { // Assuming 'vehicles' is your list of vehicles
        allServices.addAll(vehicle.getServiceHistory().getServices());
    }
    return FXCollections.observableArrayList(allServices);
}
    

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
       

    }

    public void printVehicles() {
        vehicles.forEach(System.out::println);
    }

    

    public void updateVehicle(Vehicle oldVehicle, Vehicle newVehicle) {
        if (vehicles.contains(oldVehicle)) {
            vehicles.remove(oldVehicle);
            vehicles.add(newVehicle);
        } else {
            System.out.println("Old vehicle not found in the fleet.");
        }
    }
    

    public Vehicle findVehicleByVin(String vin) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equals(vin)) { // Assuming there's a getVin method in Vehicle
                return vehicle;
            }
        }
        return null; // Return null if no vehicle is found with the given VIN
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
    ServiceHistory history = vehicle.getServiceHistory();
    if (history != null && history.getServices().contains(oldService)) {
        history.deleteService(oldService);
        history.addService(newService);
    }
}
    
    
public void removeServiceFromHistory(Vehicle vehicle, Service service) {
    ServiceHistory history = vehicle.getServiceHistory();
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

    public void setVehicles(ObservableList<Vehicle> vehicles) {
        this.vehicles = vehicles;
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

    // print the service history for the entire fleet

    public void printServiceHistoryForEntireFleet() {
        for (Vehicle vehicle : vehicles) {
            System.out.println("Service History for Vehicle ID: " + vehicle.getVehicleId());
            ServiceHistory history = vehicleServiceHistories.get(vehicle);
            if (history != null && !history.getServices().isEmpty()) {
                history.printServiceHistory();
            } else {
                System.out.println("No service history available for this vehicle.");
            }
            System.out.println("---------------------------------------");
        }
    }
    

    // Method to print the total cost for a specific vehicle

    public String getTotalCostForVehicleAsString(Vehicle vehicle) {
        ServiceHistory history = vehicle.getServiceHistory();
        if (history != null) {
            double totalCost = history.calculateTotalCost();
            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
            return "Vehicle: " + vehicle.getModel() + " (VIN: " + vehicle.getVehicleId() + ")\n" +
                   "Total cost for vehicle service: " + formatter.format(totalCost);
        } else {
            return "No service history found for this vehicle.";
        }
    }
    
    
    // Do the same for the other methods
    
    
    // Method to print the total cost for the entire fleet
    

    public String getTotalCostForFleetAsString() {
        double totalCost = 0;
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
    
        for (Vehicle vehicle : vehicles) {
            ServiceHistory history = vehicle.getServiceHistory(); // Get the ServiceHistory directly from Vehicle
            if (history != null) {
                totalCost += history.calculateTotalCost();
            }
        }
    
        return "Total cost for the entire fleet: " + formatter.format(totalCost);
    }
    
    
       
    public String getMostExpensiveMaintenanceJobAsString() {
        double maxCost = 0;
        Service mostExpensiveService = null;
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
    
        for (Vehicle vehicle : vehicles) {
            ServiceHistory history = vehicle.getServiceHistory();
            for (Service service : history.getServices()) {
                double cost = service.getServiceCost() + service.getPartCost();
                if (cost > maxCost) {
                    maxCost = cost;
                    mostExpensiveService = service;
                }
            }
        }
    
        if (mostExpensiveService != null) {
            return "Most Expensive Maintenance Job:\n" + mostExpensiveService + "\nTotal Cost: " + formatter.format(maxCost);
        } else {
            return "No maintenance jobs found.";
        }
    }
    
    
    

    public String getMostExpensiveWorkshopAsString() {
        double maxCost = 0;
        WorkShop mostExpensiveWorkshop = null;
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
    
        for (WorkShop workshop : workshops) {
            double cost = calculateTotalCostForWorkshop(workshop);
            if (cost > maxCost) {
                maxCost = cost;
                mostExpensiveWorkshop = workshop;
            }
        }
    
        if (mostExpensiveWorkshop != null) {
            return "Most Expensive Workshop: " + mostExpensiveWorkshop.getName() +
                   "\nTotal Cost: " + formatter.format(maxCost);
        } else {
            return "No workshops found.";
        }
    }
    
    
    

    public double calculateTotalCostForWorkshop(WorkShop workshop) {
        double totalCost = 0;
    
        for (Vehicle vehicle : vehicles) {
            ServiceHistory history = vehicle.getServiceHistory();
            for (Service service : history.getServices()) {
                if (service.getWorkShop().equals(workshop)) {
                    totalCost += service.getServiceCost() + service.getPartCost();
                }
            }
        }
    
        return totalCost;
    }
    
    public String calculateAverageMaintenanceCostAsString() {
        double totalCost = 0;
        int serviceCount = 0;
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
    
        for (Vehicle vehicle : vehicles) {
            ServiceHistory history = vehicle.getServiceHistory();
            for (Service service : history.getServices()) {
                totalCost += service.getServiceCost() + service.getPartCost();
                serviceCount++;
            }
        }
    
        if (serviceCount > 0) {
            double averageCost = totalCost / serviceCount;
            return "Average Maintenance Cost: " + formatter.format(averageCost);
        } else {
            return "No maintenance jobs found.";
        }
    }
    
    public int getTotalReplacedPartsCountForVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return 0;
        }
        return vehicle.getServiceHistory().getTotalReplacedPartsCount();
    }
    
    



}
