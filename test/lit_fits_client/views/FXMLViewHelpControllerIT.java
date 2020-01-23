package lit_fits_client.views;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import lit_fits_client.ApplicationMain;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * Test Class for the Help Controller
 *
 * @author Carlos Rafael Mendez Gonzalez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLViewHelpControllerIT extends ApplicationTest {
    public FXMLViewHelpControllerIT() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationMain().start(stage);
        clickOn("#rBtnCompany");
        clickOn("#btnRegister");
        clickOn("#btnHelp");
    }

    @After
    public void close() {
        try {
            stop();
        } catch (Exception ex) {
            Logger.getLogger(FXMLViewHelpControllerIT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of onBtnCancelPress method, of class FXMLViewRegisterController.
     */
    @Test
    public void testA_OnBtnCancelPress() {
        clickOn("#btnCancel");
        verifyThat("#borderPaneLogin", isVisible());
        close();
    }

    /**
     * Tests the mnemonic for canceling
     */
    @Test
    public void testB_AltC() {
        press(KeyCode.ALT, KeyCode.C);
        verifyThat("#borderPaneLogin", isVisible());
        close();
    }
}
