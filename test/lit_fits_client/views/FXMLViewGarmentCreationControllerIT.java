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
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import lit_fits_client.ApplicationMain;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * Test Class for the garment creation view and controller
 *
 * @author Carlos Rafael Mendez Gonzalez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLViewGarmentCreationControllerIT extends ApplicationTest {
    public FXMLViewGarmentCreationControllerIT() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationMain().start(stage);
        clickOn("#txtUsername");
        write("A1111111A");
        clickOn("#fieldPassword");
        write("abcd");
        clickOn("#btnLogin");
        clickOn("#btnWarehouse");
        clickOn("#btnAdd");
    }

    @After
    public void close() {
        try {
            stop();
        } catch (Exception ex) {
            Logger.getLogger(FXMLViewGarmentCreationControllerIT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of onBtnCancelPress method, of class FXMLViewCreateModifyGarmentController.
     */
    @Test
    public void testA_OnBtnCancelPress() {
        clickOn("#buttonAdd");
        clickOn("#btnCancel");
        verifyThat("#borderPaneWarehouse", isVisible());
        close();
    }

    /**
     * Tests that the submit button is disabled without entering any data
     */
    @Test
    public void testB_BtnSubmitIsDisabled() {
        clickOn("#buttonAdd");
        clickOn("#btnSubmit");
        verifyThat("#btnSubmit", isDisabled());
        close();
    }

    /**
     * Tests that the button submit is disabled when the strings in the fields are too long
     */
    @Test
    public void testC_BtnSubmitIsDisabledByLenghtOfData() {
        clickOn("#buttonAdd");
        clickOn("#txtBarcode");
        write("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        clickOn("#txtPrice");
        write("1");
        clickOn("#txtDesigner");
        write("Ann Demeulemeester");
        clickOn("#comboBodyPart");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboColors");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboGarmentType");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboMaterials");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboMood");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        verifyThat("#btnSubmit", isDisabled());
        close();
    }

    /**
     * Tests the mnemonic for submitting
     */
    @Test
    public void testD_AltR() {
        clickOn("#buttonAdd");
        // Yet to implement adding a picture to the garment
        clickOn("#txtBarcode");
        write("0123456789");
        clickOn("#txtPrice");
        write("1,11$");
        clickOn("#txtDesigner");
        write("Rick Owens");
        clickOn("#comboBodyPart");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboColors");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboGarmentType");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboMaterials");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboMood");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        press(KeyCode.ALT, KeyCode.S);
        verifyThat("#borderPaneWarehouse", isVisible());
        verifyThat("0123456789", isVisible());
        close();
    }

    /**
     * Tests that the register is successful
     */
    @Test
    public void testE_RegisterSuccess() {
        clickOn("#buttonAdd");
        // Yet to implement adding a picture to the garment
        clickOn("#txtBarcode");
        write("0123456789");
        clickOn("#txtPrice");
        write("1,11$");
        clickOn("#txtDesigner");
        write("Rick Owens");
        clickOn("#comboBodyPart");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboColors");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboGarmentType");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboMaterials");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboMood");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        press(KeyCode.ALT, KeyCode.S);
        verifyThat("#borderPaneWarehouse", isVisible());
        verifyThat("0123456789", isVisible());
        close();
    }

    /**
     * Tests that the submit is not successful by using an existing garment
     */
    @Test
    public void testF_GarmentExists() {
        clickOn("#buttonAdd");
        // Yet to implement adding a picture to the garment
        clickOn("#txtBarcode");
        write("0123456789");
        clickOn("#txtPrice");
        write("1,11$");
        clickOn("#txtDesigner");
        write("Rick Owens");
        clickOn("#comboBodyPart");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboColors");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboGarmentType");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboMaterials");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#comboMood");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        press(KeyCode.ALT, KeyCode.S);
        verifyThat("#borderPaneWarehouse", isVisible());
        verifyThat("Create exception", isVisible());
        // Create exception or something like that
        close();
    }

    /**
     * Tests the mnemonic for canceling
     */
    @Test
    public void testG_AltC() {
        press(KeyCode.ALT, KeyCode.C);
        verifyThat("#borderPaneWarehouse", isVisible());
        close();
    }
}
