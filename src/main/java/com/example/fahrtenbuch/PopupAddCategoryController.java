package com.example.fahrtenbuch;

import com.example.fahrtenbuch.business.CategoryFacade;
import com.example.fahrtenbuch.entities.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopupAddCategoryController {

    @FXML
    private TextField newCategoryTextField;

    private CategoryFacade categoryFacade;

    public PopupAddCategoryController() {
        categoryFacade = new CategoryFacade();
    }

    public void addNewCategory(ActionEvent actionEvent) {
        String categoryName = newCategoryTextField.getText();

        if (!categoryName.isEmpty()) {
            Category newCategory = new Category();
            newCategory.setName(categoryName);

            categoryFacade.persistCategory(newCategory);

            newCategoryTextField.clear();
        } else {

            showAlert(Alert.AlertType.ERROR, "Fehler", "Felder dürfen nicht leer sein.", "Bitte füllen Sie alle Felder aus.");
        }
    }

    @FXML
    public void closePopup(ActionEvent actionEvent) {

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}