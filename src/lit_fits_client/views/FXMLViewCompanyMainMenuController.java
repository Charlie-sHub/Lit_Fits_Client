package lit_fits_client.views;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import lit_fits_client.entities.Company;

/**
 * The main menu of the program for companies.
 *
 * @author Carlos Mendez
 */
public class FXMLViewCompanyMainMenuController extends FXMLDocumentController {
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnWarehouse;
    @FXML
    private Button btnModifyAccount;
    private Stage stage;
    private Stage loginStage;
    private Company company;
    private static final Logger LOG = Logger.getLogger(FXMLViewCompanyMainMenuController.class.getName());

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
     * @param happyMode It receives a boolean to change to happy mode css or not
     *
     * @param root The Parent used in previous windows
     *
     * @param stage
     */
    public void initStage(String theme, Stage stage, Parent root) {
        this.stage = stage;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.setMinWidth(1400);
        stage.setMinHeight(800);
        stage.show();
        setStylesheet(scene, theme);
        setElements();
        btnLogout.setDisable(false);
        stage.setOnCloseRequest(this::onClosing);
    }

    /**
     * This method initializes the elements in the window, setting listeners or enabling/disabling elements.
     */
    private void setElements() {
        choiceTheme.setOnAction(this::onThemeChosen);
        choiceTheme.setTooltip(new Tooltip("Choose the theme you like the most"));
        // Fill the ChoiceBox
        btnLogout.setOnAction(this::onBtnLogoutPress);
        btnLogout.setTooltip(new Tooltip("Log out of the program"));
        btnModifyAccount.setOnAction(this::onBtnModifyAccountPress);
        btnModifyAccount.setTooltip(new Tooltip("Open the window to modify the current account"));
        btnWarehouse.setOnAction(this::onBtnWarehousePress);
        btnWarehouse.setTooltip(new Tooltip("Check the list of garments, add, delete or modify them too"));
        setFocusTraversable();
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
            FXMLViewCompanyRegisterController modifyAccountView = ((FXMLViewCompanyRegisterController) fxmlLoader.getController());
            //no applogic anymore?
            modifyAccountView.setAppLogic(appLogic);
            modifyAccountView.setCompany(company);
            modifyAccountView.setLogin(stage);
            modifyAccountView.initStage(choiceTheme.getValue(), stageProgramMain, root);
            stage.hide();
        } catch (IOException ex) {
            createDialog(ex);
            LOG.severe(ex.getMessage());
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
            //no applogic anymore?
            warehouseView.setAppLogic(appLogic);
            warehouseView.setCompany(company);
            warehouseView.setStageMainMenu(stage);
            warehouseView.initStage(choiceTheme.getValue(), stageWarehouse, root);
            stage.hide();
        } catch (IOException ex) {
            createDialog(ex);
            LOG.severe(ex.getMessage());
        }
    }
}
