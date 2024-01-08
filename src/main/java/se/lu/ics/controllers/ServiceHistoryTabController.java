package se.lu.ics.controllers;
import java.time.LocalDate;
import javafx.scene.layout.GridPane;

import javafx.geometry.Insets;


import java.util.Optional;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.lu.ics.models.FleetManager;
import se.lu.ics.models.Service;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.WorkShop;
import javafx.scene.control.Dialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;


public class ServiceHistoryTabController {

     @FXML
    private AnchorPane anchorPaneServiceHistoryTab;
    @FXML
    private TableColumn<Service, LocalDate> tableColumnDateServiceHistoryTab;
    @FXML
    private TableColumn<Service, LocalDate> tableColumnLastServiceDateServiceHistoryTab;
    @FXML
    private TableColumn<Service, Integer> tableColumnPartCostServiceHistoryTab;
    @FXML
    private TableColumn<Service, String> tableColumnPartTypeServiceHistoryTab;
    @FXML
    private TableColumn<Service, String> tableColumnReplacedPartsServiceHistoryTab;
    @FXML
    private TableColumn<Service, Integer> tableColumnServiceCostServiceHistoryTab;
    @FXML
    private TableColumn<Service, String> tableColumnServiceHistoryDescription;
    @FXML
    private TableColumn<Service, String> tableColumnServiceTypeServiceHistoryTab;
    @FXML
    private TableColumn<Service, String> tableColumnVehicleTypeServiceHistoryTab;
    @FXML
    private TableColumn<Service, String> tableColumnVinServiceHistoryTab;
    @FXML
    private TableColumn<Service, String> tableColumnWorkshopServiceHistoryTab;
    @FXML
    private TableView<Service> tableViewServiceHistory;

    @FXML
    private Tab tabServiceHistory;

    @FXML
    private Button buttonAddServiceHistory;

    @FXML
    private Button buttonDeleteServiceHistory;

    @FXML
    private Button buttonUpdateServiceHistory;

    @FXML
    private Button buttonViewServiceHistory;

    @FXML
    private Label labelVinServiceHistory;

    @FXML
    private TextField textFieldEnterVinServiceHistory;  
    
    private Service selectedService;

    private FleetManager fleetManager;
  

    private Vehicle selectedVehicle;

