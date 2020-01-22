package lit_fits_client.views;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import lit_fits_client.ApplicationMain;
import org.testfx.framework.junit5.ApplicationTest;

/**
 *
 * @author Ander Rodriguez & Carlos Mendez
 */
public class FXMLViewLoginControllerIT extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationMain().start(stage);
    }

    /**
     * Test of
     */
    @Test
    public void testBtnLoginIsInitiallyDisabled() {
        verifyThat("#btnLogin", isDisabled());
    }

    /**
     * Test of button register is initially enabled
     */
    @Test
    public void testBtnRegisterIsEnabled() {
        verifyThat("#btnRegister", isEnabled());
    }

    /**
     * Test of
     */
    @Test
    public void testBtnLoginGetsEnabled() {
        clickOn("#txtUsername");
        write("Username");
        clickOn("#fieldPassword");
        write("abcd*1234");
        verifyThat("#btnLogin", isEnabled());
    }

    /**
     * Test of
     */
    @Test
    public void testBtnLoginAction() {
        clickOn("#txtUsername");
        write("Ander");
        clickOn("#fieldPassword");
        write("abcd*1234");
        clickOn("#btnLogin");
        verifyThat("#borderPaneMain", isVisible());
    }

    /**
     * Test the action of button register do
     */
    @Test
    public void testBtnRegisterAction() {
        clickOn("#btnRegister");
        verifyThat("#borderPaneRegister", isVisible());
    }

    /**
     * Test of
     */
    @Test
    public void testAltL() {
        clickOn("#txtUsername");
        write("Ander");
        clickOn("#fieldPassword");
        write("abcd*1234");
        press(KeyCode.ALT, KeyCode.L);
        verifyThat("#borderPaneMain", isVisible());
    }

    /**
     * Test of
     */
    @Test
    public void testAltS() {
        press(KeyCode.ALT, KeyCode.S);
        verifyThat("#borderPaneRegister", isVisible());
    }

    @Test
    public void testLength() {
        clickOn("#txtUsername");
        write("test of lengthhhhhhhhhhhhhhhhhhh");
        verifyThat("#btnLogin", isDisabled());
    }

    @Test
    public void testUndo() {
        clickOn("#txtUsername");
        write("test of undo");
        clickOn("#btnUndo");
    }
}
