package com.example.fahrtenbuch;

import com.example.fahrtenbuch.entities.*;

import com.example.fahrtenbuch.business.DriveFacade;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;
import com.example.fahrtenbuch.entities.*;


public class FahrtenbucherController implements Initializable{

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

    @SuppressWarnings("exports")
    public Button btnStart;
    @SuppressWarnings("exports")
    public Button btnDataAction;
    @SuppressWarnings("exports")
    public Button btnOverview;
    @SuppressWarnings("exports")
    public Button btnNewRide;

    @FXML
    private TextField durchlicheTF;

    @FXML
    private TextField fahrtzeitTF;


    @FXML
    private TextField gefahreneKmTF;

    @FXML
    private TextField jahrTF;

    @FXML
    private ComboBox<String> kategoryTF;


    @FXML
    private TextField monat;


    @FXML
    private TextField tagTF;

    private DriveFacade driveFacade;

    public FahrtenbucherController() {
        driveFacade = new DriveFacade();
    }

    @FXML
    private Button btnShowDrive;


    @FXML
    private void handleSelectedDrive(MouseEvent event, Drive selectedDrive) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditDrive.fxml"));
        Parent root = loader.load();

        EditDriveController editPageController = loader.getController();
        editPageController.setDrive(selectedDrive);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    //new method for fetching data
    @SuppressWarnings("exports")
    public void setTableLogbook(ObservableList<Drive> fahrtListe) {

        kfzColumn.setCellValueFactory(cellData -> {

            int vid = cellData.getValue().getVehicleId();
            String lp = driveFacade.getLicense_plateByDriveId(vid);

            //return new SimpleStringProperty(cellData.getValue().getVehicleId().toString());
            return new SimpleStringProperty(""+lp);
        });

        abfahrtColumn.setCellValueFactory(cellData -> {
            Time departureTime = cellData.getValue().getDepartureTime();
            return new SimpleStringProperty(departureTime != null ? departureTime.toString() : "");
        });

        ankunftColumn.setCellValueFactory(cellData -> {
            Time arrivalTime = cellData.getValue().getArrivalTime();
            return new SimpleStringProperty(arrivalTime != null ? arrivalTime.toString() : "");
        });

        gefahreneKmColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDrivenKilometres().toString()));
        aktiveFahrtColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWaitingTime().toString()));

        // Check if category is null before calling toString()
        kategorieColumn.setCellValueFactory(cellData -> {
            String category = driveFacade.getCategoryNameByDriveId(cellData.getValue().getDriveId());
            return new SimpleStringProperty(category != null ? category.toString() : "...");
        });

        tableLogbook.setItems(fahrtListe);
    }


//    public void setTableLogbook(ObservableList<Drive> fahrtListe) {
//        kfzColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVehicleId().toString()));
//        abfahrtColumn.setCellValueFactory(cellData -> new SimpleStringProperty("abfahrt")); //cellData.getValue().getDepartureTime().toString())
//        ankunftColumn.setCellValueFactory(cellData -> new SimpleStringProperty("ankunft")); //cellData.getValue().getArrivalTime().toString())
//        gefahreneKmColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDrivenKilometres().toString()));
//        aktiveFahrtColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWaitingTime().toString()));
//        kategorieColumn.setCellValueFactory(cellData -> new SimpleStringProperty("test"));
//
//        tableLogbook.setItems(fahrtListe);
//
//        tableLogbook.setOnMouseClicked(event -> {
//            if (event.getClickCount() == 1) {
//                Drive selectedDrive = tableLogbook.getSelectionModel().getSelectedItem();
//                if (selectedDrive != null) {
//                    try {
//                        handleSelectedDrive(event, selectedDrive);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//    }

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
    private void handleNewRide(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Index.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBtnEditDrive(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditDrive.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void handleShowDrive(ActionEvent event) {
        handleFilterAction(event);
    }

    @FXML
    void handleFilterAction(ActionEvent event) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM drive WHERE 1=1");

        Double avgSpeed =parseTextFieldToDouble(durchlicheTF.getText());
        Double km = parseTextFieldToDouble(gefahreneKmTF.getText());
        String category = kategoryTF.getValue();
        Integer month = parseTextFieldToInt(monat.getText());
        Integer year = parseTextFieldToInt(jahrTF.getText());
        Integer waiting_time = parseTextFieldToInt(fahrtzeitTF.getText());

        System.out.println("avgSpeed "+avgSpeed);
        System.out.println("category "+category);
        System.out.println("month "+month);
        System.out.println("km "+km);
        System.out.println("year "+year);
        System.out.println("avgSpeed "+avgSpeed);
        System.out.println("waiting_time "+waiting_time);

        if (year != null) {
            queryBuilder.append(" AND YEAR(drive_date) = ").append(year);
        }

        if (km != null) {
            queryBuilder.append(" AND driven_kilometres BETWEEN ").append(km - 0.1 * km)
                    .append(" AND ").append(km + 0.1 * km);
        }

        if (category != null && !category.isEmpty()) {
            queryBuilder.append(" AND drive_id IN (SELECT drive_id FROM category_drive cd JOIN category c ON cd.category_id = c.category_id WHERE c.category_name = ?)");
        }

        if (month != null) {
            queryBuilder.append(" AND MONTH(drive_date) = ").append(month);
        }

        if (avgSpeed != null) {
            queryBuilder.append(" AND ABS((driven_kilometres / (TIMESTAMPDIFF(SECOND, departure_time, arrival_time + INTERVAL waiting_time SECOND) / 3600)) - ")
                    .append(avgSpeed).append(") <= ").append(avgSpeed * 0.10);
        }

        if (waiting_time != null) {
            queryBuilder.append(" AND waiting_time = ").append(waiting_time);
        }

        List<Drive> driveList = driveFacade.filterDrivesWithQuery(queryBuilder.toString(), category);

        updateTable(driveList);
    }

    public Integer parseTextFieldToInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Double parseTextFieldToDouble(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void updateTable(List<Drive> driveList) {
        ObservableList<Drive> observableDriveList = FXCollections.observableList(driveList);
        setTableLogbook(observableDriveList);
    }


    @FXML
    void StatusAllDriveActionBtn(ActionEvent event) {
        handleStatusFilter("all");
    }

    @FXML
    void CompleteTFActionBtn(ActionEvent event) {
        handleStatusFilter("COMPLETED");
    }



    @FXML
    void FutureTFActionBtn(ActionEvent event) {
        handleStatusFilter("ZUKUENFTIG");
    }


    @FXML
    void DriveAwayTFActionBtn(ActionEvent event) {
        handleStatusFilter("AUF_FAHRT");
    }

    private void handleStatusFilter(String status) {
        updateTable(driveFacade.filterByStatus(status));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Drive> driveList = FXCollections.observableArrayList(driveFacade.getAllDrives());
        setTableLogbook(driveList);

        List<String> cataName = driveFacade.getAllCategoryNames();
        ObservableList<String> categories = FXCollections.observableArrayList(cataName);
        kategoryTF.setItems(categories);

    }

}


