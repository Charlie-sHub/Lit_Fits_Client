package lit_fits_client.views;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import lit_fits_client.entities.Company;
import lit_fits_client.views.themes.Theme;

/**
 * The main menu of the program for Companies.
 *
 * @author Carlos Mendez
 */
public class FXMLCompanyMainMenuController extends FXMLDocumentController {
    /**
     * the log out button
     */
    @FXML
    private Button btnLogOut;
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
     * A pretty useless DatePicker
     */
    @FXML
    private DatePicker datePicker;
    /**
     * An obnoxious label
     */
    @FXML
    private Label lblPutDate;
    /**
     * The other of the obnoxious label
     */
    @FXML
    private Label lblCorrect;
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
     * @param themes
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
            stage.show();
            this.theme = theme;
            setStylesheet(scene, theme.getThemeCssPath());
            themeList = themes;
            setElements();
            choiceTheme.setValue(theme);
            btnLogOut.setDisable(false);
            stage.setOnCloseRequest(this::onClosing);
        } catch (Exception e) {
            createExceptionDialog(e);
        }
    }

    /**
     * This method initializes the elements in the window, setting listeners or enabling/disabling elements.
     */
    private void setElements() {
        lblCorrect.setVisible(false);
        fillChoiceBoxTheme();
        setOnAction();
        setTooltips();
        setFocusTraversable();
    }

    /**
     * Sets the methods that will be called when actions are performed on different elements
     */
    private void setOnAction() {
        choiceTheme.setOnAction(this::onThemeChosen);
        btnLogOut.setOnAction(this::onBtnLogoutPress);
        btnModifyAccount.setOnAction(this::onBtnModifyAccountPress);
        btnWarehouse.setOnAction(this::onBtnWarehousePress);
        menuHelpOpenHelp.setOnAction(this::onHelpPressed);
        stage.setOnCloseRequest(this::onClosing);
        datePicker.setOnAction(this::onDatePicked);
    }

    /**
     * Sets the tooltips of different elements
     */
    private void setTooltips() {
        btnWarehouse.setTooltip(new Tooltip("Check the list of garments, add, delete or modify them too"));
        btnModifyAccount.setTooltip(new Tooltip("Open the window to modify the current account"));
        btnLogOut.setTooltip(new Tooltip("Log out of the program"));
        choiceTheme.setTooltip(new Tooltip("Choose the theme you like the most"));
        datePicker.setTooltip(new Tooltip("Pick a date to win a prize!"));
    }

    /**
     * This method allows to change the focus between the elements of the window.
     */
    private void setFocusTraversable() {
        btnModifyAccount.setFocusTraversable(true);
        btnWarehouse.setFocusTraversable(true);
        btnLogOut.setFocusTraversable(true);
        choiceTheme.setFocusTraversable(true);
        datePicker.setFocusTraversable(true);
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
            modifyAccountView.setPreviousStage(stage);
            modifyAccountView.initStage(themeList, choiceTheme.getValue(), stageProgramMain, root, uri);
            stage.hide();
        } catch (IOException ex) {
            createExceptionDialog(ex);
        }
    }

    /**
     * Opens the warehouse(company garments) window and hides the current window
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
            warehouseView.initStage(themeList, choiceTheme.getValue(), stageWarehouse, root, uri);
            stage.hide();
        } catch (IOException ex) {
            createExceptionDialog(ex);
        }
    }

    /**
     * It's some sad excuse to use the DatePicker
     *
     * @param event
     */
    public void onDatePicked(ActionEvent event) {
        if (datePicker.getValue().equals(LocalDate.now())) {
            lblCorrect.setVisible(true);
        } else {
            lblCorrect.setVisible(false);
        }
    }
}
