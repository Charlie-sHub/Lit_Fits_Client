package lit_fits_client.views;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import lit_fits_client.entities.Company;
import lit_fits_client.views.themes.Theme;

/**
 * The main menu of the program for companies.
 *
 * @author Carlos Mendez
 */
public class FXMLCompanyMainMenuController extends FXMLDocumentController {
    /**
     * the log out button
     */
    @FXML
    private Button btnLogout;
    /**
     * The warehouse button, to open the warehouse view
     */
    @FXML
    private Button btnWarehouse;
    /**
     * The modify account button
     */
    @FXML
    private Button btnModifyAccount;
    /**
     * The menu bar on top
     */
    @FXML
    private MenuBar menuBar;
    /**
     * The option of the menu to open the help window
     */
    @FXML
    private MenuItem menuHelpOpenHelp;
    /**
     * The stage used by this view
     */
    private Stage stage;
    /**
     * The login stage
     */
    private Stage loginStage;
    /**
     * The company that logged in
     */
    private Company company;
    private static final Logger LOG = Logger.getLogger(FXMLCompanyMainMenuController.class.getName());

    /**
     * @return the btnLogout
     */
    public Button getBtnLogout() {
        return btnLogout;
    }

    /**
     * @param btnLogout the btnLogout to set
     */
    public void setBtnLogout(Button btnLogout) {
        this.btnLogout = btnLogout;
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
     * @return the loginStage
     */
    public Stage getLogin() {
        return loginStage;
    }

    /**
     * @param loginStage the login to set
     */
    public void setLogin(Stage loginStage) {
        this.loginStage = loginStage;
    }

    /**
     * Returns the warehouse button
     *
     * @return Button
     */
    public Button getBtnWarehouse() {
        return btnWarehouse;
    }

    /**
     * Sets the button to be used as warehouse button
     *
     * @param btnWarehouse
     */
    public void setBtnWarehouse(Button btnWarehouse) {
        this.btnWarehouse = btnWarehouse;
    }

    /**
     * Returns the account modification button
     *
     * @return Button
     */
    public Button getBtnModifyAccount() {
        return btnModifyAccount;
    }

    /**
     * Returns the company associated with this view
     *
     * @return Company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Sets the company to be used by this window
     *
     * @param company
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Sets the button to be used for account modification
     *
     * @param btnModifyAccount
     */
    public void setBtnModifyAccount(Button btnModifyAccount) {
        this.btnModifyAccount = btnModifyAccount;
    }

    /**
     * Returns the stage used to login
     *
     * @return Stage
     */
    public Stage getLoginStage() {
        return loginStage;
    }

    /**
     * Sets the stage used to login
     *
     * @param loginStage
     */
    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    /**
     * This method initializes the window
     *
     *
     * @param theme the chosen css theme
     * @param root The Parent used in previous windows
     * @param stage
     * @param uri
     */
    public void initStage(List<Theme> themes, Theme theme, Stage stage, Parent root, String uri) {
        try {
            this.uri = uri;
            this.stage = stage;
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.setMinWidth(1400);
            stage.setMinHeight(800);
            stage.show();
            setStylesheet(scene, theme.getThemeCss());
            themeList = themes;
            setElements();
            btnLogout.setDisable(false);
            stage.setOnCloseRequest(this::onClosing);
        } catch (Exception e) {
            createExceptionDialog(e);
        }
    }

    /**
     * This method initializes the elements in the window, setting listeners or enabling/disabling elements.
     */
    private void setElements() {
        // Fill the ChoiceBox
        setOnAction();
        setTooltips();
        setFocusTraversable();
        fillChoiceBoxTheme();
    }

    /**
     * Sets the methods that will be called when actions are performed on different elements
     */
    private void setOnAction() {
        choiceTheme.setOnAction(this::onThemeChosen);
        btnLogout.setOnAction(this::onBtnLogoutPress);
        btnModifyAccount.setOnAction(this::onBtnModifyAccountPress);
        btnWarehouse.setOnAction(this::onBtnWarehousePress);
        menuHelpOpenHelp.setOnAction(this::onHelpPressed);
    }

    /**
     * Sets the tooltips of different elements
     */
    private void setTooltips() {
        btnWarehouse.setTooltip(new Tooltip("Check the list of garments, add, delete or modify them too"));
        btnModifyAccount.setTooltip(new Tooltip("Open the window to modify the current account"));
        btnLogout.setTooltip(new Tooltip("Log out of the program"));
        choiceTheme.setTooltip(new Tooltip("Choose the theme you like the most"));
    }

    /**
     * This method allows to change the focus between the elements of the window.
     */
    private void setFocusTraversable() {
        btnModifyAccount.setFocusTraversable(true);
        btnWarehouse.setFocusTraversable(true);
        btnLogout.setFocusTraversable(true);
        choiceTheme.setFocusTraversable(true);
    }

    /**
     * This method makes a logout for the current user. It also closes the window and returns to the login one
     *
     * @param actionEvent
     */
    private void onBtnLogoutPress(ActionEvent event) {
        loginStage.show();
        stage.close();
    }

    /**
     * Opens the account modification window and closes the current window
     *
     * @param event
     */
    private void onBtnModifyAccountPress(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CompanyRegisterModifyAccount.fxml"));
            Stage stageProgramMain = new Stage();
            Parent root = (Parent) fxmlLoader.load();
            FXMLCompanyRegisterController modifyAccountView = ((FXMLCompanyRegisterController) fxmlLoader.getController());
            modifyAccountView.setCompany(company);
            modifyAccountView.setLogin(stage);
            modifyAccountView.initStage(choiceTheme.getValue(), stageProgramMain, root, uri);
            stage.hide();
        } catch (IOException ex) {
            createExceptionDialog(ex);
        }
    }

    /**
     * Opens the warehouse(company garments) window and closes the current window
     *
     * @param event
     */
    private void onBtnWarehousePress(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CompanyGarments.fxml"));
            Stage stageWarehouse = new Stage();
            Parent root = (Parent) fxmlLoader.load();
            FXMLCompanyGarmentsController warehouseView = ((FXMLCompanyGarmentsController) fxmlLoader.getController());
            warehouseView.setCompany(company);
            warehouseView.setStageMainMenu(stage);
            warehouseView.initStage(choiceTheme.getValue(), stageWarehouse, root, uri);
            stage.hide();
        } catch (IOException ex) {
            createExceptionDialog(ex);
        }
    }
}
