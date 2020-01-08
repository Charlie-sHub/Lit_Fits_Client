package lit_fits_client.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
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
import javafx.stage.Stage;

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
    protected Button btnSignUp;
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

    public Button getBtnSignUp() {
        return btnSignUp;
    }

    public void setBtnSignUp(Button btnSignUp) {
        this.btnSignUp = btnSignUp;
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
     * This function will initialised the window
     *
     * @param theme the path to the theme chosen
     * @param root
     */
    public void initStage(String theme, Parent root) {
        try {
            setTheme(theme);
            setElements();
            Scene scene = new Scene(root);
            setStylesheet(scene, theme);
            stage.setScene(scene);
            stage.setTitle("Log In");
            stage.setMinWidth(700);
            stage.setMinHeight(500);
            stage.show();
        } catch (Exception e) {
            createDialog(e);
            LOG.severe(e.getMessage());
        }
    }

    /**
     * This function set the initial stages of the control
     */
    private void setElements() {
        btnLogin.setDisable(true);
        btnSignUp.setDisable(false);
        lblLength.setVisible(false);
        setFocusTraversable();
        setListeners();
        txtUsername.requestFocus();
        textFields = new ArrayList<>();
        fillArray();
        undoneStrings = new ArrayList<>();
        btnLogin.setOnAction(this::onBtnLoginPress);
        btnLogin.setMnemonicParsing(true);
        btnLogin.setText("_Login");
        btnSignUp.setOnAction(this::onRegisterPress);
        btnSignUp.setMnemonicParsing(true);
        btnSignUp.setText("_Sign Up");
        btnUndo.setOnAction(this::onUndoPress);
        btnUndo.setMnemonicParsing(true);
        btnUndo.setText("_Undo");
        btnRedo.setDisable(true);
        btnRedo.setOnAction(this::onRedoPress);
        btnRedo.setMnemonicParsing(true);
        btnRedo.setText("_Redo");
        txtUsername.setEditable(true);
        fieldPassword.setEditable(true);
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
        btnSignUp.setFocusTraversable(true);
    }

    /**
     * Add listeners to all text inputs
     */
    private void setListeners() {
        txtUsername.textProperty().addListener(this::onFieldFilledListener);
        fieldPassword.textProperty().addListener(this::onFieldFilledListener);
    }

    /**
     * This function controlls that all fields are filled
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
        // Gotta adapt it so it opens the right main menu , company's main menu for example
        user = new User();
        try {
            user.setLogin(txtUsername.getText());
            user.setPassword(fieldPassword.getText());
            user.setUserStatus(true);
            user.setUserPrivilege(false);
            user = appLogic.logIn(user);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewProgramMain.fxml"));
            Stage stageProgramMain = new Stage();
            Parent root = (Parent) fxmlLoader.load();
            FXMLViewProgramController mainView = ((FXMLViewProgramController) fxmlLoader.getController());
            //no applogic anymore?
            mainView.setAppLogic(appLogic);
            mainView.setUser(user);
            mainView.setLogin(stage);
            //adapt it so it takes the theme chosen from the choice box
            mainView.initStage(happyMode, stageProgramMain, root);
            stage.hide();
        } catch (Exception e) {
            createDialog(e);
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewSignUp.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            FXMLViewRegisterController registerView = ((FXMLViewRegisterController) fxmlLoader.getController());
            registerStage = new Stage();
            //no applogic anymore?
            registerView.setAppLogic(appLogic);
            registerView.setLogin(stage);
            //adapt it so it takes the theme chosen from the choice box
            registerView.initStage(happyMode, registerStage, root);
            stage.hide();
        } catch (IOException e) {
            LOG.severe(e.getMessage());
        }
        stage.hide();
    }
}
