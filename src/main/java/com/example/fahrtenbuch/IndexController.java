package com.example.fahrtenbuch;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.fahrtenbuch.business.*;
import com.example.fahrtenbuch.entities.*;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class IndexController{
    @FXML
    public TextField AbfahrtTF;
    @FXML
    public TextField aktiveFahTF;
    @FXML
    public TextField ankunftTF;
    @FXML
    public TextField datumTF;
    @FXML
    public TextField gefahreneKmTF;
    @FXML
    public TextField kfzTF;

    public Button btnStart;
    public ImageView logoIcon;
    public Button btnCreateRide;
    public Button btnNewRide;

    public Button btnOverview;
    public AnchorPane popupPane;
    public Button btnAddCategory;
    public Button btnAddKfz;
    @FXML
    public ComboBox<String> kategoriesTF;

    private CategoryFacade categoryFacade;

    private DatabaseConnection databaseConnection;
    private Alert alert;


    private ObservableList<Drive> fahrtListe = FXCollections.observableArrayList();

    List<Drive> drives = new ArrayList<>();
    private DriveFacade driveFacade;

    public IndexController() throws IOException {
        databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Button-Klick");
        alert.setHeaderText(null);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14;");
        alert.setDialogPane(dialogPane);

        driveFacade = new DriveFacade();
        categoryFacade = new CategoryFacade();
    }

    @FXML
    private void initialize() {
        initializeCategoryDropdown();
    }


    private void initializeCategoryDropdown() {
        CategoryFacade categoryFacade = new CategoryFacade();
        ObservableList<Category> categories = FXCollections.observableArrayList(categoryFacade.getAllCategories());

        // Konvertiere ObservableList<Category> in ObservableList<String>
        ObservableList<String> categoryNames = FXCollections.observableArrayList();
        for (Category category : categories) {
            categoryNames.add(category.toString());
        }

        kategoriesTF.setItems(categoryNames);
    }

    @FXML
    private void handleFahrtenbucherPage(ActionEvent event) throws IOException {
        Drive drive = new Drive(1, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()),Time.valueOf(LocalTime.now()), 3, 3.0);
        drives = driveFacade.getAllDrives();
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
    private void Zukunftige_Fahrt_Anlegen_Action(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Page.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {
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
    private void handleBtnOverview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Overview.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private void handleBtnCreateRide(ActionEvent event) throws IOException {
        //FahrtenbucherController fbp = new FahrtenbucherController();
        //ObservableList<Drive> driveList = FXCollections.observableArrayList(driveFacade.getAllDrives());
        //fetTableLogbook(driveList);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FahrtenbucherPage.fxml"));
        Parent root = loader.load();

        FahrtenbucherController fahrtenbucherController = loader.getController();
        ObservableList<Drive> driveList = FXCollections.observableArrayList(driveFacade.getAllDrives());
        fahrtenbucherController.setTableLogbook(driveList);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void addFahrt(ActionEvent event) throws IOException {
        String licensePlate = kfzTF.getText();
        String abfahrtField = AbfahrtTF.getText();
        String ankunftField = ankunftTF.getText();
        String gefahreneKmField = gefahreneKmTF.getText();
        String aktiveFahField = aktiveFahTF.getText();

        if (licensePlate.isEmpty() || datumTF.getText().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte füllen Sie Fahrzeug und Datum aus.");
            return;
        }

        VehicleFacade vehicleFacade = new VehicleFacade();
        Vehicle vehicle = vehicleFacade.getVehicleByLicensePlate(licensePlate);
        if (vehicle == null) {

            showAlert(Alert.AlertType.ERROR, "Fehler", "Fahrzeug mit Kennzeichen " + licensePlate + " nicht gefunden.");
            return;
        }
        Integer vehicleId = vehicleFacade.getVehicleIdByLicensePlate(licensePlate);

        Drive fahrt;
        if (!abfahrtField.isEmpty() && !ankunftField.isEmpty() && !gefahreneKmField.isEmpty() && !aktiveFahField.isEmpty()) {

            fahrt = new Drive(
                    vehicleId,
                    Date.valueOf(datumTF.getText()),
                    Time.valueOf(validateAndParseTime(abfahrtField).toLocalTime()),
                    Time.valueOf(validateAndParseTime(ankunftField).toLocalTime()),
                    Integer.parseInt(aktiveFahField),
                    Double.parseDouble(gefahreneKmField)
            );
        } else if (!abfahrtField.isEmpty()) {

            fahrt = new Drive(
                    vehicleId,
                    Date.valueOf(datumTF.getText()),
                    Time.valueOf(validateAndParseTime(abfahrtField).toLocalTime())

            );
        } else if (!datumTF.getText().isEmpty()) {
            fahrt = new Drive(
                    vehicleId,
                    Date.valueOf(datumTF.getText())
            );
        } else {

            String datumText = datumTF.getText();
            if (datumText.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte geben Sie ein Datum ein.");
                return;
            }

            try {
                Date.valueOf(datumText);
            } catch (IllegalArgumentException e) {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Ungültiges Datumsformat. Verwenden Sie das Format 'yyyy-MM-dd'.");
                return;
            }

            showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte füllen Sie die erforderlichen Felder aus.");
            return;
        }


        CategoryFacade categoryFacade = new CategoryFacade();

        Category selectedCategory = categoryFacade.getCategoryByName(kategoriesTF.getValue());

        driveFacade.persistDrive(fahrt);

        if (selectedCategory != null) {
            Category_Drive_Facade categoryDriveFacade = new Category_Drive_Facade();
            Category_Drive categoryDrive = new Category_Drive(selectedCategory.getCategory_id(), driveFacade.getLastDriveId());
            categoryDriveFacade.persistCategoryDrive(categoryDrive);
        }

        handleBtnCreateRide(event);

    }

    @FXML
    private void handleBtnAddCategory(ActionEvent event) throws IOException {

        CategoryFacade categoryFacade = new CategoryFacade();
        ObservableList<Category> categories = FXCollections.observableArrayList(categoryFacade.getAllCategories());

        // Konvertiere ObservableList<Category> in ObservableList<String>
        ObservableList<String> categoryNames = FXCollections.observableArrayList();
        for (Category category : categories) {
            categoryNames.add(category.toString());
        }

        initializeCategoryDropdown();

        kategoriesTF.setItems(categoryNames);
    }

    @FXML
    private void showAddCategoryPopup(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupAddCategory.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(root));

        popupStage.show();

        ((Node) event.getSource()).getScene().getWindow().requestFocus();
        handleBtnAddCategory(event);
    }


    public void showAddKfzPopup(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupAddKfz.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(root));

        popupStage.show();

        ((Node) event.getSource()).getScene().getWindow().requestFocus();
        handleBtnAddCategory(event);
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private Time validateAndParseTime(String timeString) {
        try {
            return Time.valueOf(timeString);
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Ungültiges Zeitformat. Verwenden Sie das Format 'hh:mm:ss'.");
            throw e; // Re-throw the exception to stop further processing
        }
    }
}

