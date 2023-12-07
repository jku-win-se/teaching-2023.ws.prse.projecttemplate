package com.example.fahrtenbuch;

import com.example.fahrtenbuch.business.VehicleFacade;
import com.example.fahrtenbuch.entities.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopupAddKfzController {

    @FXML
    public TextField newKfzTextField;
    @FXML
    public TextField newOdometer;

    private VehicleFacade vehicleFacade;

    public PopupAddKfzController() {
        // Hier kannst du auch eine Dependency Injection verwenden,
        // um die VehicleFacade-Instanz zu erhalten.
        this.vehicleFacade = new VehicleFacade();
    }

    @FXML
    public void addNewKfz(ActionEvent actionEvent) {
        String licensePlate = newKfzTextField.getText();
        double odometer = Double.parseDouble(newOdometer.getText());

        // Erstelle ein neues Fahrzeugobjekt
        Vehicle newVehicle = new Vehicle();
        newVehicle.setLicensePlate(licensePlate);
        newVehicle.setOdometer(odometer);

        // Rufe persistVehicle auf, um das Fahrzeug in der Datenbank zu speichern
        vehicleFacade.persistVehicle(newVehicle);

        // Optional: Aktualisiere die Anzeige oder führe andere Aktionen durch
        // ...

        // Schließe das Popup
        closePopup(actionEvent);
    }

    @FXML
    public void closePopup(ActionEvent actionEvent) {
        // Schließe das Popup-Fenster
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

}
