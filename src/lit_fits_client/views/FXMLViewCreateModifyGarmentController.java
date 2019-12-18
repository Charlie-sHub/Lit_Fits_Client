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
import lit_fits_client.entities.Garment;

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
    private Button btnRegister;
    /**
     * Username text field
     */
    @FXML
    private TextField txtBarcode;
    /**
     * Full name text field
     */
    @FXML
    private TextField txtDesigner;
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
    /**
     * The garment object to be used by the window, if none is given from the previous window then a new Garment will be
     * created
     */
    private Garment garment;
    /**
     * Logger object
     */
    private static final Logger LOG = Logger.getLogger(FXMLViewCreateModifyGarmentController.class.getName());

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
     * Getter for txtFullName
     *
     * @return TextField
     */
    public TextField getTxtDesigner() {
        return txtDesigner;
    }

    /**
     * Setter for txtFullName
     *
     * @param txtDesigner
     */
    public void setTxtDesigner(TextField txtDesigner) {
        this.txtDesigner = txtDesigner;
    }

    /**
     * Getter for the txtUsername
     *
     * @return TextField
     */
    public TextField getTxtBarcode() {
        return txtBarcode;
    }

    /**
     * Setter for the txtUsername
     *
     * @param txtBarcode
     */
    public void setTxtBarcode(TextField txtBarcode) {
        this.txtBarcode = txtBarcode;
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

    /**
     * Getter for the help button
     *
     * @return Button
     */
    public Button getBtnHelp() {
        return btnHelp;
    }

    /**
     * Setter for the help button
     *
     * @param btnHelp
     */
    public void setBtnHelp(Button btnHelp) {
        this.btnHelp = btnHelp;
    }

    /**
     * Getter of the previous stage
     *
     * @return Stage
     */
    public Stage getPreviousStage() {
        return previousStage;
    }

    /**
     * Setter for the previous stage
     *
     * @param previousStage
     */
    public void setPreviousStage(Stage previousStage) {
        this.previousStage = previousStage;
    }

    /**
     * Getter of the Garment used by the window
     *
     * @return Garment
     */
    public Garment getGarment() {
        return garment;
    }

    /**
     * Setter of the Garment to be used by the window
     *
     * @param garment
     */
    public void setGarment(Garment garment) {
        this.garment = garment;
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
        setElements();
        if (null != garment) {
            stage.setTitle("Modification");
            fillFields();
        } else {
            stage.setTitle("Creation");
            garment = new Garment();
        }
        stage.setOnCloseRequest(this::onClosing);
        //pretty sure these dimensions will have to change
        stage.setMinWidth(850);
        stage.setMinHeight(650);
        stage.show();
    }

    /**
     * Fills the fields with the data of a given garment
     */
    private void fillFields() {
        txtDesigner.requestFocus();
        txtBarcode.setEditable(false);
        txtBarcode.setText(garment.getBarcode());
        txtDesigner.setText(garment.getDesigner());
        //Fill the values for the combo boxes
    }

    /**
     * Sets the properties for several elements of the window
     */
    private void setElements() {
        choiceTheme.setOnAction(this::onThemeChosen);
        lblLength.setVisible(false);
        btnRegister.setDisable(true);
        btnRedo.setDisable(true);
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
        txtBarcode.requestFocus();
        setFocusTraversable();
        setListeners();
        textFields = new ArrayList<>();
        fillArray();
        undoneStrings = new ArrayList<>();
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
        textFields.add(txtBarcode);
        textFields.add(txtDesigner);
        // How to make the combo boxes work with my shotty attempt at a undo and redo?
    }

    /**
     * Enables the traverse of the focus to all elements in the window
     */
    private void setFocusTraversable() {
        txtBarcode.setFocusTraversable(true);
        txtDesigner.setFocusTraversable(true);
        btnCancel.setFocusTraversable(true);
        btnRegister.setFocusTraversable(true);
        btnRedo.setFocusTraversable(true);
        btnUndo.setFocusTraversable(true);
        //Set the comboboxes traversable too
    }

    /**
     * Add listeners to all text inputs
     */
    private void setListeners() {
        txtBarcode.textProperty().addListener(this::onFieldFilledListener);
        txtDesigner.textProperty().addListener(this::onFieldFilledListener);
        txtBarcode.lengthProperty().addListener(this::lenghtListener);
        txtDesigner.lengthProperty().addListener(this::lenghtListener);
        //how to do the same of something similar with the combo boxes?
    }

    /**
     * Simply calls the proper length listener method
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    public void lenghtListener(ObservableValue observable, Number oldValue, Number newValue) {
        lengthCheck(btnRegister);
    }

    /**
     * This function will cancel the register/modification, close the window and will return to the previous window
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
            setGarmentData();
            // how to distinguish between making a PUT to update and a POST to insert a new garment? check if their id is null perhaps?
            garment = appLogic.registerUser(user);
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
     * Sets the data of the garment to be sent to the server
     */
    private void setGarmentData() {
        garment.setBarcode(txtBarcode.getText());
        garment.setDesigner(txtDesigner.getText());
        //set the combo box values too of course
    }

    /**
     * This method starts the main program window
     *
     * @throws IOException
     */
    private void openProgramMainWindow(Garment garment) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/GarmentMainMenu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stageProgramMain = new Stage();
        FXMLViewGarmentMainMenuController mainView = ((FXMLViewGarmentMainMenuController) fxmlLoader.getController());
        //keep applogic?
        mainView.setAppLogic(appLogic);
        mainView.setGarment(garment);
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
    public void onFieldFilledListener(ObservableValue observable, String oldValue, String newValue) {
        boolean enableRegisterPass = false;
        boolean enableRegisterEmail = false;
        enableRegisterPass = passwordMatchCheck();
        enableRegisterEmail = emailPatternCheck();
        if (enableRegisterPass & enableRegisterEmail) {
            onFieldFilled(btnRegister);
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
