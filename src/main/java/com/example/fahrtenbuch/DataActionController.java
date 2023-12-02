package com.example.fahrtenbuch;

import com.example.fahrtenbuch.business.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class DataActionController {
    private DatabaseConnection databaseConnection;

    public DataActionController() {
        databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
    }

    @FXML
    private void handleDataExport() throws IOException {
        databaseConnection.exportDataToCSV();
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




