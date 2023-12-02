package com.example.fahrtenbuch;

import java.io.IOException;

import com.example.fahrtenbuch.entities.Drive;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Fahrt;

public class FahrtenbucherController{

    @FXML
    private TableView<Drive> tableLogbook;
    @FXML
    private TableColumn<Drive, String> kfzColumn;
    @FXML
    private TableColumn<Drive, String> aktiveFahrtColumn;
    @FXML
    private TableColumn<Drive, String> gefahreneKmColumn;
    @FXML
    private TableColumn<Drive, String> kategorieColumn;
    @FXML
    private TableColumn<Drive, String> abfahrtColumn;
    @FXML
    private TableColumn<Drive, String> ankunftColumn;

    public Button btnStart;
    public Button btnDataAction;
    public Button btnOverview;
    public Button btnNewRide;



    public void setTableLogbook(ObservableList<Drive> fahrtListe) {
        kfzColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVehicleId().toString()));
        abfahrtColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartureTime().toString()));
        ankunftColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArrivalTime().toString()));
        gefahreneKmColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDrivenKilometres().toString()));
        aktiveFahrtColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWaitingTime().toString()));
        kategorieColumn.setCellValueFactory(cellData -> new SimpleStringProperty("test"));

        tableLogbook.setItems(fahrtListe);

    }

    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {
        sceneChange("hello-view.fxml",event);
    }

    @FXML
    private void handleBtnDataAction (ActionEvent event) throws IOException {
        sceneChange("DataAction.fxml",event);
    }

    @FXML
    private void handleBtnOverview(ActionEvent event) throws IOException {
        sceneChange("Overview.fxml",event);
    }

    @FXML
    private void handleNewRide(ActionEvent event) throws IOException {
        sceneChange("Index.fxml",event);
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


