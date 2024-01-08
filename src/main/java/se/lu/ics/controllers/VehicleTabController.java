package se.lu.ics.controllers;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import se.lu.ics.models.FleetManager;
import se.lu.ics.models.LargeTruck;
import se.lu.ics.models.MediumTruck;
import se.lu.ics.models.Van;
import se.lu.ics.models.Vehicle;
public class VehicleTabController {

    @FXML
    private AnchorPane anchorPaneVehicleTab;
   

    @FXML
    private Tab tabVehicle;
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleBrand;
    @FXML
    private TableColumn<Vehicle, Double> tableColumnVehicleCargoCapacity;
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleCurrentLocation;
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleModel;
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleType; 
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleVin;
    @FXML
    private TableColumn<Vehicle, Integer> tableColumnVehicleYear;
    @FXML
    private TableView<Vehicle> tableViewVehicle;

    @FXML
    private AnchorPane anchorPaneVehicleTableView;

    @FXML
    private Button buttonAddVehicle;

    @FXML
    private Button buttonCountVehicles;

    @FXML
    private Button buttonDeleteVehicle;

    @FXML
    private Button buttonShowVin;

    @FXML
    private Button buttonSaveChanges;

    @FXML
    private Button buttonViewVehicles;

    @FXML
    private ChoiceBox<String> choiceBoxVehicleType;

    @FXML
    private Label labelBrand;

    @FXML
    private Label labelCargoCapacity;

    @FXML
    private Label labelCurrentLocation;

    @FXML
    private Label labelModel;

    @FXML
    private Label labelNewVehicleInformation;

    @FXML
    private Label labelRegisterNewVehicle;

    @FXML
    private Label labelUpdateVehicleInformation;

    @FXML
    private Label labelVehicleType;

    @FXML
    private Label labelYear;

    @FXML
    private ScrollPane scrollPaneVehicle;

    @FXML
    private TextField textFieldBrand;

    @FXML
    private TextField textFieldCargoCapacity;

    @FXML
    private TextField textFieldEnterVinVehicle;

    @FXML
    private TextField textFieldModel;

    @FXML
    private TextField textFieldYear;

    @FXML
    private TextField textfieldCurrentLocation;

    private FleetManager fleetManager;
      
    @FXML
    public void initialize() {

      
    

        setupVehicleTableViewColumns();
        initializeChoiceBox();

       
    }

  

    
   


    private void initializeChoiceBox() {
        choiceBoxVehicleType.getItems().addAll("LargeTruck", "MediumTruck", "Van");
        
        
    }


    public void setupVehicleTableViewColumns() {
        
        tableColumnVehicleType.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleType"));
        tableColumnVehicleBrand.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("brand"));
        tableColumnVehicleModel.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));
        tableColumnVehicleYear.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("year"));
        tableColumnVehicleVin.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleId"));
        tableColumnVehicleCurrentLocation.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("currentLocation"));
        tableColumnVehicleCargoCapacity.setCellValueFactory(new PropertyValueFactory<Vehicle, Double>("cargoCapacity"));
        
    }

    

    public void updateVehicleTableView() {
        
        tableViewVehicle.setItems(fleetManager.getVehicles());
        tableViewVehicle.refresh();
    }

    @FXML
    public void handleButtonAddVehicleAction(ActionEvent event) {
        System.out.println("Adding a vehicle...");
    
        String model = textFieldModel.getText();
        String brand = textFieldBrand.getText();
        int year = Integer.parseInt(textFieldYear.getText());
        String currentLocation = textfieldCurrentLocation.getText();
        int currentMileage = 0;
        LocalDate lastServiceDate = LocalDate.of(year, 1, 1);
        int lastServiceDistance = currentMileage;
        double cargoCapacity = Double.parseDouble(textFieldCargoCapacity.getText());
        

    
        // Corrected: Declare vehicleType once
        String vehicleType = choiceBoxVehicleType.getValue().toString();
    
        Vehicle vehicle = null;  // Make sure Vehicle is declared at the beginning of the switch
        switch (vehicleType) {
            case "LargeTruck":
                vehicle = new LargeTruck(model, brand, year, currentLocation, currentMileage, lastServiceDate, lastServiceDistance, cargoCapacity);
                break;
            case "MediumTruck":
                vehicle = new MediumTruck(model, brand, year, currentLocation, currentMileage, lastServiceDate, lastServiceDistance, cargoCapacity);
                break;
            case "Van":
                vehicle = new Van(model, brand, year, currentLocation, currentMileage, lastServiceDate, lastServiceDistance, cargoCapacity);
                break;
            default:
                System.out.println("Invalid vehicle type: " + vehicleType); // Debugging statement for invalid vehicle types
        }
    
        if (vehicle != null) {
            System.out.println("Adding vehicle to fleetManager...");
            fleetManager.addVehicle(vehicle);
            updateVehicleTableView();
            System.out.println("Vehicle added successfully!");
            // clear textfields
            textFieldModel.clear();
            textFieldBrand.clear();
            textFieldYear.clear();
            textfieldCurrentLocation.clear();
            textFieldCargoCapacity.clear();
        } else {
            System.out.println("Vehicle is null, not adding to fleetManager.");
        }
        // Alert
    
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vehicle added successfully!");
        alert.showAndWait();
    }
    
