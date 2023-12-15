package se.lu.ics.models;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServiceHistory {
    private ObservableList<Service> services;

    public ServiceHistory() {
        this.services = FXCollections.observableArrayList();
    }

    // CRUD operations for services
    public void addService(Service service) {
        services.add(service);
    }

    public void updateService(Service oldService, Service newService) {
        if (services.contains(oldService)) {
            services.remove(oldService);
            services.add(newService);
        }
    }

    //CRUD operations for services

    public void deleteService(Service service) {
        services.remove(service);
    }

    // Display specific information
    public void printServiceHistory() {
        for (Service service : services) {
            System.out.println(service);
        }
    }

    public double calculateTotalCost() {
        double totalCost = 0;
        for (Service service : services) {
            totalCost += service.getServiceCost() + service.getPartCost();
        }
        return totalCost;
    }

    public Service getMostExpensiveMaintenanceJob() {
        return services.stream()
                .max(Comparator.comparing(Service::getServiceCost))
                .orElse(null);
    }

    // Check if the amount of service exceeds a certain threshold
    public void checkServiceAmountWarning(double threshold) {
        double totalCost = calculateTotalCost();
        if (totalCost > threshold) {
            System.out.println("Warning: The total cost of service exceeds " + threshold + ".");
        }
    }

    // parts count 
    public int getTotalReplacedPartsCount() {
        int count = 0;
        for (Service service : services) {
            count += service.getReplacedPartsCount();
        }
        return count;
    }


    // Getters and setters
    public ObservableList<Service> getServices() {
        return FXCollections.unmodifiableObservableList(this.services);
    }

    public void setServices(ObservableList<Service> services) {
        this.services = services;
    }

    
}
