package se.lu.ics;
import java.net.URL;
import javafx.application.Application;
 import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
 import javafx.stage.Stage;
import se.lu.ics.controllers.MainViewController; 
import se.lu.ics.models.FleetManager;
import javafx.scene.layout.StackPane;






public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FleetManager fleetManager = new FleetManager();

        URL location = getClass().getResource("/fxml/MainView.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(location);

        StackPane root = fxmlLoader.load();

        MainViewController controller = fxmlLoader.getController();

        // Pass the FleetManager instance to the controller
        controller.setFleetManager(fleetManager);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Viking Express Fleet Manager");

        primaryStage.show();
    }
}
