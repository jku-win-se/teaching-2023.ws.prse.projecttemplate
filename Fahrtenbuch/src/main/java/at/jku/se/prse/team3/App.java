package at.jku.se.prse.team3;

import com.opencsv.exceptions.CsvValidationException;
import javafx.application.Platform;
import javafx.stage.Stage;


import java.io.IOException;


public abstract class App {
    public static void main(String[] args) throws IOException, CsvValidationException, InterruptedException {




        Fahrtenbuch fahrtenbuch = new Fahrtenbuch();
        fahrtenbuch.importFahrt();

    Platform.startup(()->

    {
        FahrtenbuchUI fahrtenbuchUI = new FahrtenbuchUI(fahrtenbuch);
        Stage stage = new Stage();
        try {
            fahrtenbuchUI.start(stage);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });

        //fahrtenbuch.exportFahrt();
}


    public abstract void start(Stage primaryStage);

}
