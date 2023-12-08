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




        Fahrtenbuch fahrtenbuch = new Fahrtenbuch();
        fahrtenbuch.importFahrt();

    Platform.startup(()->

    {
        FahrtenbuchUI fahrtenbuchUI = new FahrtenbuchUI(fahrtenbuch);
        Stage stage = new Stage();
        fahrtenbuchUI.start(stage);
    });

        //fahrtenbuch.exportFahrt();
}


    public abstract void start(Stage primaryStage);

}
