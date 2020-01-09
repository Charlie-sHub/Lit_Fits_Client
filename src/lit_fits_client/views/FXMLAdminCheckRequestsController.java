/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * The controller for the view in wich the admin can check 
 * the promotion requests for a garment and the garments 
 * that are already promoted.
 * @author Asier
 */
public class FXMLAdminCheckRequestsController extends FXMLDocumentController{
    
    private static final Logger LOG = Logger.getLogger(FXMLAdminCheckRequestsController.class.getName());
    
    //private Admin admin;
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
    
    public void initStage(String theme, Stage stage, Parent root) {
        
        this.stage = stage;
        this.theme = theme;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Check promotion requests");
        stage.show();
        this.setStylesheet(scene, theme);
        this.setElements();
        stage.setOnCloseRequest(this::onClosing);
    }
    
    private void setElements() {
        
        this.setMnemonicText();
        this.setTooltips();
        this.setOnAction();
        
        this.choiceTheme.setValue(theme);
    }
    
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
    
    private void setTooltips() {
        
        btnSave.setTooltip(new Tooltip("Save the changes"));
        btnBack.setTooltip(new Tooltip("Go to the previous window"));
    }
    
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
    
    private void onBtnSavePress(ActionEvent event) {
        
    }
    
    private void onBtnBackPress(ActionEvent event){
        
    }
    
    private void onMenuItemPromotePress(ActionEvent event) {
        
    }
    
    private void onMenuItemDeletePromotionPress(ActionEvent event) {
        
    }
    
    private void onMenuItemCancelRequestPress(ActionEvent event) {
        
    }
}
