//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.fahrtenbuch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LogbookApplication extends Application {
    public LogbookApplication() {
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LogbookApplication.class.getResource("/com/example/fahrtenbuch/hello-view.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load(), 915.0, 584.0);
        stage.setTitle("Fahrtenbuch");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(new String[0]);
    }
}
