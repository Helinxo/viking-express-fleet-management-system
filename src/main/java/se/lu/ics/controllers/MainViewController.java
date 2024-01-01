package se.lu.ics.controllers;

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
    private Button buttonGenerateVin;

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
    private ChoiceBox<?> chioceBoxvehicleType;

    @FXML
    private ChoiceBox<?> choiceBoxPartTypes;

    @FXML
    private ChoiceBox<?> choiceBoxSelectCalculations;

    @FXML
    private ChoiceBox<?> choiceBoxServiceType;

    @FXML
    private ChoiceBox<?> choiceBoxVehicleType;

    @FXML
    private ChoiceBox<?> choiceBoxWorkshop;

    @FXML
    private ChoiceBox<?> choiceBoxWorkshopCalculationsTab;

    @FXML
    private DatePicker datePickerDate;

    @FXML
    private ImageView imageViewVikingExpress;

    @FXML
    private Label labelBrand;

    @FXML
    private Label labelCargoCapacity;

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
    private TableColumn<?, ?> tableColumnDateMaintenanceScheduleTab;

    @FXML
    private TableColumn<?, ?> tableColumnDateServiceHistoryTab;

    @FXML
    private TableColumn<?, ?> tableColumnLastServiceDateMaintenanceScheduleTab;

    @FXML
    private TableColumn<?, ?> tableColumnLastServiceDateServiceHistoryTab;

    @FXML
    private TableColumn<?, ?> tableColumnLastServiceDistanceMaintenanceScheduleTab;

    @FXML
    private TableColumn<?, ?> tableColumnPartCostServiceHistoryTab;

    @FXML
    private TableColumn<?, ?> tableColumnPartTypeMaintenanceScheduleTab;

    @FXML
    private TableColumn<?, ?> tableColumnPartTypeServiceHistoryTab;

    @FXML
    private TableColumn<?, ?> tableColumnReplacedPartsServiceHistoryTab;

    @FXML
    private TableColumn<?, ?> tableColumnServiceCostServiceHistoryTab;

    @FXML
    private TableColumn<?, ?> tableColumnServiceHistoryDescription;

    @FXML
    private TableColumn<?, ?> tableColumnServiceTypeMaintenanceScheduleTab;

    @FXML
    private TableColumn<?, ?> tableColumnServiceTypeServiceHistoryTab;

    @FXML
    private TableColumn<?, ?> tableColumnVehicleBrand;

    @FXML
    private TableColumn<?, ?> tableColumnVehicleCargoCapacity;

    @FXML
    private TableColumn<?, ?> tableColumnVehicleModel;

    @FXML
    private TableColumn<?, ?> tableColumnVehicleType;

    @FXML
    private TableColumn<?, ?> tableColumnVehicleTypeMaintenanceScheduleTab;

    @FXML
    private TableColumn<?, ?> tableColumnVehicleTypeServiceHistoryTab;

    @FXML
    private TableColumn<?, ?> tableColumnVehicleVin;

    @FXML
    private TableColumn<?, ?> tableColumnVehicleYear;

    @FXML
    private TableColumn<?, ?> tableColumnVinMaintenanceScheduleTab;

    @FXML
    private TableColumn<?, ?> tableColumnVinServiceHistoryTab;

    @FXML
    private TableColumn<?, ?> tableColumnWorkshopAddress;

    @FXML
    private TableColumn<?, ?> tableColumnWorkshopId;

    @FXML
    private TableColumn<?, ?> tableColumnWorkshopMaintenanceScheduleTab;

    @FXML
    private TableColumn<?, ?> tableColumnWorkshopName;

    @FXML
    private TableColumn<?, ?> tableColumnWorkshopServiceHistoryTab;

    @FXML
    private TableColumn<?, ?> tableColumnWorkshopType;

    @FXML
    private TableColumn<?, ?> tableColumnWorkshopVin;

    @FXML
    private TableView<?> tableViewServiceHistory;

    @FXML
    private TableView<?> tableViewVehicle;

    @FXML
    private TableView<?> tableViewWorkshop;

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
    void handleButtonAddServiceHistoryAction(ActionEvent event) {

    }

    @FXML
    void handleButtonAddVehicleAction(ActionEvent event) {

    }

    @FXML
    void handleButtonAddWorkshopAction(ActionEvent event) {

    }

    @FXML
    void handleButtonCalculateAction(ActionEvent event) {

    }

    @FXML
    void handleButtonConfirmAction(ActionEvent event) {

    }

    @FXML
    void handleButtonCountVehiclesAction(ActionEvent event) {

    }

    @FXML
    void handleButtonDeleteServiceHistory(ActionEvent event) {

    }

    @FXML
    void handleButtonDeleteVehicleAction(ActionEvent event) {

    }

    @FXML
    void handleButtonDeleteWorkshopAction(ActionEvent event) {

    }

    @FXML
    void handleButtonGenerateVinAction(ActionEvent event) {

    }

    @FXML
    void handleButtonSaveChangesAction(ActionEvent event) {

    }

    @FXML
    void handleButtonUpdateServiceHistoryAction(ActionEvent event) {

    }

    @FXML
    void handleButtonUpdateWorkshopAction(ActionEvent event) {

    }

    @FXML
    void handleButtonViewServiceHistoryAction(ActionEvent event) {

    }

    @FXML
    void handleButtonViewVehiclesAction(ActionEvent event) {

    }

}

