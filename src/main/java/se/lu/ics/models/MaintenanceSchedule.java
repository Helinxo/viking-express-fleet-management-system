package se.lu.ics.models;
import java.time.LocalDate;
import se.lu.ics.models.Service.PartType;
import se.lu.ics.models.Service.ServiceType;


public class MaintenanceSchedule {

    private ServiceHistory serviceHistory;
    private WorkShop maintenanceWorkshop; // Property to store the workshop responsible for maintenance
    private Vehicle vehicle; 
    private PartType partType;
    private ServiceType serviceType;


    private static final int REGULAR_SERVICE_DISTANCE_THRESHOLD = 30000; // Threshold in kilometers
    private static final int REGULAR_SERVICE_TIME_THRESHOLD = 3; // Threshold in months

    private int lastServiceDistance;
    private LocalDate lastServiceDate;

    public MaintenanceSchedule(int lastServiceDistance, LocalDate lastServiceDate, 
                           WorkShop maintenanceWorkshop, String vin, 
                           String vehicleType, Service.ServiceType serviceType, 
                           Service.PartType partType) {
    this.lastServiceDistance = lastServiceDistance;
    this.lastServiceDate = lastServiceDate;
    this.maintenanceWorkshop = maintenanceWorkshop;
    this.partType = partType;  // Set the partType
    this.serviceType = serviceType;  // Set the serviceType
    // other initializations...
}


    public MaintenanceSchedule(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.lastServiceDate = LocalDate.now();
        this.serviceHistory = new ServiceHistory();
    }
   

    // Default constructor
    public MaintenanceSchedule() {
        this.lastServiceDate = LocalDate.now();
        this.serviceHistory = new ServiceHistory();
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
    public boolean needsRegularService(int currentDistance, LocalDate currentDate) {
        if (currentDistance < 0) {
            throw new IllegalArgumentException("Current distance cannot be negative.");
        }
        return (currentDistance - lastServiceDistance >= REGULAR_SERVICE_DISTANCE_THRESHOLD) ||
            currentDate.isAfter(lastServiceDate.plusMonths(REGULAR_SERVICE_TIME_THRESHOLD));
    }
    

    // Method to perform regular service
    public void performRegularService(int currentDistance, LocalDate currentDate) {
        if (needsRegularService(currentDistance, currentDate)) {
            // Perform regular service tasks
            this.lastServiceDistance = currentDistance;
            this.lastServiceDate = LocalDate.now();
        }
    }


    public PartType getPartType() {
        return partType;
    }

    

    public ServiceType getServiceType() {
        return serviceType;
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

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setPartType(PartType partType) {
        this.partType = partType;
    }
    
    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
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


    public LocalDate calculateNextServiceDueDate() {
        return null;
    }

    public LocalDate calculateNextServiceDueDate(int currentDistance) {
        // Calculate the next due date based on time
        LocalDate nextDueDateBasedOnTime = lastServiceDate.plusMonths(REGULAR_SERVICE_TIME_THRESHOLD);

        // Calculate the remaining distance for the next service
        int distanceForNextService = REGULAR_SERVICE_DISTANCE_THRESHOLD - (currentDistance - lastServiceDistance);

        // If the distance threshold is reached before the time threshold
        if (distanceForNextService <= 0) {
            // Return the current date since the service is already due based on distance
            return LocalDate.now();
        }

        // Otherwise, return the next due date based on time
        return nextDueDateBasedOnTime;
    }
    
}
