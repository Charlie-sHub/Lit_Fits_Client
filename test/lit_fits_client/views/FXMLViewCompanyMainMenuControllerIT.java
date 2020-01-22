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
        write("111111111"); // Should create a company with this nif
        clickOn("#fieldPassword");
        write("abcd*1234"); // Should create a company with this password
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
     * Test of OnBtnModifyAccountPress method, of class FXMLCompanyMainMenuController.
     */
    @Test
    public void testA_OnBtnModifyAccountPress() {
        clickOn("#btnModifyAccount");
        verifyThat("#borderPaneRegisterCompany", isVisible());
        close();
    }

    /**
     * Test of OnBtnWarehousePress method, of class FXMLCompanyMainMenuController.
     */
    @Test
    public void testB_OnBtnWarehousePress() {
        clickOn("#btnWarehouse");
        verifyThat("#borderPaneWarehouse", isVisible());
        close();
    }

    /**
     * Test of OnBtnLogOutPress method, of class FXMLCompanyMainMenuController.
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
     * Tests the mnemonic for loggin out
     */
    @Test
    public void testF_AltL() {
        press(KeyCode.ALT, KeyCode.L);
        verifyThat("#borderPaneLogin", isVisible());
        close();
    }
}
