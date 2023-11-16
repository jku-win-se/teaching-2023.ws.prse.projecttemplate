package com.example.fahrtenbuch;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PageController{

    private IndexController ic = new IndexController();

    @FXML
    void fahrtenbucherAction(ActionEvent event) throws IOException {
        ic.sceneChange("FahrtenbucherPage.fxml", event);
    }

    @FXML
    void newFahrtAction(ActionEvent event) throws IOException {
        ic.sceneChange("Index.fxml", event);
    }

    @FXML
    private void handleBtnOverview(ActionEvent event) throws IOException {
        sceneChange("Overview.fxml", event);
    }

    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {;
        sceneChange("hello-view.fxml",event);
    }

    @FXML
    private void handleBtnDataAction (ActionEvent event) throws IOException {
        sceneChange("DataAction.fxml",event);
    }

    public void sceneChange(String url, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
