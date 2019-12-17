/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import lit_fits_client.entities.Company;

/**
 * This is the Document Controller class for the registration view of the program.
 *
 * @author Carlos Rafael Mendez Gonzalez
 */
public class FXMLViewCompanyRegisterController extends FXMLDocumentControllerInput {
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
     * Username text field
     */
    @FXML
    private TextField txtNif;
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
     * Repeat password text field
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
    private Company company;
    /**
     * Logger object
     */
    private static final Logger LOG = Logger.getLogger(FXMLViewCompanyRegisterController.class.getName());

    /**
     * Getter for btnCancel
     *
     * @return Button
     */
    public Button getBtnCancel() {
        return btnCancel;
    }

    /**
     * Setter for btnCancel
     *
     * @param btnCancel
     */
    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    /**
     * Getter for btnRegister
     *
     * @return Button
     */
    public Button getBtnRegister() {
        return btnRegister;
    }

    /**
     * Setter for btnRegister
     *
     * @param btnRegister
     */
    public void setBtnRegister(Button btnRegister) {
        this.btnRegister = btnRegister;
    }

    /**
     * Getter for txtPassword
     *
     * @return PasswordField
     */
    public PasswordField getTxtPassword() {
        return txtPassword;
    }

    /**
     * Setter for txtPassword
     *
     * @param txtPassword
     */
    public void setTxtPassword(PasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    /**
     * Getter for txtRepeatPassword
     *
     * @return PasswordField
     */
    public PasswordField getTxtRepeatPassword() {
        return txtRepeatPassword;
    }

    /**
     * Setter for txtRepeatPassword
     *
     * @param txtRepeatPassword
     */
    public void setTxtRepeatPassword(PasswordField txtRepeatPassword) {
        this.txtRepeatPassword = txtRepeatPassword;
    }

    /**
     * Getter for txtFullName
     *
     * @return TextField
     */
    public TextField getTxtFullName() {
        return txtFullName;
    }

    /**
     * Setter for txtFullName
     *
     * @param txtFullName
     */
    public void setTxtFullName(TextField txtFullName) {
        this.txtFullName = txtFullName;
    }

    /**
     * Getter for the txtEmail
     *
     * @return TextField
     */
    public TextField getTxtEmail() {
        return txtEmail;
    }

    /**
     * Setter for the txtEmail
     *
     * @param txtEmail
     */
    public void setTxtEmail(TextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    /**
     * Getter for the txtUsername
     *
     * @return TextField
     */
    public TextField getTxtNif() {
        return txtNif;
    }

    /**
     * Setter for the txtUsername
     *
     * @param txtNif
     */
    public void setTxtNif(TextField txtNif) {
        this.txtNif = txtNif;
    }

    /**
     * Getter for the lblPassMismatch
     *
     * @return Label
     */
    public Label getLblPassMismatch() {
        return lblPassMismatch;
    }

    /**
     * Setter for the lblPassMismatch
     *
     * @param lblPassMismatch
     */
    public void setLblPassMismatch(Label lblPassMismatch) {
        this.lblPassMismatch = lblPassMismatch;
    }

    /**
     * Getter for the stage of this view
     *
     * @return Stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Setter for the stage of this view
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Getter for the stage of login
     *
     * @return Stage
     */
    public Stage getLogin() {
        return previousStage;
    }

    /**
     * Setter for the stage of login
     *
     * @param login
     */
    public void setLogin(Stage login) {
        this.previousStage = login;
    }

    public Label getLblInvalidMail() {
        return lblInvalidMail;
    }

    public void setLblInvalidMail(Label lblInvalidMail) {
        this.lblInvalidMail = lblInvalidMail;
    }

    public TextField getTxtPhone() {
        return txtPhone;
    }

    public void setTxtPhone(TextField txtPhone) {
        this.txtPhone = txtPhone;
    }

    public Button getBtnHelp() {
        return btnHelp;
    }

    public void setBtnHelp(Button btnHelp) {
        this.btnHelp = btnHelp;
    }

    public Stage getPreviousStage() {
        return previousStage;
    }

    public void setPreviousStage(Stage previousStage) {
        this.previousStage = previousStage;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Initializes the register window
     *
     * @param theme The chosen css theme
     * @param stage The stage to be used
     * @param root The Parent created in the previous window
     */
    public void initStage(String theme, Stage stage, Parent root) {
        this.stage = stage;
        Scene scene = new Scene(root);
        setStylesheet(scene, theme);
        stage.setScene(scene);
        stage.setTitle("Registration");
        setElements();
        stage.setOnCloseRequest(this::onClosing);
        stage.setMinWidth(850);
        stage.setMinHeight(650);
        stage.show();
    }

    /**
     * Sets the properties for several elements of the window
     */
    private void setElements() {
        choiceTheme.setOnAction(this::onThemeChosen);
        btnRegister.setDisable(true);
        btnRedo.setDisable(true);
        lblPassMismatch.setVisible(false);
        lblLength.setVisible(false);
        setFocusTraversable();
        setListeners();
        txtNif.requestFocus();
        textFields = new ArrayList<>();
        fillArray();
        undoneStrings = new ArrayList<>();
        btnCancel.setOnAction(this::onBtnCancelPress);
        btnCancel.setMnemonicParsing(true);
        btnCancel.setText("_Cancel");
        btnRegister.setOnAction(this::onRegisterPress);
        btnRegister.setMnemonicParsing(true);
        btnRegister.setText("_Register");
        btnUndo.setOnAction(this::onUndoPress);
        btnUndo.setMnemonicParsing(true);
        btnUndo.setText("_Undo");
        btnRedo.setOnAction(this::onRedoPress);
        btnRedo.setMnemonicParsing(true);
        btnRedo.setText("_Redo");
        btnHelp.setOnKeyPressed(this::onF1Pressed);
        btnHelp.setOnAction(this::onHelpPressed);
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
            } catch (Exception e) {
                createDialog(e);
            }
        }
    }

    /**
     * Open the help window
     *
     * @throws IOException
     */
    private void openHelpView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewHelp.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stageHelp = new Stage();
        FXMLHelpController helpView = ((FXMLHelpController) fxmlLoader.getController());
        helpView.initStage(theme, stageHelp, root);
    }