@FXML
public void handleButtonDeleteVehicleAction(ActionEvent event) {
    String vin = textFieldEnterVinVehicle.getText();

    Vehicle vehicle = fleetManager.findVehicleByVin(vin);
    if (vehicle != null) {
        fleetManager.removeVehicle(vehicle);
        updateVehicleTableView();
        textFieldEnterVinVehicle.clear();
    } else {
        
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vehicle not found!");
        alert.showAndWait();
    }

    // Alert

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText("Vehicle deleted successfully!");
    alert.showAndWait();
}

@FXML
public void handleButtonViewVehiclesAction (ActionEvent event) {
    updateVehicleTableView();

}

public void handleButtonSaveChangesAction(ActionEvent event) {
    String vin = textFieldEnterVinVehicle.getText();
    Vehicle oldVehicle = fleetManager.findVehicleByVin(vin);

    if (oldVehicle != null) {
        // Gather new vehicle details from the user input
        String model = textFieldModel.getText();
        String brand = textFieldBrand.getText();
        int year = Integer.parseInt(textFieldYear.getText());
        String currentLocation = textfieldCurrentLocation.getText();
        LocalDate lastServiceDate = LocalDate.now();  // Assuming this is correct
        double cargoCapacity = Double.parseDouble(textFieldCargoCapacity.getText());

        String vehicleType = choiceBoxVehicleType.getValue().toString();
        Vehicle newVehicle = null;

        // Create a new vehicle based on the selected type
        switch (vehicleType) {
            case "LargeTruck":
                newVehicle = new LargeTruck(model, brand, year, currentLocation, 0, lastServiceDate, 0, cargoCapacity);
                break;
            case "MediumTruck":
                newVehicle = new MediumTruck(model, brand, year, currentLocation, 0, lastServiceDate, 0, cargoCapacity);
                break;
            case "Van":
                newVehicle = new Van(model, brand, year, currentLocation, 0, lastServiceDate, 0, cargoCapacity);
                break;
            // Add a default case if necessary
        }

        if (newVehicle != null) {
            newVehicle.setVehicleId(oldVehicle.getVehicleId()); // Copy the VIN from the old vehicle
            fleetManager.updateVehicle(oldVehicle, newVehicle);
            updateVehicleTableView();

            textFieldBrand.clear();
            textFieldModel.clear();
            textFieldYear.clear();
            textfieldCurrentLocation.clear();
            textFieldCargoCapacity.clear();
            textFieldEnterVinVehicle.clear();

            showAlert("Information Dialog", "Vehicle updated successfully!");
        } else {
            showAlert("Error", "Failed to update vehicle.");
        }
    } else {
        showAlert("Error", "Vehicle not found.");
    }
}

private void showAlert(String title, String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}



public void handleButtonShowVinAction (ActionEvent event) {
    
}

public void handleButtonCountVehiclesAction(ActionEvent event) {
    int count = fleetManager.getVehicles().size();

    Alert alert = new Alert(Alert.AlertType.INFORMATION); // Create an alert of type INFORMATION
    alert.setTitle("Vehicle Count");
    alert.setHeaderText(null); // You can set a header text or leave it as null
    alert.setContentText("Total number of vehicles: " + count);

    alert.showAndWait(); // Display the alert and wait for it to be closed
}



public void setFleetManager(FleetManager fleetManager) {
        this.fleetManager = fleetManager;
        System.out.println("VehicleTabController: FleetManager set");
    }


}
