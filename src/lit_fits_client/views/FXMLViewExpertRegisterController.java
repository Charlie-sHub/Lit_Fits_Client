/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lit_fits_client.entities.FashionExpert;

/**
 *
 * @author 2dam
 */
public class FXMLViewExpertRegisterController extends FXMLDocumentControllerInput {
    /**
     * Invalid email label
     */
    @FXML
    private Label lblInvalidMail;
    /**
     * Cancel button
     */
    @FXML
    private Button btnCancel;
    /**
     * Register button
     */
    @FXML
    private Button btnRegister;
    /**
     * Username textField
     */
    @FXML
    private TextField txtUsername;
    /**
     * Phone text field
     */
    @FXML
    private TextField txtPhone;
    /**
     * Email text field
     */
    @FXML
    private TextField txtEmail;
    /**
     * Full name text field
     */
    @FXML
    private TextField txtFullName;
    /**
     * Password text field
     */
    @FXML
    private PasswordField txtPassword;
    /**
     * New Password text field
     */
    @FXML
    private PasswordField txtRepeatPassword;
    /**
     * Label password mismatch
     */
    @FXML
    private Label lblPassMismatch;
    /**
     * Help button
     */
    @FXML
    private Button btnHelp;
    
    /**
     * Stage to be used by the current controller
     */
    private Stage stage;
    /**
     * Stage of the previous window, the login in this case
     */
    private Stage previousStage;
    
    private FashionExpert expert;
    
    /**
     * Logger object
     */
    private static final Logger LOG = Logger.getLogger(FXMLViewExpertModifyAccountController.class.getName());

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void onRegisterPress(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
