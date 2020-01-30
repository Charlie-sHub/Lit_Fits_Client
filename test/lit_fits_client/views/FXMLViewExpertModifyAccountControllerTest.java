/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lit_fits_client.ApplicationMain;
import lit_fits_client.entities.FashionExpert;
import lit_fits_client.views.themes.Theme;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author Ander Rodriguez
 */
public class FXMLViewExpertModifyAccountControllerTest extends ApplicationTest{
    public FXMLViewExpertModifyAccountControllerTest(){
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationMain().start(stage);
        clickOn("#txtUsername");
        write("ander");
        clickOn("#fieldPassword");
        write("abcd*1234");
        clickOn("#btnLogin");
        clickOn("#btnModify");
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
        verifyThat("#borderPainMainMenu", isVisible());
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
    public void testC_ModificationSuccess() {
        FashionExpert expert = new FashionExpert();
        expert.setUsername("Ander");
        expert.setFullName("Ander");
        expert.setEmail("ander@ander.com");
        expert.setPassword("Ander");
        expert.setPhoneNumber("Ander");
        Long id = null;
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
        clickOn("#btnSubmit");
        clickOn("#btnModify");
        //Comprobar que el campo full name es el mismo
        close();
    }
    
}
