/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.util.List;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lit_fits_client.entities.FashionExpert;
import lit_fits_client.views.themes.Theme;

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

    
        

    /**
     * @return the lblInvalidMail
     */
    public Label getLblInvalidMail() {
        return lblInvalidMail;
    }

    /**
     * @param lblInvalidMail the lblInvalidMail to set
     */
    public void setLblInvalidMail(Label lblInvalidMail) {
        this.lblInvalidMail = lblInvalidMail;
    }

    /**
     * @return the btnCancel
     */
    public Button getBtnCancel() {
        return btnCancel;
    }

    /**
     * @param btnCancel the btnCancel to set
     */
    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    /**
     * @return the btnRegister
     */
    public Button getBtnRegister() {
        return btnRegister;
    }

    /**
     * @param btnRegister the btnRegister to set
     */
    public void setBtnRegister(Button btnRegister) {
        this.btnRegister = btnRegister;
    }

    /**
     * @return the txtUsername
     */
    public TextField getTxtUsername() {
        return txtUsername;
    }

    /**
     * @param txtUsername the txtUsername to set
     */
    public void setTxtUsername(TextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    /**
     * @return the txtPhone
     */
    public TextField getTxtPhone() {
        return txtPhone;
    }

    /**
     * @param txtPhone the txtPhone to set
     */
    public void setTxtPhone(TextField txtPhone) {
        this.txtPhone = txtPhone;
    }

    /**
     * @return the txtEmail
     */
    public TextField getTxtEmail() {
        return txtEmail;
    }

    /**
     * @param txtEmail the txtEmail to set
     */
    public void setTxtEmail(TextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    /**
     * @return the txtFullName
     */
    public TextField getTxtFullName() {
        return txtFullName;
    }

    /**
     * @param txtFullName the txtFullName to set
     */
    public void setTxtFullName(TextField txtFullName) {
        this.txtFullName = txtFullName;
    }

    /**
     * @return the txtPassword
     */
    public PasswordField getTxtPassword() {
        return txtPassword;
    }

    /**
     * @param txtPassword the txtPassword to set
     */
    public void setTxtPassword(PasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    /**
     * @return the txtRepeatPassword
     */
    public PasswordField getTxtRepeatPassword() {
        return txtRepeatPassword;
    }

    /**
     * @param txtRepeatPassword the txtRepeatPassword to set
     */
    public void setTxtRepeatPassword(PasswordField txtRepeatPassword) {
        this.txtRepeatPassword = txtRepeatPassword;
    }

    /**
     * @return the lblPassMismatch
     */
    public Label getLblPassMismatch() {
        return lblPassMismatch;
    }

    /**
     * @param lblPassMismatch the lblPassMismatch to set
     */
    public void setLblPassMismatch(Label lblPassMismatch) {
        this.lblPassMismatch = lblPassMismatch;
    }

    /**
     * @return the btnHelp
     */
    public Button getBtnHelp() {
        return btnHelp;
    }

    /**
     * @param btnHelp the btnHelp to set
     */
    public void setBtnHelp(Button btnHelp) {
        this.btnHelp = btnHelp;
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * @return the previousStage
     */
    public Stage getPreviousStage() {
        return previousStage;
    }

    /**
     * @param previousStage the previousStage to set
     */
    public void setPreviousStage(Stage previousStage) {
        this.previousStage = previousStage;
    }

    /**
     * @return the expert
     */
    public FashionExpert getExpert() {
        return expert;
    }

    /**
     * @param expert the expert to set
     */
    public void setExpert(FashionExpert expert) {
        this.expert = expert;
    }
    
    
        public void initStage(List<Theme> themes, Theme theme, Stage stageProgramMain, Parent root, String uri) {
        try {
            this.uri = uri;
            this.setStage(stageProgramMain);
            Scene scene = new Scene(root);
            getStage().setScene(scene);
            getStage().setTitle("Modify Account");
            getStage().setMinWidth(1400);
            getStage().setMinHeight(800);
            getStage().show();
            setStylesheet(scene, theme.getThemeCssPath());
            themeList = themes;
            setElements();
            getStage().setOnCloseRequest(this::onClosing);
        } catch (Exception e) {
            createExceptionDialog(e);
            LOG.severe(e.getMessage());
        }
    }
    
    
    
    @Override
    public void onRegisterPress(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setElements() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
