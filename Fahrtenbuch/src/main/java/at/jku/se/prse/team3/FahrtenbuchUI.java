package at.jku.se.prse.team3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

import static javafx.application.Application.launch;

public class FahrtenbuchUI extends App {


    private Fahrtenbuch fahrtenbuch;
    private Button button;
    public FahrtenbuchUI (Fahrtenbuch fahrtenbuch){
        this.fahrtenbuch=fahrtenbuch;
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//start tabellerische Ansicht
        ObservableList<Fahrt> fahrtenListe= FXCollections.observableList(fahrtenbuch.listeFahrten());
        TableView<Fahrt> fahrtenTabelle = new TableView<>(fahrtenListe);
        fahrtenTabelle.setItems(fahrtenListe);
        TableColumn<Fahrt, String> kfz = new TableColumn<>("KFZ-Kennzeichen");
        kfz.setCellValueFactory(new PropertyValueFactory<>("kfzKennzeichen"));

        TableColumn<Fahrt, LocalDate> date = new TableColumn<>("Datum");
        date.setCellValueFactory(new PropertyValueFactory<>("datum"));

        TableColumn<Fahrt, LocalTime> abf = new TableColumn<>("Abfahrtszeit");
        abf.setCellValueFactory(new PropertyValueFactory<>("abfahrtszeit"));

        TableColumn<Fahrt, LocalTime> ank = new TableColumn<>("ankunftszeit");
        ank.setCellValueFactory(new PropertyValueFactory<>("Ankunftszeit"));

        TableColumn<Fahrt, Double> gefKM = new TableColumn<>("gefahrene Kilometer");
        gefKM.setCellValueFactory(new PropertyValueFactory<>("gefahreneKilometer"));

        TableColumn<Fahrt, LocalTime> aktivFZ = new TableColumn<>("aktive Fahrzeit");
        aktivFZ.setCellValueFactory(new PropertyValueFactory<>("aktiveFahrzeit"));

        TableColumn<Fahrt, FahrtStatus> status = new TableColumn<>("Fahrtstatus");
        status.setCellValueFactory(new PropertyValueFactory<>("fahrtstatus"));


        //Kategorien ist eine Liste idk wie ich die einfügen soll



     //   TableColumn<Fahrt, List<String>> kateg = new TableColumn<>("Kategorien");
       // kateg.setCellValueFactory(new PropertyValueFactory<>("fahrtKategorie"));

        fahrtenTabelle.getColumns().addAll(kfz,date,abf,ank,gefKM,aktivFZ,status/*,kateg*/);

        //ende tabellarische ansicht
        primaryStage.setTitle("Fahrtenbuch");
        button =new Button();
        button.setText("Settings");

        button.setOnAction(new EventHandler<ActionEvent>() {
                               @Override
                               public void handle(ActionEvent actionEvent) {
                                    switchToSettings(primaryStage);
                                   System.out.println("Testbutton");
                               }
                           });
        StackPane layoutFahrten = new StackPane();
        layoutFahrten.getChildren().addAll(fahrtenTabelle,button);
        layoutFahrten.setAlignment(button, Pos.TOP_RIGHT);
        layoutFahrten.setAlignment(fahrtenTabelle,Pos.CENTER);



        Scene fahrten =  new Scene(layoutFahrten,1080,720);

        primaryStage.setScene(fahrten);
        primaryStage.show();
    }
    private void switchToFahrten(Stage primaryStage){
        TextField enterSavePath = new TextField();
        enterSavePath.setText("enter Save Path:");
        enterSavePath.setAlignment(Pos.TOP_RIGHT);
        primaryStage.setTitle("Einstellungen");
        StackPane layoutSettings=new StackPane();
        layoutSettings.getChildren().add(enterSavePath);
        Scene einstellungen = new Scene(layoutSettings,1080, 720);
        primaryStage.setScene(einstellungen);
        primaryStage.show();
    }

    private void switchToSettings(Stage primaryStage){
        Button back= new Button();
        back.setText("<- BACK");
        back.setOnAction(actionEvent -> start(primaryStage));
        TextField enterSavePath = new TextField();
        enterSavePath.setText("enter Save Path:");

        primaryStage.setTitle("Einstellungen");
        StackPane layoutSettings=new StackPane();
        layoutSettings.getChildren().addAll(enterSavePath,back);
        layoutSettings.setAlignment(back,Pos.TOP_LEFT);
        layoutSettings.setAlignment(enterSavePath,Pos.CENTER);


        Scene einstellungen = new Scene(layoutSettings,1080, 720);
        primaryStage.setScene(einstellungen);
        primaryStage.show();
    }
}
