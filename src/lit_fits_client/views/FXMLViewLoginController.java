package lit_fits_client.views;

import lit_fits_client.miscellaneous.TextChange;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javax.ws.rs.ClientErrorException;
import lit_fits_client.miscellaneous.Encryptor;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.CompanyClientInterface;
import lit_fits_client.RESTClients.ExpertClientInterface;
import lit_fits_client.RESTClients.PublicKeyClientInterface;
import lit_fits_client.RESTClients.UserClientInterface;
import lit_fits_client.entities.Company;
import lit_fits_client.entities.FashionExpert;
import lit_fits_client.entities.User;
import lit_fits_client.views.themes.Theme;
import org.apache.commons.io.IOUtils;
import org.fxmisc.undo.UndoManagerFactory;
import org.reactfx.EventStream;
import static org.reactfx.EventStreams.changesOf;
import static org.reactfx.EventStreams.merge;

/**
 * Controller for the Login view
 *
 * @author Ander and Carlos Mendez
 */
public class FXMLViewLoginController extends FXMLDocumentControllerInput {
    /**
     * Button to attempt login
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
    
    @FXML
    private RadioButton rBtnAdmin;
    /**
     * Text field where username must be entered to log in
     */
    @FXML
    private TextField txtUsername;
    /**
     * Field of the password
     */
    @FXML
    private PasswordField fieldPassword;
    /**
     * ToggleGroup for the two radio buttons that select what kind of registering it will be done
     */
    private ToggleGroup radioButtonGroup;
    /**
     * Stage to be used by this controller
     */
    private Stage stage;
    /**
     * Register stage
     */
    private Stage registerStage;
    /**
     * Main menu Stage
     */
    private Stage mainStage;

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
     * @param themes
     * @param theme
     * @param root
     * @param uri
     * @author Carlos Mendez
     */
    public void initStage(List<Theme> themes, Theme theme, Parent root, String uri) {
        try {
            this.uri = uri;
            Scene scene = new Scene(root);
            this.theme = theme;
            setStylesheet(scene, theme.getThemeCssPath());
            themeList = themes;
            stage.setScene(scene);
            stage.setTitle("Log In");
            stage.setMinWidth(700);
            stage.setMinHeight(500);
            setElements();
            choiceTheme.setValue(theme);
            stage.show();
        } catch (Exception e) {
            createExceptionDialog(e);
        }
    }

    /**
     * This function set the initial stages of the control
     *
     * @author Carlos Mendez
     */
    private void setElements() {
        fillChoiceBoxTheme();
        lblLength.setVisible(false);
        setFocusTraversable();
        setListeners();
        txtUsername.requestFocus();
        textFields = new ArrayList<>();
        fillArray();
        setOnAction();
        setMnemonicParsing();
        btnRedo.setDisable(true);
        btnLogin.setDisable(true);
        btnRegister.setDisable(false);
        txtUsername.setEditable(true);
        fieldPassword.setEditable(true);
        setTooltips();
        setUndoRedo();
        radioButtonGroup = new ToggleGroup();
        rBtnCompany.setToggleGroup(radioButtonGroup);
        rBtnFashionExpert.setToggleGroup(radioButtonGroup);
    }

    /**
     * Sets up all the things related to undoing and redoing.
     *
     * Following: https://github.com/FXMisc/UndoFX
     *
     * @author Carlos Mendez
     */
    private void setUndoRedo() {
        EventStream<TextChange> usernameChanges = changesOf(txtUsername.textProperty()).map(textChange -> new TextChange(textChange, txtUsername));
        EventStream<TextChange> passwordChanges = changesOf(fieldPassword.textProperty()).map(textChange -> new TextChange(textChange, fieldPassword));
        inputChanges = merge(usernameChanges, passwordChanges);
        undoManager = UndoManagerFactory.unlimitedHistorySingleChangeUM(
                inputChanges,
                changes -> changes.invert(),
                changes -> changes.redo(),
                (previousChange, nextChange) -> previousChange.mergeWith(nextChange));
        btnUndo.disableProperty().bind(undoManager.undoAvailableProperty().map(x -> !x));
        btnRedo.disableProperty().bind(undoManager.redoAvailableProperty().map(x -> !x));
        btnUndo.setOnAction(evt -> undoManager.undo());
        btnRedo.setOnAction(evt -> undoManager.redo());
    }

    /**
     * Sets the tooltips of different elements
     *
     * @author Carlos Mendez
     */
    private void setTooltips() {
        btnLogin.setTooltip(new Tooltip("Log into the program"));
        btnReestablishPassword.setTooltip(new Tooltip("Forgot your password?"));
        btnRegister.setTooltip(new Tooltip("Register a new account"));
        btnRedo.setTooltip(new Tooltip("Redoes what's been erased"));
        btnUndo.setTooltip(new Tooltip("Undoes the last change"));
        rBtnCompany.setTooltip(new Tooltip("Do you represent a company?"));
        rBtnFashionExpert.setTooltip(new Tooltip("Are you a fashion expert?"));
        choiceTheme.setTooltip(new Tooltip("Choose the theme you like the most"));
    }

