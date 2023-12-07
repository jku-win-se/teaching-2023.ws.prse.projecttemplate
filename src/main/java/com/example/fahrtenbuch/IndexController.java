package com.example.fahrtenbuch;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.fahrtenbuch.business.CategoryFacade;
import com.example.fahrtenbuch.business.DatabaseConnection;
import com.example.fahrtenbuch.business.DriveFacade;
import com.example.fahrtenbuch.entities.Category;
import com.example.fahrtenbuch.entities.Drive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


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
    public ComboBox<String> kategoriesTF;
    @FXML
    public TextField kfzTF;
    public Button btnStart;
    public ImageView logoIcon;
    public Button btnCreateRide;
    public Button btnNewRide;

    public Button btnOverview;
    public AnchorPane popupPane;
    public Button btnAddCategory;
    public ComboBox kategoryTF;
    public Button btnAddKfz;

    private DatabaseConnection databaseConnection;
    private Alert alert;


    private ObservableList<Drive> fahrtListe = FXCollections.observableArrayList();

    List<Drive> drives = new ArrayList<>();
    private DriveFacade driveFacade;

    public IndexController() {
        databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Button-Klick");
        alert.setHeaderText(null);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14;");
        alert.setDialogPane(dialogPane);

        driveFacade = new DriveFacade();
    }

    @FXML
    private void handleFahrtenbucherPage(ActionEvent event) throws IOException {
        Drive drive = new Drive(1, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()),Time.valueOf(LocalTime.now()), 3, 3.0);
        drives = driveFacade.getAllDrives();
        drives.add(drive);

        fahrtListe = FXCollections.observableArrayList(driveFacade.getAllDrives());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FahrtenbucherPage.fxml"));
        Parent overviewPage = loader.load();

        FahrtenbucherController fahrtenbucherController = loader.getController();
        fahrtenbucherController.setTableLogbook(fahrtListe);

        Scene scene = new Scene(overviewPage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void Zukunftige_Fahrt_Anlegen_Action(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Page.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBtnDataAction (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataAction.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBtnOverview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Overview.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private void handleBtnCreateRide(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FahrtenbucherPage.fxml"));
        Parent root = loader.load();

        FahrtenbucherController fahrtenbucherController = loader.getController();
        fahrtenbucherController.setTableLogbook(fahrtListe);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void addFahrt(ActionEvent event) throws IOException {
        String kfzField = kfzTF.getText();
        String abfahrtField = AbfahrtTF.getText();
        String ankunftField = ankunftTF.getText();
        String gefahreneKmField = gefahreneKmTF.getText();
        String aktiveFahField = aktiveFahTF.getText();
        ComboBox<Category> kategoryTF;

        //Drive fahrt = new Drive(kfzField, aktiveFahField, abfahrtField, ankunftField, gefahreneKmField, kategorieField);
        Drive fahrt = new Drive(1, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()),Time.valueOf(LocalTime.now()), Integer.valueOf(aktiveFahField), Double.valueOf(gefahreneKmField));
        fahrtListe.add(fahrt);
        driveFacade.persistDrive(fahrt);

        handleBtnCreateRide(event);
    }


    @FXML
    private void handleBtnAddCategory(ActionEvent event) throws IOException {
        CategoryFacade categoryFacade = new CategoryFacade();
        ObservableList<Category> categories = FXCollections.observableArrayList(categoryFacade.getAllCategories());
        kategoryTF.setItems(categories);
    }

    @FXML
    private void showAddCategoryPopup(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupAddCategory.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(root));

        popupStage.show();

        ((Node) event.getSource()).getScene().getWindow().requestFocus();
        handleBtnAddCategory(event);
    }


    public void showAddKfzPopup(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupAddKfz.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(root));

        popupStage.show();

        ((Node) event.getSource()).getScene().getWindow().requestFocus();
        handleBtnAddCategory(event);
    }
}

