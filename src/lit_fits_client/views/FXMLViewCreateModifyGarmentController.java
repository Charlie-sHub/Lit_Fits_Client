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
//import thebestprogramlogiclibrary.User;

/**
 * This is the Document Controller class for the registration view of the program.
 *
 * @author Carlos Rafael Mendez Gonzalez
 */
public class FXMLViewCreateModifyGarmentController extends FXMLDocumentControllerInput {
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
     * Help window button
     */
    @FXML
    private Button btnHelp;
    private Stage stage;
    /**
     * Logger object
     */
    private static final Logger LOG = Logger.getLogger(FXMLViewCreateModifyGarmentController.class.getName());

    /**
     * Initializes the garment creation/modification window
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
        chkHappyMode.setOnAction(this::onThemeChosen);
        chkHappyMode.setSelected(happyMode);
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
        btnHelp.setOnKeyPressed(this::onF1Pressed); // Modificacion DIN 14/11/2019
        btnHelp.setOnAction(this::onHelpPressed); // Modificacion DIN 14/11/2019
    }

    /**
     * Checks that the F1 key is pressed to open the help vindow
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
        helpView.initStage(happyMode, stageHelp, root);
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
        txtNif.lengthProperty().addListener(this::lenghtListener);
        txtPassword.lengthProperty().addListener(this::lenghtListener);
        txtRepeatPassword.lengthProperty().addListener(this::lenghtListener);
        txtFullName.lengthProperty().addListener(this::lenghtListener);
        txtEmail.lengthProperty().addListener(this::lenghtListener);
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
        loginStage.show();
        stage.hide();
    }

    @Override
    public void onRegisterPress(ActionEvent event) {
        user = new User();
        try {
            setUserdata();
            user = appLogic.registerUser(user);
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
    private void setUserdata() {
        user.setEmail(txtEmail.getText());
        user.setFullName(txtFullName.getText());
        user.setLogin(txtNif.getText());
        user.setPassword(txtPassword.getText());
        user.setUserStatus(true);
        user.setUserPrivilege(false);
        user.setUserPrivilege(false);
        user.setUserStatus(true);
    }

    /**
     * This method starts the main program window
     *
     * @throws IOException
     */
    private void openProgramMainWindow(User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewProgramMain.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stageProgramMain = new Stage();
        FXMLViewProgramController mainView = ((FXMLViewProgramController) fxmlLoader.getController());
        mainView.setAppLogic(appLogic);
        mainView.setUser(user);
        mainView.setLogin(loginStage);
        mainView.initStage(happyMode, stageProgramMain, root);
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