    /**
     * Checks when the help button is pressed
     *
     * @param event
     */
    private void onHelpPressed(ActionEvent event) {
        try {
            openHelpView();
        } catch (Exception e) {
            createDialog(e);
        }
    }

    /**
     * Fills the array of text fields to check later if they're filled with text
     */
    private void fillArray() {
        textFields.add(txtNif);
        textFields.add(txtFullName);
        textFields.add(txtEmail);
        textFields.add(txtPhone);
        textFields.add(txtPassword);
        textFields.add(txtRepeatPassword);
    }

    /**
     * Enables the traverse of the focus to all elements in the window
     */
    private void setFocusTraversable() {
        txtNif.setFocusTraversable(true);
        txtPassword.setFocusTraversable(true);
        txtRepeatPassword.setFocusTraversable(true);
        txtFullName.setFocusTraversable(true);
        txtEmail.setFocusTraversable(true);
        txtPhone.setFocusTraversable(true);
        btnCancel.setFocusTraversable(true);
        btnRegister.setFocusTraversable(true);
        btnRedo.setFocusTraversable(true);
        btnUndo.setFocusTraversable(true);
    }

    /**
     * Add listeners to all text inputs
     */
    private void setListeners() {
        txtNif.textProperty().addListener(this::onFieldFilledListener);
        txtPassword.textProperty().addListener(this::onFieldFilledListener);
        txtRepeatPassword.textProperty().addListener(this::onFieldFilledListener);
        txtFullName.textProperty().addListener(this::onFieldFilledListener);
        txtEmail.textProperty().addListener(this::onFieldFilledListener);
        txtPhone.textProperty().addListener(this::onFieldFilledListener);
        txtNif.lengthProperty().addListener(this::lenghtListener);
        txtPassword.lengthProperty().addListener(this::lenghtListener);
        txtRepeatPassword.lengthProperty().addListener(this::lenghtListener);
        txtFullName.lengthProperty().addListener(this::lenghtListener);
        txtEmail.lengthProperty().addListener(this::lenghtListener);
        txtPhone.lengthProperty().addListener(this::lenghtListener);
    }

