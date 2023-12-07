package com.example.fahrtenbuch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class PopupAddCategoryController {
    public TextField popUpPane;

    public void addNewCategory(ActionEvent actionEvent) {
    }/*
    @FXML
    private TextField newCategoryTextField;

    private IndexController indexController;

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }

    @FXML
    private void addNewCategory(ActionEvent event) {
        String newCategory = newCategoryTextField.getText().trim();

        if (!newCategory.isEmpty()) {
            // Hier könntest du die Logik zum Hinzufügen der neuen Kategorie implementieren
            // Z.B., füge die Kategorie zur ComboBox im IndexController hinzu
            indexController.addCategory(newCategory);

            // Schließe das Popup-Fenster
            closePopup();
        }
    }

    @FXML
    private void closePopup() {
        // Hier könntest du die Logik zum Schließen des Popup-Fensters implementieren
        Stage stage = (Stage) newCategoryTextField.getScene().getWindow();
        stage.close();
    }*/

    public void closePopup(ActionEvent actionEvent) {
    }
}