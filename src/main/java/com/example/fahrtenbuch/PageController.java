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
import com.example.fahrtenbuch.entities.Drive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PageController{
    @FXML
    public TextField kfzTF;
    @FXML
    public TextField AbfahrtTF;
    @FXML
    public TextField aktiveFahTF;
    @FXML
    public TextField ankunftTF;
    @FXML
    public TextField dutumTF;
    @FXML
    public TextField gefahreneKmTF;
    @FXML
    public ComboBox<String> kategoriesTF;

    private DatabaseConnection databaseConnection;
    private Alert alert;
    private ObservableList<Drive> fahrtListe = FXCollections.observableArrayList();

    List<Drive> drives = new ArrayList<>();
    private DriveFacade driveFacade;

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

    /*
    @FXML
    private void handleBtnCreateDrive(ActionEvent event) throws IOException {
    TODO

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FahrtenbucherPage.fxml"));
        Parent root = loader.load();

        FahrtenbucherController fahrtenbucherController = loader.getController();
        fahrtenbucherController.setTableLogbook(fahrtListe);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML //bei BtnCreadeDrive-> onAction="#addDrive"
    public void addDrive(ActionEvent event) throws IOException {
    TODO

        Drive fahrt = new Drive(1, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()));
        fahrtListe.add(fahrt);
        driveFacade.persistDrive(fahrt);

        handleBtnCreateDrive(event);
    }*/
}
