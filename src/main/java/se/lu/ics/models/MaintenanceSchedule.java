package se.lu.ics.models;
import java.time.LocalDate;
import se.lu.ics.models.Service.PartType;
import se.lu.ics.models.Service.ServiceType;


public class MaintenanceSchedule {

    private ServiceHistory serviceHistory;
    private WorkShop maintenanceWorkshop; 
    private Vehicle vehicle; 
    private PartType partType;
    private ServiceType serviceType;


    private static final int REGULAR_SERVICE_DISTANCE_THRESHOLD = 30000; 
    private static final int REGULAR_SERVICE_TIME_THRESHOLD = 3; 

    private int lastServiceDistance;
    private LocalDate lastServiceDate;

    public MaintenanceSchedule(int lastServiceDistance, LocalDate lastServiceDate, WorkShop maintenanceWorkshop, String vin,  String vehicleType, Service.ServiceType serviceType, Service.PartType partType) {
    this.lastServiceDistance = lastServiceDistance;
    this.lastServiceDate = lastServiceDate;
    this.maintenanceWorkshop = maintenanceWorkshop;
    this.partType = partType;  
    this.serviceType = serviceType; 
    
}


    public MaintenanceSchedule(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.lastServiceDate = LocalDate.now();
        this.serviceHistory = new ServiceHistory();
    }
   

   
    public MaintenanceSchedule() {
        this.lastServiceDate = LocalDate.now();
        this.serviceHistory = new ServiceHistory();
    }

    
    public MaintenanceSchedule(ServiceHistory serviceHistory, WorkShop maintenanceWorkshop) {
        this.serviceHistory = serviceHistory;
        this.maintenanceWorkshop = maintenanceWorkshop;
        this.lastServiceDate = LocalDate.now(); 
    }

   
    public MaintenanceSchedule(int lastServiceDistance, LocalDate lastServiceDate, WorkShop maintenanceWorkshop) {
        this.lastServiceDistance = lastServiceDistance;
        this.lastServiceDate = lastServiceDate;
        this.maintenanceWorkshop = maintenanceWorkshop;
    }

    
    public void performService(Service.ServiceType serviceType, Vehicle vehicle) {
        
        Service service = createService(serviceType, vehicle, this.maintenanceWorkshop);
        
       
        if (serviceHistory != null) {
            serviceHistory.addService(service);
        }
    }

    
    private Service createService(Service.ServiceType serviceType, Vehicle vehicle, WorkShop workshop) {
        Service service = new Service();
        service.setServiceType(serviceType);
        service.setWorkShop(workshop);
        
        return service;
    }

    
    public boolean needsRegularService(int currentDistance, LocalDate currentDate) {
        if (currentDistance < 0) {
            throw new IllegalArgumentException("Current distance cannot be negative.");
        }
        return (currentDistance - lastServiceDistance >= REGULAR_SERVICE_DISTANCE_THRESHOLD) ||
            currentDate.isAfter(lastServiceDate.plusMonths(REGULAR_SERVICE_TIME_THRESHOLD));
    }
    

    
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

        int currentDistance = this.vehicle.getCurrentMileage();
    
        LocalDate nextDueDateBasedOnTime = lastServiceDate.plusMonths(REGULAR_SERVICE_TIME_THRESHOLD);
        int distanceForNextService = REGULAR_SERVICE_DISTANCE_THRESHOLD - (currentDistance - lastServiceDistance);
    
        if (distanceForNextService <= 0) {
            return LocalDate.now();
        }
    
        return nextDueDateBasedOnTime;
    }
    
}
