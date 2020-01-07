/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import lit_fits_client.ApplicationMain;

/**
 * Test Class for the register wiew and controller
 *
 * @author Carlos Rafael Mendez Gonzalez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLViewRegisterControllerIT extends ApplicationTest {
    public FXMLViewRegisterControllerIT() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationMain().start(stage);
        clickOn("#btnSignUp");
    }

    @After
    public void close() {
        try {
            stop();
        } catch (Exception ex) {
            Logger.getLogger(FXMLViewRegisterControllerIT.class.getName()).log(Level.SEVERE, null, ex);
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
     * Tests that the register button is disabled without entering any data
     */
    @Test
    public void testB_BtnRegisterIsDisabled() {
        clickOn("#btnRegister");
        verifyThat("#btnRegister", isDisabled());
        close();
    }

    /**
     * Tests that the button register is disabled when the strings in the fields are too long
     */
    @Test
    public void testC_BtnRegisterIsDisabledByLenghtOfData() {
        clickOn("#txtUsername");
        write("SomeUserNameSomeUserNameSomeUserNameSomeUserNameSomeUserName");
        clickOn("#txtEmail");
        write("SomeEMail@Some.server");
        clickOn("#txtFullName");
        write("SomeFullName");
        clickOn("#txtPassword");
        write("SomePassword");
        clickOn("#txtRepeatPassword");
        write("SomePassword");
        clickOn("#btnRegister");
        verifyThat("#btnRegister", isDisabled());
        close();
    }

    /**
     * Tests the mnemonic for registering
     */
    @Test
    public void testD_AltR() {
        clickOn("#txtUsername");
        write("UsernameForTest9");
        clickOn("#txtEmail");
        write("SomeEMail@Some.server");
        clickOn("#txtFullName");
        write("SomeFullName");
        clickOn("#txtPassword");
        write("SomePassword");
        clickOn("#txtRepeatPassword");
        write("SomePassword");
        clickOn("#btnRegister");
        press(KeyCode.ALT, KeyCode.R);
        verifyThat("#borderPaneMain", isVisible());
        close();
    }

    /**
     * Tests that the register is succesful
     */
    @Test
    public void testE_RegisterSuccess() {
        User user = new User();
        user.setEmail("SomeEMail@Some.server");
        user.setFullName("SomeFullName");
        user.setLogin("AReallyReallyNewUser9");
        user.setPassword("SomePassword");
        user.setUserStatus(true);
        user.setUserPrivilege(false);
        user.setUserPrivilege(false);
        user.setUserStatus(true);
        clickOn("#txtUsername");
        write(user.getLogin());
        clickOn("#txtEmail");
        write(user.getEmail());
        clickOn("#txtFullName");
        write(user.getFullName());
        clickOn("#txtPassword");
        write(user.getPassword());
        clickOn("#txtRepeatPassword");
        write(user.getPassword());
        clickOn("#btnRegister");
        verifyThat("#borderPaneMain", isVisible());
        verifyThat("#lblUserFullname", hasText(user.getFullName()));
        close();
    }

    /**
     * Tests that the register is not successful by using an existing user
     */
    @Test
    public void testF_UserExists() {
        User user = new User();
        user.setEmail("SomeEMail@Some.server");
        user.setFullName("SomeFullName");
        user.setLogin("SomeRealNewUserName");
        user.setPassword("SomePassword");
        user.setUserStatus(true);
        user.setUserPrivilege(false);
        user.setUserPrivilege(false);
        user.setUserStatus(true);
        clickOn("#txtUsername");
        write(user.getLogin());
        clickOn("#txtEmail");
        write(user.getEmail());
        clickOn("#txtFullName");
        write(user.getFullName());
        clickOn("#txtPassword");
        write(user.getPassword());
        clickOn("#txtRepeatPassword");
        write(user.getPassword());
        clickOn("#btnRegister");
        verifyThat("That username is already in use \n" + "This username is already in use", isVisible());
        close();
    }

    /**
     * Tests the mnemonic for canceling
     */
    @Test
    public void testG_AltC() {
        press(KeyCode.ALT, KeyCode.C);
        verifyThat("#borderPaneLogin", isVisible());
        close();
    }
}
