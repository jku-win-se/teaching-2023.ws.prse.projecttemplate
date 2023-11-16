package com.example.fahrtenbuch;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Fahrt;

public class FahrtenbucherController{

    @FXML
    private TableView<Fahrt> tableLogbook;
    @FXML
    private TableColumn<Fahrt, String> kfzColumn;
    @FXML
    private TableColumn<Fahrt, String> aktiveFahrtColumn;
    @FXML
    private TableColumn<Fahrt, String> gefahreneKmColumn;
    @FXML
    private TableColumn<Fahrt, String> kategorieColumn;
    @FXML
    private TableColumn<Fahrt, String> abfahrtColumn;
    @FXML
    private TableColumn<Fahrt, String> ankunftColumn;

    public Button btnStart;
    public Button btnDataAction;
    public Button btnOverview;
    public Button btnNewRide;



    public void setTableLogbook(ObservableList<Fahrt> fahrtListe) {
        kfzColumn.setCellValueFactory(new PropertyValueFactory<>("KFZ_kennzeichen"));
        abfahrtColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAbfahrtszeit()));
        ankunftColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAnkunftszeit()));
        gefahreneKmColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGefahreneKM()));
        aktiveFahrtColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getActiveFahrzeit()));
        kategorieColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKategorie()));

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


