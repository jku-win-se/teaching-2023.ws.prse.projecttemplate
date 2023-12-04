package at.jku.se.prse.team3;

import com.opencsv.exceptions.CsvValidationException;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class App {
    public static void main(String[] args) throws IOException, CsvValidationException, InterruptedException {
        System.out.println("  ___     _        _            _            _   ");
        System.out.println(" | __|_ _| |_  _ _| |_ ___ _ _ | |__ _  _ __| |_  ");
        System.out.println(" | _/ _` | ' \\| '_|  _/ -_) ' \\| '_ \\ || / _| ' \\ ");
        System.out.println(" |_|\\__,_|_||_|_|  \\__\\___|_||_|_.__/\\_,_\\__|_||_|");
        System.out.println("         by  TEAM 3 - JKU   release 0.0.3                   ");



        Fahrtenbuch fahrtenbuch = new Fahrtenbuch();
        fahrtenbuch.importFahrt();

    Platform.startup(()->

    {
        FahrtenbuchUI fahrtenbuchUI = new FahrtenbuchUI(fahrtenbuch);
        Stage stage = new Stage();
        fahrtenbuchUI.start(stage);
    });

        fahrtenbuch.exportFahrt();
}


    public abstract void start(Stage primaryStage);

}
