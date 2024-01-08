module javafx.git.group.is {
    exports se.lu.ics;
    requires javafx.fxml;
    requires javafx.controls;
    requires transitive javafx.graphics;

    // Open the 'se.lu.ics.controllers' package to javafx.fxml
    opens se.lu.ics.controllers to javafx.fxml;

    // Add this line to open 'se.lu.ics.models' to javafx.base
    opens se.lu.ics.models to javafx.base;
}
