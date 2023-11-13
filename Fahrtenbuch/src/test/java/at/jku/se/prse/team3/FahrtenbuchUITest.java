package at.jku.se.prse.team3;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;

// TODO: bugfix clickOn("fahrtenTabelle") funktioniert irgendwie nicht => es selektiert keine Zeile
public class FahrtenbuchUITest extends ApplicationTest {
    Fahrtenbuch fahrtenbuch = new Fahrtenbuch();
    FahrtenbuchUI app;

    @Override
    public void start(Stage stage) {
        try {
            fahrtenbuch.neueFahrt("Lv-254", LocalDate.now(), LocalTime.now(), LocalTime.now(), 10d, LocalTime.now(), FahrtStatus.ABSOLVIERT, Arrays.asList(""));
            fahrtenbuch.neueFahrt("GB-214", LocalDate.now(), LocalTime.now(), LocalTime.now(), 10d, LocalTime.now(), FahrtStatus.ABSOLVIERT, Arrays.asList(""));
        } catch (IOException e) {
            System.out.println("FahrtenbuchUITest: Konnte keine neue Fahrten zum Testen eintragen:" + e);
        }
        app = new FahrtenbuchUI(fahrtenbuch);
        app.start(stage);
    }

    @Test
    public void testEditButton() {
        verifyThat("#fahrtenTabelle", NodeMatchers.isVisible());
        clickOn("#fahrtenTabelle");

        TableView tableView = lookup("#fahrtenTabelle").queryAs(TableView.class);

        //clickOn(".table-row").clickOn(MouseButton.PRIMARY);
        if (!tableView.getSelectionModel().isEmpty()) {
            Fahrt selectedFahrt = (Fahrt) tableView.getSelectionModel().getSelectedItem();
            System.out.println("Fahrt ausgewählt:" + selectedFahrt.getKfzKennzeichen());

            clickOn("#editButton");
            verifyThat("#kfzKennzeichenEditField", TextInputControlMatchers.hasText(selectedFahrt.getKfzKennzeichen()));

        } else {
            System.out.println("Keine Fahrt ausgewählt.");
        }
    }

    @Test
    public void testAddNewTripDialog() {
        clickOn("#newTripButton");
        verifyThat("#saveButton", NodeMatchers.isVisible());

        //TODO befülle alle felder für eine neue Fahrt
        TextField kfzField = lookup("#kfzKennzeichenAddField").query();
        clickOn(kfzField);
        write("LVA-111");

        //klicke dann auf Speichern Button
        //  clickOn("#saveButton");

        //Überprüfe danach, ob die neue Fahrt tatsächlich, in der Tabelle erscheint
        TableView tableView = lookup("#fahrtenTabelle").queryAs(TableView.class);
        ObservableList<Fahrt> items = tableView.getItems();
        boolean neueFahrtVorhanden = items.stream()
                .anyMatch(fahrt -> fahrt.getKfzKennzeichen().equals("LVA-111"));

        //Test schlägt dann grün falls die neue Fahrt in der Tabelle gefunden wurde
        assertTrue(neueFahrtVorhanden);
    }

    @Test
    public void testDeleteButton() {
        clickOn("#fahrtenTabelle");
        TableView tableView = lookup("#fahrtenTabelle").queryAs(TableView.class);
        if (!tableView.getSelectionModel().isEmpty()) {
            Fahrt ausgewaehlterFahrt = (Fahrt) tableView.getSelectionModel().getSelectedItem();
            clickOn("#editButton");
            clickOn("#deleteButton");
            verifyThat("Löschen bestätigen", NodeMatchers.isVisible());
            clickOn("Löschen");

            ObservableList<Fahrt> fahrten = tableView.getItems();
            boolean fahrtNichtVorhanden = fahrten.stream().noneMatch(fahrt ->
                            fahrt.getKfzKennzeichen().equals(ausgewaehlterFahrt.getKfzKennzeichen())
            );
            assertTrue(fahrtNichtVorhanden);
        } else {
            System.out.println("Keine Fahrt ausgewählt.");
        }
    }

    @Test
    public void testSettingsButton() {
        //clickOn("#settingsButton");
    }

}

