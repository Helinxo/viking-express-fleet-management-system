package se.lu.ics.models;
import java.time.LocalDate;

public class MaintenanceSchedule {

    private ServiceHistory serviceHistory;
    // Add a property to store the workshop responsible for maintenanc private WorkShop maintenanceWorkshop;e

    private static final int REGULAR_SERVICE_DISTANCE_THRESHOLD = 30000; // Threshold in kilometers
    private static final int REGULAR_SERVICE_TIME_THRESHOLD = 3; // Threshold in months

    private int lastServiceDistance;
    private LocalDate lastServiceDate;

    public MaintenanceSchedule(int lastServiceDistance, LocalDate lastServiceDate) {
        this.lastServiceDistance = lastServiceDistance;
        this.lastServiceDate = lastServiceDate;
    
    }

    public MaintenanceSchedule(ServiceHistory serviceHistory) {
        this.serviceHistory = serviceHistory;
        this.lastServiceDistance = 25000;
        this.lastServiceDate = LocalDate.now(); // Assuming service was 2 months ago
    }

    public void performService(Service.ServiceType serviceType, Vehicle vehicle, WorkShop workshop) {
        // Create a new Service object with the necessary details
        Service service = createService(serviceType, vehicle, workshop);
        
        // Add this service to the service history
        serviceHistory.addService(service);
    }

    private Service createService(Service.ServiceType serviceType, Vehicle vehicle, WorkShop workshop) {
        Service service = new Service();
        service.setServiceType(serviceType);
        service.setWorkShop(workshop);
        // Set other service properties (description, mileage, date, partType, partCost, etc.) as needed
        return service;
    }

    public boolean needsRegularService(int currentDistance) {
        if (currentDistance < 0) {
            throw new IllegalArgumentException("Current distance cannot be negative.");
        }
        LocalDate currentDate = LocalDate.now();
        return (currentDistance - lastServiceDistance >= REGULAR_SERVICE_DISTANCE_THRESHOLD) ||
            currentDate.isAfter(lastServiceDate.plusMonths(REGULAR_SERVICE_TIME_THRESHOLD));
    }

    public void performRegularService(int currentDistance) {
        if (needsRegularService(currentDistance)) {
            // Perform regular service tasks

            // Update last service details
            this.lastServiceDistance = currentDistance;
            this.lastServiceDate = LocalDate.now();
        }
    }


    //getters and setters

 
    

    public ServiceHistory getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(ServiceHistory serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    public int getLastServiceDistance() {
        return lastServiceDistance;
    }

    public void setLastServiceDistance(int lastServiceDistance) {
        this.lastServiceDistance = lastServiceDistance;
    }

    public LocalDate getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(LocalDate lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public static int getRegularServiceDistanceThreshold() {
        return REGULAR_SERVICE_DISTANCE_THRESHOLD;
    }

    public static int getRegularServiceTimeThreshold() {
        return REGULAR_SERVICE_TIME_THRESHOLD;
    }

    @Override

    public String toString() {
        return "MaintenanceSchedule{" +
                "serviceHistory=" + serviceHistory +
                ", lastServiceDistance=" + lastServiceDistance +
                ", lastServiceDate=" + lastServiceDate +
                '}';
    }

}
