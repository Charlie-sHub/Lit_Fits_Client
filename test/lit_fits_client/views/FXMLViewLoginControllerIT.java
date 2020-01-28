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
 * Test class for the login view
 *
 * @author Carlos Mendez
 */
public class FXMLViewLoginControllerIT extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationMain().start(stage);
    }

    /**
     * Tests the login button is disabled if no data has been entered
     */
    @Test
    public void testBtnLoginIsInitiallyDisabled() {
        verifyThat("#btnLogin", isDisabled());
    }

    /**
     * Tests the register button is enabled
     */
    @Test
    public void testBtnRegisterIsEnabled() {
        verifyThat("#btnRegister", isEnabled());
    }

    /**
     * Tests the login button gets enabled when entering correct data
     */
    @Test
    public void testBtnLoginGetsEnabled() {
        clickOn("#txtUsername");
        write("A1111111A");
        clickOn("#fieldPassword");
        write("abcd");
        verifyThat("#btnLogin", isEnabled());
    }

    /**
     * Tests that an actual login attempt is successful
     */
    @Test
    public void testBtnLoginAction() {
        clickOn("#txtUsername");
        write("A1111111A");
        clickOn("#fieldPassword");
        write("abcd");
        clickOn("#btnLogin");
        verifyThat("#borderPaneMain", isVisible());
    }

    /**
     * Tests that the register view of the company opens
     */
    @Test
    public void testCompanyBtnRegisterAction() {
        clickOn("#rBtnCompany");
        clickOn("#btnRegister");
        verifyThat("#borderPaneRegister", isVisible());
    }

    /**
     * Tests that the register view of the expert opens
     */
    @Test
    public void testExpertBtnRegisterAction() {
        clickOn("#rBtnFashionExpert");
        clickOn("#btnRegister");
        verifyThat("#borderPaneRegister", isVisible());
    }

    /**
     * Tests the mnemonic for the login
     */
    @Test
    public void testAltL() {
        clickOn("#txtUsername");
        write("A1111111A");
        clickOn("#fieldPassword");
        write("abcd");
        press(KeyCode.ALT, KeyCode.L);
        verifyThat("#borderPaneMain", isVisible());
    }

    /**
     * Tests the mnemonic for the register
     */
    @Test
    public void testAltS() {
        clickOn("#rBtnCompany");
        press(KeyCode.ALT, KeyCode.S);
        verifyThat("#borderPaneRegister", isVisible());
    }

    /**
     * Tests that the login button becomes disabled when entering data that's too long
     */
    @Test
    public void testLength() {
        clickOn("#txtUsername");
        write("A111111111111111111111111111111111111111111111111111111111111111111111111");
        clickOn("#txtPassword");
        write("abcd");
        verifyThat("#btnLogin", isDisabled());
    }
    /**
     * Tests that redo is enabled after undoing
     */
    @Test
    public void testUndo() {
        clickOn("#txtUsername");
        write("test of undo");
        clickOn("#btnUndo");
        verifyThat("#btnRedo", isEnabled());
    }
}
