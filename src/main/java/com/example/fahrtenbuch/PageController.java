package com.example.fahrtenbuch;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class PageController{

    @FXML
    private TextField bisDatumTF;

    @FXML
    private ImageView fahrtenbucherIcon;

    @FXML
    private ImageView importExportIcon;

    @FXML
    private TextField kategoriesTF;

    @FXML
    private TextField kfzTF;

    @FXML
    private ImageView logoIcon;

    @FXML
    private ImageView newFahrtIcon;

    @FXML
    private ImageView startlogoimg;

    @FXML
    private TextField vonDatumTF;

    ActionEvent event;

    private IndexController ic = new IndexController();

    @FXML
    void FahrtAnlegenActionBtn(ActionEvent event) {

        String bisDatum = bisDatumTF.getText();
        String kategorie = kategoriesTF.getText();
        String kfz = kfzTF.getText();
        String vonDatum = vonDatumTF.getText();

        int id = generateRandom8DigitNumber();
        display(id,  bisDatum,  kategorie,  kfz,  vonDatum);
    }

    public void display(int id, String bisDatum, String kategorie, String kfz, String vonDatum) {
        System.out.println("ID : " + id);
        System.out.println("Bis Datum: " + bisDatum);
        System.out.println("Kategorie: " + kategorie);
        System.out.println("KFZ: " + kfz);
        System.out.println("Von Datum: " + vonDatum);
    }


    @FXML
    void fahrtenbucherAction(ActionEvent event) throws IOException {
        ic.sceneChange("FahrtenbucherPage.fxml", event);
    }

    @FXML
    void importExportAction(ActionEvent event) {

    }

    @FXML
    void newFahrtAction(ActionEvent event) throws IOException {
        ic.sceneChange("Index.fxml", event);
    }

    @FXML
    void startAction(ActionEvent event) {

    }

    public static int generateRandom8DigitNumber() {
        Random random = new Random();
        int min = 10000000;
        int max = 99999999;
        return random.nextInt(max - min + 1) + min;
    }

    public void loadImages(ImageView imgview, String URL ) {
        Image img = new Image(getClass().getResourceAsStream(URL));
        imgview.setImage(img);
    }

    @FXML
    private void handleBtnOverview(ActionEvent event) throws IOException {
        Parent overviewPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Overview.fxml")));
        Scene scene = new Scene(overviewPage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
