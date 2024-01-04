package se.lu.ics.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import se.lu.ics.models.Service;
import se.lu.ics.models.ServiceHistory;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.WorkShop;
import se.lu.ics.models.MaintenanceSchedule;
import se.lu.ics.models.FleetManager;
import se.lu.ics.models.LargeTruck;
import se.lu.ics.models.MediumTruck;
import se.lu.ics.models.Van;
import se.lu.ics.models.Service.ServiceType;
import se.lu.ics.models.Service.PartType;


// Alerts

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;



public class MainViewController {

    
    @FXML
    private AnchorPane anchorPaneCalculations;

    @FXML
    private AnchorPane anchorPaneHomeTab;

    @FXML
    private AnchorPane anchorPaneMaintenanceScheduleTab;

    @FXML
    private AnchorPane anchorPaneServiceHistoryTab;

    @FXML
    private AnchorPane anchorPaneVehicleTab;

    @FXML
    private AnchorPane anchorPaneVehicleTableView;

    @FXML
    private AnchorPane anchorPaneWorkshop;

    @FXML
    private Button buttonAddServiceHistory;

    @FXML
    private Button buttonAddVehicle;

    @FXML
    private Button buttonAddWorkshop;

    @FXML
    private Button buttonCalculate;

    @FXML
    private Button buttonConfirm;

    @FXML
    private Button buttonCountVehicles;

    @FXML
    private Button buttonDeleteServiceHistory;

    @FXML
    private Button buttonDeleteVehicle;

    @FXML
    private Button buttonDeleteWorkshop;

    @FXML
    private Button buttonShowVin;

    @FXML
    private Button buttonSaveChanges;

    @FXML
    private Button buttonUpdateServiceHistory;

    @FXML
    private Button buttonUpdateWorkshop;

    @FXML
    private Button buttonViewServiceHistory;

    @FXML
    private Button buttonViewVehicles;

    @FXML
    private ChoiceBox<String> chioceBoxvehicleType;

    @FXML
    private ChoiceBox<PartType> choiceBoxPartTypes;

    @FXML
    private ChoiceBox<String> choiceBoxSelectCalculations;

    @FXML
    private ChoiceBox<ServiceType> choiceBoxServiceType;

    @FXML
    private ChoiceBox<String> choiceBoxVehicleType;

    @FXML
    private ChoiceBox<WorkShop> choiceBoxWorkshop;

    @FXML
    private ChoiceBox<String> choiceBoxWorkshopCalculationsTab;

    @FXML
    private DatePicker datePickerDate;

    @FXML
    private ImageView imageViewVikingExpress;

    @FXML
    private Label labelBrand;

    @FXML
    private Label labelCargoCapacity;

    @FXML
    private Label labelCurrentLocation;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelLastServiceDate;

    @FXML
    private Label labelLastServiceDistance;

    @FXML
    private Label labelModel;

    @FXML
    private Label labelNewVehicleInformation;

    @FXML
    private Label labelPartTypes;

    @FXML
    private Label labelRegisterNewVehicle;

    @FXML
    private Label labelSelectCalculations;

    @FXML
    private Label labelServiceType;

    @FXML
    private Label labelUpdateVehicleInformation;

    @FXML
    private Label labelVehicleType;

    @FXML
    private Label labelVehicleTypeMaintenanceScheduleTab;

    @FXML
    private Label labelVinCalculationsTab;

    @FXML
    private Label labelVinMaintenanceSchedule;

    @FXML
    private Label labelVinServiceHistory;

    @FXML
    private Label labelVinWorkshop;

    @FXML
    private Label labelWorkshop;

    @FXML
    private Label labelWorkshopAddress;

    @FXML
    private Label labelWorkshopCalculationsTab;

    @FXML
    private Label labelWorkshopId;

    @FXML
    private Label labelWorkshopName;

    @FXML
    private Label labelWorkshopType;

    @FXML
    private Label labelYear;

    @FXML
    private RadioButton radioButtonExternal;

    @FXML
    private RadioButton radioButtonInternal;

    @FXML
    private ScrollPane scrollPaneVehicle;

    @FXML
    private StackPane stackPaneRoot;

    @FXML
    private Tab tabCalculations;

    @FXML
    private Tab tabHome;

    @FXML
    private Tab tabMaintenanceSchedule;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabServiceHistory;

    @FXML
    private Tab tabVehicle;

    @FXML
    private Tab tabWorkshop;

