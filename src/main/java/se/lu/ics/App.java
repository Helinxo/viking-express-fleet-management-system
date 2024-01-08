package se.lu.ics;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
 import javafx.stage.Stage;
import se.lu.ics.controllers.MainViewController;
import se.lu.ics.models.FleetManager;
import javafx.scene.layout.StackPane;

    public class App extends Application {
        public static void main(String[] args) { launch();}
        @Override
        public  void start(Stage primaryStage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
    StackPane root = fxmlLoader.load();
    MainViewController controller = fxmlLoader.getController();
  



    FleetManager fleetManager = new FleetManager();
    controller.setFleetManager(fleetManager);

    primaryStage.setScene(new Scene(root));
    primaryStage.show();
}
 }
        
    






