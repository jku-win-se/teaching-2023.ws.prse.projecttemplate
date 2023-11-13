package com.example.fahrtenbuch;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartPageController {

    public Button newRideStart;

    @FXML
    private void handleBtnDataAction (ActionEvent event) throws IOException {
        Parent dataActionPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DataAction.fxml")));
        Scene scene = new Scene(dataActionPage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBtnOverview(ActionEvent event) throws IOException {
        Parent overviewPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Overview.fxml")));
        Scene scene = new Scene(overviewPage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNewRide(ActionEvent event) throws IOException {
        Parent newFahrt = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Index.fxml")));
        Scene scene = new Scene(newFahrt);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleFahrtenbuecherPage(ActionEvent event) throws IOException {
        Parent overviewPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FahrtenbucherPage.fxml")));
        Scene scene = new Scene(overviewPage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private Button btnDataAction;

    @FXML
    private Button btnLogBook;

    @FXML
    private Button btnNewRide;

    @FXML
    private Button btnStart;

    @FXML
    private Button dataAction;

    @FXML
    private Button logBooks;

    @FXML
    private Button btnOverview;

    @FXML
    private Label welcomeText;

}

