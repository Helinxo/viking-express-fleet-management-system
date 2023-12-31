package se.lu.ics.models;
import java.time.LocalDate;

public class MaintenanceSchedule {

    private ServiceHistory serviceHistory;
    private WorkShop maintenanceWorkshop; // Property to store the workshop responsible for maintenance

    private static final int REGULAR_SERVICE_DISTANCE_THRESHOLD = 30000; // Threshold in kilometers
    private static final int REGULAR_SERVICE_TIME_THRESHOLD = 3; // Threshold in months

    private int lastServiceDistance;
    private LocalDate lastServiceDate;

    // Default constructor
    public MaintenanceSchedule() {
        this.lastServiceDate = LocalDate.now();
    }

    // Constructor with ServiceHistory and WorkShop
    public MaintenanceSchedule(ServiceHistory serviceHistory, WorkShop maintenanceWorkshop) {
        this.serviceHistory = serviceHistory;
        this.maintenanceWorkshop = maintenanceWorkshop;
        this.lastServiceDate = LocalDate.now(); 
    }

    // Constructor with last service details and WorkShop
    public MaintenanceSchedule(int lastServiceDistance, LocalDate lastServiceDate, WorkShop maintenanceWorkshop) {
        this.lastServiceDistance = lastServiceDistance;
        this.lastServiceDate = lastServiceDate;
        this.maintenanceWorkshop = maintenanceWorkshop;
    }

    // Method to perform service
    public void performService(Service.ServiceType serviceType, Vehicle vehicle) {
        // Create a new Service object with the necessary details
        Service service = createService(serviceType, vehicle, this.maintenanceWorkshop);
        
        // Add this service to the service history
        if (serviceHistory != null) {
            serviceHistory.addService(service);
        }
    }

    // Helper method to create a Service object
    private Service createService(Service.ServiceType serviceType, Vehicle vehicle, WorkShop workshop) {
        Service service = new Service();
        service.setServiceType(serviceType);
        service.setWorkShop(workshop);
        // Set other service properties as needed
        return service;
    }

    // Check if regular service is needed
    public boolean needsRegularService(int currentDistance) {
        if (currentDistance < 0) {
            throw new IllegalArgumentException("Current distance cannot be negative.");
        }
        LocalDate currentDate = LocalDate.now();
        return (currentDistance - lastServiceDistance >= REGULAR_SERVICE_DISTANCE_THRESHOLD) ||
            currentDate.isAfter(lastServiceDate.plusMonths(REGULAR_SERVICE_TIME_THRESHOLD));
    }

    // Method to perform regular service
    public void performRegularService(int currentDistance) {
        if (needsRegularService(currentDistance)) {
            // Perform regular service tasks
            this.lastServiceDistance = currentDistance;
            this.lastServiceDate = LocalDate.now();
        }
    }

    // Getters and Setters
    public ServiceHistory getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(ServiceHistory serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    public WorkShop getMaintenanceWorkshop() {
        return maintenanceWorkshop;
    }

    public void setMaintenanceWorkshop(WorkShop maintenanceWorkshop) {
        this.maintenanceWorkshop = maintenanceWorkshop;
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
                ", maintenanceWorkshop=" + maintenanceWorkshop +
                ", lastServiceDistance=" + lastServiceDistance +
                ", lastServiceDate=" + lastServiceDate +
                '}';
    }
}
