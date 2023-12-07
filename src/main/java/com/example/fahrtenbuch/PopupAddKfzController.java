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

        this.vehicleFacade = new VehicleFacade();
    }

    @FXML
    public void addNewKfz(ActionEvent actionEvent) {
        String licensePlate = newKfzTextField.getText();
        String odometerStr = newOdometer.getText();

        if (!licensePlate.isEmpty() && !odometerStr.isEmpty()) {
            try {

                double odometer = Double.parseDouble(odometerStr);

                Vehicle newVehicle = new Vehicle(licensePlate, odometer);

                vehicleFacade.persistVehicle(newVehicle);

                System.out.println("Fahrzeug erfolgreich hinzugefügt: " + newVehicle);

                newKfzTextField.clear();
                newOdometer.clear();
            } catch (NumberFormatException e) {

                System.out.println("Fehler beim Konvertieren des Kilometerstands.");
            }
        } else {

            System.out.println("KFZ-Kennzeichen und Kilometerstand dürfen nicht leer sein.");
        }
    }

    @FXML
    public void closePopup(ActionEvent actionEvent) {

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

}
