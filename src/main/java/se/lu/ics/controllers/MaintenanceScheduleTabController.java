package se.lu.ics.controllers;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import se.lu.ics.models.Service.PartType;
import se.lu.ics.models.Service.ServiceType;
import se.lu.ics.models.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class MaintenanceScheduleTabController {

     @FXML
    private AnchorPane anchorPaneMaintenanceScheduleTab;

    @FXML
    private Tab tabMaintenanceSchedule;

    @FXML
    private TableView<MaintenanceSchedule> tableViewMaintenanceScheduleTab;

    @FXML
    private TableColumn<MaintenanceSchedule, LocalDate> tableColumnDateMaintenanceScheduleTab;
    @FXML
    private TableColumn<MaintenanceSchedule, LocalDate> tableColumnLastServiceDateMaintenanceScheduleTab;
    @FXML
    private TableColumn<MaintenanceSchedule, Integer> tableColumnLastServiceDistanceMaintenanceScheduleTab;
     @FXML
    private TableColumn<MaintenanceSchedule, String> tableColumnPartTypeMaintenanceScheduleTab;
    @FXML
    private TableColumn<MaintenanceSchedule, String> tableColumnServiceTypeMaintenanceScheduleTab;
    @FXML
    private TableColumn<MaintenanceSchedule, String> tableColumnVehicleTypeMaintenanceScheduleTab;
    @FXML
    private TableColumn<MaintenanceSchedule, String> tableColumnVinMaintenanceScheduleTab;
    @FXML
    private TableColumn<MaintenanceSchedule, String> tableColumnWorkshopMaintenanceScheduleTab;

    @FXML
    private Button buttonConfirm;

    @FXML
    private ChoiceBox<PartType> choiceBoxPartTypes;

    @FXML
    private ChoiceBox<ServiceType> choiceBoxServiceType;

    @FXML
   private Button buttonCheckMaintenance;

    @FXML
    private ChoiceBox<WorkShop> choiceBoxWorkshop;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelLastServiceDate;

    @FXML
    private Label labelLastServiceDistance;

    @FXML
    private Label labelPartTypes;

    @FXML
    private Label labelServiceType;

    @FXML
    private Label labelVehicleTypeMaintenanceScheduleTab;

    @FXML
    private Label labelVinMaintenanceSchedule;

    @FXML
    private Label labelWorkshop;

    @FXML
    private TextField textFieldEnterVinMaintenanceSchedule;

    @FXML
    private TextField textFieldLastServiceDate;

    @FXML
    private TextField textFieldLastServiceDistance;

    @FXML
    private DatePicker datePickerDate;

    private FleetManager fleetManager;

    public void setFleetManager(FleetManager fleetManager) {
        this.fleetManager = fleetManager;
        System.out.println("MaintenanceSchedule: FleetManager set");
        setupWorkshops();
    }

    @FXML

    public void initialize() {
       choiceBoxPartTypes.setItems(FXCollections.observableArrayList(Service.PartType.values()));

       choiceBoxServiceType.setItems(FXCollections.observableArrayList(Service.ServiceType.values()));
       
        setupWorkshops();
        setUptableViewMaintenanceScheduleTab();

        textFieldEnterVinMaintenanceSchedule.textProperty().addListener((observable, oldValue, newValue) -> {
            updateLastServiceFields(newValue);
        });

    }

    
    private void setupWorkshops() {
        if (this.fleetManager != null) {
            ObservableList<WorkShop> workshops = fleetManager.getWorkshops();
            choiceBoxWorkshop.setItems(workshops);
        }
    }

    @FXML
    public void handleButtonCheckMaintenanceAction(ActionEvent event) {
        String vin = textFieldEnterVinMaintenanceSchedule.getText();
        Vehicle vehicle = fleetManager.findVehicleByVin(vin);
    
        if (vehicle != null) {
            MaintenanceSchedule maintenanceSchedule = vehicle.getMaintenanceSchedule();
            LocalDate currentDate = LocalDate.now();
            int currentMileage = vehicle.getCurrentMileage();
    
            boolean isMaintenanceDue = maintenanceSchedule.needsRegularService(currentMileage, currentDate);
    
            if (isMaintenanceDue) {
                WorkShop assignedWorkshop = vehicle.getAssignedWorkshop();
                if (assignedWorkshop == null) {
                    assignedWorkshop = choiceBoxWorkshop.getValue(); // Get selected workshop from ChoiceBox
                    if (assignedWorkshop == null) {
                        showAlert("No Workshop Selected", "Please select a workshop for maintenance.");
                        return; // Exit the method if no workshop is selected
                    }
                    vehicle.setAssignedWorkshop(assignedWorkshop); // Assign the selected workshop
                }
    
                maintenanceSchedule.setMaintenanceWorkshop(assignedWorkshop);
                showAlert("Maintenance Due", "Maintenance is due for vehicle with VIN: " + vin + 
                          ". Assigned to workshop: " + assignedWorkshop.getName());
            } else {
                LocalDate nextServiceDueDate = maintenanceSchedule.calculateNextServiceDueDate();
                showAlert("Maintenance Status", "No immediate maintenance required for VIN: " + vin + 
                          ". Next service is due on: " + nextServiceDueDate);
            }
        } else {
            showAlert("Vehicle not found", "No vehicle found with VIN: " + vin);
        }
    }
    
    
    

    
    @FXML
    public void handleButtonConfirmAction(ActionEvent event) {
        String vin = textFieldEnterVinMaintenanceSchedule.getText();
        LocalDate lastServiceDate = LocalDate.parse(textFieldLastServiceDate.getText()); // Assuming date format is correct
        int lastServiceDistance = Integer.parseInt(textFieldLastServiceDistance.getText()); // Assuming the distance is an integer
        ServiceType serviceType = choiceBoxServiceType.getValue();
        PartType partType = choiceBoxPartTypes.getValue();
        WorkShop selectedWorkshop = choiceBoxWorkshop.getValue();
    
        Vehicle vehicle = fleetManager.findVehicleByVin(vin);
    
        if (vehicle != null) {
            ServiceHistory serviceHistory = vehicle.getServiceHistory();
            MaintenanceSchedule maintenanceSchedule = vehicle.getMaintenanceSchedule();
    
            // Create a new Service object
            Service newService = new Service();
            newService.setDate(lastServiceDate);
            newService.setServiceType(serviceType);
            newService.setPartType(partType);
            newService.setWorkShop(selectedWorkshop);
            newService.setVehicle(vehicle); 
    
            serviceHistory.addService(newService);
    
            // Update the existing MaintenanceSchedule of the vehicle
            maintenanceSchedule.setLastServiceDistance(lastServiceDistance);
            maintenanceSchedule.setLastServiceDate(lastServiceDate);
            maintenanceSchedule.setMaintenanceWorkshop(selectedWorkshop);
            // Update partType and serviceType in the MaintenanceSchedule
            maintenanceSchedule.setPartType(partType);
            maintenanceSchedule.setServiceType(serviceType);
    
            // Check if regular service is needed by comparing dates
            LocalDate pickedDate = datePickerDate.getValue();
            if (maintenanceSchedule.needsRegularService(vehicle.getCurrentMileage(), pickedDate)) {
                showAlert("Maintenance Warning", "Regular maintenance is due for the vehicle with VIN: " + vin);
            }
    
            // Update your table view here to reflect the new service
            updateServiceTableView();
    
            // Clearing input fields
            clearInputFields();
    
            labelVehicleTypeMaintenanceScheduleTab.setText(vehicle.getClass().getSimpleName());
            labelVinMaintenanceSchedule.setText(vin);
    
            showAlert("Success", "Service added successfully for vehicle with VIN: " + vin);
        } else {
            showAlert("Vehicle not found", "No vehicle found with VIN: " + vin);
        }
    }
    
    
    
    private void clearInputFields() {
        // Clear all the input fields
        textFieldEnterVinMaintenanceSchedule.clear();
        textFieldLastServiceDate.clear();
        textFieldLastServiceDistance.clear();
        choiceBoxServiceType.getSelectionModel().clearSelection();
        choiceBoxPartTypes.getSelectionModel().clearSelection();
        choiceBoxWorkshop.getSelectionModel().clearSelection();

        // Any other fields you want to clear
    }
    public void updateServiceTableView() {
        // Clear the existing items in the table view
        tableViewMaintenanceScheduleTab.getItems().clear();
    
        // Iterate over each vehicle in the fleet and add their maintenance schedules
        for (Vehicle vehicle : fleetManager.getVehicles()) {
            MaintenanceSchedule schedule = vehicle.getMaintenanceSchedule();
            if (schedule != null) {
                tableViewMaintenanceScheduleTab.getItems().add(schedule);
            }
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

   
    

    
   
public void handleButtonShowVinMaintenanceScheduleAction(ActionEvent event) {
    String vin = textFieldEnterVinMaintenanceSchedule.getText();

    Vehicle vehicle = fleetManager.findVehicleByVin(vin);
    if (vehicle != null) {
        labelVinMaintenanceSchedule.setText(vin);
    } else {
        // Handle the case where no vehicle is found with the given VIN
        // For example, show an alert to the user
    }
}

   

private void setUptableViewMaintenanceScheduleTab() {
   

    // Assuming 'lastServiceDate' is updated from DatePicker or other source
    tableColumnDateMaintenanceScheduleTab.setCellValueFactory(new PropertyValueFactory<>("lastServiceDate"));
    tableColumnLastServiceDistanceMaintenanceScheduleTab.setCellValueFactory(new PropertyValueFactory<>("lastServiceDistance"));

    tableColumnPartTypeMaintenanceScheduleTab.setCellValueFactory(cellData -> {
        MaintenanceSchedule schedule = cellData.getValue();
        PartType partType = schedule.getPartType();
        return new SimpleStringProperty(partType != null ? partType.name() : "N/A");
    });
    
    tableColumnServiceTypeMaintenanceScheduleTab.setCellValueFactory(cellData -> {
        MaintenanceSchedule schedule = cellData.getValue();
        ServiceType serviceType = schedule.getServiceType();
        return new SimpleStringProperty(serviceType != null ? serviceType.name() : "N/A");
    });
    // VIN and Vehicle Type (assuming Vehicle ID is used as VIN)
    tableColumnVinMaintenanceScheduleTab.setCellValueFactory(cellData -> {
        MaintenanceSchedule schedule = cellData.getValue();
        Vehicle vehicle = schedule.getVehicle();
        return new SimpleStringProperty(vehicle != null ? vehicle.getVehicleId() : "N/A");
    });

    tableColumnVehicleTypeMaintenanceScheduleTab.setCellValueFactory(cellData -> {
        MaintenanceSchedule schedule = cellData.getValue();
        Vehicle vehicle = schedule.getVehicle();
        return new SimpleStringProperty(vehicle != null ? vehicle.getVehicleType() : "N/A");
    });
    

    // Workshop Name
    tableColumnWorkshopMaintenanceScheduleTab.setCellValueFactory(cellData -> {
        MaintenanceSchedule schedule = cellData.getValue();
        WorkShop workshop = schedule.getMaintenanceWorkshop();
        return new SimpleStringProperty(workshop != null ? workshop.getName() : "N/A"); // Assuming WorkShop has a getName() method
    });



    // ... (other setup)
}
private void updateLastServiceFields(String vin) {
    if (vin != null && !vin.isEmpty()) {
        Vehicle vehicle = fleetManager.findVehicleByVin(vin);
        if (vehicle != null && vehicle.getMaintenanceSchedule() != null) {
            MaintenanceSchedule maintenanceSchedule = vehicle.getMaintenanceSchedule();

            // Update last service distance
            int lastServiceDistance = maintenanceSchedule.getLastServiceDistance();
            textFieldLastServiceDistance.setText(String.valueOf(lastServiceDistance));

            // Update last service date
            LocalDate lastServiceDate = maintenanceSchedule.getLastServiceDate();
            if (lastServiceDate != null) {
                textFieldLastServiceDate.setText(lastServiceDate.toString());
            } else {
                textFieldLastServiceDate.clear();
            }
        } else {
            // Clear the fields if no vehicle is found
            textFieldLastServiceDistance.clear();
            textFieldLastServiceDate.clear();
        }
    } else {
        // Clear the fields if VIN is empty
        textFieldLastServiceDistance.clear();
        textFieldLastServiceDate.clear();
    }
}





}

