package at.jku.se.prse.team3;

import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.NodeQueryUtils;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.ButtonMatchers.isDefaultButton;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.NodeQueryUtils.hasText;


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

