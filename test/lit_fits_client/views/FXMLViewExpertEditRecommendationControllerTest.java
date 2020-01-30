/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lit_fits_client.ApplicationMain;
import lit_fits_client.entities.Color;
import lit_fits_client.entities.FashionExpert;
import lit_fits_client.entities.Material;
import lit_fits_client.views.themes.Theme;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author Ander Rodriguez
 */
public class FXMLViewExpertEditRecommendationControllerTest extends ApplicationTest{
    
    public FXMLViewExpertEditRecommendationControllerTest() {
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
        clickOn("#btnRecommendations");
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
    
}
