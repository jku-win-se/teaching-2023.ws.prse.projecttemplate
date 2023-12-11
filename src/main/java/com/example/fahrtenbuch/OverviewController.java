//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.fahrtenbuch;

import com.example.fahrtenbuch.business.DatabaseConnection;
import com.example.fahrtenbuch.business.DriveFacade;
import com.example.fahrtenbuch.entities.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OverviewController {
    private DriveFacade driveFacade;
    private ObservableList<Drive> fahrtListe = FXCollections.observableArrayList();
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private Alert alert;

    public OverviewController() {
        this.databaseConnection.getConnection();
        this.alert = new Alert(AlertType.INFORMATION);
        this.alert.setTitle("Button-Klick");
        this.alert.setHeaderText((String)null);
        DialogPane dialogPane = this.alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14;");
        this.alert.setDialogPane(dialogPane);
        this.driveFacade = new DriveFacade();
    }

    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("hello-view.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleBtnDataAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("DataAction.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNewRide(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Index.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleFahrtenbucherPage(ActionEvent event) throws IOException {
        Drive drive = new Drive(1, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), 3, 3.0);
        List<Drive> drives = this.driveFacade.getAllDrives();
        drives.add(drive);
        this.fahrtListe = FXCollections.observableArrayList(this.driveFacade.getAllDrives());
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("FahrtenbucherPage.fxml"));
        Parent overviewPage = (Parent)loader.load();
        FahrtenbucherController fahrtenbucherController = (FahrtenbucherController)loader.getController();
        fahrtenbucherController.setTableLogbook(this.fahrtListe);
        Scene scene = new Scene(overviewPage);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
