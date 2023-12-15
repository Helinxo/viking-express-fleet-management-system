package se.lu.ics.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkShop {

    private String workshopId;
    private String name;
    private String address;
    private boolean isInternal;
    private ObservableList<Service> associatedServices;



public WorkShop(String workshopId, String name, String address, boolean isInternal) {

    this.workshopId = workshopId;
    this.name = name;
    this.address = address;
    this.isInternal = isInternal;
    this.associatedServices = FXCollections.observableArrayList();
   

}

  // Method to add a service to this workshop
  public void addService(Service service) {
    if (service == null) {
        // Handle the null case appropriately, e.g., throw an exception or log a warning
        throw new IllegalArgumentException("Service cannot be null");
    }

    if (!associatedServices.contains(service)) {
        associatedServices.add(service);
    } else {
        // Optionally handle the case where the service is already in the list
        // For example, log a message or ignore
    }
}

// Getters and Setters

public ObservableList<Service> getAssociatedServices() {
    return FXCollections.unmodifiableObservableList(associatedServices);
}

public boolean isInternal() {
    return isInternal;
}

public void setInternal(boolean internal) {
     this.isInternal = internal;
}

public String getWorkshopId() {
    return workshopId;
}

public void setWorkshopId(String workshopId) {
    this.workshopId = workshopId;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getAddress() {
    return address;
}

public void setAddress(String address) {
    this.address = address;
}


 
@Override
public String toString() {
    return String.format(
            "WorkShop Details:\n" +
                    "Workshop ID: %s\n" +
                    "Name: %s\n" +
                    "Location: %s\n" +
                    "Internal: %s\n",
            workshopId, name, address, isInternal);
}

 }
    
