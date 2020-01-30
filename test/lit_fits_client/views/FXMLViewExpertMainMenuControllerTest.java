/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author Ander Rodriguez
 */
public class FXMLViewExpertMainMenuControllerTest extends ApplicationTest {
    
    public FXMLViewExpertMainMenuControllerTest() {
    }
    
    /**
     * Start of the application
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationMain().start(stage);
        clickOn("#txtUsername");
        write("ander");
        clickOn("#fieldPassword");
        write("abcd*1234");
        clickOn("#btnLogin");
    }
    
    /**
     * Close operation
     */
    @After
    public void close() {
        try{
            stop();
        } catch (Exception ex) {
            Logger.getLogger(FXMLViewExpertRegisterControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Test Open Modify account
     */
    @Test
    public void testA_onBtnModifyAccountPress() {
        clickOn("btnModify");
        verifyThat("borderPaneModifyAccount", isVisible());
        close();
    }
    
    /**
     * Test open recommendation
     */
    @Test
    public void testB_onBtnEditRecommendationsPress() {
        clickOn("#btnRecommendations");
        verifyThat("#borderPaneRecommendations", isVisible());
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
}
