module com.example.fahrtenbuch {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires junit;

    opens model;
    opens com.example.fahrtenbuch to javafx.fxml;
    exports com.example.fahrtenbuch;
    exports com.example.fahrtenbuch.business;
}
