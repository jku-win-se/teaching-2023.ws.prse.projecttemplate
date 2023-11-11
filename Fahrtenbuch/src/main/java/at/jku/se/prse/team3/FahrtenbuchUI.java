package at.jku.se.prse.team3;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.TimeStringConverter;
import org.jfree.data.xy.CategoryTableXYDataset;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static javafx.application.Application.launch;

public class FahrtenbuchUI extends App {


    private Fahrtenbuch fahrtenbuch;
    private Button setButton;
    private Button backButton;
    private Button newTripButton;
    private TableView<Fahrt> fahrtenTabelle;
    private Button speichernButton;

    private ObservableList<Fahrt> fahrtenListe; // Klassenvariable für die Fahrtenliste

    public FahrtenbuchUI(Fahrtenbuch fahrtenbuch) {
        this.fahrtenbuch = fahrtenbuch;
        // Initialisierung der fahrtenListe mit leeren Daten oder vorhandenen Daten aus fahrtenbuch
        this.fahrtenListe = FXCollections.observableArrayList();

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    //start tabellerische Ansicht
        // Laden der vorhandenen Fahrten aus dem Fahrtenbuch und Initialisierung der fahrtenListe
        fahrtenListe.addAll(fahrtenbuch.listeFahrten());

        // Erstellen und Konfigurieren der TableView und anderer UI-Komponenten
        fahrtenTabelle = new TableView<>(fahrtenListe);
        fahrtenTabelle.setItems(fahrtenListe);

        TableColumn<Fahrt, String> kfz = new TableColumn<>("KFZ-Kennzeichen");
        kfz.setCellValueFactory(new PropertyValueFactory<>("kfzKennzeichen"));

        TableColumn<Fahrt, LocalDate> date = new TableColumn<>("Datum");
        date.setCellValueFactory(new PropertyValueFactory<>("datum"));

        TableColumn<Fahrt, LocalTime> abf = new TableColumn<>("Abfahrtszeit");
        abf.setCellValueFactory(new PropertyValueFactory<>("abfahrtszeit"));

        TableColumn<Fahrt, LocalTime> ank = new TableColumn<>("Ankunftszeit");
        ank.setCellValueFactory(new PropertyValueFactory<>("ankunftszeit"));

        TableColumn<Fahrt, Double> gefKM = new TableColumn<>("gefahrene Kilometer");
        gefKM.setCellValueFactory(new PropertyValueFactory<>("gefahreneKilometer"));

        TableColumn<Fahrt, LocalTime> aktivFZ = new TableColumn<>("aktive Fahrzeit");
        aktivFZ.setCellValueFactory(new PropertyValueFactory<>("aktiveFahrzeit"));

        TableColumn<Fahrt, FahrtStatus> status = new TableColumn<>("Fahrtstatus");
        status.setCellValueFactory(new PropertyValueFactory<>("fahrtstatus"));



       TableColumn<Fahrt, String> kateg = new TableColumn<>("Kategorien");
       kateg.setCellValueFactory(cellData -> {
           List<String> categories= cellData.getValue().getKategorien();
           String catToString=categories.stream().collect(Collectors.joining(", "));
           return new SimpleStringProperty(catToString);
       });

        fahrtenTabelle.getColumns().addAll(kfz,date,abf,ank,gefKM,aktivFZ,status,kateg);

        //ende tabellarische ansicht
        primaryStage.setTitle("Fahrtenbuch");
        setButton =new Button();
        setButton.setText("Settings");

        setButton.setOnAction(new EventHandler<ActionEvent>() {
                               @Override
                               public void handle(ActionEvent actionEvent) {
                                    switchToSettings(primaryStage);
                                   System.out.println("Testbutton");
                               }
                           });
        StackPane layoutFahrten = new StackPane();

        newTripButton =new Button();
        newTripButton.setText("Neue Fahrt");
        newTripButton.setStyle("-fx-background-colour: #00ff00");
        newTripButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                neueFahrt(primaryStage);
            }
        });

        HBox box = new HBox(1);
        box.getChildren().addAll(newTripButton,setButton);
        box.setAlignment(Pos.TOP_RIGHT);




        layoutFahrten.getChildren().addAll(fahrtenTabelle, box);
        layoutFahrten.setAlignment(box, Pos.TOP_CENTER);
        layoutFahrten.setAlignment(fahrtenTabelle,Pos.BOTTOM_CENTER);




        Scene fahrten =  new Scene(layoutFahrten,500,400);

        primaryStage.setScene(fahrten);
        primaryStage.show();
    }
    private void neueFahrt(Stage primaryStage){
        //Liste von zukünftigen LocalDates von wiederkehrenden Fahrten
        List<LocalDate> futureDates= new ArrayList<>();
        List<String> kategorienListe = new ArrayList<>();
        List<Integer> kilometerListe = new ArrayList<>();
        List<LocalTime> fahrzeitListe = new ArrayList<>();
        List<LocalTime> abfahrtszeitListe = new ArrayList<>();
        List<LocalTime> ankunftszeitListe = new ArrayList<>();
        List<LocalDate> datumListe = new ArrayList<>();
        List<String> kfzKennzeichenListe = new ArrayList<>();

        TextField kfzKennzeichen = new TextField();
        kfzKennzeichen.setPromptText("KFZ-Kennzeichen:");

        DatePicker datum= new DatePicker();
        datum.setPromptText("Datum der Fahrt");
        datum.setOnAction(event -> {
            datum.getValue();

        });

        TextField abfahrtsZeit = new TextField();
        abfahrtsZeit.setPromptText("Abfahrtszeit im Format HH:MM:SS");
        abfahrtsZeit.setTextFormatter(new TextFormatter<>(new TimeStringConverter()));


        TextField ankunftsZeit = new TextField();
        ankunftsZeit.setPromptText("Ankunftszeit im Format HH:MM:SS");
        ankunftsZeit.setTextFormatter(new TextFormatter<>(new TimeStringConverter()));

        TextField gefahreneKilometer = new TextField();
        gefahreneKilometer.setPromptText("gefahrene Kilometer");
        gefahreneKilometer.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

        TextField aktiveFahrzeit = new TextField();
        aktiveFahrzeit.setPromptText("Fahrzeit in HH:MM:SS");
        aktiveFahrzeit.setTextFormatter(new TextFormatter<>(new TimeStringConverter()));


        DatePicker future= new DatePicker();
        future.setPromptText("Zukünftige Fahrten");



        TextArea selectedDates= new TextArea();
        selectedDates.setDisable(true);
        selectedDates.setVisible(false);
        selectedDates.setPrefWidth(84);

        future.setOnAction(event -> {
            LocalDate date =future.getValue();
            addToReoccurances(date, futureDates::add);
            selectedDates.setVisible(true);
            selectedDates.setText(selectedDates.getText()+future.getValue().toString()+"; ");
            selectedDates.setPrefWidth(84*futureDates.size());
        });

        selectedDates.setPrefHeight(20);

        HBox futureDatesBox= new HBox(10);
        futureDatesBox.getChildren().addAll(future,selectedDates);

        futureDatesBox.setVisible(false);

        ComboBox fahrtstatus= new ComboBox<>();
        fahrtstatus.setItems(FXCollections.observableArrayList(FahrtStatus.values()));
        fahrtstatus.setPromptText("Fahrtstatus:");
        fahrtstatus.setOnAction(event -> {
            if (FahrtStatus.ZUKUENFTIG.equals(fahrtstatus.getValue())){

                futureDatesBox.setVisible(true);
            }

        });



        TextField kategorien = new TextField();
        kategorien.setPromptText("Kategorien eingeben:");

// TextArea zur Anzeige der hinzugefügten Kategorien
        TextArea angezeigteKategorien = new TextArea();
        angezeigteKategorien.setPrefHeight(40);
        angezeigteKategorien.setEditable(true);
        angezeigteKategorien.setVisible(true);

// Button zum Hinzufügen von Kategorien
        Button kategorieHinzufuegenButton = new Button("Kategorie hinzufügen");
        kategorieHinzufuegenButton.setOnAction(event -> {
            String kategorie = kategorien.getText().trim();
            if (!kategorie.isEmpty()) {
                addToKategories(kategorie, kategorienListe::add);
                angezeigteKategorien.setVisible(true);
                angezeigteKategorien.appendText(kategorie + "; ");
                kategorien.clear();
            }
        });
        TextArea angezeigteKilometer = new TextArea();
        angezeigteKilometer.setPrefHeight(40);
        angezeigteKilometer.setEditable(true);
        angezeigteKilometer.setVisible(true);

        Button kilometerHinzufuegenButton = new Button("Kilometer hinzufügen");
        kilometerHinzufuegenButton.setOnAction(event -> {
            String kilometerText = gefahreneKilometer.getText().trim();
            if (!kilometerText.isEmpty()) {
                try {
                    int kilometer = Integer.parseInt(kilometerText);
                    kilometerListe.add(kilometer);
                    angezeigteKilometer.setVisible(true);
                    angezeigteKilometer.appendText(kilometerText + " km; ");
                    gefahreneKilometer.clear();
                } catch (NumberFormatException e) {
                    // Fehlerbehandlung, falls die Eingabe keine gültige Zahl ist
                }
            }
        });

        // TextArea zur Anzeige der hinzugefügten Fahrzeiten
        TextArea angezeigteFahrzeit = new TextArea();
        angezeigteFahrzeit.setPrefHeight(50);
        angezeigteFahrzeit.setEditable(true);
        angezeigteFahrzeit.setVisible(true);

// Button zum Hinzufügen von Fahrzeiten
        Button fahrzeitHinzufuegenButton = new Button("Fahrzeit hinzufügen");
        fahrzeitHinzufuegenButton.setOnAction(event -> {
            String fahrzeitText = aktiveFahrzeit.getText().trim();
            if (!fahrzeitText.isEmpty()) {
                try {
                    LocalTime fahrzeit = LocalTime.parse(fahrzeitText);
                    fahrzeitListe.add(fahrzeit);
                    angezeigteFahrzeit.setVisible(true);
                    angezeigteFahrzeit.appendText(fahrzeitText + "; ");
                    aktiveFahrzeit.clear();
                } catch (DateTimeParseException e) {
                    // Fehlerbehandlung, falls die Eingabe kein gültiges Zeitformat ist
                }
            }
        });

        // TextAreas zur Anzeige der hinzugefügten Zeiten
        TextArea angezeigteAbfahrtszeiten = new TextArea();
        angezeigteAbfahrtszeiten.setPrefHeight(40);
        TextArea angezeigteAnkunftszeiten = new TextArea();
        angezeigteAnkunftszeiten.setPrefHeight(40);
        angezeigteAbfahrtszeiten.setEditable(true);
        angezeigteAnkunftszeiten.setEditable(true);
        angezeigteAbfahrtszeiten.setVisible(true);
        angezeigteAnkunftszeiten.setVisible(true);

// Buttons zum Hinzufügen der Zeiten
        Button abfahrtszeitHinzufuegenButton = new Button("Abfahrtszeit hinzufügen");
        Button ankunftszeitHinzufuegenButton = new Button("Ankunftszeit hinzufügen");

        abfahrtszeitHinzufuegenButton.setOnAction(event -> {
            String zeitText = abfahrtsZeit.getText().trim();
            if (!zeitText.isEmpty()) {
                try {
                    LocalTime zeit = LocalTime.parse(zeitText);
                    abfahrtszeitListe.add(zeit);
                    angezeigteAbfahrtszeiten.setVisible(true);
                    angezeigteAbfahrtszeiten.appendText(zeitText + "; ");
                    abfahrtsZeit.clear();
                } catch (DateTimeParseException e) {
                    // Fehlerbehandlung
                }
            }
        });

        ankunftszeitHinzufuegenButton.setOnAction(event -> {
            String zeitText = ankunftsZeit.getText().trim();
            if (!zeitText.isEmpty()) {
                try {
                    LocalTime zeit = LocalTime.parse(zeitText);
                    ankunftszeitListe.add(zeit);
                    angezeigteAnkunftszeiten.setVisible(true);
                    angezeigteAnkunftszeiten.appendText(zeitText + "; ");
                    ankunftsZeit.clear();
                } catch (DateTimeParseException e) {
                    // Fehlerbehandlung
                }
            }
        });

        // TextAreas zur Anzeige der hinzugefügten Daten
        TextArea angezeigteDatum = new TextArea();
        angezeigteDatum.setPrefHeight(50);
        TextArea angezeigteKFZKennzeichen = new TextArea();
        angezeigteKFZKennzeichen.setPrefHeight(40);
        angezeigteDatum.setEditable(true);
        angezeigteKFZKennzeichen.setEditable(true);
        angezeigteDatum.setVisible(true);
        angezeigteKFZKennzeichen.setVisible(true);
        TextArea angezeigteSpeicherButton = new TextArea();
        angezeigteSpeicherButton.setVisible(true);

// Buttons zum Hinzufügen der Daten
        Button datumHinzufuegenButton = new Button("Datum hinzufügen");
        Button kfzKennzeichenHinzufuegenButton = new Button("KFZ-Kennzeichen hinzufügen");

        datumHinzufuegenButton.setOnAction(event -> {
            LocalDate ausgewaehltesDatum = datum.getValue();
            if (ausgewaehltesDatum != null) {
                datumListe.add(ausgewaehltesDatum);
                angezeigteDatum.setVisible(true);
                angezeigteDatum.appendText(ausgewaehltesDatum.toString() + "; ");
                datum.setValue(null);
            }
        });

        kfzKennzeichenHinzufuegenButton.setOnAction(event -> {
            String kfzText = kfzKennzeichen.getText().trim();
            if (!kfzText.isEmpty()) {
                kfzKennzeichenListe.add(kfzText);
                angezeigteKFZKennzeichen.setVisible(true);
                angezeigteKFZKennzeichen.appendText(kfzText + "; ");
                kfzKennzeichen.clear();
            }
        });



        //SPACER BOX
        Box box= new Box(10,30,720);
        box.setVisible(true);

        VBox fahrtTextinputboxen = new VBox(1);
        fahrtTextinputboxen.getChildren().addAll(box,kfzKennzeichen,datum,abfahrtsZeit,ankunftsZeit,
                gefahreneKilometer, aktiveFahrzeit, fahrtstatus,futureDatesBox, kategorien, kategorieHinzufuegenButton,
                angezeigteKategorien, kilometerHinzufuegenButton, angezeigteKilometer,
                fahrzeitHinzufuegenButton, angezeigteFahrzeit, abfahrtszeitHinzufuegenButton, angezeigteAbfahrtszeiten,
                ankunftszeitHinzufuegenButton, angezeigteAnkunftszeiten, datumHinzufuegenButton, angezeigteDatum,
                kfzKennzeichenHinzufuegenButton, angezeigteKFZKennzeichen

        );



        backButton = new Button();
        backButton.setText("<- BACK");
        backButton.setOnAction(actionEvent -> start(primaryStage));
        ScrollPane scrollPane = new ScrollPane(fahrtTextinputboxen);
        scrollPane.setFitToWidth(true); // Passt die Breite der ScrollPane an die Breite der VBox an
        scrollPane.setPrefHeight(400); // Setzen Sie eine bevorzugte Höhe



        primaryStage.setTitle("neue Fahrt");
        StackPane layoutNewTrip=new StackPane();
        layoutNewTrip.getChildren().add(scrollPane);
        backButton = new Button("<- BACK");
        backButton.setOnAction(event -> start(primaryStage));
        layoutNewTrip.getChildren().add(backButton);
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);

        speichernButton = new Button("Fahrt speichern");
        speichernButton.setOnAction(event -> {
            // Sammeln der Benutzereingaben

            String kfzText = kfzKennzeichen.getText();
            LocalDate ausgewaehltesDatum = datum.getValue();
            LocalTime abfahrtsZeitValue = LocalTime.parse(abfahrtsZeit.getText());
            LocalTime ankunftsZeitValue = LocalTime.parse(ankunftsZeit.getText());
            Double gefahreneKilometerValue = Double.parseDouble(gefahreneKilometer.getText());
            LocalTime aktiveFahrzeitValue = LocalTime.parse(aktiveFahrzeit.getText());
            FahrtStatus ausgewaehlterStatus = (FahrtStatus) fahrtstatus.getValue();
            List<String> category = new ArrayList<>(kategorienListe);


            // Hinzufügen der neuen Fahrt zum Fahrtenbuch und zur fahrtenListe
            fahrtenbuch.neueFahrt(kfzText, ausgewaehltesDatum, abfahrtsZeitValue, ankunftsZeitValue,
                    gefahreneKilometerValue, aktiveFahrzeitValue, ausgewaehlterStatus);

        });
        layoutNewTrip.getChildren().add(speichernButton);
        StackPane.setAlignment(speichernButton, Pos.TOP_RIGHT);

        Scene neueFahrt = new Scene(layoutNewTrip,1080, 720);

        primaryStage.setScene(neueFahrt);
        primaryStage.show();

    }

    private void addToReoccurances(LocalDate date, Consumer<LocalDate> addFutureDate) {
        addFutureDate.accept(date);
    }

    private void addToKategories(String kate, Consumer<String> addKategorie) {
        addKategorie.accept(kate);
    }
    private void switchToSettings(Stage primaryStage){
        backButton =new Button();
        backButton.setText("<- BACK");
        backButton.setOnAction(actionEvent -> start(primaryStage));
        TextField enterSavePath = new TextField();
        enterSavePath.setText("enter Save Path:");

        primaryStage.setTitle("Einstellungen");
        StackPane layoutSettings=new StackPane();
        layoutSettings.getChildren().addAll(enterSavePath,backButton);
        layoutSettings.setAlignment(backButton,Pos.TOP_LEFT);
        layoutSettings.setAlignment(enterSavePath,Pos.CENTER);


        Scene einstellungen = new Scene(layoutSettings,1080, 720);
        primaryStage.setScene(einstellungen);
        primaryStage.show();
    }
}