    @FXML
    private TableColumn<Vehicle, LocalDate> tableColumnDateMaintenanceScheduleTab; // If it refers to Vehicle's maintenance schedule date
    @FXML
    private TableColumn<ServiceHistory, LocalDate> tableColumnDateServiceHistoryTab; // Assuming ServiceHistory has a date field
    
    @FXML
    private TableColumn<Vehicle, LocalDate> tableColumnLastServiceDateMaintenanceScheduleTab;
    @FXML
    private TableColumn<ServiceHistory, LocalDate> tableColumnLastServiceDateServiceHistoryTab;
    
    @FXML
    private TableColumn<Vehicle, Integer> tableColumnLastServiceDistanceMaintenanceScheduleTab;
    
    // Assuming ServiceHistory has parts and costs attributes
    @FXML
    private TableColumn<ServiceHistory, Double> tableColumnPartCostServiceHistoryTab;
    @FXML
    private TableColumn<ServiceHistory, String> tableColumnPartTypeMaintenanceScheduleTab;
    @FXML
    private TableColumn<ServiceHistory, String> tableColumnPartTypeServiceHistoryTab;
    @FXML
    private TableColumn<ServiceHistory, String> tableColumnReplacedPartsServiceHistoryTab;
    
    @FXML
    private TableColumn<ServiceHistory, Double> tableColumnServiceCostServiceHistoryTab;
    @FXML
    private TableColumn<ServiceHistory, String> tableColumnServiceHistoryDescription;
    
    @FXML
    private TableColumn<MaintenanceSchedule, String> tableColumnServiceTypeMaintenanceScheduleTab;
    @FXML
    private TableColumn<ServiceHistory, String> tableColumnServiceTypeServiceHistoryTab;
    
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleBrand;
    @FXML
    private TableColumn<Vehicle, Double> tableColumnVehicleCargoCapacity;
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleCurrentLocation;
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleModel;
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleType; // Assuming there's a type field
    @FXML
    private TableColumn<MaintenanceSchedule, String> tableColumnVehicleTypeMaintenanceScheduleTab; // Assuming MaintenanceSchedule has a vehicle type
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleTypeServiceHistoryTab; // Assuming ServiceHistory has a vehicle type
    
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleVin;
    @FXML
    private TableColumn<Vehicle, Integer> tableColumnVehicleYear;
    @FXML
    private TableColumn<MaintenanceSchedule, String> tableColumnVinMaintenanceScheduleTab; // If MaintenanceSchedule has a VIN
    @FXML
    private TableColumn<Service, String> tableColumnVinServiceHistoryTab; // If ServiceHistory has a VIN
    @FXML
    private TableColumn<WorkShop, String> tableColumnWorkshopAddress;
    @FXML
    private TableColumn<WorkShop, String> tableColumnWorkshopId;
    @FXML
    private TableColumn<WorkShop, String> tableColumnWorkshopMaintenanceScheduleTab; // If this is for displaying Workshop in MaintenanceSchedule
    @FXML
    private TableColumn<WorkShop, String> tableColumnWorkshopName;
    @FXML
    private TableColumn<Service, String> tableColumnWorkshopServiceHistoryTab; // If this is for displaying Workshop in ServiceHistory
    @FXML
    private TableColumn<WorkShop, String> tableColumnWorkshopType; // Assuming Workshop has a type
    @FXML
    private TableColumn<WorkShop, String> tableColumnWorkshopVin; // Assuming Workshop has a VIN

    @FXML
    private TableView<Service> tableViewServiceHistory;
    @FXML
    private TableView<Vehicle> tableViewVehicle;

    @FXML
    private TableView<WorkShop> tableViewWorkshop;

    @FXML
    private TextArea textAreaCalculations;

    @FXML
    private TextArea textAreaHomeTab;

    @FXML
    private TextField textFieldBrand;

    @FXML
    private TextField textFieldCargoCapacity;

    @FXML
    private TextField textFieldEnterVinCalculationsTab;

    @FXML
    private TextField textFieldEnterVinMaintenanceSchedule;

    @FXML
    private TextField textFieldEnterVinServiceHistory;

    @FXML
    private TextField textFieldEnterVinVehicle;

    @FXML
    private TextField textFieldEnterVinWorkshop;

    @FXML
    private TextField textFieldLastServiceDate;

    @FXML
    private TextField textFieldLastServiceDistance;

    @FXML
    private TextField textFieldModel;

    @FXML
    private TextField textFieldWorkshopAddress;

