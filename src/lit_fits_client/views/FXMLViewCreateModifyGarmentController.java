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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private Button btnSubmit;
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
     * Combo box of moods
     */
    @FXML
    private ComboBox comboMood;
    /**
     * Combo box of body parts
     */
    @FXML
    private ComboBox comboBodyPart;
    /**
     * Combo box of the types of garment
     */
    @FXML
    private ComboBox comboGarmentType;
    /**
     * Combo box of colors
     */
    @FXML
    private ComboBox comboColors;
    /**
     * Combo box of materials
     */
    @FXML
    private ComboBox comboMaterials;
    /**
     * Label for the price text field
     */
    @FXML
    private Label lblPrice;
    /**
     * Label that appears when a field has too much text
     */
    @FXML
    private Label lblLength;
    /**
     * Text field for entering the price of the garment
     */
    @FXML
    private TextField txtPrice;
    /**
     * Label that appears when the price doesn't match the pattern
     */
    @FXML
    private Label lblInvalidPrice;
    /**
     * Label that appears when the price doesn't match the pattern
     */
    @FXML
    private Label lblInvalidBarcode;
    /**
     * Stage to be used by the current controller
     */
    private Stage stage;
    /**
     * Stage of the previous window
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
     * Getter for btnSubmit
     *
     * @return Button
     */
    public Button getBtnRegister() {
        return btnSubmit;
    }

    /**
     * Setter for btnSubmit
     *
     * @param btnSubmit
     */
    public void setBtnRegister(Button btnSubmit) {
        this.btnSubmit = btnSubmit;
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
     * Getter for the submit button
     *
     * @return Button
     */
    public Button getBtnSubmit() {
        return btnSubmit;
    }

    /**
     * Setter for the submit button
     *
     * @param btnSubmit
     */
    public void setBtnSubmit(Button btnSubmit) {
        this.btnSubmit = btnSubmit;
    }

    /**
     * Getter for the mood combo box
     *
     * @return ComboBox
     */
    public ComboBox getComboMood() {
        return comboMood;
    }

    /**
     * Setter for the mood combo box
     *
     * @param comboMood
     */
    public void setComboMood(ComboBox comboMood) {
        this.comboMood = comboMood;
    }

    /**
     * Getter for the body part combo box
     *
     * @return ComboBox
     */
    public ComboBox getComboBodyPart() {
        return comboBodyPart;
    }

    /**
     * Setter for the body parts combo box
     *
     * @param comboMood
     */
    public void setComboBodyPart(ComboBox comboBodyPart) {
        this.comboBodyPart = comboBodyPart;
    }

    /**
     * Getter for the garment type combo box
     *
     * @return ComboBox
     */
    public ComboBox getComboGarmentType() {
        return comboGarmentType;
    }

    /**
     * Setter for the garment type combo box
     *
     * @param comboMood
     */
    public void setComboGarmentType(ComboBox comboGarmentType) {
        this.comboGarmentType = comboGarmentType;
    }

    /**
     * Getter for the colors combo box
     *
     * @return ComboBox
     */
    public ComboBox getComboColors() {
        return comboColors;
    }

    /**
     * Setter for the colors combo box
     *
     * @param comboMood
     */
    public void setComboColors(ComboBox comboColors) {
        this.comboColors = comboColors;
    }

    /**
     * Getter for the materials combo box
     *
     * @return ComboBox
     */
    public ComboBox getComboMaterials() {
        return comboMaterials;
    }

    /**
     * Setter for the materials combo box
     *
     * @param comboMood
     */
    public void setComboMaterials(ComboBox comboMaterials) {
        this.comboMaterials = comboMaterials;
    }

    /**
     * Getter for the price label
     *
     * @return Label
     */
    public Label getLblPrice() {
        return lblPrice;
    }

    /**
     * Setter for the price label
     *
     * @param lblPrice
     */
    public void setLblPrice(Label lblPrice) {
        this.lblPrice = lblPrice;
    }

    /**
     * Getter for the length label
     *
     * @return label
     */
    public Label getLblLength() {
        return lblLength;
    }

    /**
     * Setter for the length label
     *
     * @param lblLength
     */
    public void setLblLength(Label lblLength) {
        this.lblLength = lblLength;
    }

    /**
     * Getter for the price text field
     *
     * @return TextField
     */
    public TextField getTxtPrice() {
        return txtPrice;
    }

    /**
     * Setter for the price text field
     *
     * @param txtPrice
     */
    public void setTxtPrice(TextField txtPrice) {
        this.txtPrice = txtPrice;
    }

    /**
     * Getter for the invalid price label
     *
     * @return Label
     */
    public Label getLblInvalidPrice() {
        return lblInvalidPrice;
    }

    /**
     * Setter for the invalid price label
     *
     * @param lblInvalidPrice
     */
    public void setLblInvalidPrice(Label lblInvalidPrice) {
        this.lblInvalidPrice = lblInvalidPrice;
    }

    /**
     * Getter for the invalid barcode label
     *
     * @return Label
     */
    public Label getLblInvalidBarcode() {
        return lblInvalidBarcode;
    }

    /**
     * Setter for the invalid barcode label
     *
     * @param lblInvalidBarcode
     */
    public void setLblInvalidBarcode(Label lblInvalidBarcode) {
        this.lblInvalidBarcode = lblInvalidBarcode;
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
        txtBarcode.requestFocus();
        txtBarcode.setText(garment.getBarcode());
        txtDesigner.setText(garment.getDesigner());
        txtPrice.setText(garment.getPrice().toString());
        //Fill the values for the combo boxes
        //Set value or set CHOSEN value or what?
        comboBodyPart.setValue(garment.getBodyPart().toString());
        comboGarmentType.setValue(garment.getGarmentType().toString());
        comboMood.setValue(garment.getMood().toString());
    }

    /**
     * Sets the properties for several elements of the window
     */
    private void setElements() {
        //Get list of colors and materials to fill those combo boxes with
        //Fill the other combo boxes with the enums
        choiceTheme.setOnAction(this::onThemeChosen);
        lblLength.setVisible(false);
        btnSubmit.setDisable(true);
        btnRedo.setDisable(true);
        btnCancel.setOnAction(this::onBtnCancelPress);
        btnCancel.setMnemonicParsing(true);
        btnCancel.setText("_Cancel");
        btnSubmit.setOnAction(this::onRegisterPress);
        btnSubmit.setMnemonicParsing(true);
        btnSubmit.setText("_Register");
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
        btnSubmit.setFocusTraversable(true);
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
        lengthCheck(btnSubmit);
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
     * Enables or disables the btnSubmit based on several factors
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    public void onFieldFilledListener(ObservableValue observable, String oldValue, String newValue) {
        boolean enableRegisterBarcode = false;
        boolean enableRegisterPrice = false;
        enableRegisterBarcode = barcodePatternCheck();
        enableRegisterPrice = pricePatternCheck();
        if (enableRegisterPrice & enableRegisterBarcode) {
            onFieldFilled(btnSubmit);
        } else {
            btnSubmit.setDisable(true);
        }
    }

    /**
     * Checks that the price follows the price pattern
     *
     * @return boolean true if the price matches the pattern
     */
    private boolean pricePatternCheck() {
        boolean enableRegister;
        if (!Pattern.matches("[0-9]+,{1}[0-9]{2}[$€£¥]{1}", txtPrice.getText().trim())) {
            enableRegister = false;
        } else {
            enableRegister = true;
        }
        lblInvalidPrice.setVisible(!enableRegister);
        return enableRegister;
    }

    /**
     * Checks that the barcode follows the barcode pattern
     *
     * @return boolean true if the barcode matches the pattern
     */
    private boolean barcodePatternCheck() {
        boolean enableRegister;
        if (!Pattern.matches("[0-9]+", txtBarcode.getText().trim())) {
            enableRegister = false;
        } else {
            enableRegister = true;
        }
        lblInvalidBarcode.setVisible(!enableRegister);
        return enableRegister;
    }
}
