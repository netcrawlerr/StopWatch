module com.example.stopwatch {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.stopwatch to javafx.fxml;
    exports com.example.stopwatch;
}