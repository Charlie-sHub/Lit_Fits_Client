package lit_fits_client.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javax.ws.rs.ClientErrorException;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.CompanyClient;
import lit_fits_client.entities.Company;

/**
 * Controller for the Login view
 *
 * @author Ander & Carlos Mendez
 */
public class FXMLViewLoginController extends FXMLDocumentControllerInput {
    /**
     * Button to attempt loggin in
     */
    @FXML
    protected Button btnLogin;
    /**
     * Button to access the registration view
     */
    @FXML
    protected Button btnRegister;
    /**
     * Button to reestablish the password
     */
    @FXML
    private Button btnReestablishPassword;
    /**
     * Radio button to access the company registration view
     */
    @FXML
    private RadioButton rBtnCompany;
    /**
     * Radio button to access the fashion expert registration view
     */
    @FXML
    private RadioButton rBtnFashionExpert;
    /**
     * Text field where username must be entered to log in
     */
    @FXML
    protected TextField txtUsername;
    /**
     *
     */
    @FXML
    protected PasswordField fieldPassword;
    private Stage stage;
    private Stage registerStage;
    private Stage mainStage;
    private static final Logger LOG = Logger.getLogger(FXMLViewLoginController.class.getName());

    public Button getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(Button btnLogin) {
        this.btnLogin = btnLogin;
    }

    public Button getBtnRegister() {
        return btnRegister;
    }

    public void setBtnRegister(Button btnRegister) {
        this.btnRegister = btnRegister;
    }

    public Button getBtnReestablishPassword() {
        return btnReestablishPassword;
    }

    public void setBtnReestablishPassword(Button btnReestablishPassword) {
        this.btnReestablishPassword = btnReestablishPassword;
    }

    public RadioButton getrBtnCompany() {
        return rBtnCompany;
    }

    public void setrBtnCompany(RadioButton rBtnCompany) {
        this.rBtnCompany = rBtnCompany;
    }

    public RadioButton getrBtnFashionExpert() {
        return rBtnFashionExpert;
    }

    public void setrBtnFashionExpert(RadioButton rBtnFashionExpert) {
        this.rBtnFashionExpert = rBtnFashionExpert;
    }

