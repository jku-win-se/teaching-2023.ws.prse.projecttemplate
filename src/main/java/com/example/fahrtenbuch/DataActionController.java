package com.example.fahrtenbuch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DataActionController {

    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {
        Parent startPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Scene scene = new Scene(startPage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBtnOverview(ActionEvent event) throws IOException {
        Parent startPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Overview.fxml")));
        Scene scene = new Scene(startPage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button btnDataAction;

    @FXML
    private Button btnDataActionExport;

    @FXML
    private Button btnDataActionImport;

    @FXML
    private Button btnLogBook;

    @FXML
    private Button btnNewRide;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnOverview;


}




