package com.example.fahrtenbuch;

import com.example.fahrtenbuch.business.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class DataActionController {
    private DatabaseConnection databaseConnection;
    private Alert alert;

    public DataActionController() {
        databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Button-Klick");
        alert.setHeaderText(null);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14;");
        alert.setDialogPane(dialogPane);
    }

    @FXML
    private void handleDataExport() throws IOException {
        databaseConnection.exportDataToCSV();
        alert.setContentText("Daten wurden exportiert");
        alert.showAndWait();
    }
    @FXML
    private void handleDataImport() throws IOException {
        databaseConnection.importDataFromCSV();
        alert.setContentText("Daten wurden importiert");
        alert.showAndWait();
    }

    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {
        sceneChange("hello-view.fxml", event);
    }

    @FXML
    private void handleBtnOverview(ActionEvent event) throws IOException {
        sceneChange("Overview.fxml", event);
    }

    @FXML
    private void handleNewRide(ActionEvent event) throws IOException {
        sceneChange("Index.fxml", event);
    }

    @FXML
    private void handleFahrtenbuecherPage(ActionEvent event) throws IOException {
        sceneChange("FahrtenbucherPage.fxml", event);
    }

    public void sceneChange(String url, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}




