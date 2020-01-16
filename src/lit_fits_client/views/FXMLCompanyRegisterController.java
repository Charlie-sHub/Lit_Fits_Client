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
import javax.ws.rs.ClientErrorException;
import lit_fits_client.Encryptor;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.CompanyClient;
import lit_fits_client.RESTClients.PublicKeyClient;
import lit_fits_client.entities.Company;

/**
 * This is the Document Controller class for the registration view of the program.
 *
 * @author Carlos Rafael Mendez Gonzalez
 */
public class FXMLCompanyRegisterController extends FXMLDocumentControllerInput {
    /**
     * Invalid email label
     */
    @FXML
    private Label lblInvalidMail;
    /**
     * Invalid NIF label
     */
    @FXML
    private Label lblInvalidNIF;
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
    /**
     * The company object to be used by the window, if none is given from the previous window then a new Company will be
     * created
     */
    private Company company;
    /**
     * Logger object
     */
    private static final Logger LOG = Logger.getLogger(FXMLCompanyRegisterController.class.getName());

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

    /**
     * Getter for the invalid email label
     *
     * @return Label
     */
    public Label getLblInvalidMail() {
        return lblInvalidMail;
    }

    /**
     * Setter for the invalid email label
     *
     * @param lblInvalidMail
     */
    public void setLblInvalidMail(Label lblInvalidMail) {
        this.lblInvalidMail = lblInvalidMail;
    }

    /**
     * Getter for the phone text field
     *
     * @return TextField
     */
    public TextField getTxtPhone() {
        return txtPhone;
    }

