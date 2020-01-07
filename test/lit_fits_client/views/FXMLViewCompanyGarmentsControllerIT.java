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
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import lit_fits_client.ApplicationMain;

/**
 * Test Class for the company's garments wiew and controller
 *
 * @author Carlos Rafael Mendez Gonzalez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLViewCompanyGarmentsControllerIT extends ApplicationTest {
    public FXMLViewCompanyGarmentsControllerIT() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationMain().start(stage);
        clickOn("#txtUsername");
        write("111111111"); // Should create a company with this nif
        clickOn("#fieldPassword");
        write("abcd*1234"); // Should create a company with this password
        clickOn("#btnLogin");
        clickOn("#btnWarehouse");
    }

    @After
    public void close() {
        try {
            stop();
        } catch (Exception ex) {
            Logger.getLogger(FXMLViewCompanyGarmentsControllerIT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of onBtnCancelPress method, of class FXMLCompanyGarmentsController.
     */
    @Test
    public void testA_OnBtnCancelPress() {
        clickOn("#btnCancel");
        verifyThat("#borderPaneMainMenu", isVisible());
        close();
    }

    /**
     * Test of onBtnDeletePress method, of class FXMLCompanyGarmentsController.
     */
    @Test
    public void testB_OnBtnDeletePress() {
        // Click on a garment from the table first
        clickOn("#buttonDelete");
        // How to check that the garment was deleted
        close();
    }

    /**
     * Test of onBtnPromotePress method, of class FXMLCompanyGarmentsController.
     */
    @Test
    public void testC_OnBtnPromotePress() {
        // Click on a garment from the table first
        clickOn("#buttonPromote");
        // How to check that the garment was promoted (requested)
        close();
    }

    /**
     * Test of onBtnModifyPress method, of class FXMLCompanyGarmentsController.
     */
    @Test
    public void testD_OnBtnModifyPress() {
        // Click on a garment from the table first
        clickOn("#buttonModify");
        verifyThat("#borderPaneRegisterGarment", isVisible());
        close();
    }

    /**
     * Test of onBtnAddPress method, of class FXMLCompanyGarmentsController.
     */
    @Test
    public void testE_OnBtnAddPress() {
        clickOn("#buttonAdd");
        verifyThat("#borderPaneRegisterGarment", isVisible());
        close();
    }

    /**
     * Tests the mnemonic for canceling
     */
    @Test
    public void testF_AltC() {
        press(KeyCode.ALT, KeyCode.C);
        verifyThat("#borderPaneMainMenu", isVisible());
        close();
    }

    /**
     * Tests the mnemonic for deleting
     */
    @Test
    public void testG_AltD() {
        // Click on a garment from the table first
        press(KeyCode.ALT, KeyCode.D);
        // How to check that the garment was deleted
        close();
    }

    /**
     * Tests the mnemonic for promoting
     */
    @Test
    public void testH_AltP() {
        // Click on a garment from the table first
        press(KeyCode.ALT, KeyCode.P);
        // How to check that the garment was promoted (requested)
        close();
    }

    /**
     * Tests the mnemonic for modifying
     */
    @Test
    public void testI_AltM() {
        // Click on a garment from the table first
        press(KeyCode.ALT, KeyCode.M);
        verifyThat("#borderPaneRegisterGarment", isVisible());
        close();
    }

    /**
     * Tests the mnemonic for adding
     */
    @Test
    public void testJ_AltA() {
        // Click on a garment from the table first
        press(KeyCode.ALT, KeyCode.A);
        verifyThat("#borderPaneRegisterGarment", isVisible());
        close();
    }
}
