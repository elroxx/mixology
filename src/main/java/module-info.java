module com.example.mixologyinventory {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mixologyinventory to javafx.fxml;
    exports com.example.mixologyinventory;
}