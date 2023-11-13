package com.example.fahrtenbuch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OverviewController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {
        Parent startPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Scene scene = new Scene(startPage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void handleBtnDataAction(ActionEvent event) throws IOException {
        Parent dataActionPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DataAction.fxml")));
        Scene scene = new Scene(dataActionPage);
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
    private Button btnOverview;

    @FXML
    private Button btnStart;

    @FXML
    private TextField durchschnittlicheGeschTextField;

    @FXML
    private TextField fahrtzeitTextField;

    @FXML
    private TextField gefahreneKmTextField;

    @FXML
    private TextField jahrTextField;

    @FXML
    private TextField kategorieTextField;

    @FXML
    private TextField monatTextField;

    @FXML
    private TextField tagTextField;

}




