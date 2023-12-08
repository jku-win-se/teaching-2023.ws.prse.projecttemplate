package com.example.fahrtenbuch;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.fahrtenbuch.business.DatabaseConnection;
import com.example.fahrtenbuch.business.DriveFacade;
import com.example.fahrtenbuch.business.VehicleFacade;
import com.example.fahrtenbuch.entities.Drive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PageController{
    @FXML
    public TextField kfzTF;
    @FXML
    public TextField vonDatumTF;

    @FXML
    public TextField bisDatumTF;
    @FXML
    public ComboBox<String> repeatDrive;

    public Button btnStart;
    public ImageView logoIcon;
    public Button btnNewRide;

    public Button btnOverview;

    public Button btnCreateDrive;


    private DatabaseConnection databaseConnection;
    private Alert alert;
    private ObservableList<Drive> fahrtListe = FXCollections.observableArrayList();

    List<Drive> drives = new ArrayList<>();
    private DriveFacade driveFacade;

    private VehicleFacade vehicleFacade;

    public PageController() {
        databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Button-Klick");
        alert.setHeaderText(null);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14;");
        alert.setDialogPane(dialogPane);

        driveFacade = new DriveFacade();
        vehicleFacade =new VehicleFacade();
    }


    @FXML
    private void fahrtenbucherAction(ActionEvent event) throws IOException {
        Drive drive = new Drive(1, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()),Time.valueOf(LocalTime.now()), 3, 3.0);
        List<Drive> drives = driveFacade.getAllDrives();
        drives.add(drive);

        fahrtListe = FXCollections.observableArrayList(driveFacade.getAllDrives());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FahrtenbucherPage.fxml"));
        Parent overviewPage = loader.load();

        FahrtenbucherController fahrtenbucherController = loader.getController();
        fahrtenbucherController.setTableLogbook(fahrtListe);

        Scene scene = new Scene(overviewPage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();



    }

    @FXML
    void newFahrtAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Index.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBtnOverview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Overview.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBtnDataAction (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataAction.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void handleBtnCreateDrive(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("FahrtenbucherPage.fxml"));
        Parent root = loader.load();

        FahrtenbucherController fahrtenbucherController = loader.getController();
        fahrtenbucherController.setTableLogbook(fahrtListe);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addDrive(ActionEvent event) throws IOException {

        String licensePlate = kfzTF.getText();
        String startDateString = vonDatumTF.getText();
        String endDateString = bisDatumTF.getText();

        if (licensePlate.isEmpty() || startDateString.isEmpty() || endDateString.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte füllen Sie alle Felder aus.");
            return;
        }

        try {
            Date startDate = Date.valueOf(startDateString);
            Date endDate = Date.valueOf(endDateString);

            if (startDate.after(endDate)) {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Das Startdatum darf nicht größer als das Enddatum sein.");
                return;
            }

            Integer vehicleId = vehicleFacade.getVehicleIdByLicensePlate(licensePlate);

            if (vehicleId == null) {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Das Fahrzeug mit dem Kennzeichen existiert nicht.");
                return;
            }

            int interval = 0;
            String selectedInterval = repeatDrive.getValue();
            switch (selectedInterval) {
                case "täglich":
                    interval = 1;
                    break;
                case "wöchentlich":
                    interval = 7;
                    break;
                case "alle 2 Wochen":
                    interval = 14;
                    break;
                default:
                    showAlert(Alert.AlertType.ERROR, "Fehler", "Ungültiges Intervall ausgewählt.");
                    return;
            }

            driveFacade.persistRecurringDrive(vehicleId, startDate, endDate, interval);

            showAlert(Alert.AlertType.INFORMATION, "Erfolg", "Wiederholende Fahrten wurden erfolgreich angelegt.");


        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Ungültiges Datumsformat.");
        }

        handleBtnCreateDrive(event);
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
