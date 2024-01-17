module com.example.wealthwatch {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.wealthwatch to javafx.fxml;
    exports com.example.wealthwatch;
}