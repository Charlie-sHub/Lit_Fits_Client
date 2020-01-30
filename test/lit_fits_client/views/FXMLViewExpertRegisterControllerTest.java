/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.util.logging.Logger;
import lit_fits_client.ApplicationMain;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import java.util.logging.Level;
import javafx.stage.Stage;
import lit_fits_client.entities.FashionExpert;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author Ander Rodriguez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLViewExpertRegisterControllerTest extends ApplicationTest {
    
    public FXMLViewExpertRegisterControllerTest() {
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationMain().start(stage);
        clickOn("#rBtnExpert");
        clickOn("#btnRegister");
    }
    
    @After
    public void close() {
        try{
            stop();
        } catch (Exception ex) {
            Logger.getLogger(FXMLViewExpertRegisterControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testA_OnBtnCancelPress() {
        clickOn("#btnCancel");
        verifyThat("#borderPaneLogin", isVisible());
        close();
    }
    
    @Test
    public void testB_BtnRegisterIsDisabledByLenghtOfData() {
        clickOn("#txtUsername");
        write("11111111111111111111111111111111111111111111111111");
        clickOn("#txtFullName");
        write("SomeFullName");
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
    
    @Test
    public void testC_RegisterSuccess() {
        FashionExpert expert = new FashionExpert();
        expert.setUsername("Ander");
        expert.setFullName("Ander");
        expert.setEmail("ander@ander.com");
        expert.setPassword("Ander");
        expert.setPhoneNumber("Ander");
        Long id = null;
        expert.setId(id);
        clickOn("#txtUsername");
        write(expert.getUsername());
        clickOn("#txtFullName");
        write(expert.getFullName());
        clickOn("#txtEmail");
        write(expert.getEmail());
        clickOn("#txtPhone");
        write(expert.getPhoneNumber());
        clickOn("#txtPassword");
        write(expert.getPassword());
        clickOn("#txtRepeatPassword");
        write(expert.getPassword());
        clickOn("#btnRegister");
        verifyThat("#borderPaneMainMenu", isVisible());
        close();
    }
}
