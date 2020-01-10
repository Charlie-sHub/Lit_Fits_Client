package lit_fits_client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lit_fits_client.entities.User;

/**
 * The view controller for the admin's main menu.
 * 
 * @author Asier
 */
public class FXMLAdminMainMenuController extends FXMLDocumentController {
    
    private User admin;
    
    private Stage stage;
    
    @FXML
    private Label lblAdmin;
    
    @FXML
    private Menu menuFile;
    
    @FXML
    private MenuItem menuItemClose;
    
    @FXML
    private Menu menuEdit;
    
    @FXML
    private MenuItem menuItemCheckEntities;
    
    @FXML
    private MenuItem menuItemCheckPromotions;
    
    @FXML
    private Pane paneLitFItsLogo;
    
    @FXML
    private Button btnClose;
    
    @FXML
    private Button btnCheckDatabase;
    
    @FXML
    private Button btnCheckPromotions;
    
    /**
     * 
     * @param theme
     * @param stage
     * @param root 
     */
    private void initStage(String theme, Stage stage, Parent root) {
        
        this.stage = stage;
        this.theme = theme;
        
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.setTitle("Administrator - Main menu");
        this.stage.show();
        
        this.setStylesheet(scene, theme);
        this.setElements();
        this.choiceTheme.setValue(theme);
        
        // The paneLitFitsLogo should get the image
    }
    
    /**
     * 
     */
    private void setElements() {
        
        this.setMnemonicText();
        this.setTooltips();
        this.setOnAction();
    }
    
    /**
     * 
     */
    private void setMnemonicText() {
        menuFile.setText("_File");
        menuItemClose.setText("_Close");
        menuEdit.setText("_Edit");
        menuItemCheckEntities.setText("Check _database");
        menuItemCheckPromotions.setText("Check _promotions");
        
        btnClose.setText("_Close");
        btnCheckPromotions.setText("Check _promotions");
        btnCheckDatabase.setText("Check _database");
    }
    
    /**
     * This method sets the tooltips for the buttons on the view.
     */
    private void setTooltips() {
        
        btnClose.setTooltip(new Tooltip("Log out your account"));
        btnCheckPromotions.setTooltip(new Tooltip("Check promoted and promotion requested garments"));
        btnCheckDatabase.setTooltip(new Tooltip("Check all the entities on the database"));
    }
    
    /**
     * This method sets the actions that should happen in the view.
     */
    private void setOnAction() {
        
        choiceTheme.setOnAction(this::onThemeChosen);
        
        menuItemClose.setOnAction(this::onBtnClosePress);
        menuItemCheckEntities.setOnAction(this::onBtnCheckDatabasePress);
        menuItemCheckPromotions.setOnAction(this::onBtnCheckPromotionsPress);
        
        btnClose.setOnAction(this::onBtnClosePress);
        btnCheckDatabase.setOnAction(this::onBtnCheckDatabasePress);
        btnCheckPromotions.setOnAction(this::onBtnCheckPromotionsPress);
    }
    
    /**
     * This method closes the view when the Close button is pressed.
     * 
     * @param event 
     */
    private void onBtnClosePress(ActionEvent event) {
        
    }
    
    /**
     * This method opens the view in wich the administrator 
     * can check all the entities stored in the database.
     * 
     * @param event 
     */
    private void onBtnCheckDatabasePress(ActionEvent event) {
        
    }
    
    /**
     * This method opens the view in wich the administrator 
     * can check all the promoted garments and the garments that have a promotion request.
     * 
     * @param event 
     */
    private void onBtnCheckPromotionsPress(ActionEvent event) {
        
    }
}
