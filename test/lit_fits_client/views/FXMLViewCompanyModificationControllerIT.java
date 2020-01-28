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
import lit_fits_client.entities.Company;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * Test Class for the company register view and controller
 *
 *
 * @author Carlos Rafael Mendez Gonzalez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLViewCompanyModificationControllerIT extends ApplicationTest {
    public FXMLViewCompanyModificationControllerIT() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationMain().start(stage);
        clickOn("#txtUsername");
        write("A1111111A"); // Should create a company with this nif
        clickOn("#fieldPassword");
        write("abcd"); // Should create a company with this password
        clickOn("#btnLogin");
        clickOn("#btnModifyAccount");
    }

    @After
    public void close() {
        try {
            stop();
        } catch (Exception ex) {
            Logger.getLogger(FXMLViewCompanyModificationControllerIT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of onBtnCancelPress method, of class FXMLViewCompanyRegisterController.
     */
    @Test
    public void testA_OnBtnCancelPress() {
        clickOn("#btnCancel");
        verifyThat("#borderPaneLogin", isVisible());
        close();
    }

    /**
     * Tests that the submit button is disabled without entering any data
     */
    @Test
    public void testB_BtnSubmitIsDisabled() {
        clickOn("#btnSubmit");
        verifyThat("#btnSubmit", isDisabled());
        close();
    }

    /**
     * Tests that the button submit is disabled when the strings in the fields are too long
     */
    @Test
    public void testC_BtnSubmitIsDisabledByLenghtOfData() {
        clickOn("#txtFullName");
        write("SomeFullNameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        clickOn("#txtEmail");
        write("SomeEMail@Some.server");
        clickOn("#txtPhone");
        write("123456789");
        clickOn("#txtPassword");
        write("SomePassword");
        clickOn("#txtRepeatPassword");
        write("SomePassword");
        clickOn("#btnSubmit");
        verifyThat("#btnSubmit", isDisabled());
        close();
    }

    /**
     * Tests the mnemonic for submitting
     */
    @Test
    public void testD_AltS() {
        Company company = new Company();
        company.setNif("A1111111A");
        company.setFullName("SomeOtherFullName");
        company.setEmail("SomeOtherEMail@Some.server");
        company.setPassword("SomeOtherPassword");
        company.setPhoneNumber("123456789");
        Long id = null;
        company.setId(id);
        clickOn("#txtFullName");
        write(company.getFullName());
        clickOn("#txtEmail");
        write(company.getEmail());
        clickOn("#txtPhone");
        write("123456789");
        clickOn("#txtPassword");
        write(company.getPassword());
        clickOn("#txtRepeatPassword");
        write(company.getPassword());
        press(KeyCode.ALT, KeyCode.S);
        verifyThat("#borderPaneMainMenu", isVisible());
        close();
    }

    /**
     * Tests that the modification is successful
     */
    @Test
    public void testE_ModificationSuccess() {
        Company company = new Company();
        company.setNif("A1111111A");
        company.setFullName("SomeOtherFullName");
        company.setEmail("SomeOtherEMail@Some.server");
        company.setPassword("SomeOtherPassword");
        company.setPhoneNumber("123456789");
        Long id = null;
        company.setId(id);
        clickOn("#txtFullName");
        write(company.getFullName());
        clickOn("#txtEmail");
        write(company.getEmail());
        clickOn("#txtPhone");
        write("123456789");
        clickOn("#txtPassword");
        write(company.getPassword());
        clickOn("#txtRepeatPassword");
        write(company.getPassword());
        clickOn("#btnsubmit");
        verifyThat("#borderPaneMainMenu", isVisible());
        close();
    }

    /**
     * Tests the mnemonic for canceling
     */
    @Test
    public void testF_AltC() {
        press(KeyCode.ALT, KeyCode.C);
        verifyThat("#borderPaneLogin", isVisible());
        close();
    }
    /**
     * Tests that the nif text field is disabled
     */
    @Test
    public void testG_TxtNifIsDisabled() {
        clickOn("#txtNif");
        verifyThat("#txtNif", isDisabled());
        close();
    }
}