    public TextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(TextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public PasswordField getFieldPassword() {
        return fieldPassword;
    }

    public void setFieldPassword(PasswordField fieldPassword) {
        this.fieldPassword = fieldPassword;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getRegisterStage() {
        return registerStage;
    }

    public void setRegisterStage(Stage registerStage) {
        this.registerStage = registerStage;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    /**
     * This function will initialize the window
     *
     * @param theme the path to the theme chosen
     * @param root
     * @param uri
     */
    public void initStage(String theme, Parent root, String uri) {
        try {
            this.uri = uri;
            Scene scene = new Scene(root);
            setStylesheet(scene, theme);
            stage.setScene(scene);
            stage.setTitle("Log In");
            stage.setMinWidth(700);
            stage.setMinHeight(500);
            setElements();
            stage.show();
        } catch (Exception e) {
            createExceptionDialog(e);
            LOG.severe(e.getMessage());
        }
    }

    /**
     * This function set the initial stages of the control
     */
    private void setElements() {
        lblLength.setVisible(false);
        setFocusTraversable();
        setListeners();
        txtUsername.requestFocus();
        textFields = new ArrayList<>();
        fillArray();
        undoneStrings = new ArrayList<>();
        setOnAction();
        setMnemonicParsing();
        btnRedo.setDisable(true);
        btnLogin.setDisable(true);
        btnRegister.setDisable(false);
        txtUsername.setEditable(true);
        fieldPassword.setEditable(true);
        setTooltips();
    }

    /**
     * Sets the tooltips of different elements
     */
    private void setTooltips() {
        btnLogin.setTooltip(new Tooltip("Log into the program"));
        btnReestablishPassword.setTooltip(new Tooltip("Forgot your password?"));
        btnRegister.setTooltip(new Tooltip("Register a new account"));
        btnRedo.setTooltip(new Tooltip("Redo what's been erased"));
        btnUndo.setTooltip(new Tooltip("Erase everything"));
        rBtnCompany.setTooltip(new Tooltip("Do you represent a company?"));
        rBtnFashionExpert.setTooltip(new Tooltip("Are you a fashion expert?"));
        choiceTheme.setTooltip(new Tooltip("Choose the theme you like the most"));
    }

    /**
     * Sets the mnemonic parsing for different elements
     */
    private void setMnemonicParsing() {
        btnLogin.setText("_Login");
        btnRegister.setText("_Sign Up");
        btnUndo.setText("_Undo");
        btnRedo.setText("_Redo");
        btnRegister.setMnemonicParsing(true);
        btnLogin.setMnemonicParsing(true);
        btnUndo.setMnemonicParsing(true);
        btnRedo.setMnemonicParsing(true);
    }

    /**
     * Sets the methods that will be called when actions are performed on different elements
     */
    private void setOnAction() {
        btnLogin.setOnAction(this::onBtnLoginPress);
        btnRegister.setOnAction(this::onRegisterPress);
        btnUndo.setOnAction(this::onUndoPress);
        btnRedo.setOnAction(this::onRedoPress);
        btnReestablishPassword.setOnAction(this::onReestablishPasswordPress);
    }

    /**
     * Checks what kind and what specific of account it is and requests the server to reestablish the corresponding
     * password
     *
     * @param event
     */
    private void onReestablishPasswordPress(ActionEvent event) {
        try {
            if (txtUsername.getText() != null) {
                if (nifPatternCheck(txtUsername.getText())) {
                    CompanyClient companyClient = new ClientFactory().getCompanyClient(uri);
                    companyClient.reestablishPassword(txtUsername.getText());
                    companyClient.close(); // is it important to close them?
                } else {
                    //fashion expert and admin register
                }
            } else {
                createDialog("Please insert your username/nif");
            }
        } catch (ClientErrorException e) {
            createExceptionDialog(e);
        }
    }

    /**
     * Fills the array of text fields to check later if they're filled with text
     */
    private void fillArray() {
        textFields.add(txtUsername);
        textFields.add(fieldPassword);
    }

    /**
     * This function set the focus of the fields
     */
    private void setFocusTraversable() {
        txtUsername.setFocusTraversable(true);
        fieldPassword.setFocusTraversable(true);
        btnLogin.setFocusTraversable(true);
        btnRegister.setFocusTraversable(true);
        btnUndo.setFocusTraversable(true);
        btnRedo.setFocusTraversable(true);
        rBtnCompany.setFocusTraversable(true);
        rBtnFashionExpert.setFocusTraversable(true);
        btnReestablishPassword.setFocusTraversable(true);
    }

    /**
     * Add listeners to all text inputs
     */
    private void setListeners() {
        txtUsername.textProperty().addListener(this::onFieldFilledListener);
        fieldPassword.textProperty().addListener(this::onFieldFilledListener);
    }

    /**
     * This function controls that all fields are filled
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    public void onFieldFilledListener(ObservableValue observable,
            String oldValue,
            String newValue) {
        onFieldFilled(btnLogin);
    }

    /**
     * This function will be on charge of doing the login of the user will be used when the button Login is pressed
     *
     * @param event
     */
    private void onBtnLoginPress(ActionEvent event) {
        try {
            if (nifPatternCheck(txtUsername.getText())) {
                Company company = new Company();
                CompanyClient companyClient = new ClientFactory().getCompanyClient(uri);
                company.setNif(txtUsername.getText());
                company.setPassword(fieldPassword.getText());
                company = companyClient.login(company, Company.class);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewCompanyMainMenuController.fxml"));
                Stage stageCompanyMainMenu = new Stage();
                Parent root = (Parent) fxmlLoader.load();
                FXMLViewCompanyMainMenuController mainView = ((FXMLViewCompanyMainMenuController) fxmlLoader.getController());
                mainView.setCompany(company);
                mainView.setLoginStage(this.stage);
                mainView.initStage(theme, stageCompanyMainMenu, root, uri);
            } else {
                //fashion expert
            }
            stage.hide();
        } catch (IOException | ClientErrorException e) {
            createExceptionDialog(e);
            LOG.severe(e.getMessage());
        }
    }

    /**
     * This functions open the window of register and hide the stage of login
     *
     * @param event
     */
    @Override
    public void onRegisterPress(ActionEvent event) {
        try {
            if (rBtnCompany.isSelected()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewCompanyRegisterController.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                FXMLViewCompanyRegisterController registerView = ((FXMLViewCompanyRegisterController) fxmlLoader.getController());
                registerStage = new Stage();
                registerView.setLogin(stage);
                registerView.initStage(theme, registerStage, root, uri);
            } else {
                // fashion expert registration
            }
            stage.hide();
        } catch (IOException e) {
            LOG.severe(e.getMessage());
        }
        stage.hide();
    }

    /**
     * Checks that a given String follows the nif pattern
     *
     * @return boolean true if the nif matches the pattern
     */
    private boolean nifPatternCheck(String nif) {
        boolean isNif;
        isNif = Pattern.matches("[A-W]{1}[0-9]{7}[A-Z_0-9]{1}", nif);
        return isNif;
    }
}
