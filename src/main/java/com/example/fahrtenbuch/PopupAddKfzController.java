package com.example.fahrtenbuch;

import com.example.fahrtenbuch.business.VehicleFacade;
import com.example.fahrtenbuch.entities.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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

                newKfzTextField.clear();
                newOdometer.clear();
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Konvertieren des Kilometerstands.", "Bitte gebem Sie eine Zahl ein.");

            }
        } else {

            showAlert(Alert.AlertType.ERROR, "Fehler", "Felder dürfen nicht leer sein.", "Bitte füllen Sie alle Felder aus.");
            return;
        }
    }

    @FXML
    public void closePopup(ActionEvent actionEvent) {

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
