package lit_fits_client.views;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.ws.rs.ClientErrorException;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.ColorClient;
import lit_fits_client.RESTClients.GarmentClient;
import lit_fits_client.RESTClients.MaterialClient;
import lit_fits_client.entities.BodyPart;
import lit_fits_client.entities.Color;
import lit_fits_client.entities.Company;
import lit_fits_client.entities.Garment;
import lit_fits_client.entities.GarmentType;
import lit_fits_client.entities.Material;
import lit_fits_client.entities.Mood;

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
     * The ImageView for the picture of the Garment
     */
    @FXML
    private ImageView imageViewGarmentPicture;
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
     * The company that logged in
     */
    private Company company;
    /**
     * An ArrayList of the combo boxes in the stage, used to check if they've all been filled
     */
    private ArrayList<ComboBox> comboBoxes;
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
     * @param comboBodyPart
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
     * @param comboGarmentType
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
     * @param comboColors
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
     * @param comboMaterials
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
     * Getter of the company
     *
     * @return Company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Setter of the Company
     *
     * @param company
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Getter for the ImageView
     *
     * @return ImageView
     */
    public ImageView getImageViewGarmentPicture() {
        return imageViewGarmentPicture;
    }

    /**
     * Setter for the ImageView
     *
     * @param imageViewGarmentPicture
     */
    public void setImageViewGarmentPicture(ImageView imageViewGarmentPicture) {
        this.imageViewGarmentPicture = imageViewGarmentPicture;
    }

    /**
     * Getter for the Arraylist of combo boxes
     *
     * @return ArrayList
     */
    public ArrayList<ComboBox> getComboBoxes() {
        return comboBoxes;
    }

    /**
     * Setter for the ArrayList of combo boxes
     *
     * @param comboBoxes
     */
    public void setComboBoxes(ArrayList<ComboBox> comboBoxes) {
        this.comboBoxes = comboBoxes;
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
        stage.initModality(Modality.APPLICATION_MODAL);
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
        Image garmentPicture = new Image(garment.getPicture().getAbsolutePath()); // Does this even have an absolute path? doubt it
        imageViewGarmentPicture.setImage(garmentPicture);
    }

    /**
     * Sets the properties for several elements of the window
     */
    private void setElements() {
        //How to choose several colors and materials?
        //Fill the other combo boxes with the enums
        Image image = new Image("/placeholder.jpg");
        imageViewGarmentPicture.setImage(image);
        setTooltips();
        lblLength.setVisible(false);
        btnSubmit.setDisable(true);
        btnRedo.setDisable(true);
        setOnAction();
        btnHelp.setOnKeyPressed(this::onF1Pressed);
        setTextMnemonic();
        setButtonsMnemonicParsing();
        txtBarcode.requestFocus();
        setFocusTraversable();
        setListeners();
        textFields = new ArrayList<>();
        fillTxtArray();
        undoneStrings = new ArrayList<>();
        fillComboBoxes();
        fillComboBoxArray();
    }

    /**
     * Fills the combo boxes with their corresponding items
     *
     * @throws ClientErrorException
     */
    private void fillComboBoxes() throws ClientErrorException {
        ColorClient colorClient;
        colorClient = new ClientFactory().getColorClient();
        comboColors.setItems(colorClient.findAll(List.class));
        colorClient.close();
        MaterialClient materialClient;
        materialClient = new ClientFactory().getMaterialClient();
        comboColors.setItems(materialClient.findAll(List.class));
        materialClient.close();
        comboBodyPart.getItems().setAll(Arrays.toString(BodyPart.values()));
        comboMood.getItems().setAll(Arrays.toString(Mood.values()));
        comboGarmentType.getItems().setAll(Arrays.toString(GarmentType.values()));
    }

    /**
     * Fills the ArrayList of combo boxes with the combo boxes of the stage
     */
    private void fillComboBoxArray() {
        comboBoxes.add(comboBodyPart);
        comboBoxes.add(comboColors);
        comboBoxes.add(comboGarmentType);
        comboBoxes.add(comboMaterials);
        comboBoxes.add(comboMood);
    }

    /**
     * Sets the tooltip text for the elements of the window
     */
    private void setTooltips() {
        choiceTheme.setTooltip(new Tooltip("Choose the theme you like the most"));
        btnCancel.setTooltip(new Tooltip("Get back"));
        btnHelp.setTooltip(new Tooltip("Displays a window with help"));
        btnRedo.setTooltip(new Tooltip("Redoes everything undo erased"));
        btnSubmit.setTooltip(new Tooltip("Send the information"));
        btnUndo.setTooltip(new Tooltip("Erases everything"));
        comboBodyPart.setTooltip(new Tooltip("Choose where the garment is worn"));
        comboGarmentType.setTooltip(new Tooltip("Erases everything"));
        comboMood.setTooltip(new Tooltip("Erases everything"));
        comboColors.setTooltip(new Tooltip("Choose the colors of the garment"));
        comboMaterials.setTooltip(new Tooltip("Choose the materials of the garment"));
    }

    /**
     * Sets the mnemonic parsing to true
     */
    private void setButtonsMnemonicParsing() {
        btnSubmit.setMnemonicParsing(true);
        btnCancel.setMnemonicParsing(true);
        btnRedo.setMnemonicParsing(true);
        btnUndo.setMnemonicParsing(true);
    }

    /**
     * Sets the text used for mnemonic parsing
     */
    private void setTextMnemonic() {
        btnCancel.setText("_Cancel");
        btnSubmit.setText("_Register");
        btnUndo.setText("_Undo");
        btnRedo.setText("_Redo");
    }

    /**
     * Sets what happens when the buttons are pressed
     */
    private void setOnAction() {
        choiceTheme.setOnAction(this::onThemeChosen);
        btnCancel.setOnAction(this::onBtnCancelPress);
        btnSubmit.setOnAction(this::onRegisterPress);
        btnUndo.setOnAction(this::onUndoPress);
        btnHelp.setOnAction(this::onHelpPressed);
        btnRedo.setOnAction(this::onRedoPress);
        imageViewGarmentPicture.setOnMouseClicked(this::onImageViewPress);
        imageViewGarmentPicture.setOnKeyPressed(this::onImageViewPress);
    }

    /**
     * Fills the array of text fields to check later if they're filled with text
     */
    private void fillTxtArray() {
        textFields.add(txtBarcode);
        textFields.add(txtDesigner);
        textFields.add(txtPrice);
        // How to make the combo boxes work with my shitty attempt at a undo and redo?
    }

    /**
     * Enables the traverse of the focus to all elements in the window
     */
    private void setFocusTraversable() {
        txtBarcode.setFocusTraversable(true);
        txtDesigner.setFocusTraversable(true);
        txtPrice.setFocusTraversable(true);
        btnCancel.setFocusTraversable(true);
        btnSubmit.setFocusTraversable(true);
        btnRedo.setFocusTraversable(true);
        btnUndo.setFocusTraversable(true);
        imageViewGarmentPicture.setFocusTraversable(true);
        choiceTheme.setFocusTraversable(true);
        comboBodyPart.setFocusTraversable(true);
        comboColors.setFocusTraversable(true);
        comboGarmentType.setFocusTraversable(true);
        comboMaterials.setFocusTraversable(true);
        comboMood.setFocusTraversable(true);
    }

    /**
     * Add listeners to all text inputs
     */
    private void setListeners() {
        txtBarcode.textProperty().addListener(this::onFieldFilledListener);
        txtDesigner.textProperty().addListener(this::onFieldFilledListener);
        txtBarcode.lengthProperty().addListener(this::lenghtListener);
        txtDesigner.lengthProperty().addListener(this::lenghtListener);
        comboBodyPart.onActionProperty().addListener(this::onItemChosen);
        comboColors.onActionProperty().addListener(this::onItemChosen);
        comboGarmentType.onActionProperty().addListener(this::onItemChosen);
        comboMaterials.onActionProperty().addListener(this::onItemChosen);
        comboMood.onActionProperty().addListener(this::onItemChosen);
    }

    /**
     * simply calls the proper method to check if something has been chosen
     *
     * @param object
     */
    public void onItemChosen(Object object) {
        checkComboBoxes(btnSubmit);
    }

    /**
     * Enables the submit button if there's something chosen in each combo box, it also calls onFieldFilled to check the
     * rest of the fields
     *
     * @param btnSubmit
     */
    private void checkComboBoxes(Button btnSubmit) {
        Boolean disableSubmit = null;
        for (ComboBox comboBox : comboBoxes) {
            if (comboBox.getValue() == null) {
                disableSubmit = true;
                break;
            } else {
                disableSubmit = false;
            }
        }
        btnSubmit.setDisable(disableSubmit);
        onFieldFilled(btnSubmit);
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
        GarmentClient garmentClient = new ClientFactory().getGarmentClient();
        try {
            setGarmentData();
            if (garment.getId() == 0) {
                garmentClient.editGarment(garment);
            } else {
                garmentClient.createGarment(garment);
            }
            stage.hide();
        } catch (ClientErrorException e) {
            createExceptionDialog(e);
            LOG.log(Level.SEVERE, "{0} at: {1}", new Object[]{e.getMessage(), LocalDateTime.now()});
        } finally {
            garmentClient.close();
        }
    }

    /**
     * Sets the data of the garment to be sent to the server
     */
    private void setGarmentData() {
        garment.setBarcode(txtBarcode.getText());
        garment.setPrice(Double.valueOf(txtPrice.getText()));
        garment.setDesigner(txtDesigner.getText());
        garment.setCompany(company);
        garment.setAvailable(true);
        garment.setPromoted(false);
        garment.setPromotionRequest(false);
        garment.setBodyPart((BodyPart) comboBodyPart.getValue());
        garment.setColors((Set<Color>) comboColors.getValue());
        garment.setGarmentType((GarmentType) comboGarmentType.getValue());
        garment.setMaterials((Set<Material>) comboMaterials.getValue());
        garment.setMood((Mood) comboMood.getValue());
        //garment.setPicture(imageViewGarmentPicture.getImage()); // How to do that?
    }

    /**
     * Enables or disables the btnSubmit based on several factors
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    public void onFieldFilledListener(ObservableValue observable, String oldValue, String newValue) {
        if (pricePatternCheck() & barcodePatternCheck()) {
            onFieldFilled(btnSubmit);
        } else {
            btnSubmit.setDisable(true);
        }
    }

    /**
     * Opens a file chooser to change the Image
     */
    public void onImageViewPress() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File garmentPictureFile = fileChooser.showOpenDialog(stage);
        Image garmentPicture = new Image(garmentPictureFile.getAbsolutePath()); //Maybe this will be a problem in Linux
        imageViewGarmentPicture.setImage(garmentPicture);
    }

    /**
     * Checks that the price follows the price pattern
     *
     * @return boolean true if the price matches the pattern
     */
    private boolean pricePatternCheck() {
        boolean enableRegister;
        enableRegister = Pattern.matches("[0-9]+,{1}[0-9]{2}[$€£¥]{1}", txtPrice.getText().trim());
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
        enableRegister = Pattern.matches("[0-9]+", txtBarcode.getText().trim());
        lblInvalidBarcode.setVisible(!enableRegister);
        return enableRegister;
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
        } catch (IOException e) {
            createExceptionDialog(e);
        }
    }
}