    @FXML 
public void initialize() {


tableColumnServiceHistoryDescription.setCellValueFactory(cellData -> 
new SimpleStringProperty(cellData.getValue().getDescription()));
tableColumnServiceTypeServiceHistoryTab.setCellValueFactory(cellData ->
new SimpleStringProperty(cellData.getValue().getServiceType().toString()));
tableColumnDateServiceHistoryTab.setCellValueFactory(cellData ->
new SimpleObjectProperty<>(cellData.getValue().getDate()));
tableColumnPartTypeServiceHistoryTab.setCellValueFactory(cellData ->
new SimpleStringProperty(cellData.getValue().getPartType().toString()));
tableColumnVinServiceHistoryTab.setCellValueFactory(cellData -> {
    Service service = cellData.getValue();
    Vehicle vehicle = service.getVehicle();
    return new SimpleStringProperty(vehicle != null ? vehicle.getVehicleId() : "No Vehicle");
});


tableColumnWorkshopServiceHistoryTab.setCellValueFactory(cellData ->
new SimpleStringProperty(cellData.getValue().getWorkShop().getName()));
tableColumnServiceCostServiceHistoryTab.setCellValueFactory(cellData ->
new SimpleObjectProperty<>(cellData.getValue().getServiceCost()));
tableColumnDateServiceHistoryTab.setCellValueFactory(cellData ->
    new SimpleObjectProperty<LocalDate>(cellData.getValue().getDate()));

    // Assuming getServiceCost() and getPartCost() return int or Integer
tableColumnServiceCostServiceHistoryTab.setCellValueFactory(cellData ->
new SimpleObjectProperty<Integer>(cellData.getValue().getServiceCost()));
tableColumnPartCostServiceHistoryTab.setCellValueFactory(cellData ->
new SimpleObjectProperty<Integer>(cellData.getValue().getPartCost()));

// For the LocalDate column
tableColumnDateServiceHistoryTab.setCellValueFactory(cellData ->
new SimpleObjectProperty<LocalDate>(cellData.getValue().getDate()));

tableColumnReplacedPartsServiceHistoryTab.setCellValueFactory(cellData -> {
    Service service = cellData.getValue();
    int replacedPartsCount = service.getReplacedPartsCount();
    return new SimpleStringProperty(String.valueOf(replacedPartsCount));
});
tableColumnVehicleTypeServiceHistoryTab.setCellValueFactory(cellData -> {
    Service service = cellData.getValue();
    Vehicle vehicle = service.getVehicle();
    return new SimpleStringProperty(vehicle != null ? vehicle.getVehicleType().toString() : "No Vehicle");
});
  

}
    @FXML
    
public void handleButtonAddServiceHistoryAction(ActionEvent event) {
    System.out.println("Add Service Button Clicked");

    // Fetch the Vehicle based on VIN entered
    String vin = textFieldEnterVinServiceHistory.getText();
    selectedVehicle = fleetManager.findVehicleByVin(vin);

    if (selectedVehicle != null) {
        // Get new service details from the user
        Service newService = getUserInputForNewService();

        System.out.println("New Service: " + newService);

        if (newService != null) {
            System.out.println("Adding new service to the selected vehicle.");

            // Add the new service to the selected vehicle's service history
            selectedVehicle.getServiceHistory().addService(newService);

            System.out.println("Service added to vehicle's history.");
            updateServiceHistoryTableView(selectedVehicle);
            System.out.println("Service history table view updated.");
        } else {
            System.out.println("No service selected.");
        }
    } else {
        System.out.println("No vehicle found with VIN: " + vin);
        // Optionally, display an alert or notification to the user
    }
}

      

private Service getUserInputForNewService() {
    Dialog<Service> dialog = new Dialog<>();
    dialog.setTitle("New Service");

    // Set the button types
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    // Create input fields
    TextField descriptionField = new TextField();
    descriptionField.setPromptText("Description");

    ComboBox<Service.ServiceType> serviceTypeComboBox = new ComboBox<>();
    serviceTypeComboBox.getItems().setAll(Service.ServiceType.values()); // Assuming ServiceType is an enum

    DatePicker serviceDatePicker = new DatePicker(LocalDate.now());

    TextField mileageField = new TextField();
    mileageField.setPromptText("Mileage");

    ComboBox<Service.PartType> partTypeComboBox = new ComboBox<>();
    partTypeComboBox.getItems().setAll(Service.PartType.values()); // Assuming PartType is an enum

    ComboBox<WorkShop> workShopComboBox = new ComboBox<>();
    workShopComboBox.setItems(fleetManager.getWorkshops());
   

    // Layout
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    grid.add(new Label("Description:"), 0, 0);
    grid.add(descriptionField, 1, 0);
    grid.add(new Label("Service Type:"), 0, 1);
    grid.add(serviceTypeComboBox, 1, 1);
    grid.add(new Label("Date:"), 0, 2);
    grid.add(serviceDatePicker, 1, 2);
    grid.add(new Label("Mileage:"), 0, 3);
    grid.add(mileageField, 1, 3);
    grid.add(new Label("Part Type:"), 0, 4);
    grid.add(partTypeComboBox, 1, 4);
    grid.add(new Label("Workshop:"), 0, 5);
    grid.add(workShopComboBox, 1, 5);

    dialog.getDialogPane().setContent(grid);


    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == ButtonType.OK) {
            try {
                Service.ServiceType selectedServiceType = serviceTypeComboBox.getValue();
                String description = descriptionField.getText();
                LocalDate date = serviceDatePicker.getValue();
                int mileage = Integer.parseInt(mileageField.getText());
                Service.PartType selectedPartType = partTypeComboBox.getValue();
                WorkShop selectedWorkShop = workShopComboBox.getValue();

                // Use the previously fetched selectedVehicle based on the VIN
                if (selectedVehicle != null) {
                    return new Service(selectedServiceType, description, mileage, date, selectedPartType, selectedVehicle, selectedWorkShop);
                } else {
                    System.out.println("No vehicle found with VIN");
                    return null;
                }
            } catch (NumberFormatException e) {
                // Handle parse exception for mileage
                System.out.println("Invalid input for mileage");
                return null;
            }
        }
        return null;
    });

    Optional<Service> result = dialog.showAndWait();

    if (result.isPresent()) {
        return result.get();
    }

    return null;
}





@FXML
public void handleButtonDeleteServiceHistory(ActionEvent event) {
    Service selectedService = tableViewServiceHistory.getSelectionModel().getSelectedItem();
    
    if (selectedService != null && selectedVehicle != null) {
        // Remove the service from the selectedVehicle's service history
        selectedVehicle.getServiceHistory().deleteService(selectedService);
        
        // Remove the service from the TableView
        tableViewServiceHistory.getItems().remove(selectedService);
        
        // Friendly message to the user
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Service deleted successfully!\n" + selectedService.toString());
        alert.showAndWait();
    } else {
        System.out.println("No service selected for deletion or no vehicle selected.");
    }
}


