/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.ws.rs.ClientErrorException;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.ExpertClient;
import lit_fits_client.RESTClients.PublicKeyClient;
import lit_fits_client.entities.FashionExpert;
import lit_fits_client.miscellaneous.Encryptor;
import lit_fits_client.views.themes.Theme;

/**
 *
 * @author 2dam
 */
public class FXMLViewExpertRegisterController extends FXMLDocumentControllerInput {
    /**
     * Invalid username label
     */
    @FXML
    private Label lblInvalidUsername;
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
    private Label lblPasswordMismatch;
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
    
    private boolean correctPatterns;
    
    /**
     * Logger object
     */
    private static final Logger LOG = Logger.getLogger(FXMLViewExpertModifyAccountController.class.getName());

    /**
     * @return the lblLabel
     */
    public Label getLblLabel() {
        return lblInvalidUsername;
    }

    /**
     * @param lblInvalidUsername
     */
    public void setLblLabel(Label lblInvalidUsername) {
        this.lblInvalidUsername = lblInvalidUsername;
    }

        

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
     * @return the lblPasswordMismatch
     */
    public Label getLblPassMismatch() {
        return lblPasswordMismatch;
    }

    /**
     * @param lblPassMismatch the lblPasswordMismatch to set
     */
    public void setLblPassMismatch(Label lblPassMismatch) {
        this.lblPasswordMismatch = lblPassMismatch;
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

    private void setElements() {
        setOnAction();
        setFocusTraversable();
        setListeners();
        fillArray();
        txtUsername.requestFocus();
        lblInvalidMail.setVisible(false);
        lblLength.setVisible(false);
        lblPasswordMismatch.setVisible(false);
        lblInvalidUsername.setVisible(false);
        btnRegister.setDisable(true);
    }

    private void setOnAction() {
        btnRegister.setOnAction(this::onRegisterPress);
        btnCancel.setOnAction(this::onCancelPress);
        btnHelp.setOnAction(this::onHelpPressed);
        btnRedo.setOnAction(this::onRedoPress);
        btnUndo.setOnAction(this::onUndoPress);
        btnHelp.setOnKeyPressed(this::onF1Pressed);
        
    }

    private void setFocusTraversable() {
        txtUsername.setFocusTraversable(true);
        txtFullName.setFocusTraversable(true);
        txtEmail.setFocusTraversable(true);
        txtPhone.setFocusTraversable(true);
        txtPassword.setFocusTraversable(true);
        txtRepeatPassword.setFocusTraversable(true);
    }

    private void setListeners() {
        txtUsername.textProperty().addListener(this::onFieldChange);
        txtFullName.textProperty().addListener(this::onFieldChange);
        txtEmail.textProperty().addListener(this::onFieldChange);
        txtPhone.textProperty().addListener(this::onFieldChange);
        txtPassword.textProperty().addListener(this::onFieldChange);
        txtRepeatPassword.textProperty().addListener(this::onFieldChange);
        txtUsername.lengthProperty().addListener(this::lengthListener);
        txtFullName.lengthProperty().addListener(this::lengthListener);
        txtEmail.lengthProperty().addListener(this::lengthListener);
        txtPhone.lengthProperty().addListener(this::lengthListener);        
        txtPassword.lengthProperty().addListener(this::lengthListener);
        txtRepeatPassword.lengthProperty().addListener(this::lengthListener);                
    }
    
    private void fillArray() {
        textFields = new ArrayList<>();
        textFields.add(txtUsername);
        textFields.add(txtFullName);
        textFields.add(txtEmail);
        textFields.add(txtPhone);
        textFields.add(txtPassword);
        textFields.add(txtRepeatPassword);
    }

        
    @Override
    public void onRegisterPress(ActionEvent event) {
        ExpertClient expertClient = ClientFactory.getExpertClient(uri);
        PublicKeyClient publicKeyClient = ClientFactory.getPublicKeyClient(uri);
        try {
            setExpertData(publicKeyClient.getPublicKey(String.class));
            expertClient.create(expert);
            openMainWindow();
        } catch (ClientErrorException e) {
            createExceptionDialog(e);
            LOG.log(Level.SEVERE, "{0} at: {1}", new Object[]{e.getMessage(), LocalDateTime.now()});
        } catch(Exception ex) {
            createExceptionDialog(ex);
            LOG.log(Level.SEVERE, "{0} at: {1}", new Object[]{ex.getMessage(), LocalDateTime.now()});
        } finally {
            expertClient.close();
            publicKeyClient.close();
        }
    }
    
    protected void onCancelPress(ActionEvent event){
        previousStage.show();
        stage.hide();
    }
    
        
   
    /**
     * Opens the help window when the help button is pressed
     *
     * @param event
     */
    @Override
    protected void onHelpPressed(ActionEvent event) {
        try {
            openHelpView();
        } catch (IOException e) {
            createExceptionDialog(e);
        }
    }
    
    @Override
    protected void openHelpView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ViewHelp.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stageHelp = new Stage();
        FXMLHelpController helpView = ((FXMLHelpController) fxmlLoader.getController());
        helpView.initStage(theme, stageHelp, root);
    }

