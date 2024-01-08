package se.lu.ics.controllers;

import java.text.NumberFormat;
import java.util.Locale;
import javafx.scene.control.ChoiceBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.lu.ics.models.FleetManager;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.WorkShop;



public class CalculationTabController {

     @FXML
    private AnchorPane anchorPaneCalculation;

    @FXML
    private Tab tabCalculation;

    @FXML
    private TextArea textAreaCalculation;

    @FXML
    private Button buttonCalculate;

    @FXML
    private ChoiceBox<String> choiceBoxSelectCalculation;

    @FXML
    private ChoiceBox<WorkShop> choiceBoxWorkshopCalculationTab;

    @FXML
    private Label labelSelectCalculation;

    @FXML
    private Label labelVinCalculationsTab;

    @FXML
    private Label labelWorkshopCalculationTab;

    @FXML
    private TextField textFieldEnterVinCalculationTab;

   
    private FleetManager fleetManager;

    public void setFleetManager(FleetManager fleetManager) {
        this.fleetManager = fleetManager;
        System.out.println("Calculation: FleetManager set");
        setupWorkshops();
    }

    

    @FXML
    public void initialize() {

         choiceBoxSelectCalculation.getItems().addAll("Total Cost for Vehicle", "Total Parts Replaced for Vehicle", "Total Cost for Fleet",
    "Most Expensive Maintenance Job", "Most Expensive Workshop", "Total Cost for Workshop",
    "Average Maintenance Cost");
choiceBoxSelectCalculation.getSelectionModel().selectFirst();

     
setupWorkshops();


}


private void setupWorkshops() {
if (this.fleetManager != null) {
    ObservableList<WorkShop> workshops = fleetManager.getWorkshops();
    choiceBoxWorkshopCalculationTab.setItems(workshops);
}
}


@FXML
public void handleButtonCalculateAction(ActionEvent event) {
    String selectedCalculation = choiceBoxSelectCalculation.getValue();
    String result = "";

    switch (selectedCalculation) {
        case "Total Cost for Vehicle":
            String vin = textFieldEnterVinCalculationTab.getText();
            Vehicle vehicle = fleetManager.findVehicleByVin(vin);
            if (vehicle != null) {
                result = fleetManager.getTotalCostForVehicleAsString(vehicle);
            } else {
                result = "Vehicle with VIN " + vin + " not found.";
            }
            break;
            case "Total Parts Replaced for Vehicle":
            vin = textFieldEnterVinCalculationTab.getText();
            vehicle = fleetManager.findVehicleByVin(vin);
            if (vehicle != null) {
                int totalPartsReplaced = fleetManager.getTotalReplacedPartsCountForVehicle(vehicle);
                result = "Total parts replaced for vehicle with VIN " + vin + ": " + totalPartsReplaced;
            } else {
                result = "Vehicle with VIN " + vin + " not found.";
            }
            break;
        case "Total Cost for Fleet":
            result = fleetManager.getTotalCostForFleetAsString();
            break;
        case "Most Expensive Maintenance Job":
            // No need to select anything specific, just calculate directly
            result = fleetManager.getMostExpensiveMaintenanceJobAsString();
            break;
        case "Most Expensive Workshop":
            // No need for specific workshop selection for this option
            result = fleetManager.getMostExpensiveWorkshopAsString();
            break;
        case "Total Cost for Workshop":
            WorkShop selectedWorkshop = choiceBoxWorkshopCalculationTab.getValue();
            if (selectedWorkshop != null) {
                double cost = fleetManager.calculateTotalCostForWorkshop(selectedWorkshop);
                NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                result = "Total Cost for Workshop: " + selectedWorkshop.getName() + ": " + formatter.format(cost);
            } else {
                result = "Please select a workshop.";
            }
            break;
        case "Average Maintenance Cost":
            result = fleetManager.calculateAverageMaintenanceCostAsString();
            break;
        default:
            result = "Please select a calculation type.";
            break;
    }

    textAreaCalculation.setText(result); // Display the result in the TextArea
}

}



