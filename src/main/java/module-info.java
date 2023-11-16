module com.example.fahrtenbuch {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens model;
    opens com.example.fahrtenbuch to javafx.fxml;
    exports com.example.fahrtenbuch;
}
