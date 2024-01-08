package se.lu.ics.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import se.lu.ics.models.FleetManager;


public class MainViewController {
    @FXML private AnchorPane anchorPaneHomeTab;
    @FXML private ImageView imageViewVikingExpress;
    @FXML private StackPane stackPaneRoot;
    @FXML private Tab tabHome;
    @FXML private TabPane tabPane;    
    @FXML private TextArea textAreaHomeTab;
    @FXML private Tab tabVehicle;
    @FXML private Tab tabWorkshop;
    @FXML private Tab tabMaintenanceSchedule;
    @FXML private Tab tabServiceHistory;
    @FXML private Tab tabCalculations;
    @FXML private VehicleTabController vehicleTabController;
    @FXML private WorkshopTabController workshopTabController;
    @FXML private MaintenanceScheduleTabController maintenanceScheduleTabController;
    @FXML private ServiceHistoryTabController serviceHistoryTabController;
    @FXML private CalculationTabController calculationTabController;


    private FleetManager fleetManager;

    
        public void setFleetManager(FleetManager fleetManager) {
            this.fleetManager = fleetManager;
            vehicleTabController.setFleetManager(fleetManager);
            workshopTabController.setFleetManager(fleetManager);
            maintenanceScheduleTabController.setFleetManager(fleetManager);
            serviceHistoryTabController.setFleetManager(fleetManager);
            calculationTabController.setFleetManager(fleetManager);
            
    
}

}
        
        
       
      



