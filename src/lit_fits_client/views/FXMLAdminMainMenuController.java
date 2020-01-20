package lit_fits_client.views;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    
    private static final Logger LOG = Logger.getLogger(FXMLAdminMainMenuController.class.getName());
    
    private User admin;
    private Stage stage;
    private Stage previousStage;
    private String uri;
    
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
    
//    @FXML
//    private Pane paneLitFItsLogo;
    
    @FXML
    private Button btnClose;
    @FXML
    private Button btnCheckDatabase;
    @FXML
    private Button btnCheckPromotions;
    
    /**
     * Gets the admin that is logged.
     * 
     * @return the admin
     */
    public User getAdmin () {
        return admin;
    }

    /**
     * Sets the admin that is logged.
     * 
     * @param admin the admin to set
     */
    public void setAdmin (User admin) {
        this.admin = admin;
    }

    /**
     * Gets the current stage.
     * 
     * @return the stage
     */
    public Stage getStage () {
        return stage;
    }

    /**
     * 
     * 
     * @param stage the stage to set
     */
    public void setStage (Stage stage) {
        this.stage = stage;
    }

    /**
     * @return the previousStage
     */
    public Stage getPreviousStage () {
        return previousStage;
    }

    /**
     * @param previousStage the previousStage to set
     */
    public void setPreviousStage (Stage previousStage) {
        this.previousStage = previousStage;
    }

    /**
     * @param lblAdmin the lblAdmin to set
     */
    public void setLblAdmin (Label lblAdmin) {
        this.lblAdmin = lblAdmin;
    }
    
    /**
     * This method initializes the view.
     * 
     * @param theme
     * @param stage
     * @param root 
     */
    private void initStage(String theme, Stage stage, Parent root, String uri) {
        
        this.setStage(stage);
        this.theme = theme;
        this.uri = uri;
        
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.setTitle("Administrator - Main menu");
        this.stage.show();
        
        this.setStylesheet(scene, theme);
        this.setElements();
        this.choiceTheme.setValue(theme);
        this.lblAdmin.setText(this.admin.getFullName());
        
        // The paneLitFitsLogo should get the image
    }
    
    /**
     * This method is created to group anothers, so the code is easier
     * to read. Here are the methods that should execute in <i>initStage</i>.
     */
    private void setElements() {
        
        this.setMnemonicText();
        this.setTooltips();
        this.setOnAction();
    }
    
    /**
     * This method sets the shortcuts on the app, so the user can 
     * use <b>Alt + The correct letter</b> to access the buttons.
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
        previousStage.show();
        stage.hide();
    }
    
    /**
     * This method opens the view in wich the administrator 
     * can check all the entities stored in the database.
     * 
     * @param event 
     */
    private void onBtnCheckDatabasePress(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/AdminCheckDatabase.fxml"));
            Stage databaseStage = new Stage();
            Parent root = (Parent) fxmlLoader.load();
            FXMLAdminCheckDatabaseController databaseView = ((FXMLAdminCheckDatabaseController) fxmlLoader.getController());
            databaseView.setAdmin(admin);
            databaseView.setPreviousStage(stage);
            databaseView.initStage(choiceTheme.getValue(), databaseStage, root, uri);
            databaseView.getStage().show();
            stage.hide();
            
        } catch (IOException ioException) {
            
            createExceptionDialog(ioException);
            LOG.severe(ioException.getMessage());
        }
    }
    
    /**
     * This method opens the view in wich the administrator 
     * can check all the promoted garments and the garments that have a promotion request.
     * 
     * @param event 
     */
    private void onBtnCheckPromotionsPress(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/AdminCheckRequests.fxml"));
            Stage requestsStage = new Stage();
            Parent root = (Parent) fxmlLoader.load();
            FXMLAdminCheckRequestsController requestView = ((FXMLAdminCheckRequestsController) fxmlLoader.getController());
            requestView.setAdmin(admin);
            requestView.setPreviousStage(stage);
            requestView.initStage(choiceTheme.getValue(), requestsStage, root, uri);
            requestView.getStage().show();
            stage.hide();
            
        } catch (IOException ioException) {
            
            createExceptionDialog(ioException);
            LOG.severe(ioException.getMessage());
        }
    }
}
