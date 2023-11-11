module com.example.fahrtenbuch {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fahrtenbuch to javafx.fxml;
    exports com.example.fahrtenbuch;
}