/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

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
        // Check if the happyMode is true, and change the CSS.
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
        Tooltip choiceThemeTip = new Tooltip("Choose the theme you like the most");
        choiceTheme.setTooltip(choiceThemeTip);
        // Fill the ChoiceBox
        btnLogout.setOnAction(this::onBtnLogoutPress);
        Tooltip btnLogOutTip = new Tooltip("Log out of the program");
        btnLogout.setTooltip(btnLogOutTip);
        btnModifyAccount.setOnAction(this::onBtnModifyAccountPress);
        Tooltip btnModifyAccountTip = new Tooltip("Open the window to modify the current account");
        btnModifyAccount.setTooltip(btnModifyAccountTip);
        btnWarehouse.setOnAction(this::onBtnWarehousePress);
        Tooltip btnWarehouseTip = new Tooltip("Check the list of garments, add, delete or modify them too");
        btnWarehouse.setTooltip(btnWarehouseTip);
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
            FXMLViewProgramController modifyAccountView = ((FXMLViewProgramController) fxmlLoader.getController());
            //no applogic anymore?
            modifyAccountView.setAppLogic(appLogic);
            //Declare local company object? get an account object from the login and use it here as a company?
            modifyAccountView.setCompany(company);
            modifyAccountView.setLogin(stage);
            //adapt it so it takes the theme chosen from the choice box
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
            Stage stageProgramMain = new Stage();
            Parent root = (Parent) fxmlLoader.load();
            FXMLViewProgramController warehouseView = ((FXMLViewProgramController) fxmlLoader.getController());
            //no applogic anymore?
            warehouseView.setAppLogic(appLogic);
            //Declare local company object? get an account object from the login and use it here as a company?
            warehouseView.setCompany(company);
            warehouseView.setLogin(stage);
            //adapt it so it takes the theme chosen from the choice box
            warehouseView.initStage(choiceTheme.getValue(), stageProgramMain, root);
            stage.hide();
        } catch (IOException ex) {
            createDialog(ex);
            LOG.severe(ex.getMessage());
        }
    }
}