@FXML
public void handleButtonUpdateServiceHistoryAction(ActionEvent event) {
    Service selectedService = tableViewServiceHistory.getSelectionModel().getSelectedItem();
    
    if (selectedService != null && selectedVehicle != null) {
        Service updatedService = getUserInputForUpdatedService(selectedService);
        if (updatedService != null) {
            selectedVehicle.getServiceHistory().updateService(selectedService, updatedService);
            updateServiceHistoryTableView(selectedVehicle);
        }
    } else {
        System.out.println("No service selected for update or no vehicle selected.");
    }
}


    private Service getUserInputForUpdatedService(Service service) {
        Dialog<Service> dialog = new Dialog<>();
        dialog.setTitle("Update Service");
    
        // Set the button types
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    
        // Create input fields
        TextField descriptionField = new TextField(service.getDescription());
        
        ComboBox<Service.ServiceType> serviceTypeComboBox = new ComboBox<>();
        serviceTypeComboBox.getItems().setAll(Service.ServiceType.values());
        serviceTypeComboBox.setValue(service.getServiceType()); 
        DatePicker serviceDatePicker = new DatePicker(service.getDate());
    
        TextField mileageField = new TextField(String.valueOf(service.getMileage()));
        
        ComboBox<Service.PartType> partTypeComboBox = new ComboBox<>();
        partTypeComboBox.getItems().setAll(Service.PartType.values());
        partTypeComboBox.setValue(service.getPartType()); 
    
        // Layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
    
        grid.add(new Label("Description:"), 0, 0);
        grid.add(descriptionField, 1, 0);
        grid.add(new Label("Service Type:"), 0, 1);
        grid.add(serviceTypeComboBox, 1, 1);
        grid.add(new Label("Date:"), 0, 2);
        grid.add(serviceDatePicker, 1, 2);
        grid.add(new Label("Mileage:"), 0, 3);
        grid.add(mileageField, 1, 3);
        grid.add(new Label("Part Type:"), 0, 4);
        grid.add(partTypeComboBox, 1, 4);
    
        dialog.getDialogPane().setContent(grid);
    
        // Convert the result when OK button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                try {
                    Service.ServiceType selectedServiceType = serviceTypeComboBox.getValue();
                    String description = descriptionField.getText();
                    LocalDate date = serviceDatePicker.getValue();
                    int mileage = Integer.parseInt(mileageField.getText());
                    Service.PartType selectedPartType = partTypeComboBox.getValue();
    
                    // Return a new Service object with the updated data
                    return new Service(selectedServiceType, description, mileage, date, selectedPartType, service.getVehicle(), service.getWorkShop());
                } catch (NumberFormatException e) {
                    // Handle parse exception for mileage
                    System.out.println("Invalid input for mileage");
                    return null;
                }
            }
            return null;
        });
    
        Optional<Service> result = dialog.showAndWait();
    
        if (result.isPresent()) {
            return result.get();
        }
    
        return null;
    }
    

    @FXML
    public void handleButtonViewServiceHistoryAction(ActionEvent event) {
        String input = textFieldEnterVinServiceHistory.getText().trim();
    
        if (!input.isEmpty()) {
            
            Vehicle selectedVehicle = fleetManager.findVehicleByVin(input);
            updateServiceHistoryTableView(selectedVehicle);
        } else {
            
            updateServiceHistoryTableViewForAllVehicles();
        }
    }
    
    
    public void updateServiceHistoryTableViewForAllVehicles() {
        ObservableList<Service> allServices = fleetManager.getAllServicesForFleet();
        tableViewServiceHistory.setItems(allServices);
    }
    
    public void updateServiceHistoryTableView(Vehicle vehicle) {
        if (vehicle != null) {
            ObservableList<Service> services = FXCollections.observableArrayList(vehicle.getServiceHistory().getServices());
            tableViewServiceHistory.setItems(services);
        } else {
            System.out.println("Vehicle not found or null vehicle reference.");
            tableViewServiceHistory.getItems().clear();
            // Optionally
    
        }
    }
public void postInitialization() {
}

  public void setFleetManager(FleetManager fleetManager) {
        this.fleetManager = fleetManager;
        System.out.println("ServiceHistory: FleetManager set");
    }


}
