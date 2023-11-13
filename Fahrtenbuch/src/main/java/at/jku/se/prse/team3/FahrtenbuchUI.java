package at.jku.se.prse.team3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.*;
import java.io.IOException;
import java.text.Format;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static javafx.application.Application.launch;

public class FahrtenbuchUI extends Application {

    private String path;
    private Fahrtenbuch fahrtenbuch;
    private Button setButton;
    private Button backButton;
    private Button newTripButton;
    private TableView<Fahrt> fahrtenTabelle;
    private Button speichernButton = new Button("\uD83D\uDCBE Fahrt speichern");

    private Button newEditButton;

    private ObservableList<Fahrt> fahrtenListe; // Klassenvariable für die Fahrtenliste
    private ButtonType deleteButtonType = new ButtonType("Löschen", ButtonBar.ButtonData.APPLY);
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
        fahrtenListe.clear();
        fahrtenListe.addAll(fahrtenbuch.listeFahrten());
        // Erstellen und Konfigurieren der TableView und anderer UI-Komponenten
        fahrtenTabelle = new TableView<>(fahrtenListe);
        fahrtenTabelle.setId("fahrtenTabelle");
        fahrtenTabelle.setItems(fahrtenListe);
        // Weitere Konfigurationen des Buttons hier

        speichernButton.setId("saveButton");

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
            List<String> categories = cellData.getValue().getKategorien();
            String catToString = categories.stream().collect(Collectors.joining(", "));
            return new SimpleStringProperty(catToString);
        });

        fahrtenTabelle.getColumns().addAll(kfz, date, abf, ank, gefKM, aktivFZ, status, kateg);

        //ende tabellarische ansicht
        primaryStage.setTitle("Fahrtenbuch");
        setButton = new Button();
        setButton.setText("Settings");
        setButton.setId("settingsButton");

        setButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                switchToSettings(primaryStage);
                System.out.println("Testbutton");
            }
        });
        StackPane layoutFahrten = new StackPane();

        newEditButton = new Button();
        newEditButton.setText("Fahrt bearbeiten");
        newEditButton.setId("editButton");

        newEditButton.setOnAction(event -> {
            Fahrt ausgewaehlteFahrt = fahrtenTabelle.getSelectionModel().getSelectedItem();
            if (ausgewaehlteFahrt != null) {
                try {
                    bearbeiteFahrt(ausgewaehlteFahrt, primaryStage);
                }catch (DateTimeParseException d){
                    Alert dateAlert=new Alert(Alert.AlertType.WARNING);
                    dateAlert.setContentText("Wrong Format! use: DD:MM:YYYY or HH:MM:SS..");
                    d.printStackTrace();
                    dateAlert.showAndWait();

                }catch(NumberFormatException n){
                    Alert numberAlert=new Alert(Alert.AlertType.WARNING);
                    numberAlert.setContentText("Numeric entries only..");
                    numberAlert.showAndWait();
                    n.printStackTrace();

                }

            } else {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Keine Auswahl");
                alert.setHeaderText(null);
                alert.setContentText("Bitte wählen Sie zuerst eine Fahrt aus der Liste aus.");
                alert.showAndWait();
            }
        });

        newTripButton = new Button();
        newTripButton.setText("Neue Fahrt");
        newTripButton.setId("newTripButton");
        newTripButton.setStyle("-fx-background-colour: #00ff00");
        newTripButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                neueFahrt(primaryStage);
            }
        });

        // HBox für die Buttons erstellen
        HBox leftButtonBox = new HBox(10);
        leftButtonBox.getChildren().addAll(newTripButton,setButton,newEditButton);
        leftButtonBox.setAlignment(Pos.TOP_LEFT);
        leftButtonBox.setPadding(new javafx.geometry.Insets(4, 1, 10, 1));



        // Menü und Buttons im VBox platzieren
        VBox topBox = new VBox();
        topBox.getChildren().addAll(leftButtonBox);
        topBox.setAlignment(Pos.TOP_CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(fahrtenTabelle); // Ersetzen Sie createTableView() durch Ihre TableView-Initialisierung
        root.setTop(topBox);

        // dies würde bei jedem Klick einer Zeile das Edit-Fenster aufmachen
/*        fahrtenTabelle.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
             bearbeiteFahrt(newSelection, primaryStage);
            }
        });*/

        Scene fahrten = new Scene(root, 720, 400);
        primaryStage.setScene(fahrten);
        primaryStage.show();
    }

    private void neueFahrt(Stage primaryStage) {
        //Liste von zukünftigen LocalDates von wiederkehrenden Fahrten
        List<LocalDate> futureDates = new ArrayList<>();
        List<String> kategorienListe = new ArrayList<>();



        TextField kfzKennzeichen = new TextField();
        kfzKennzeichen.setPromptText("KFZ-Kennzeichen:");
        kfzKennzeichen.setMaxWidth(200);


        DatePicker datum = new DatePicker();
        datum.setPromptText("Datum der Fahrt");
        datum.getEditor().setDisable(true);
        datum.setMaxWidth(200);
        datum.setOnAction(event -> {
            datum.getValue();

        });

        TextField abfahrtsZeit = new TextField();
        abfahrtsZeit.setPromptText("Abfahrtszeit im Format HH:MM:SS");
        abfahrtsZeit.setMaxWidth(200);
        abfahrtsZeit.setTextFormatter(new TextFormatter<>(new TimeStringConverter()));


        TextField ankunftsZeit = new TextField();
        ankunftsZeit.setPromptText("Ankunftszeit im Format HH:MM:SS");
        ankunftsZeit.setMaxWidth(200);
        ankunftsZeit.setTextFormatter(new TextFormatter<>(new TimeStringConverter()));

        TextField gefahreneKilometer = new TextField();
        gefahreneKilometer.setPromptText("gefahrene Kilometer");
        gefahreneKilometer.setMaxWidth(200);
        gefahreneKilometer.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

        TextField aktiveFahrzeit = new TextField();
        aktiveFahrzeit.setPromptText("Fahrzeit in HH:MM:SS");
        aktiveFahrzeit.setMaxWidth(200);
        aktiveFahrzeit.setTextFormatter(new TextFormatter<>(new TimeStringConverter()));

// UI-Komponenten für die Kategorie
        TextField kategorienInput = new TextField();
        kategorienInput.setPromptText("Kategorien eingeben:");
        kategorienInput.setMaxWidth(200);

        TextArea angezeigteKategorien = new TextArea();
        angezeigteKategorien.setEditable(false);
        angezeigteKategorien.setVisible(false); // Anfangs nicht sichtbar machen
        angezeigteKategorien.setPrefHeight(50); // Höhe der TextArea anpassen

        Button kategorieHinzufuegenButton = new Button("Kategorie hinzufügen");
        kategorieHinzufuegenButton.setOnAction(event -> {
            String kategorie = kategorienInput.getText().trim();
            if (!kategorie.isEmpty()) {
                addToKategories(kategorie, kategorienListe::add); // Füge die Kategorie zur Liste hinzu
                angezeigteKategorien.setVisible(true); // TextArea sichtbar machen
                angezeigteKategorien.appendText(kategorie + "; "); // Kategorie zur TextArea hinzufügen
                kategorienInput.clear(); // Eingabefeld leeren
            }
        });

// Füge die Kategorien-Komponenten zur Benutzeroberfläche hinzu
        VBox kategorienBox = new VBox(10);
        kategorienBox.getChildren().addAll(kategorienInput, kategorieHinzufuegenButton, angezeigteKategorien);

        DatePicker future = new DatePicker();
        future.getEditor().setDisable(true);
        future.setPromptText("Zukünftige Fahrten");


        TextArea selectedDates = new TextArea();
        selectedDates.setDisable(true);
        selectedDates.setVisible(false);
        selectedDates.setPrefWidth(84);


        future.setOnAction(event -> {
            LocalDate date = future.getValue();
            addToReoccurances(date, futureDates::add);
            selectedDates.setVisible(true);
            selectedDates.setText(selectedDates.getText() + future.getValue().toString() + "; ");
            selectedDates.setPrefWidth(84 * futureDates.size());
        });

        selectedDates.setPrefHeight(20);

        HBox futureDatesBox = new HBox(10);
        futureDatesBox.getChildren().addAll(future, selectedDates);

        futureDatesBox.setVisible(false);

        ComboBox fahrtstatus = new ComboBox<>();
        fahrtstatus.setItems(FXCollections.observableArrayList(FahrtStatus.values()));
        fahrtstatus.setPromptText("Fahrtstatus:");
        fahrtstatus.setOnAction(event -> {
            if (FahrtStatus.ZUKUENFTIG.equals(fahrtstatus.getValue())) {

                futureDatesBox.setVisible(true);
            }
            else if (FahrtStatus.ABSOLVIERT.equals(fahrtstatus.getValue())){
                futureDatesBox.setVisible(false);
            }
            else if (FahrtStatus.AUF_FAHRT.equals(fahrtstatus.getValue())){
                futureDatesBox.setVisible(false);
            }

        });


        //SPACER BOX
        Box box = new Box(10, 30, 720);
        box.setVisible(true);

        VBox fahrtTextinputboxen = new VBox(1);
        fahrtTextinputboxen.getChildren().addAll(box, kfzKennzeichen, datum, abfahrtsZeit, ankunftsZeit,
                gefahreneKilometer, aktiveFahrzeit, fahrtstatus, futureDatesBox, kategorienBox
        );


        backButton = new Button();
        backButton.setText("<- Zurück");
        backButton.setOnAction(actionEvent -> start(primaryStage));
        ScrollPane scrollPane = new ScrollPane(fahrtTextinputboxen);
        scrollPane.setFitToWidth(true); // Passt die Breite der ScrollPane an die Breite der VBox an
        scrollPane.setPrefHeight(400); // Setzen Sie eine bevorzugte Höhe

        Label info = new Label("    Fahrtinformartionen unten eingeben");
        primaryStage.setTitle("Neue Fahrt");
        StackPane layoutNewTrip = new StackPane();
        layoutNewTrip.getChildren().add(scrollPane);
        backButton = new Button("<- Zurück");
        backButton.setOnAction(event -> start(primaryStage));
        layoutNewTrip.getChildren().add(backButton);
        layoutNewTrip.getChildren().add(speichernButton);
        layoutNewTrip.getChildren().add(info);

        speichernButton.setOnAction(event -> {
            // Sammeln der Benutzereingaben
            speichernButton.setId("saveButton");
            Boolean b=!(FahrtStatus.ZUKUENFTIG.equals(fahrtstatus.getValue()));
            if (!(FahrtStatus.ZUKUENFTIG.equals(fahrtstatus.getValue()))&&(kfzKennzeichen.getText().isEmpty() || abfahrtsZeit.getText().isEmpty() || ankunftsZeit.getText().isEmpty()
                    || gefahreneKilometer.getText().isEmpty() || aktiveFahrzeit.getText().isEmpty() || (fahrtstatus == null ))){
                Alert datumOrKennZ =new Alert(Alert.AlertType.WARNING);
                datumOrKennZ.setContentText("Pflichtdaten für Erstellung neuer Fahrt nicht eingetragen!");
                datumOrKennZ.showAndWait();
                return;
            }
            try {
                if (kfzKennzeichen.getText().isEmpty()||futureDates.isEmpty()||abfahrtsZeit.getText().isEmpty()||fahrtstatus==null){
                    Alert datumOrKennZ =new Alert(Alert.AlertType.WARNING);
                    datumOrKennZ.setContentText("Pflichtdaten für Erstellung zukünftiger Fahrt nicht eingetragen!");
                    datumOrKennZ.showAndWait();
                    return;
                }
                String kfzText = kfzKennzeichen.getText();
                LocalDate ausgewaehltesDatum = datum.getValue();
                LocalTime abfahrtsZeitValue = LocalTime.parse(abfahrtsZeit.getText());


                FahrtStatus ausgewaehlterStatus = (FahrtStatus) fahrtstatus.getValue();
                List<String> category = new ArrayList<>(kategorienListe);


            // Hinzufügen der neuen Fahrt zum Fahrtenbuch und zur fahrtenListe

                if (FahrtStatus.ZUKUENFTIG.equals(fahrtstatus.getValue())){

                    if(datum.getValue()!=null){
                        futureDates.add(datum.getValue());
                    }

                    fahrtenbuch.planeZukuenftigeFahrten(futureDates, kfzText, abfahrtsZeitValue, category);
                }
                else{
                    Double gefahreneKilometerValue = Double.parseDouble(gefahreneKilometer.getText());
                    LocalTime ankunftsZeitValue = LocalTime.parse(ankunftsZeit.getText());
                    LocalTime aktiveFahrzeitValue = LocalTime.parse(aktiveFahrzeit.getText());

                fahrtenbuch.neueFahrt(kfzText, ausgewaehltesDatum, abfahrtsZeitValue, ankunftsZeitValue,
                        gefahreneKilometerValue, aktiveFahrzeitValue, ausgewaehlterStatus, category);
            } }catch (IOException e) {
                throw new RuntimeException(e);
            } catch (DateTimeParseException d) {
            Alert dateAlert = new Alert(Alert.AlertType.WARNING);
            dateAlert.setContentText("Wrong Format! use: DD:MM:YYYY or HH:MM:SS..");
            dateAlert.showAndWait();

        }catch(NumberFormatException n){
            Alert numberAlert=new Alert(Alert.AlertType.WARNING);
            numberAlert.setContentText("Numeric entries only..");
            numberAlert.showAndWait();
        }

        });

        StackPane.setAlignment(speichernButton, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(info, Pos.TOP_LEFT);

        Scene neueFahrt = new Scene(layoutNewTrip, 720, 400);

        primaryStage.setScene(neueFahrt);
        primaryStage.show();


    }

    // Diese Methode wird aufgerufen, wenn ein Benutzer einen Eintrag zum Bearbeiten auswählt.
    private void bearbeiteFahrt(Fahrt ausgewaehlteFahrt, Stage primaryStage) {
        // Prüfen, ob eine Fahrt ausgewählt wurde
        if (ausgewaehlteFahrt == null) {
            // Fehlermeldung anzeigen
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Keine Fahrt ausgewählt");
            alert.setContentText("Bitte wählen Sie eine Fahrt aus der Liste aus.");
            alert.showAndWait();
            return;
        }

        // Dialogfenster für die Bearbeitung erstellen
        Dialog<Fahrt> dialog = new Dialog<>();
        dialog.setTitle("Fahrt bearbeiten");
        dialog.setHeaderText("Bearbeiten Sie die Details der ausgewählten Fahrt.");

        // Buttons setzen
        ButtonType speichernButtonType = new ButtonType(" Speichern", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(speichernButtonType, ButtonType.CANCEL);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType);


        // GridPane für die Eingabefelder
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField kfzKennzeichenField = new TextField(ausgewaehlteFahrt.getKfzKennzeichen());

        DatePicker datumPicker = new DatePicker(ausgewaehlteFahrt.getDatum());
        datumPicker.setConverter(new LocalDateStringConverter());
        datumPicker.getEditor().setDisable(true);

        TextField abfahrtszeitField = new TextField(ausgewaehlteFahrt.getAbfahrtszeit().toString());
        TextField aktiveFahrzeitField = new TextField(ausgewaehlteFahrt.getAktiveFahrzeit().toString());
        aktiveFahrzeitField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                aktiveFahrzeitField.setTextFormatter(new TextFormatter<>(new TimeStringConverter()));
            }
        });

        abfahrtszeitField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                abfahrtszeitField.setTextFormatter(new TextFormatter<>(new TimeStringConverter()));
            }
        });

        TextField ankunftszeitField = new TextField(ausgewaehlteFahrt.getAnkunftszeit().toString());

        ankunftszeitField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ankunftszeitField.setTextFormatter(new TextFormatter<>(new TimeStringConverter()));
            }
        });


        TextField gefahreneKilometerField = new TextField(String.valueOf(ausgewaehlteFahrt.getGefahreneKilometer()));
        gefahreneKilometerField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gefahreneKilometerField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));

            }
        });

        grid.add(new Label("KFZ-Kennzeichen:"), 0, 0);
        grid.add(kfzKennzeichenField, 1, 0);
        grid.add(new Label("Datum:"), 0, 1);
        grid.add(datumPicker, 1, 1);
        grid.add(new Label("Abfahrtszeit:"), 0, 2);
        grid.add(abfahrtszeitField, 1, 2);
        grid.add(new Label("Ankunftszeit:"), 0, 3);
        grid.add(ankunftszeitField, 1, 3);
        grid.add(new Label("Gefahrene Kilometer:"), 0, 4);
        grid.add(gefahreneKilometerField, 1, 4);
        grid.add(new Label("Aktive Fahrzeit:"), 0, 5);
        grid.add(aktiveFahrzeitField, 1, 5);
        // GridPane zum Dialog hinzufügen
        dialog.getDialogPane().setContent(grid);

        // Request focus auf das erste Eingabefeld
        Platform.runLater(() -> kfzKennzeichenField.requestFocus());


        // Ergebnis des Dialogs konvertieren, wenn der Benutzer "Speichern" klickt
        dialog.setResultConverter(dialogButton -> {

            if (dialogButton == speichernButtonType) {

                    ausgewaehlteFahrt.setKfzKennzeichen(kfzKennzeichenField.getText());

                    try {
                        LocalDate temp = datumPicker.getValue();
                        ausgewaehlteFahrt.setDatum(temp);
                        ausgewaehlteFahrt.setAbfahrtszeit(LocalTime.parse(abfahrtszeitField.getText()));
                        ausgewaehlteFahrt.setAnkunftszeit(LocalTime.parse(ankunftszeitField.getText()));
                        ausgewaehlteFahrt.setGefahreneKilometer(Double.parseDouble(gefahreneKilometerField.getText()));
                        ausgewaehlteFahrt.setAktiveFahrzeit(LocalTime.parse(aktiveFahrzeitField.getText()));
                    } catch (DateTimeParseException d) {
                        Alert dateAlert = new Alert(Alert.AlertType.WARNING);
                        dateAlert.setContentText("Wrong Format! use: DD:MM:YYYY or HH:MM:SS..");
                        dateAlert.showAndWait();

                    }catch(NumberFormatException n){
                        Alert numberAlert=new Alert(Alert.AlertType.WARNING);
                        numberAlert.setContentText("Numeric entries only..");
                        numberAlert.showAndWait();


                    }







                // ... Aktualisieren Sie weitere Eigenschaften ...
                try {
                    fahrtenbuch.exportFahrt();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else if (dialogButton == deleteButtonType) {
                //Bestätigungsdialog
                Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationDialog.setTitle("Bestätigung");
                confirmationDialog.setHeaderText("Löschen bestätigen");
                confirmationDialog.setContentText("Möchten Sie die ausgewählte Fahrt wirklich löschen?");

                confirmationDialog.initModality(Modality.APPLICATION_MODAL);
                confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                ButtonType result = confirmationDialog.showAndWait().orElse(ButtonType.NO);
                if (result == ButtonType.YES) {
                    fahrtenbuch.loescheFahrten(ausgewaehlteFahrt.getKfzKennzeichen(), ausgewaehlteFahrt.getDatum(), ausgewaehlteFahrt.getAbfahrtszeit());
                    fahrtenTabelle.getItems().remove(ausgewaehlteFahrt);
                    try {
                        fahrtenbuch.exportFahrt();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            return null;
        });

        // Dialog anzeigen und warten, bis der Benutzer ihn schließt


            Optional<Fahrt> result = dialog.showAndWait();



            result.ifPresent(fahrt -> {
                // Aktualisierte Fahrt in der Liste und in der TableView anzeigen
                fahrtenTabelle.refresh();
                dialog.close();
                // Eventuell Änderungen im Fahrtenbuch speichern oder weitere Aktionen ausführen
            });




    }

    private void addToReoccurances(LocalDate date, Consumer<LocalDate> addFutureDate) {
        addFutureDate.accept(date);
    }

    private void addToKategories(String kate, Consumer<String> addKategorie) {
        addKategorie.accept(kate);
    }

    private void switchToSettings(Stage primaryStage) {
        backButton = new Button();
        backButton.setText("<- Zurück");
        backButton.setOnAction(actionEvent -> start(primaryStage));
        TextField enterSavePath = new TextField();
        enterSavePath.setText("Hier eingeben:");
        enterSavePath.setStyle("-fx-text-fill: grey;");
        enterSavePath.setMaxWidth(200);
        Label Pfad = new Label("Speicherpfad: ");

        primaryStage.setTitle("Einstellungen");
        StackPane layoutSettings = new StackPane();
        layoutSettings.getChildren().addAll(enterSavePath, backButton, Pfad);
        layoutSettings.setAlignment(backButton, Pos.BOTTOM_LEFT);
        layoutSettings.setAlignment(enterSavePath, Pos.CENTER);
        layoutSettings.setAlignment(Pfad, Pos.CENTER_LEFT);
        layoutSettings.requestFocus();

        Scene einstellungen = new Scene(layoutSettings, 720, 400);
        primaryStage.setScene(einstellungen);
        Platform.runLater(() -> layoutSettings.requestFocus());
        primaryStage.show();
    }
}
