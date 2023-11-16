package com.example.fahrtenbuch;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Fahrt;

public class IndexController{
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
    public TextField kategoriesTF;
    @FXML
    public TextField kfzTF;
    public Button btnStart;
    public ImageView logoIcon;
    public Button btnCreateRide;
    public Button btnNewRide;

    public Button btnOverview;
    @FXML
    private void Fahrtenbucher_ACTION(ActionEvent event) throws IOException {
        sceneChange("FahrtenbucherPage.fxml", event);
    }

    @FXML
    private void Zukunftige_Fahrt_Anlegen_Action(ActionEvent event) throws IOException {
        sceneChange("Page.fxml", event);
    }

    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {
        sceneChange("hello-view.fxml", event);
    }

    @FXML
    private void handleBtnDataAction (ActionEvent event) throws IOException {
        sceneChange("DataAction.fxml", event);
    }

    @FXML
    private void handleBtnOverview(ActionEvent event) throws IOException {
        sceneChange("Overview.fxml", event);
    }

    private final ObservableList<Fahrt> fahrtListe = FXCollections.observableArrayList();
    @FXML
    private void addFahrt(ActionEvent event){
        String kfzField = kfzTF.getText();
        String abfahrtField = AbfahrtTF.getText();
        String ankunftField = ankunftTF.getText();
        String gefahreneKmField = gefahreneKmTF.getText();
        String aktiveFahField = aktiveFahTF.getText();
        String kategorieField = kategoriesTF.getText();

        Fahrt fahrt = new Fahrt(kfzField, aktiveFahField, abfahrtField, ankunftField, gefahreneKmField, kategorieField);
        fahrtListe.add(fahrt);

        handleBtnCreateRide(event);
    }


    @FXML
    private void handleBtnCreateRide(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FahrtenbucherPage.fxml"));
        Parent overviewPage = null;
        try {
            overviewPage = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FahrtenbucherController fahrtenbucherController = loader.getController();
        fahrtenbucherController.setTableLogbook(fahrtListe);

        Scene scene = new Scene(overviewPage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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

