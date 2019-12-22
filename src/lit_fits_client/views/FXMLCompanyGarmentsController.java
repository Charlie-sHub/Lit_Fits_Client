package lit_fits_client.views;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javax.ws.rs.ClientErrorException;
import lit_fits_client.RESTClients.GarmentClient;
import lit_fits_client.entities.Company;
import lit_fits_client.entities.Garment;

/**
 * The "Warehouse" window for companies
 *
 * @author Carlos Mendez
 */
public class FXMLCompanyGarmentsController extends FXMLDocumentController {
    /**
     * Garment promotion button
     */
    @FXML
    private Button buttonPromote;
    /**
     * Button to add garments
     */
    @FXML
    private Button buttonAdd;
    /**
     * Delete garment button
     */
    @FXML
    private Button buttonDelete;
    /**
     * Modify garment button
     */
    @FXML
    private Button buttonModify;
    /**
     * Cancel button
     */
    @FXML
    private Button buttonCancel;
    /**
     * Table with the company's garments
     */
    @FXML
    private TableView tableGarments;
    /**
     * Stage of the view
     */
    private Stage stage;
    /**
     * Stage of the main menu
     */
    private Stage stageMainMenu;
    /**
     * Company logged in
     */
    private Company company;
    private static final Logger LOG = Logger.getLogger(FXMLViewCompanyMainMenuController.class.getName());

    /**
     * Getter of the promote button
     *
     * @return Button
     */
    public Button getButtonPromote() {
        return buttonPromote;
    }

    /**
     * Setter for the promote button
     *
     * @param buttonPromote
     */
    public void setButtonPromote(Button buttonPromote) {
        this.buttonPromote = buttonPromote;
    }

    /**
     * Getter of the add button
     *
     * @return Button
     */
    public Button getButtonAdd() {
        return buttonAdd;
    }

    /**
     * Setter for the add button
     *
     * @param buttonAdd
     */
    public void setButtonAdd(Button buttonAdd) {
        this.buttonAdd = buttonAdd;
    }

    public Button getButtonDelete() {
        return buttonDelete;
    }

    /**
     * Setter for the delete button
     *
     * @param buttonDelete
     */
    public void setButtonDelete(Button buttonDelete) {
        this.buttonDelete = buttonDelete;
    }

    /**
     * Getter of the modify button
     *
     * @return Button
     */
    public Button getButtonModify() {
        return buttonModify;
    }

    /**
     * Setter for the modify button
     *
     * @param buttonModify
     */
    public void setButtonModify(Button buttonModify) {
        this.buttonModify = buttonModify;
    }

    /**
     * Getter for the cancel button
     *
     * @return Button
     */
    public Button getButtonCancel() {
        return buttonCancel;
    }

    /**
     * Setter for the cancel button
     *
     * @param buttonCancel
     */
    public void setButtonCancel(Button buttonCancel) {
        this.buttonCancel = buttonCancel;
    }

    /**
     * Getter of the table of garments
     *
     * @return TableView
     */
    public TableView getTableGarments() {
        return tableGarments;
    }

    /**
     * Setter of the table of garments
     *
     * @param tableGarments
     */
    public void setTableGarments(TableView tableGarments) {
        this.tableGarments = tableGarments;
    }

    /**
     * Getter of the stage in use by this window
     *
     * @return Stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Setter of the stage to be used by this window
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Getter of the company in use by this window
     *
     * @return Company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Setter for the company this window will use
     *
     * @param company
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Getter of the main menu stage
     *
     * @return Stage
     */
    public Stage getStageMainMenu() {
        return stageMainMenu;
    }

    /**
     * Setter for the main menu stage
     *
     * @param stageMainMenu
     */
    public void setStageMainMenu(Stage stageMainMenu) {
        this.stageMainMenu = stageMainMenu;
    }

    /**
     * This method initializes the window
     *
     *
     * @param theme the chosen css theme
     * @param root The Parent used in previous windows
     *
     * @param stage
     */
    public void initStage(String theme, Stage stage, Parent root) {
        this.stage = stage;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Warehouse");
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
        stage.show();
        setStylesheet(scene, theme);
        setElements();
        stage.setOnCloseRequest(this::onClosing);
    }

