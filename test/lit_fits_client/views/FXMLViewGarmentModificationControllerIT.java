package lit_fits_client.views;

import java.util.Set;
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
import lit_fits_client.entities.BodyPart;
import lit_fits_client.entities.Color;
import lit_fits_client.entities.Garment;
import lit_fits_client.entities.GarmentType;
import lit_fits_client.entities.Material;
import lit_fits_client.entities.Mood;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * Test Class for the garment modification wiew and controller
 *
 * @author Carlos Rafael Mendez Gonzalez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLViewGarmentModificationControllerIT extends ApplicationTest {
    public FXMLViewGarmentModificationControllerIT() {
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
            Logger.getLogger(FXMLViewGarmentModificationControllerIT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of onBtnCancelPress method, of class FXMLViewCreateModifyGarmentController.
     */
    @Test
    public void testA_OnBtnCancelPress() {
        clickOn("#buttonModify");
        clickOn("#btnCancel");
        verifyThat("#borderPaneWarehouse", isVisible());
        close();
    }

    /**
     * Tests that the submit button is disabled without entering any data
     */
    @Test
    public void testB_BtnSubmitIsDisabled() {
        clickOn("#buttonModify");
        clickOn("#btnSubmit");
        verifyThat("#btnSubmit", isDisabled());
        close();
    }

    /**
     * Tests that the button submit is disabled when the strings in the fields are too long
     */
    @Test
    public void testC_BtnSubmitIsDisabledByLenghtOfData() {
        clickOn("#buttonModify");
        clickOn("#txtBarcode");
        write("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        clickOn("#txtPrice");
        write("1");
        clickOn("#txtDesigner");
        write("Some Guy");
        // How to click on and choose something from the combo box
        verifyThat("#btnSubmit", isDisabled());
        close();
    }

    /**
     * Tests the mnemonic for submitting
     */
    @Test
    public void testD_AltR() {
        clickOn("#buttonModify");
        Garment garment = new Garment();
        garment.setAvailable(true);
        garment.setBarcode("11111111111");
        garment.setBodyPart(BodyPart.TOP);
        Set<Color> colors = null;
        colors.add(new Color("Black"));
        garment.setColors(colors);
        garment.setDesigner("Some guy");
        garment.setGarmentType(GarmentType.PANTS);
        garment.setPictureName(null);
        Set<Material> materials = null;
        materials.add(new Material("Leather"));
        garment.setMaterials(materials);
        garment.setMood(Mood.SPORT);
        garment.setPicture(picture);
        // Yet to implement adding a picture to the garment
        clickOn("#txtBarcode");
        write(garment.getBarcode());
        clickOn("#txtPrice");
        write(garment.getPrice().toString());
        clickOn("#txtDesigner");
        write(garment.getDesigner());
        // How to click on and choose something from the combo box
        press(KeyCode.ALT, KeyCode.S);
        verifyThat("#borderPaneWarehouse", isVisible());
        verifyThat(garment.getBarcode(), isVisible());
        close();
    }

    /**
     * Tests that the register is succesful
     */
    @Test
    public void testE_RegisterSuccess() {
        clickOn("#buttonModify");
        Garment garment = new Garment();
        garment.setAvailable(true);
        garment.setBarcode("11111111111");
        garment.setBodyPart(BodyPart.TOP);
        Set<Color> colors = null;
        colors.add(new Color("Black"));
        garment.setColors(colors);
        garment.setDesigner("Some guy");
        garment.setGarmentType(GarmentType.PANTS);
        garment.setPictureName(null);
        Set<Material> materials = null;
        materials.add(new Material("Leather"));
        garment.setMaterials(materials);
        garment.setMood(Mood.SPORT);
        garment.setPicture(picture);
        // Yet to implement adding a picture to the garment
        clickOn("#txtBarcode");
        write(garment.getBarcode());
        clickOn("#txtPrice");
        write(garment.getPrice().toString());
        clickOn("#txtDesigner");
        write(garment.getDesigner());
        // How to click on and choose something from the combo box
        clickOn("#btnSubmit");
        verifyThat("#borderPaneWarehouse", isVisible());
        verifyThat(garment.getBarcode(), isVisible());
        close();
    }

    /**
     * Tests that the submit is not successful by using an existing garment
     */
    @Test
    public void testF_GarmentExists() {
        clickOn("#buttonModify");
        Garment garment = new Garment();
        garment.setAvailable(true);
        garment.setBarcode("11111111111");
        garment.setBodyPart(BodyPart.TOP);
        Set<Color> colors = null;
        colors.add(new Color("Black"));
        garment.setColors(colors);
        garment.setDesigner("Some guy");
        garment.setGarmentType(GarmentType.PANTS);
        garment.setPictureName(null);
        Set<Material> materials = null;
        materials.add(new Material("Leather"));
        garment.setMaterials(materials);
        garment.setMood(Mood.SPORT);
        garment.setPicture(picture);
        // Yet to implement adding a picture to the garment
        clickOn("#txtBarcode");
        write(garment.getBarcode());
        clickOn("#txtPrice");
        write(garment.getPrice().toString());
        clickOn("#txtDesigner");
        write(garment.getDesigner());
        // How to click on and choose something from the combo box
        clickOn("#btnSubmit");
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
