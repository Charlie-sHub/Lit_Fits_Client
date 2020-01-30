/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.ws.rs.ClientErrorException;
import lit_fits_client.miscellaneous.Encryptor;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.ExpertClient;
import lit_fits_client.RESTClients.PublicKeyClient;
import lit_fits_client.entities.FashionExpert;
import lit_fits_client.miscellaneous.TextChange;
import lit_fits_client.views.themes.Theme;
import org.apache.commons.io.IOUtils;
import org.fxmisc.undo.UndoManagerFactory;
import org.reactfx.EventStream;
import static org.reactfx.EventStreams.changesOf;
import static org.reactfx.EventStreams.merge;

/**
 *
 * @author Ander
 */
//TODO Comprobar si los campos estan vacios
public class FXMLViewExpertModifyAccountController extends FXMLDocumentControllerInput{
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
    private Button btnSubmit;
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
    private PasswordField txtNewPassword;

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

    boolean change = false; 
    boolean passwordChange = false;
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
     * @return the btnSubmit
     */
    public Button getBtnSubmit() {
        return btnSubmit;
    }

    /**
     * @param btnSubmit the btnSubmit to set
     */
    public void setBtnSubmit(Button btnSubmit) {
        this.btnSubmit = btnSubmit;
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
     * @return the txtNewPassword
     */
    public PasswordField getTxtNewPassword() {
        return txtNewPassword;
    }

    /**
     * @param txtNewPassword the txtNewPassword to set
     */
    public void setTxtNewPassword(PasswordField txtNewPassword) {
        this.txtNewPassword = txtNewPassword;
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
    public void setStageMainMenu(Stage previousStage) {
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

    
    public void initStage(List<Theme> themes, Theme theme, Stage stage, Parent root, String uri) {
        try {
            this.uri = uri;
            this.stage = stage;
            this.theme = theme;
            Scene scene = new Scene(root);
            setStylesheet(scene, theme.getThemeCssPath());
            stage.setScene(scene);
            stage.setTitle("Modify Account");
            stage.setMinWidth(850);
            stage.setMinHeight(650);
            stage.show();
            themeList = themes;
            setElements();
        } catch (Exception e) {
            createExceptionDialog(e);
            LOG.severe(e.getMessage());
        }
    }
    /**
     * Sets the options for different elements of the window
     */
    private void setElements() {
        fillChoiceBoxTheme();
        setTextFields();
        setOnAction();
        setFocusTraversable();
        setListeners();
        setUndoRedo();
        textFields = new ArrayList<>();
        fillArray();
        lblInvalidMail.setVisible(false);
        lblLength.setVisible(false);
        lblInvalidUsername.setVisible(false);
    }

    /**
     * Implements the actions
     */
    private void setOnAction() {
        btnHelp.setOnAction(this::onHelpPressed);
        btnSubmit.setOnAction(this::onRegisterPress);
        btnCancel.setOnAction(this::onBtnCancelPress);
        btnHelp.setOnAction(this::onHelpPressed);
        btnHelp.setOnKeyPressed(this::onF1Pressed);
        stage.setOnCloseRequest(this::onClosing);
    }


    private void setFocusTraversable() {
        txtUsername.setFocusTraversable(true);
        txtFullName.setFocusTraversable(true);
        txtEmail.setFocusTraversable(true);
        txtPhone.setFocusTraversable(true);
        txtPassword.setFocusTraversable(true);
        txtNewPassword.setFocusTraversable(true);
    }


    private void setTextFields() {
        txtUsername.setText(expert.getUsername());
        txtUsername.setEditable(false);
        txtUsername.setDisable(true);
        txtFullName.setText(expert.getFullName());
        txtEmail.setText(expert.getEmail());
        txtPhone.setText(expert.getPhoneNumber());
        
    }
    

    private void setListeners() {
        txtFullName.textProperty().addListener(this::onFieldChange);
        txtEmail.textProperty().addListener(this::onFieldChange);
        txtPhone.textProperty().addListener(this::onFieldChange);
        txtPassword.textProperty().addListener(this::onFieldChange);
        txtNewPassword.textProperty().addListener(this::onFieldChange);        
        txtFullName.lengthProperty().addListener(this::lengthListener);
        txtEmail.lengthProperty().addListener(this::lengthListener);
        txtPhone.lengthProperty().addListener(this::lengthListener);        
        txtPassword.lengthProperty().addListener(this::lengthListener);
        txtNewPassword.lengthProperty().addListener(this::lengthListener);
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
    
    @Override
    public void onRegisterPress(ActionEvent event) {
        try {
            if(enableModify()){
                ExpertClient expertClient = ClientFactory.getExpertClient(uri);
                PublicKeyClient publicKeyClient = ClientFactory.getPublicKeyClient(uri);
                try {
                    byte[] publicKeyBytes = IOUtils.toByteArray(publicKeyClient.getPublicKey(InputStream.class));
                    setExpertData(publicKeyBytes);
                    expertClient.edit(expert);
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
            } else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("You have written something wrongly");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            Logger.getLogger(FXMLViewExpertModifyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillArray() {
        textFields.add(txtPhone);
        textFields.add(txtEmail);
        textFields.add(txtFullName);
    }
    
    /**
     * Simply calls the proper length listener method
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    public void lengthListener(ObservableValue observable, Number oldValue, Number newValue) {
        lengthCheck(btnSubmit);
    }

    private void onFieldChange(ObservableValue observable, String oldValue, String newValue){
        
        PublicKeyClient publicKeyClient = ClientFactory.getPublicKeyClient(uri);
        // Commented so it compiles
        // byte[] publicKeyBytes = IOUtils.toByteArray(publicKeyClient.getPublicKey(InputStream.class));
        String fullName = expert.getFullName();
        String email = expert.getEmail();
        String phone = expert.getPhoneNumber();
        change = !txtFullName.getText().equals(fullName) ||
                !txtEmail.getText().equals(email) ||
                !txtPhone.getText().equals(phone);
        try {
            // Commented so it compiles
            // passwordChange = comparePasswords(publicKeyBytes);
        } catch (Exception ex) {
            createExceptionDialog(ex);
            
        }
    }
    
    private void onBtnCancelPress(ActionEvent e){
        if(change || passwordChange){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmation");
            alert.setContentText("You have made changes. If you continue, changes will be discarded.");
            Optional <ButtonType> action = alert.showAndWait();
            if(action.get() == ButtonType.OK) {
                try {
                    openMainWindow();
                } catch (IOException ex) {
                    createExceptionDialog(ex);
                    LOG.log(Level.SEVERE, "{0} at: {1}", new Object[]{ex.getMessage(), LocalDateTime.now()});
                }
            } 
        }else{
            try {
                openMainWindow();
            } catch (IOException ex) {
                createExceptionDialog(ex);
                LOG.log(Level.SEVERE, "{0} at: {1}", new Object[]{ex.getMessage(), LocalDateTime.now()});
            }
        }
    }
    
    private void setExpertData(byte[] publicKey) throws Exception {
        
        expert.setFullName(txtFullName.getText());
        expert.setEmail(txtEmail.getText());
        expert.setPhoneNumber(txtPhone.getText());
        expert.setUsername(txtUsername.getText());
        if(txtNewPassword.getText().isEmpty()){
            expert.setPassword(expert.getPassword());
        }else if (passwordChange && !txtNewPassword.getText().isEmpty()){
            expert.setPassword(Encryptor.encryptText(txtNewPassword.getText(), publicKey));
        }else{
            expert.setPassword(expert.getPassword());
        }
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

    private boolean comparePasswords(byte[] publicKey) throws Exception{
        String passw = Encryptor.encryptText(txtPassword.getText(), publicKey);
        String passwordInDB = expert.getPassword();
         
        passwordChange = passw.trim().equals(passwordInDB.trim());
        
        return passwordChange;
    }
    
    private boolean emailPatternCheck(){
        boolean verifyEmail;
        verifyEmail = Pattern.matches("[a-zA-Z_0-9]+@{1}[a-zA-Z_0-9]+[.]{1}[a-zA-Z_0-9]+", txtEmail.getText().trim());
        return verifyEmail;
    }
    
    private boolean enableModify() throws Exception{
        boolean enableModify;
        PublicKeyClient publicKeyClient = ClientFactory.getPublicKeyClient(uri);
        byte[] publicKeyBytes = IOUtils.toByteArray(publicKeyClient.getPublicKey(InputStream.class));
        
        if(txtPassword.getText().isEmpty()){
            enableModify = emailPatternCheck();
        }else{
            enableModify = emailPatternCheck() & comparePasswords(publicKeyBytes);
        }
        
        return enableModify;
    }

    
        /**
     * Sets up all the things related to undoing and redoing.
    */
    private void setUndoRedo() {
        EventStream<TextChange> usernameChanges = changesOf(txtUsername.textProperty()).map(textChange -> new TextChange(textChange, txtUsername));
        EventStream<TextChange> nameChanges = changesOf(txtFullName.textProperty()).map(textChange -> new TextChange(textChange, txtFullName));
        EventStream<TextChange> emailChanges = changesOf(txtEmail.textProperty()).map(textChange -> new TextChange(textChange, txtEmail));
        EventStream<TextChange> phoneChanges = changesOf(txtPhone.textProperty()).map(textChange -> new TextChange(textChange, txtPhone));
        EventStream<TextChange> passwordChanges = changesOf(txtPassword.textProperty()).map(textChange -> new TextChange(textChange, txtPassword));
        EventStream<TextChange> passwordConfirmChanges = changesOf(txtNewPassword.textProperty()).map(textChange -> new TextChange(textChange, txtNewPassword));
        inputChanges = merge(usernameChanges, nameChanges, emailChanges, phoneChanges, passwordChanges, passwordConfirmChanges);
        undoManager = UndoManagerFactory.unlimitedHistorySingleChangeUM(
                inputChanges,
                changes -> changes.invert(),
                changes -> changes.redo(),
                (previousChange, nextChange) -> previousChange.mergeWith(nextChange));
        btnUndo.disableProperty().bind(undoManager.undoAvailableProperty().map(x -> !x));
        btnRedo.disableProperty().bind(undoManager.redoAvailableProperty().map(x -> !x));
        btnUndo.setOnAction(event -> undoManager.undo());
        btnRedo.setOnAction(event -> undoManager.redo());
    }



         
}
