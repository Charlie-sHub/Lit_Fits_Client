package lit_fits_client.views;

import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import lit_fits_client.entities.User;

/**
 * The view controller in wich the admin can check 
 * the promotion requests for a garment and the 
 * garments that are already promoted.
 * 
 * @author Asier
 */
public class FXMLAdminCheckRequestsController extends FXMLDocumentController{
    
    private static final Logger LOG = Logger.getLogger(FXMLAdminCheckRequestsController.class.getName());
    
    private User admin;
    
    private boolean saved;
    
    private Stage stage;
    
    @FXML
    private Button btnSave;
    
    @FXML
    private Button btnBack;
    
    @FXML
    private TableView tableViewGarment;
    
    @FXML
    private Menu menuFile;
    
    @FXML
    private MenuItem menuItemSave;
    
    @FXML
    private MenuItem menuItemBack;
    
    @FXML
    private Menu menuEdit;
    
    @FXML
    private MenuItem menuItemPromote;
    
    @FXML
    private MenuItem menuItemDeletePromotion;
    
    @FXML
    private MenuItem menuItemCancelRequest;
    
    @FXML
    private ChoiceBox choiceBoxFilterBy;
    
    /**
     * The method that initiates the view.
     * 
     * @param theme
     * @param stage
     * @param root 
     */
    public void initStage(String theme, Stage stage, Parent root) {
        
        this.stage = stage;
        this.theme = theme;
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Administrator - Check promotion requests");
        stage.show();
        
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
        menuItemSave.setText("_Save");
        menuItemBack.setText("_Back");
        menuEdit.setText("_Edit");
        menuItemPromote.setText("_Promote");
        menuItemDeletePromotion.setText("_Delete promotion");
        menuItemCancelRequest.setText("_Cancel request");
        
        btnSave.setText("_Save");
        btnBack.setText("_Back");
    }
    
    /**
     * Sets the tooltips for the buttons on the view.
     */
    private void setTooltips() {
        
        btnSave.setTooltip(new Tooltip("Save the changes"));
        btnBack.setTooltip(new Tooltip("Go to the previous window"));
    }
    
    /**
     * Sets the actions for all the controllers on the view.
     */
    private void setOnAction() {
        
        choiceTheme.setOnAction(this::onThemeChosen);
        
        menuItemSave.setOnAction(this::onBtnSavePress);
        menuItemBack.setOnAction(this::onBtnBackPress);
        menuItemPromote.setOnAction(this::onMenuItemPromotePress);
        menuItemDeletePromotion.setOnAction(this::onMenuItemDeletePromotionPress);
        menuItemCancelRequest.setOnAction(this::onMenuItemCancelRequestPress);
        
        btnSave.setText(this::onBtnSavePress);
        btnBack.setText(this::onBtnBackPress);
    }
    
    /**
     * The event that will happen when the <i>Save</i> button is pressed.
     * 
     * @param event The action event of the view.
     */
    private void onBtnSavePress(ActionEvent event) {
        
    }
    
    /**
     * The event that will happen when the <i>Back</i> button is pressed. 
     * If the data is not saved before exiting, a confirmation dialog appears to 
     * confirm the operation.
     * 
     * @param event The action event of the view.
     */
    private void onBtnBackPress(ActionEvent event){
        
        //if (saved) {
            // Close the window
        //} else
            // Open confirmation dialog
        //}
    }
    
    /**
     * The event that will happen when the <i>Promote</i> menu item is pressed.
     * 
     * @param event The action event of the view.
     */
    private void onMenuItemPromotePress(ActionEvent event) {
        
    }
    
    /**
     * The event that will happen when the <i>Delete promotion</i> menu item is pressed.
     * 
     * @param event The action event of the view.
     */
    private void onMenuItemDeletePromotionPress(ActionEvent event) {
        
    }
    
    /**
     * The event that will happen when the <i>Cancel request</i> menu item is pressed.
     * 
     * @param event The action event of the view.
     */
    private void onMenuItemCancelRequestPress(ActionEvent event) {
        
    }
}
