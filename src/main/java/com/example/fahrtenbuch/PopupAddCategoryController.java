package com.example.fahrtenbuch;

import com.example.fahrtenbuch.business.CategoryFacade;
import com.example.fahrtenbuch.entities.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

            System.out.println("Kategorie erfolgreich hinzugefügt: " + newCategory);

            newCategoryTextField.clear();
        } else {

            System.out.println("Kategorienamen dürfen nicht leer sein.");
        }
    }

    @FXML
    public void closePopup(ActionEvent actionEvent) {

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}