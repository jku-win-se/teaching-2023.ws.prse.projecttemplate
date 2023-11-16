package com.example.fahrtenbuch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OverviewController {

    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {
        sceneChange("hello-view.fxml",event);
    }


    @FXML
    public void handleBtnDataAction(ActionEvent event) throws IOException {
        sceneChange("DataAction.fxml",event);
    }

    @FXML
    private void handleNewRide(ActionEvent event) throws IOException {
        sceneChange("Index.fxml",event);
    }

    @FXML
    private void handleFahrtenbuecherPage(ActionEvent event) throws IOException {
        sceneChange("FahrtenbucherPage.fxml",event);
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




