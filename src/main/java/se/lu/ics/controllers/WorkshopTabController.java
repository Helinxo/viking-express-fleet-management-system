package se.lu.ics.controllers;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.lu.ics.models.FleetManager;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.WorkShop;


public class WorkshopTabController {

    @FXML
    private AnchorPane anchorPaneWorkshop;

    @FXML
    private Tab tabWorkshop;
    @FXML
    private TableColumn<WorkShop, String> tableColumnWorkshopAddress;
    @FXML
    private TableColumn<WorkShop, String> tableColumnWorkshopId;
    @FXML
    private TableColumn<WorkShop, String> tableColumnWorkshopName;
    @FXML
    private TableColumn<WorkShop, String> tableColumnWorkshopType; 
    @FXML
    private TableColumn<WorkShop, String> tableColumnWorkshopVin; 
    @FXML
    private TableView<WorkShop> tableViewWorkshop;

    @FXML
    private Button buttonAddWorkshop;

    @FXML
    private Button buttonDeleteWorkshop;

    @FXML
    private Button buttonUpdateWorkshop;

    

    @FXML
    private Label labelVinWorkshop;

    @FXML
    private Label labelWorkshopAddress;

    @FXML
    private Label labelWorkshopId;

    @FXML
    private Label labelWorkshopName;

    @FXML
    private Label labelWorkshopType;

    @FXML
    private RadioButton radioButtonExternal;

    @FXML
    private RadioButton radioButtonInternal;

    @FXML
    private TextField textFieldEnterVinWorkshop;

    @FXML
    private TextField textFieldWorkshopAddress;

    @FXML
    private TextField textFieldWorkshopId;

    @FXML
    private TextField textFieldWorkshopName;

    private FleetManager fleetManager;

 

    

    @FXML 
    public void initialize() {
        setupWorkshops();



        
    }

    public void setFleetManager(FleetManager fleetManager) {
        this.fleetManager = fleetManager;
        System.out.println("WorkShop: FleetManager set");
       
    }
@FXML
public void setupWorkshops() {
    tableColumnWorkshopVin.setCellValueFactory(cellData -> {
        WorkShop workshop = cellData.getValue();
        Vehicle vehicle = workshop.getAssignedVehicle();
        String vin = vehicle != null ? vehicle.getVehicleId() : "";
        return new SimpleStringProperty(vin);
    });
    tableColumnWorkshopId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWorkshopId()));
    tableColumnWorkshopName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
    tableColumnWorkshopAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
    tableColumnWorkshopType.setCellValueFactory(cellData -> {
        WorkShop workshop = cellData.getValue();
        String workshopType = workshop.isInternal() ? "Internal" : "External";
        return new SimpleStringProperty(workshopType);
    });

   
}




   

@FXML
public void handleButtonAddWorkshopAction(ActionEvent event) {
    if (fleetManager == null) {
        showAlert("Fleet Manager not initialized", AlertType.ERROR);
        return;
    }
    String workshopId = textFieldWorkshopId.getText();
    String workshopName = textFieldWorkshopName.getText();
    String workshopAddress = textFieldWorkshopAddress.getText();
    boolean isInternal = radioButtonInternal.isSelected();
    String vin = textFieldEnterVinWorkshop.getText();

    WorkShop existingWorkshop = fleetManager.findWorkShopById(workshopId);
    if (existingWorkshop != null) {
        showAlert("Workshop already exists", AlertType.ERROR);
        return;
    }

    WorkShop newWorkshop = new WorkShop(workshopId, workshopName, workshopAddress, isInternal);

    // Check if VIN is provided to link the workshop with a vehicle
    if (!vin.isEmpty()) {
        Vehicle vehicle = fleetManager.findVehicleByVin(vin);
        if (vehicle == null) {
            showAlert("Vehicle not found", AlertType.ERROR);
            return;
        }

        if (vehicle.isLargeTruck() && isInternal) {
            showAlert("Large trucks cannot be assigned to internal workshops", AlertType.ERROR);
            return;
        }

    }

    fleetManager.addWorkShop(newWorkshop);
    updateWorkShopTableView();

    // Clearing input fields after action
    textFieldWorkshopId.clear();
    textFieldWorkshopName.clear();
    textFieldWorkshopAddress.clear();
    radioButtonInternal.setSelected(false);
    textFieldEnterVinWorkshop.clear();

    showAlert("Workshop added successfully!", AlertType.INFORMATION);
}

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertType == Alert.AlertType.ERROR ? "Error" : "Information");
        alert.setHeaderText(null); 
        alert.setContentText(message);
        alert.showAndWait();
    }



@FXML
public void handleButtonDeleteWorkshopAction(ActionEvent event) {
    String workshopId = textFieldWorkshopId.getText();

    WorkShop workshop = fleetManager.findWorkShopById(workshopId);
    if (workshop != null) {
        fleetManager.removeWorkShop(workshop);
        updateWorkShopTableView();

         textFieldWorkshopId.clear();

        // Show confirmation alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Workshop deleted successfully!");
        alert.showAndWait();
    } else {
        // Show alert that the workshop wasn't found
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Workshop not found!");
        alert.showAndWait();
    }
}

@FXML

public void handleButtonUpdateWorkshopAction(ActionEvent event) {
    String workshopId = textFieldWorkshopId.getText();

    WorkShop workshop = fleetManager.findWorkShopById(workshopId);
    if (workshop != null) {
        String workshopName = textFieldWorkshopName.getText();
        String workshopAddress = textFieldWorkshopAddress.getText();
        boolean isInternal = radioButtonInternal.isSelected();

        WorkShop newWorkshop = new WorkShop(workshopId, workshopName, workshopAddress, isInternal);
        fleetManager.updateWorkshop(workshop, newWorkshop);
        updateWorkShopTableView();

        textFieldWorkshopId.clear();
        textFieldWorkshopName.clear();
        textFieldWorkshopAddress.clear();
        radioButtonInternal.setSelected(false);


        // Show confirmation alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Workshop updated successfully!");
        alert.showAndWait();
    } else {
        // Show alert that the workshop wasn't found
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Workshop not found!");
        alert.showAndWait();
    }
}

public void updateWorkShopTableView() {
    ObservableList<WorkShop> workshopData = fleetManager.getWorkshops(); 
    tableViewWorkshop.setItems(workshopData); 
    tableViewWorkshop.refresh(); 
}






}
