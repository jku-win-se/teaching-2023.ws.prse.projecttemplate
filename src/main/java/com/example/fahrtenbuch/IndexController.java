package com.example.fahrtenbuch;


import java.io.IOException;
import java.net.URL;
import java.util.*;


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
    public Button btnOverview;

    @FXML
    public TextField gefahreneKmTF;

    @FXML
    public TextField kategoriesTF;

    @FXML
    public TextField kfzTF;

    public List<Fahrt> fahrt = new ArrayList<>();
    public int id;
    public ImageView startlogoimg;
    public Button btnStart;
    public ImageView logoIcon;
    public ImageView newFahrtIcon;
    public ImageView fahrtenbucherIcon;
    public ImageView importExportIcon;

    public static String generateID() {
        Random random = new Random();
        int min = 10000000;
        int max = 1000000000;
        int randomNum = random.nextInt(max - min + 1) + min;
        String randomID = String.valueOf(randomNum);
        return randomID;
    }

    @FXML
    public
    void FAHRT_ANLEGEN_ACTION(ActionEvent event) {
        System.out.println("called");

        id = Integer.parseInt(generateID());
        String kfzKennzeichen = kfzTF.getText();
        String dutum = dutumTF.getText();
        String aktiveFahrt = aktiveFahTF.getText();
        String gefahreneKm = gefahreneKmTF.getText();
        String kategorie = kategoriesTF.getText();
        String abfahrt = AbfahrtTF.getText();
        String ankunft = ankunftTF.getText();


        System.out.println("id: " + id);
        System.out.println("KFZ-Kennzeichen: " + kfzKennzeichen);
        System.out.println("Datum: " + dutum);
        System.out.println("Aktive Fahrt: " + aktiveFahrt);
        System.out.println("Gefahrene km: " + gefahreneKm);
        System.out.println("Kategorie: " + kategorie);
        System.out.println("Abfahrt: " + abfahrt);
        System.out.println("Ankunft: " + ankunft);

        Fahrt newFahrt = new Fahrt(id, kfzKennzeichen, dutum, aktiveFahrt, abfahrt, gefahreneKm, ankunft, kategorie);
        fahrt.add(newFahrt);

    }


    @FXML
    void Fahrtenbucher_ACTION(ActionEvent event) throws IOException {
        sceneChange("FahrtenbucherPage.fxml", event);
    }

    @FXML
    void Zukunftige_Fahrt_Anlegen_Action(ActionEvent event) throws IOException {
        sceneChange("Page.fxml", event);
    }

    @FXML
    void importExportAction(ActionEvent event) {

    }

    @FXML
    void new_fahrt_action(ActionEvent event) {

    }

    @FXML
    void start_action(ActionEvent event) {

    }

    public void loadImages(ImageView iv, String URL ) {
        Image img = new Image(getClass().getResourceAsStream(URL));
        iv.setImage(img);
    }

    public void sceneChange(String url, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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





}

