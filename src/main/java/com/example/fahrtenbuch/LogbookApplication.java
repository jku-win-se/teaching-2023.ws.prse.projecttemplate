package com.example.fahrtenbuch;

import com.example.fahrtenbuch.business.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LogbookApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DatabaseConnection db = new DatabaseConnection();
        try {
            db.initiateDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(LogbookApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 915, 584);
        stage.setTitle("Fahrtenbuch");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

