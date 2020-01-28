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
import static org.testfx.matcher.base.NodeMatchers.isEnabled;

/**
 * Test Class for the main menu view and controller
 *
 * @author Carlos Rafael Mendez Gonzalez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLViewCompanyMainMenuControllerIT extends ApplicationTest {
    public FXMLViewCompanyMainMenuControllerIT() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationMain().start(stage);
        clickOn("#txtUsername");
        write("A1111111A");
        clickOn("#fieldPassword");
        write("abcd");
        clickOn("#btnLogin");
    }

    @After
    public void close() {
        try {
            stop();
        } catch (Exception ex) {
            Logger.getLogger(FXMLViewCompanyMainMenuControllerIT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Tests opening the account modification view
     */
    @Test
    public void testA_OnBtnModifyAccountPress() {
        clickOn("#btnModifyAccount");
        verifyThat("#borderPaneRegisterCompany", isVisible());
        close();
    }

    /**
     * Tests opening the warehouse view
     */
    @Test
    public void testB_OnBtnWarehousePress() {
        clickOn("#btnWarehouse");
        verifyThat("#borderPaneWarehouse", isVisible());
        close();
    }

    /**
     * Tests logging out
     */
    @Test
    public void testC_OnBtnLogOutPress() {
        clickOn("#btnLogOut");
        verifyThat("#borderPaneLogin", isVisible());
        close();
    }

    /**
     * Tests the mnemonic for checking the warehouse
     */
    @Test
    public void testD_AltC() {
        press(KeyCode.ALT, KeyCode.C);
        verifyThat("#borderPaneWarehouse", isVisible());
        close();
    }

    /**
     * Tests the mnemonic for modifying the account
     */
    @Test
    public void testE_AltM() {
        press(KeyCode.ALT, KeyCode.M);
        verifyThat("#borderPaneRegisterCompany", isVisible());
        close();
    }

    /**
     * Tests the mnemonic for logging out
     */
    @Test
    public void testF_AltL() {
        press(KeyCode.ALT, KeyCode.L);
        verifyThat("#borderPaneLogin", isVisible());
        close();
    }
    /**
     * Tests that the date picker is usable
     */
    @Test
    public void testG_DatePicker() {
        clickOn("#datePicker");
        verifyThat("#borderPaneLogin", isEnabled());
        close();
    }
}
