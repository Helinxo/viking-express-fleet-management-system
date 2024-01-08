package se.lu.ics.controllers;

import java.text.NumberFormat;
import java.util.Locale;
import javafx.scene.control.ChoiceBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        choiceBoxSelectCalculation.getItems().addAll(
            "Total Cost for Vehicle",
            "Total Parts Replaced for Vehicle",
            "Total Cost for Fleet",
            "Most Expensive Maintenance Job",
            "Most Expensive Workshop",
            "Total Cost for Workshop",
            "Average Maintenance Cost"
        );
        choiceBoxSelectCalculation.getSelectionModel().selectFirst();
        setupWorkshops();

        choiceBoxSelectCalculation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateInputFieldsBasedOnSelection(newValue);
        });

        updateInputFieldsBasedOnSelection(choiceBoxSelectCalculation.getValue());
    }

    private void updateInputFieldsBasedOnSelection(String selection) {
        boolean requiresVin = "Total Cost for Vehicle".equals(selection) || "Total Parts Replaced for Vehicle".equals(selection);
        boolean requiresWorkshop = "Most Expensive Workshop".equals(selection) || "Total Cost for Workshop".equals(selection);

        textFieldEnterVinCalculationTab.setDisable(!requiresVin);
        choiceBoxWorkshopCalculationTab.setDisable(!requiresWorkshop);

        if (!requiresVin) {
            textFieldEnterVinCalculationTab.clear();
        }
        if (!requiresWorkshop) {
            choiceBoxWorkshopCalculationTab.getSelectionModel().clearSelection();
        }
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
    String vin;
    Vehicle vehicle;

    switch (selectedCalculation) {
        case "Total Cost for Vehicle":
        case "Total Parts Replaced for Vehicle":
            vin = textFieldEnterVinCalculationTab.getText();
            if (vin.isEmpty()) {
                showAlert("Error", "Please enter a VIN.");
                return;
            }
            vehicle = fleetManager.findVehicleByVin(vin);
            if (vehicle == null) {
                showAlert("Error", "Vehicle with VIN " + vin + " not found.");
                return;
            }
            if ("Total Cost for Vehicle".equals(selectedCalculation)) {
                result = fleetManager.getTotalCostForVehicleAsString(vehicle);
            } else {
                int totalPartsReplaced = fleetManager.getTotalReplacedPartsCountForVehicle(vehicle);
                result = "Total parts replaced for vehicle with VIN " + vin + ": " + totalPartsReplaced;
            }
            break;

        case "Total Cost for Fleet":
            result = fleetManager.getTotalCostForFleetAsString();
            break;

        case "Most Expensive Maintenance Job":
            result = fleetManager.getMostExpensiveMaintenanceJobAsString();
            break;

        case "Most Expensive Workshop":
            if (choiceBoxWorkshopCalculationTab.getSelectionModel().isEmpty()) {
                showAlert("Error", "Please select a workshop.");
                return;
            }
            result = fleetManager.getMostExpensiveWorkshopAsString();
            break;

        case "Total Cost for Workshop":
            WorkShop selectedWorkshop = choiceBoxWorkshopCalculationTab.getValue();
            if (selectedWorkshop == null) {
                showAlert("Error", "Please select a workshop.");
                return;
            }
            double cost = fleetManager.calculateTotalCostForWorkshop(selectedWorkshop);
            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
            result = "Total Cost for Workshop: " + selectedWorkshop.getName() + ": " + formatter.format(cost);
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

private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}

}



