package lit_fits_client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import lit_fits_client.entities.User;

/**
 * The controller for the view in wich the admin can check all the 
 * entities in the database.
 * 
 * @author Asier
 */
public class FXMLAdminCheckDatabaseController extends FXMLDocumentController {
    
    private User admin;
    
    private Stage stage;
    
    private Stage previousStage;
    
    @FXML
    private Menu menuFile;
    
    @FXML
    private MenuItem menuItemDeleteItem;
    
    @FXML
    private MenuItem menuItemBack;
    
    @FXML
    private Button btnDeleteItem;
    
    @FXML
    private Button btnBack;
    
    
        /**
     * @return the admin
     */
    public User getAdmin () {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin (User admin) {
        this.admin = admin;
    }

    /**
     * @return the stage
     */
    public Stage getStage () {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage (Stage stage) {
        this.stage = stage;
    }
    
    public void setPreviousStage(Stage previousStage) {
        this.previousStage = previousStage;
    }
    
    public Stage getPreviousStage() {
        return this.previousStage;
    }
    
    /**
     * The method that initiates the view.
     * 
     * @param theme
     * @param stage
     * @param root 
     */
    private void initStage (String theme, Stage stage, Parent root, String uri) {
        
        this.stage = stage;
        this.theme = theme;
        
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.setTitle("Administrator - Check promotion requests");
        this.stage.show();
        
        this.setStylesheet(scene, theme);
        this.setElements();
        this.choiceTheme.setValue(theme);
        
        stage.setOnCloseRequest(this::onClosing);
        
    }
    
    /**
     * This method was created to group another 3 different methods. 
     * That makes reading the code easier.
     */
    private void setElements() {
        
        this.setMnemonicText();
        this.setTooltips();
        this.setOnAction();
    }
    
    /**
     * This method sets the texts of the controllers so the user can press 
     * <i>Alt + the first letter of the word</i> as a shortcut.
     */
    private void setMnemonicText() {
        
        menuFile.setText("_File");
        menuItemDeleteItem.setText("_Delete item");
        menuItemBack.setText("_Back");
        
        btnDeleteItem.setText("_Delete item");
        btnBack.setText("_Back");
    }

    private void setTooltips() {
        
        btnDeleteItem.setTooltip(new Tooltip("Delete the selected item"));
        btnBack.setTooltip(new Tooltip("Go back"));
    }

    private void setOnAction() {
        menuItemDeleteItem.setOnAction(this::onBtnDeleteItemPress);
        menuItemBack.setOnAction(this::onBtnBackPress);
        
        btnDeleteItem.setOnAction(this::onBtnDeleteItemPress);
        btnBack.setOnAction(this::onBtnBackPress);
    }
    
    /**
     * The event that will happen when the <i>Back</i> button is pressed.
     * 
     * @param event 
     */
    private void onBtnBackPress(ActionEvent event) {
        
    }
    
    /**
     * The event that will happen when the <i>Delete item</i> button is pressed.
     * 
     * @param event 
     */
    private void onBtnDeleteItemPress (ActionEvent event) {
        
    }
}
