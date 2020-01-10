/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Asier
 */
public class FXMLAdminCheckDatabaseController extends FXMLDocumentController {
    
    private User admin;
    
    private Stage stage;
    
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
    
    // initStage
    private void initStage (String theme, Stage stage, Parent root) {
        
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
    
    // setElements
    private void setElements() {
        
        this.setMnemonicText();
        this.setTooltips();
        this.setOnAction();
    }
    
    // setMnemonicText
    private void setMnemonicText() {
        
        menuFile.setText("_File");
        menuItemDeleteItem.setText("_File");
        menuItemBack.setText("_File");
        
        btnDeleteItem.setText("_File");
        btnBack.setText("_Back");
    }

    // setTooltips
    private void setTooltips() {
        
        btnDeleteItem.setTooltip(new Tooltip(""));
        btnBack.setTooltip(new Tooltip(""));
    }

    // setOnAction
    private void setOnAction() {
        menuItemDeleteItem.setOnAction(this::onBtnDeleteItemPress);
        menuItemBack.setOnAction(this::onBtnBackPress);
        
        btnDeleteItem.setOnAction(this::onBtnDeleteItemPress);
        btnBack.setOnAction(this::onBtnBackPress);
    }
    
    // onBtnBackPress
    // onBtnDeleteItemPress
    
    private void onBtnBackPress(ActionEvent event) {
        
    }
    
    private void onBtnDeleteItemPress (ActionEvent event) {
        
    }
}
