package com.example.fahrtenbuch;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FahrtenbucherController{

    public Button btnStart;
    public Button btnDataAction;
    public Button btnOverview;
    public Button btnNewRide;
    @FXML
    private TextField durchlicheTF;

    @FXML
    private ImageView fahrtenbucherIcon;

    @FXML
    private TextField fahrtzeitTF;

    @FXML
    private TextField gefahreneKmTF;

    @FXML
    private ImageView importExportIcon;

    @FXML
    private TextField jahrTF;

    @FXML
    private TextField kategoryTF;

    @FXML
    private ImageView logoIcon;

    @FXML
    private TextField monat;

    @FXML
    private ImageView newFahrtIcon;

    @FXML
    private ImageView startlogoimg;

    @FXML
    private TextField tagTF;

    private IndexController ic = new IndexController();

    @FXML
    void Fahrtenbucher_ACTION(ActionEvent event) throws IOException {
        ic.sceneChange("FahrtenbucherPage.fxml", event);
    }

    @FXML
    void importExportAction(ActionEvent event) {

    }

    @FXML
    void new_fahrt_action(ActionEvent event) throws IOException {
        ic.sceneChange("Index.fxml", event);
    }

    @FXML
    void start_action(ActionEvent event) {

    }

    public void loadImages(ImageView iv, String URL ) {
        Image img = new Image(getClass().getResourceAsStream(URL));
        iv.setImage(img);
    }

    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {
        Parent startPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Scene scene = new Scene(startPage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

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


}


