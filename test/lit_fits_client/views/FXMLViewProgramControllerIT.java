/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isNull;
import lit_fits_client.ApplicationMain;

/**
 *
 * @author Charlie
 */
public class FXMLViewProgramControllerIT extends ApplicationTest{
    
    public FXMLViewProgramControllerIT() {
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationMain().start(stage);
    }
    /**
     * Tests the logout button
     */
    @Test
    public void testBtnLogout() {
        clickOn("#btnLogout");
        verifyThat("#borderPaneLogin", isVisible());
        verifyThat("#borderPaneMain", isNull());
    }
    /**
     * Tests the logout Mnemonic
     */
    @Test
    public void testAltL() {
        press(KeyCode.ALT, KeyCode.L);
        verifyThat("#borderPaneLogin", isVisible());
        verifyThat("#borderPaneMain", isNull());
    }
    /**
     * Tests the user name label
     */
    @Test
    public void testNameShown() {
        verifyThat("#lblUserFullname", isVisible());
        // How to get a user and check that the lbl has the value of said user's name?
    }
}
