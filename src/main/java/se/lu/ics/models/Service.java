package se.lu.ics.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Service {
    public enum ServiceType {
        OIL_CHANGE(5000),
        TIRE_CHANGE(10000),
        TIRE_ROTATION(5000),
        WHEEL_BALANCE(5000),
        ALIGNMENT(5000),
        BRAKE_ADJUSTMENT(5000),
        BRAKE_REPLACEMENT(10000),
        TRANSMISSION_FLUSH(10000),
        ENGINE_FLUSH(10000),
        ENGINE_REPAIR(10000),
        ENGINE_REPLACEMENT(10000),
        BI_ANNUAL_MAINTENANCE(30000),
        ANNUAL_MAINTENANCE(50000),
        BI_ANNUAL_MAINTENANCE_WITH_OIL_CHANGE(35000),
        ANNUAL_MAINTENANCE_WITH_OIL_CHANGE(50000);

        private int serviceCost;

        ServiceType(int serviceCost) {
            this.serviceCost = serviceCost;
        }

        public int getServiceCost() {
            return serviceCost;
        }
    }

    public enum PartType {
        TIRE(1000),
        FOURTIRES(4000),
        BRAKEPADS(1000),
        ENGINEBLOCK(40000),
        TRANSMISSION(20000),
        ENGINE(500000),
        CYLINDERHEAD(100000),
        OILFILTER(100),
        AIRFILTER(100),
        FUELFILTER(100),
        SPARKPLUGS(100),
        TIMINGBELT(1000),
        TIMINGCHAIN(1000),
        NOPARTNEEDED(0);

        private int partCost;

        PartType(int partCost) {
            this.partCost = partCost;
        }

        public int getPartCost() {
            return partCost;
        }
    }

   private ServiceType serviceType;
    private String description;
    private int mileage;
    private LocalDate date; // Changed to LocalDate
    private PartType partType;
    private Vehicle vehicle;
    private WorkShop workShop;
    private ObservableList<String> replacedParts = FXCollections.observableArrayList();

    private int replacedPartsCount = 0;

    public Service() {
    }

public Service(ServiceType serviceType, String description, int mileage, LocalDate date,
PartType partType, Vehicle vehicle, WorkShop workShop) {
this.serviceType = serviceType;
this.description = description;
this.mileage = mileage;
this.date = date;
this.partType = partType;
this.vehicle = vehicle;
this.workShop = workShop;

if (partType != PartType.NOPARTNEEDED) {
    addReplacedPart(partType.name());
 // Simplified part addition
}
}
    
    // Getters and setters...

    public WorkShop getWorkShop() {
        return workShop;
    }

    public void setWorkShop(WorkShop workShop) {
        this.workShop = workShop;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public PartType getPartType() {
        return partType;
    }

    public void setPartType(PartType partType) {
        this.partType = partType;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getServiceCost() {
        return serviceType.getServiceCost();
    }

    public int getPartCost() {
        return partType.getPartCost();
    }

    public LocalDate getDate() {
        return date;
    }

    public ObservableList<String> getReplacedParts() {
        return replacedParts;
    }

    public void setReplacedParts(ObservableList<String> replacedParts) {
        this.replacedParts = replacedParts;
    }

    public int getReplacedPartsCount() {
        return replacedPartsCount;
    }

    public void setReplacedPartsCount(int replacedPartsCount) {
        this.replacedPartsCount = replacedPartsCount;
    }

    public void addReplacedPart(String part) {
        replacedParts.add(part);
        replacedPartsCount++;
    }

    public void removeReplacedPart(String part) {
        replacedParts.remove(part);
        replacedPartsCount--;
    }

    public void addReplacedParts(List<String> parts) {
        replacedParts.addAll(parts);
        replacedPartsCount += parts.size();
    }

    @Override
public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Service Details:");

    // Common properties
    stringBuilder
            .append("\nService made at Workshop: ").append(getWorkShop().getName())
            .append("\nVehicle: ").append(getVehicle().getBrand()).append(" ").append(getVehicle().getModel())
            .append("\nService Type: ").append(getServiceType())
            .append("\nDescription: ").append(getDescription())
            .append("\nMileage: ").append(getMileage()).append("km")
            .append("\nDate: ").append(getDate().format(DateTimeFormatter.ISO_LOCAL_DATE)) // Format date
            .append("\nService Cost: ").append(getServiceCost()).append("USD")
            .append("\nPart Type: ").append(getPartType())
            .append("\nPart Cost: ").append(getPartCost()).append("USD");

    // Replaced parts
    if (!replacedParts.isEmpty()) {
        stringBuilder.append("\nReplaced Parts: ").append(replacedParts);
    } else {
        stringBuilder.append("\nReplaced Parts: No parts needed");
    }

    stringBuilder.append("\n");
    return stringBuilder.toString();
}
}