    /**
     * Sets the options for different elements of the window
     */
    private void setElements() {
        // Fill the ChoiceBox of themes or take an alredy filled box?
        // Set the chosen choice of theme
        // Fill the table with the list of garments of the company
        setMnemonicText();
        setOnAction();
        setTooltips();
    }

    /**
     * Sets the text used for mnemonic parsing
     */
    private void setMnemonicText() {
        buttonAdd.setText("_Add");
        buttonCancel.setText("_Cancel");
        buttonDelete.setText("_Delete");
        buttonModify.setText("_Modify");
        buttonPromote.setText("_Promote");
    }

    /**
     * Sets the tooltip text for the elements of the window
     */
    private void setTooltips() {
        buttonPromote.setTooltip(new Tooltip("Request the promotion of a garment"));
        buttonAdd.setTooltip(new Tooltip("Add a new garment"));
        buttonCancel.setTooltip(new Tooltip("Go back to the main menu"));
        buttonDelete.setTooltip(new Tooltip("Delete the garment chosen"));
        buttonModify.setTooltip(new Tooltip("Modify the data of a garment"));
        choiceTheme.setTooltip(new Tooltip("Choose the theme you like the most"));
        tableGarments.setTooltip(new Tooltip("List of garments owned by the company"));
    }

    /**
     * Sets what happens when the buttons are pressed
     */
    private void setOnAction() {
        choiceTheme.setOnAction(this::onThemeChosen);
        buttonAdd.setOnAction(this::onBtnAddPress);
        buttonCancel.setOnAction(this::onBtnCancelPress);
        buttonDelete.setOnAction(this::onBtnDeletePress);
        buttonModify.setOnAction(this::onBtnModifyPress);
        buttonPromote.setOnAction(this::onBtnPromotePress);
    }

    /**
     * Opens the garment creation/modification window with a new garment
     *
     * @param event
     */
    private void onBtnAddPress(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/GarmentCreationModification"));
            Stage stageCreate = new Stage();
            Parent root = (Parent) fxmlLoader.load();
            FXMLViewCreateModifyGarmentController garmentCreationView = ((FXMLViewCreateModifyGarmentController) fxmlLoader.getController());
            Garment newGarment = null;
            garmentCreationView.setGarment(newGarment);
            garmentCreationView.setStage(stage);
            garmentCreationView.initStage(choiceTheme.getValue(), stageCreate, root);
        } catch (IOException ex) {
            createDialog(ex);
            LOG.severe(ex.getMessage());
        }
    }

    /**
     * Opens the garment creation/modification window with the chosen garment
     *
     * @param event
     */
    private void onBtnModifyPress(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(getClass().getResource("fxml/GarmentCreationModification"));
            Stage stageModify = new Stage();
            Parent root = (Parent) fxmlLoader.load();
            FXMLViewCreateModifyGarmentController garmentCreationView = ((FXMLViewCreateModifyGarmentController) fxmlLoader.getController());
            garmentCreationView.setGarment(garment); // take the chosen garment from the table
            garmentCreationView.setStage(stage);
            garmentCreationView.initStage(choiceTheme.getValue(), stageModify, root);
        } catch (IOException ex) {
            createDialog(ex);
            LOG.severe(ex.getMessage());
        }
    }

    /**
     * Deletes the chosen garment when the delete button is pressed
     *
     * @param event
     */
    private void onBtnDeletePress(ActionEvent event) {
        Garment garment = null; // take the chosen garment from the table
        GarmentClient garmentClient = new GarmentClient();
        try {
            garmentClient.remove(garment.getId());
        } catch (ClientErrorException e) {
            createDialog(e);
        }
    }

    /**
     * Closes the current window and shows again the main menu window
     *
     * @param event
     */
    private void onBtnCancelPress(ActionEvent event) {
        stage.close();
        stageMainMenu.show();
    }

    /**
     * Sends a promotion request to the server when the promote button is pressed
     *
     * @param event
     */
    private void onBtnPromotePress(ActionEvent event) {
        Garment garment = null; // take the chosen garment from the table
        GarmentClient garmentClient = new GarmentClient();
        try {
            garment.setPromoted(true);
            garmentClient.editGarment(garment);
        } catch (ClientErrorException e) {
            createDialog(e);
        }
    }
}