    /**
     * Setter for the phone text field
     *
     * @param txtPhone
     */
    public void setTxtPhone(TextField txtPhone) {
        this.txtPhone = txtPhone;
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
     * Getter of the Company used by the window
     *
     * @return Company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Setter of the Company to be used by the window
     *
     * @param company
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Gets the invalid nif label
     *
     * @return Label
     */
    public Label getLblInvalidNIF() {
        return lblInvalidNIF;
    }

    /**
     * Sets the invalid nif label
     *
     * @param lblInvalidNIF
     */
    public void setLblInvalidNIF(Label lblInvalidNIF) {
        this.lblInvalidNIF = lblInvalidNIF;
    }

    /**
     * Initializes the register window
     *
     * @param theme The chosen css theme
     * @param stage The stage to be used
     * @param root The Parent created in the previous window
     * @param uri
     */
    public void initStage(String theme, Stage stage, Parent root, String uri) {
        try {
            this.uri = uri;
            this.stage = stage;
            Scene scene;
            scene = new Scene(root);
            setStylesheet(scene, theme);
            stage.setScene(scene);
            setElements();
            if (null != company) {
                stage.setTitle("Modification");
                fillFields();
            } else {
                stage.setTitle("Registration");
                company = new Company();
                company.setId(0);
            }
            stage.setOnCloseRequest(this::onClosing);
            //pretty sure these dimensions will have to change
            stage.setMinWidth(850);
            stage.setMinHeight(650);
            stage.show();
        } catch (Exception e) {
            createExceptionDialog(e);
            LOG.severe(e.getMessage());
        }
    }

    /**
     * Fills the fields with the data of a given company
     */
    private void fillFields() {
        txtFullName.requestFocus();
        txtNif.setEditable(false);
        txtNif.setText(company.getNif());
        txtEmail.setText(company.getEmail());
        txtFullName.setText(company.getFullName());
        txtPhone.setText(company.getPhoneNumber());
    }

    /**
     * Sets the properties for several elements of the window
     */
    private void setElements() {
        setOnAction();
        setMnemonicParsing();
        lblPassMismatch.setVisible(false);
        lblLength.setVisible(false);
        btnSubmit.setDisable(true);
        btnRedo.setDisable(true);
        txtNif.requestFocus();
        setFocusTraversable();
        setListeners();
        textFields = new ArrayList<>();
        fillArray();
        undoneStrings = new ArrayList<>();
    }

    /**
     * Sets the mnemonic parsing for different elements
     */
    private void setMnemonicParsing() {
        btnCancel.setText("_Cancel");
        btnSubmit.setText("_Register");
        btnUndo.setText("_Undo");
        btnRedo.setText("_Redo");
        btnSubmit.setMnemonicParsing(true);
        btnCancel.setMnemonicParsing(true);
        btnUndo.setMnemonicParsing(true);
        btnRedo.setMnemonicParsing(true);
    }

    /**
     * Sets the methods that will be called when actions are performed on different elements
     */
    private void setOnAction() {
        choiceTheme.setOnAction(this::onThemeChosen);
        btnCancel.setOnAction(this::onBtnCancelPress);
        btnSubmit.setOnAction(this::onRegisterPress);
        btnUndo.setOnAction(this::onUndoPress);
        btnRedo.setOnAction(this::onRedoPress);
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
        btnSubmit.setFocusTraversable(true);
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
        CompanyClient companyClient = ClientFactory.getCompanyClient(uri);
        PublicKeyClient publicKeyClient = ClientFactory.getPublicKeyClient(uri);
        try {
            setCompanyData(publicKeyClient.getPublicKey(String.class));
            if (company.getId() == 0) {
                companyClient.edit(company);
            } else {
                companyClient.create(company);
            }
            try {
                openCompanyMainMenu(company);
                stage.hide();
            } catch (IOException e) {
                LOG.severe(e.getMessage());
            }
        } catch (ClientErrorException e) {
            createExceptionDialog(e);
            LOG.log(Level.SEVERE, "{0} at: {1}", new Object[]{e.getMessage(), LocalDateTime.now()});
        } catch (Exception ex) {
            createExceptionDialog(ex);
            LOG.log(Level.SEVERE, "{0} at: {1}", new Object[]{ex.getMessage(), LocalDateTime.now()});
        } finally {
            companyClient.close();
            publicKeyClient.close();
        }
    }

    /**
     * Sets the data of the company to be sent to the server
     */
    private void setCompanyData(String publicKey) throws Exception {
        company.setEmail(txtEmail.getText());
        company.setFullName(txtFullName.getText());
        company.setNif(txtNif.getText());
        company.setPassword(Encryptor.encryptText(txtPassword.getText(), publicKey.getBytes()));
        company.setPhoneNumber(txtPhone.getText());
    }

    /**
     * This method starts the main program window
     *
     * @throws IOException
     */
    private void openCompanyMainMenu(Company company) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CompanyMainMenu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stageProgramMain = new Stage();
        FXMLCompanyMainMenuController mainView = ((FXMLCompanyMainMenuController) fxmlLoader.getController());
        mainView.setCompany(company);
        mainView.setLogin(previousStage);
        mainView.initStage(theme, stageProgramMain, root, uri);
        stage.hide();
    }

    /**
     * Enables or disables the btnSubmit based on several factors
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    public void onFieldFilledListener(ObservableValue observable, String oldValue, String newValue) {
        if (passwordMatchCheck() & emailPatternCheck() & nifPatternCheck()) {
            onFieldFilled(btnSubmit);
        } else {
            btnSubmit.setDisable(true);
        }
    }

    /**
     * Checks that the email follows the email pattern
     *
     * @return boolean true if the email matches the pattern
     */
    private boolean emailPatternCheck() {
        boolean enableRegister;
        enableRegister = Pattern.matches("[a-zA-Z_0-9]+@{1}[a-zA-Z_0-9]+[.]{1}[a-zA-Z_0-9]+", txtEmail.getText().trim());
        lblInvalidMail.setVisible(!enableRegister);
        return enableRegister;
    }

    /**
     * Checks that the nif follows the nif pattern
     *
     * @return boolean true if the nif matches the pattern
     */
    private boolean nifPatternCheck() {
        boolean enableRegister;
        enableRegister = Pattern.matches("[A-W]{1}[0-9]{7}[A-Z_0-9]{1}", txtNif.getText().trim());
        lblInvalidNIF.setVisible(!enableRegister);
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