    public void lenghtListener(ObservableValue observable,
            Number oldValue,
            Number newValue) {
        lengthCheck(btnRegister); // Modificacion DIN 14/11/2019
    }

    /**
     * This function will cancel the register, close the window and will return to the login window
     *
     * @param event
     */
    public void onBtnCancelPress(ActionEvent event) {
        previousStage.show();
        stage.hide();
    }

    @Override
    public void onRegisterPress(ActionEvent event) {
        try {
            setCompanyData();
            company = appLogic.registerUser(user);
            try {
                openProgramMainWindow(user);
                stage.hide();
            } catch (IOException e) {
                LOG.severe(e.getMessage());
            }
        } catch (Exception e) {
            createDialog(e);
            LOG.log(Level.SEVERE, "{0} at: {1}", new Object[]{e.getMessage(), LocalDateTime.now()});
        }
    }

    /**
     * Sets the data of the user to be sent to the server
     */
    private void setCompanyData() {
        company.setEmail(txtEmail.getText());
        company.setFullName(txtFullName.getText());
        company.setNif(txtNif.getText());
        company.setPassword(txtPassword.getText());
        company.setPhoneNumber(txtPhone.getText());
    }

    /**
     * This method starts the main program window
     *
     * @throws IOException
     */
    private void openProgramMainWindow(Company company) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CompanyMainMenu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stageProgramMain = new Stage();
        FXMLViewCompanyMainMenuController mainView = ((FXMLViewCompanyMainMenuController) fxmlLoader.getController());
        //keep applogic?
        mainView.setAppLogic(appLogic);
        mainView.setCompany(company);
        mainView.setLogin(previousStage);
        mainView.initStage(theme, stageProgramMain, root);
        stage.hide();
    }

    /**
     * Enables or disables the btnRegister based on several factors
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    public void onFieldFilledListener(ObservableValue observable,
            String oldValue,
            String newValue) {
        // Modificacion DIN 13/11/2019        
        boolean enableRegisterPass = false;
        boolean enableRegisterEmail = false;
        enableRegisterPass = passwordMatchCheck();
        enableRegisterEmail = emailPatternCheck();
        if (enableRegisterPass & enableRegisterEmail) {
            onFieldFilled(btnRegister); // Modificacion DIN 14/11/2019
        } else {
            btnRegister.setDisable(true);
        }
    }

    /**
     * Checks that the email follows the email pattern
     *
     * @return boolean true if the email matches the pattern
     */
    private boolean emailPatternCheck() {
        boolean enableRegister;
        if (!Pattern.matches("[a-zA-Z_0-9]+@{1}[a-zA-Z_0-9]+[.]{1}[a-zA-Z_0-9]+", txtEmail.getText().trim())) {
            enableRegister = false;
        } else {
            enableRegister = true;
        }
        lblInvalidMail.setVisible(!enableRegister);
        return enableRegister;
    }

    /**
     * Checks that the passwords entered match each other
     *
     * @return boolean true if the passwords are the same
     */
    private boolean passwordMatchCheck() {
        boolean enableRegister;
        if (txtPassword.getText().trim().equals(txtRepeatPassword.getText().trim())) {
            lblPassMismatch.setVisible(false);
            enableRegister = true;
        } else {
            lblPassMismatch.setVisible(true);
            enableRegister = false;
        }
        return enableRegister;
    }
}