    @FXML
    private TextField textFieldWorkshopId;

    @FXML
    private TextField textFieldWorkshopName;

    @FXML
    private TextField textFieldYear;

    @FXML
    private TextField textfieldCurrentLocation;

   @FXML
    private FleetManager fleetManager;

    public void setFleetManager(FleetManager fleetManager) {
        this.fleetManager = fleetManager;
      
    }
    @FXML
    public void initialize() {

        
   
    initializeChoiceBoxes();
    setupVehicleTableViewColumns();
}

    

    private void initializeChoiceBoxes() {
        choiceBoxVehicleType.getItems().addAll("LargeTruck", "MediumTruck", "Van");
        choiceBoxSelectCalculations.getItems().addAll(
            "Total Cost for Vehicle", 
            "Total Cost for Fleet", 
            "Most Expensive Maintenance Job", 
            "Most Expensive Workshop"
        );
        
        if (fleetManager != null) {
            ObservableList<WorkShop> workshops = fleetManager.getWorkshops();
            if (workshops != null) {
                choiceBoxWorkshop.getItems().addAll(workshops);
            }
        } else {
            // Handle the case when fleetManager is null, perhaps by displaying an error message.
        }
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

    private void updateVehicleTableView() {
        
        tableViewVehicle.setItems(fleetManager.getVehicles());
        tableViewVehicle.refresh();
    }
    



    


    

    

    // Vehicle Tab
@FXML
public void handleButtonAddVehicleAction(ActionEvent event) {
    String model = textFieldModel.getText();
    String brand = textFieldBrand.getText();
    int year = Integer.parseInt(textFieldYear.getText());
    String currentLocation = textfieldCurrentLocation.getText();
    int currentMileage = 0;
    LocalDate lastServiceDate = LocalDate.now();
    int lastServiceDistance = 0;
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
        // You may want to add a default case to handle unexpected values
    }

    if (vehicle != null) {
       
        fleetManager.addVehicle(vehicle);
        updateVehicleTableView();
        // clear textfields
        textFieldModel.clear();
        textFieldBrand.clear();
        textFieldYear.clear();
        textfieldCurrentLocation.clear();
        textFieldCargoCapacity.clear();
    } else {
        
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
    } else {
        // Handle the case where no vehicle is found with the given VIN
        // For example, show an alert to the user
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

@FXML

public void handleButtonSaveChangesAction(ActionEvent event) {
    String vin = textFieldEnterVinVehicle.getText();

    Vehicle oldVehicle = fleetManager.findVehicleByVin(vin);
    if (oldVehicle != null) {
        String model = textFieldModel.getText();
        String brand = textFieldBrand.getText();
        int year = Integer.parseInt(textFieldYear.getText());
        String currentLocation = textfieldCurrentLocation.getText();
        LocalDate lastServiceDate = LocalDate.now();
        double cargoCapacity = Double.parseDouble(textFieldCargoCapacity.getText());

        String vehicleType = choiceBoxVehicleType.getValue().toString();
        Vehicle newVehicle = null; // Declare a new vehicle variable

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
            fleetManager.updateVehicle(oldVehicle, newVehicle);
            updateVehicleTableView();
        } else {
            // Handle the case where newVehicle is null, possibly


    // Alert

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText("Vehicle updated successfully!");
    alert.showAndWait();
}
}
}

@FXML

public void handleButtonShowVinAction (ActionEvent event) {
    String vin = textFieldEnterVinVehicle.getText();

    Vehicle vehicle = fleetManager.findVehicleByVin(vin);
    if (vehicle != null) {
        labelVinServiceHistory.setText(vin);
        labelVinMaintenanceSchedule.setText(vin);
        labelVinCalculationsTab.setText(vin);
        labelVinWorkshop.setText(vin);
    } else {
        // Handle the case where no vehicle is found with the given VIN
        // For example, show an alert to the user
    }
   
}

@FXML
public void handleButtonCountVehiclesAction (ActionEvent event) {
    int count = fleetManager.getVehicles().size();
    textFieldEnterVinVehicle.setText(Integer.toString(count));
}

// Service History Tab

// Workshop Tab



@FXML
public void handleButtonAddWorkshopAction(ActionEvent event) {
    try {
        String workshopId = textFieldWorkshopId.getText().trim();
        String workshopName = textFieldWorkshopName.getText().trim();
        String workshopAddress = textFieldWorkshopAddress.getText().trim();
        
        // Validate the input data
        if (workshopId.isEmpty() || workshopName.isEmpty() || workshopAddress.isEmpty()) {
            // Show error message to the user
            return;
        }

        // Handle potential null value from the choice box
        Object choiceBoxValue = choiceBoxWorkshop.getValue();
        if (choiceBoxValue == null) {
            // Show error message to the user
            return;
        }
        
        String workshopTypeString = choiceBoxValue.toString();
        boolean isInternal = "Internal".equals(workshopTypeString); // Assuming "Internal" represents an internal workshop

        // Check for duplicate workshop ID
        if (fleetManager.containsWorkshop(workshopId)) {
            // Show error message about duplicate ID
            return;
        }

        WorkShop workshop = new WorkShop(workshopId, workshopName, workshopAddress, isInternal);
        fleetManager.addWorkShop(workshop);
        updateWorkShopTableView();
        // Optionally, show a success message to the user
    } catch (Exception e) {
        // Handle exceptions and show error message to the user
    }
}


@FXML
public void handleButtonDeleteWorkshopAction(ActionEvent event) {
    String workshopId = textFieldWorkshopId.getText();

    WorkShop workshop = fleetManager.findWorkShopById(workshopId);
    if (workshop != null) {
        fleetManager.removeWorkShop(workshop);
        updateWorkShopTableView();

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

// Maintenance Schedule Tab

@FXML

public void handleButtonConfirmAction (ActionEvent event) {
    String vin = textFieldEnterVinMaintenanceSchedule.getText();

    Vehicle vehicle = fleetManager.findVehicleByVin(vin);
    if (vehicle != null) {
        String vehicleType = vehicle.getClass().getSimpleName();
        labelVehicleTypeMaintenanceScheduleTab.setText(vehicleType);
    } else {
        // Handle the case where no vehicle is found with the given VIN
        // For example, show an alert to the user
    }
}

@FXML

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

// Service History Tab

@FXML

public void handleButtonAddServiceHistoryAction(ActionEvent event) {
    String vin = textFieldEnterVinServiceHistory.getText();

    Vehicle vehicle = fleetManager.findVehicleByVin(vin);
    if (vehicle != null) {
        ServiceType serviceType = choiceBoxServiceType.getValue();
        String description = textAreaCalculations.getText();
        int mileage = Integer.parseInt(textFieldLastServiceDistance.getText());
        LocalDate date = datePickerDate.getValue();
        PartType partType = choiceBoxPartTypes.getValue();
        WorkShop workShop = choiceBoxWorkshop.getValue();

        Service service = new Service(serviceType, description, mileage, date, partType, vehicle, workShop);
        vehicle.getServiceHistory().addService(service);
        updateServiceHistoryTableView();
    } else {
        // Handle the case where no vehicle is found with the given VIN
        // For example, show an alert to the user
    }

    // Alert

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText("Service added successfully!");
    alert.showAndWait();
}



public void handleButtonDeleteServiceHistory (ActionEvent event) {
    String vin = textFieldEnterVinServiceHistory.getText();

    Vehicle vehicle = fleetManager.findVehicleByVin(vin);
    if (vehicle != null) {
        ServiceType serviceType = choiceBoxServiceType.getValue();
        String description = textAreaCalculations.getText();
        int mileage = Integer.parseInt(textFieldLastServiceDistance.getText());
        LocalDate date = datePickerDate.getValue();
        PartType partType = choiceBoxPartTypes.getValue();
        WorkShop workShop = choiceBoxWorkshop.getValue();

        Service service = new Service(serviceType, description, mileage, date, partType, vehicle, workShop);
        vehicle.getServiceHistory().addService(service);
        updateServiceHistoryTableView();
    } else {
        // Handle the case where no vehicle is found with the given VIN
        // For example, show an alert to the user
    }

    // Alert

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText("Service added successfully!");
    alert.showAndWait();
}
@FXML

public void handleButtonUpdateServiceHistoryAction(ActionEvent event) {
    String vin = textFieldEnterVinServiceHistory.getText();

    Vehicle vehicle = fleetManager.findVehicleByVin(vin);
    if (vehicle != null) {
        ServiceHistory serviceHistory = vehicle.getServiceHistory();
        Service oldService = tableViewServiceHistory.getSelectionModel().getSelectedItem();
        if (oldService != null) {
            ServiceType serviceType = choiceBoxServiceType.getValue();
            String description = textAreaCalculations.getText();
            int mileage = Integer.parseInt(textFieldLastServiceDistance.getText());
            LocalDate date = datePickerDate.getValue();
            PartType partType = choiceBoxPartTypes.getValue();
            WorkShop workShop = choiceBoxWorkshop.getValue();

            Service newService = new Service(serviceType, description, mileage, date, partType, vehicle, workShop);
            serviceHistory.updateService(oldService, newService);
            updateServiceHistoryTableView();
        } else {
            // Handle the case where no service is selected
            // For example, show an alert to the user
        }
    } else {
        // Handle the case where no vehicle is found with the given VIN
        // For example, show an alert to the user
    }

    // Alert

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText("Service updated successfully!");
    alert.showAndWait();
}

@FXML

public void handleButtonViewServiceHistoryAction(ActionEvent event) {
    String vin = textFieldEnterVinServiceHistory.getText();

    Vehicle vehicle = fleetManager.findVehicleByVin(vin);
    if (vehicle != null) {
        updateServiceHistoryTableView();
    } else {
        // Handle the case where no vehicle is found with the given VIN
        // For example, show an alert to the user
    }
}

@FXML

public void handleButtonShowVinServiceHistoryAction(ActionEvent event) {
    String vin = textFieldEnterVinServiceHistory.getText();

    Vehicle vehicle = fleetManager.findVehicleByVin(vin);
    if (vehicle != null) {
        labelVinServiceHistory.setText(vin);
    } else {
        // Handle the case where no vehicle is found with the given VIN
        // For example, show an alert to the user
    }
}

// Calculation Tab

@FXML
public void handleButtonCalculateAction(ActionEvent event) {
    String selectedCalculation = choiceBoxSelectCalculations.getValue();
    WorkShop selectedWorkshop = choiceBoxWorkshop.getValue();
    String vin = textFieldEnterVinCalculationsTab.getText(); // This might be used in multiple cases
    String result = "";

    switch (selectedCalculation) {
        case "Total Cost for Vehicle":
            Vehicle vehicle = fleetManager.findVehicleByVin(vin);
            if (vehicle != null) {
                result = fleetManager.getTotalCostForVehicleAsString(vehicle); // Ensure this method returns a String
            } else {
                result = "Vehicle with VIN " + vin + " not found.";
            }
            break;
        case "Total Cost for Fleet":
            result = fleetManager.getTotalCostForFleetAsString(); // Ensure this method returns a String
            break;
        case "Most Expensive Maintenance Job":
            result = fleetManager.getMostExpensiveMaintenanceJobAsString(); // Ensure this method returns a String
            break;
        case "Most Expensive Workshop":
            if (selectedWorkshop != null) {
                result = fleetManager.getMostExpensiveWorkshopAsString(); // Ensure this method returns a String
            } else {
                result = "Please select a workshop.";
            }
            break;
        case "Total Cost for Workshop":
            if (selectedWorkshop != null) {
                double cost = fleetManager.calculateTotalCostForWorkshop(selectedWorkshop); // This should return a double
                NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                result = "Total Cost for Workshop: " + selectedWorkshop.getName() + ": " + formatter.format(cost);
            } else {
                result = "Please select a workshop.";
            }
            break;
        case "Average Maintenance Cost":
            result = fleetManager.calculateAverageMaintenanceCostAsString(); // Ensure this method returns a String
            break;
        // ... additional cases if there are more calculations ...
        default:
            result = "Please select a calculation type.";
            break;
    }

    textAreaCalculations.setText(result); // Display the result in the TextArea
}
































// Tabelview for Service History

@FXML

public void updateServiceHistoryTableView() {
    String vin = textFieldEnterVinServiceHistory.getText();

    Vehicle vehicle = fleetManager.findVehicleByVin(vin);
    if (vehicle != null) {
        ObservableList<Service> serviceData = vehicle.getServiceHistory().getServices();
        tableViewServiceHistory.setItems(serviceData);
        tableViewServiceHistory.refresh();
    } else {
        // Handle the case where no vehicle is found with the given VIN
        // For example, show an alert to the user
    }
}

// Tabelview for Workshop

public void updateWorkShopTableView() {
    ObservableList<WorkShop> workshopData = fleetManager.getWorkshops(); // Assuming fleetManager.getWorkshops() returns an ObservableList
    tableViewWorkshop.setItems(workshopData); // Assuming workshopTableView is your TableView
    tableViewWorkshop.refresh(); // Refresh the view to display the latest data
}


// Tabelview for Vehicle





}