    /**
     * Sets the mnemonic parsing for different elements
     *
     * @author Carlos Mendez
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
        KeyCombination undoKeyCombination = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
        Runnable undoRunnable = () -> undoManager.undo();
        stage.getScene().getAccelerators().put(undoKeyCombination, undoRunnable);
        KeyCombination redoKeyCombination = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
        Runnable redoRunnable = () -> undoManager.redo();
        stage.getScene().getAccelerators().put(redoKeyCombination, redoRunnable);
    }

    /**
     * Sets the methods that will be called when actions are performed on different elements
     *
     * @author Carlos Mendez
     */
    private void setOnAction() {
        btnLogin.setOnAction(this::onBtnLoginPress);
        btnRegister.setOnAction(this::onRegisterPress);
        choiceTheme.setOnAction(this::onThemeChosen);
        btnReestablishPassword.setOnAction(this::onReestablishPasswordPress);
        stage.setOnCloseRequest(this::onClosing);
    }

    /**
     * Checks what kind and what specific of account it is and requests the server to reestablish the corresponding
     * password
     *
     * @param event
     * @author Carlos Mendez
     */
    private void onReestablishPasswordPress(ActionEvent event) {
        try {
            if (txtUsername.getText() != null) {
                if (nifPatternCheck(txtUsername.getText())) {
                    LOG.info("Attempting to reestablish a company's password");
                    CompanyClientInterface companyClient = ClientFactory.getCompanyClient(uri);
                    companyClient.reestablishPassword(txtUsername.getText());
                    companyClient.close();
                } else if (txtUsername.getText().startsWith("admin")) {
                    LOG.info("Attempting to reestablish an admin's password");
                    UserClientInterface userClient = ClientFactory.getUserClient(uri);
                    userClient.reestablishPassword(txtUsername.getText());
                    userClient.close();
                } else {
                    LOG.info("Attempting to reestablish an expert's password");
                    ExpertClientInterface expertClient = ClientFactory.getExpertClient(uri);
                    expertClient.reestablishPassword(txtUsername.getText());
                    expertClient.close();
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
     *
     * @author Carlos Mendez
     */
    private void fillArray() {
        textFields.add(txtUsername);
        textFields.add(fieldPassword);
    }

    /**
     * This function set the focus of the fields
     *
     * @author Carlos Mendez
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
     *
     * @author Carlos Mendez
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
     * @author Carlos Mendez
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
     * @author Carlos Mendez
     */
    private void onBtnLoginPress(ActionEvent event) {
        try {
            if (nifPatternCheck(txtUsername.getText())) {
                openCompanyMainMenu(loginCompany());
            } else if (txtUsername.getText().startsWith("admin")) {
                
                // Asier modification to login as admin
                
                //openAdminMainMenu(adminLogin());
                User admin = new User();
                admin.setUsername("admin");
                admin.setFullName("Lit Fits Admin");
                admin.setPassword(fieldPassword.getText());
                openAdminMainMenu(admin);
                
            } else {
                openExpertMainMenu(expertLogin());
            }
            stage.hide();
        } catch (IOException | ClientErrorException ex) {
            createExceptionDialog(ex);
        } catch (Exception ex) {
            createExceptionDialog(ex);
        }
    }

    /**
     * Opens the main menu of the admin User
     *
     * @param user Admin
     * @throws IOException
     * @author Carlos Mendez
     */
    private void openAdminMainMenu(User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/AdminMainMenu.fxml"));
        Stage stageAdminMainMenu = new Stage();
        Parent root = (Parent) fxmlLoader.load();
        FXMLAdminMainMenuController mainView = ((FXMLAdminMainMenuController) fxmlLoader.getController());
        mainView.setAdmin(user);
        mainView.setPreviousStage(this.stage);
        mainView.initStage(themeList, theme, stageAdminMainMenu, root, uri);
        stage.hide();
    }

    /**
     * Login of the admin User
     *
     * @return User admin
     * @throws ClientErrorException
     * @throws Exception
     * @author Carlos Mendez
     */
    private User adminLogin() throws ClientErrorException, Exception {
        UserClientInterface userClient = ClientFactory.getUserClient(uri);
        PublicKeyClientInterface publicKeyClient = ClientFactory.getPublicKeyClient(uri);
        User admin = new User();
        byte[] publicKeyBytes = IOUtils.toByteArray(publicKeyClient.getPublicKey(InputStream.class));
        admin.setUsername(txtUsername.getText());
        admin.setPassword(Encryptor.encryptText(fieldPassword.getText(), publicKeyBytes));
        admin = userClient.login(admin, User.class);
        userClient.close();
        publicKeyClient.close();
        return admin;
    }

    /**
     * Opens the main menu of the expert
     *
     * @param fashionExpert
     * @throws IOException
     * @author Carlos Mendez
     */
    private void openExpertMainMenu(FashionExpert fashionExpert) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ExpertMainMenu.fxml"));
        Stage stageExpertMainMenu = new Stage();
        Parent root = (Parent) fxmlLoader.load();
        FXMLViewExpertMainMenuController mainView = ((FXMLViewExpertMainMenuController) fxmlLoader.getController());
        mainView.setExpert(fashionExpert);
        mainView.setLoginStage(this.stage);
        mainView.initStage(themeList, theme, stageExpertMainMenu, root, uri);
        stage.hide();
    }

    /**
     * Login of the Expert
     *
     * @return FashionExpert
     * @throws ClientErrorException
     * @throws Exception
     * @author Carlos Mendez
     */
    private FashionExpert expertLogin() throws ClientErrorException, Exception {
        ExpertClientInterface expertClient = ClientFactory.getExpertClient(uri);
        PublicKeyClientInterface publicKeyClient = ClientFactory.getPublicKeyClient(uri);
        FashionExpert fashionExpert = new FashionExpert();
        byte[] publicKeyBytes = IOUtils.toByteArray(publicKeyClient.getPublicKey(InputStream.class));
        fashionExpert.setUsername(txtUsername.getText());
        fashionExpert.setPassword(Encryptor.encryptText(fieldPassword.getText(), publicKeyBytes));
        fashionExpert = expertClient.login(fashionExpert, FashionExpert.class);
        expertClient.close();
        publicKeyClient.close();
        return fashionExpert;
    }

    /**
     * Opens the Company main menu
     *
     * @param company
     * @throws IOException
     * @author Carlos Mendez
     */
    private void openCompanyMainMenu(Company company) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CompanyMainMenu.fxml"));
        Stage stageCompanyMainMenu = new Stage();
        Parent root = (Parent) fxmlLoader.load();
        FXMLCompanyMainMenuController mainView = ((FXMLCompanyMainMenuController) fxmlLoader.getController());
        mainView.setCompany(company);
        mainView.setLoginStage(this.stage);
        mainView.initStage(themeList, theme, stageCompanyMainMenu, root, uri);
        stage.hide();
    }

    /**
     * Login of the company
     *
     * @return Company
     * @throws ClientErrorException
     * @throws Exception
     * @author Carlos Mendez
     */
    private Company loginCompany() throws ClientErrorException, Exception {
        CompanyClientInterface companyClient = ClientFactory.getCompanyClient(uri);
        PublicKeyClientInterface publicKeyClient = ClientFactory.getPublicKeyClient(uri);
        Company company = new Company();
        byte[] publicKeyBytes = IOUtils.toByteArray(publicKeyClient.getPublicKey(InputStream.class));
        company.setNif(txtUsername.getText());
        company.setPassword(Encryptor.encryptText(fieldPassword.getText(), publicKeyBytes));
        company = companyClient.login(company, Company.class);
        companyClient.close();
        publicKeyClient.close();
        return company;
    }

    /**
     * This functions open the window of register and hide the stage of login
     *
     * @param event
     * @author Carlos Mendez
     */
    @Override
    public void onRegisterPress(ActionEvent event) {
        try {
            if (rBtnCompany.isSelected()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CompanyRegisterModifyAccount.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                FXMLCompanyRegisterController registerView = ((FXMLCompanyRegisterController) fxmlLoader.getController());
                registerStage = new Stage();
                registerView.setPreviousStage(stage);
                registerView.initStage(themeList, theme, registerStage, root, uri);
                stage.hide();
            } else if (rBtnFashionExpert.isSelected()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ExpertRegisterAccount.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                FXMLViewExpertRegisterController registerView = ((FXMLViewExpertRegisterController) fxmlLoader.getController());
                registerStage = new Stage();
                registerView.setPreviousStage(stage);
                registerView.initStage(themeList, theme, registerStage, root, uri);
                stage.hide();
                
                // ASIER DIN MODIFICATION
                
            }else if (rBtnAdmin.isSelected()) {
                
                
                
                
                
                
                
                
            } else {
                createDialog("Please select what kind of registration you want");
            }
        } catch (IOException e) {
            createExceptionDialog(e);
        }
    }

    /**
     * Checks that a given String follows the nif pattern, if it does it means the user is a Company
     *
     * @return boolean true if the nif matches the pattern
     * @author Carlos Mendez
     */
    private boolean nifPatternCheck(String nif) {
        return Pattern.matches("[A-W]{1}[0-9]{7}[A-Z_0-9]{1}", nif);
    }
}