    /**
     * Checks that the F1 key is pressed to open the help window
     *
     * @param event
     */
    private void onF1Pressed(KeyEvent event) {
        if (event.getCode() == KeyCode.F1) {
            try {
                openHelpView();
            } catch (IOException e) {
                createExceptionDialog(e);
            }
        }
    }
    
    /**
     * Simply calls the proper length listener method
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    public void lengthListener(ObservableValue observable, Number oldValue, Number newValue) {
        lengthCheck(btnRegister);
    }
    
    private void onFieldChange(ObservableValue observable, String oldValue, String newValue) {
        boolean correctUser = false;
        boolean correctEmail = false;
        boolean passwordMatch = false;
        boolean length = false;
        if(!txtUsername.getText().isEmpty()){
            correctUser = verifyUser();
        }
        if(!txtEmail.getText().isEmpty()) {
            correctEmail = verifyEmail();
        }
        if(!txtPassword.getText().isEmpty() && !txtRepeatPassword.getText().isEmpty()){
            passwordMatch = verifyPasswords();
        }
        length = verifyLength();
        
        if(correctUser && correctEmail && passwordMatch && length){
            correctPatterns = true;
            btnRegister.setDisable(false);
        }else {
            correctPatterns = false;
            btnRegister.setDisable(true);
        }
        
    }

    private boolean verifyUser() {
        boolean correctUser;
        correctUser = txtUsername.getText().startsWith("admin");
        lblInvalidUsername.setVisible(!correctUser);
        return correctUser;
    }

    private boolean verifyEmail() {
        boolean correctEmail;
        correctEmail = Pattern.matches("[a-zA-Z_0-9]+@{1}[a-zA-Z_0-9]+[.]{1}[a-zA-Z_0-9]+", txtEmail.getText().trim());
        lblInvalidMail.setVisible(!correctEmail);
        return correctEmail;
    }

    private boolean verifyPasswords() {
        boolean correctPassword;
        correctPassword = txtPassword.getText().equals(txtRepeatPassword.getText());
        lblPasswordMismatch.setVisible(!correctPatterns);
        return correctPassword;
    }

    private boolean verifyLength() {
        boolean length = true;
           for (TextField tField : textFields) {
               if (tField.getText().isEmpty()) {
                    length = false;
                    break;
               }
        }
        lblLength.setVisible(!length);
        return length;
    }    

    private void setExpertData(String publicKey) throws Exception {
        expert.setUsername(txtUsername.getText());
        expert.setFullName(txtFullName.getText());
        expert.setEmail(txtEmail.getText());
        expert.setPhoneNumber(txtPhone.getText());
        expert.setPassword(Encryptor.encryptText(txtPassword.getText(), publicKey.getBytes()));
    }

    private void openMainWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ExpertMainMenu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stageProgramMain = new Stage();
        FXMLViewExpertMainMenuController mainView = ((FXMLViewExpertMainMenuController) fxmlLoader.getController());
        mainView.setExpert(expert);
        mainView.setLoginStage(previousStage);
        mainView.initStage(themeList, theme, stageProgramMain, root, uri);
        stage.hide();        
    }




    
    
}
