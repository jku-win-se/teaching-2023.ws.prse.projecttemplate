package at.jku.se.prse.team3;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SimpleTableViewExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Daten erstellen
        ObservableList<Person> data = FXCollections.observableArrayList(
                new Person("John", "Doe", 25),
                new Person("Jane", "Doe", 30),
                new Person("Bob", "Smith", 22)
        );

        // TableView und Spalten erstellen
        TableView<Person> tableView = new TableView<>(data);

        TableColumn<Person, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        TableColumn<Person, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, ageColumn);

        // Layout erstellen und TableView hinzufügen
        StackPane root = new StackPane();
        root.getChildren().add(tableView);

        // Szene erstellen und der Bühne hinzufügen
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Simple TableView Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class Person {
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleIntegerProperty age;

        public Person(String firstName, String lastName, int age) {
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);
            this.age = new SimpleIntegerProperty(age);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public SimpleStringProperty firstNameProperty() {
            return firstName;
        }

        public String getLastName() {
            return lastName.get();
        }

        public SimpleStringProperty lastNameProperty() {
            return lastName;
        }

        public int getAge() {
            return age.get();
        }

        public SimpleIntegerProperty ageProperty() {
            return age;
        }
    }
}
