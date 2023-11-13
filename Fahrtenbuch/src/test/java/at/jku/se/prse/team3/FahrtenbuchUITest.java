package at.jku.se.prse.team3;


import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Test;



public class FahrtenbuchUITest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        FahrtenbuchUI app = new FahrtenbuchUI(new Fahrtenbuch());
        app.start(stage);
    }


    @Test
    public void testEditButton() {

        clickOn("#fahrtenTabelle");
        clickOn("#editButton");


    }

    @Test
    public void testAddNewTripDialog() {
        clickOn("#newTripButton");
        clickOn("#saveButton");

    }
    @Test
    public void testDeleteButton() {

        clickOn("#fahrtenTabelle");

        clickOn("#deleteButton");


    }
    @Test
    public void testSettingsButton() {
        clickOn("#settingsButton");
    }

}

