package lit_fits_client.views;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.ws.rs.ClientErrorException;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.GarmentClient;
import lit_fits_client.entities.BodyPart;
import lit_fits_client.entities.Company;
import lit_fits_client.entities.Garment;
import lit_fits_client.entities.GarmentType;
import lit_fits_client.entities.Mood;

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
    private Button btnPromote;
    /**
     * Button to add garments
     */
    @FXML
    private Button btnAdd;
    /**
     * Delete garment button
     */
    @FXML
    private Button btnDelete;
    /**
     * Modify garment button
     */
    @FXML
    private Button btnModify;
    /**
     * Cancel button
     */
    @FXML
    private Button btnCancel;
    /**
     * Table with the company's garments
     */
    @FXML
    private TableView tableGarments;
    /**
     * The columns for the pictures
     */
    @FXML
    private TableColumn<Garment, File> tableColumnPicture;
    /**
     * The columns for the barcodes
     */
    @FXML
    private TableColumn<Garment, String> tableColumnBarcode;
    /**
     * The columns for the prices
     */
    @FXML
    private TableColumn<Garment, String> tableColumnPrice;
    /**
     * The columns for the designers
     */
    @FXML
    private TableColumn<Garment, String> tableColumnDesigner;
    /**
     * The columns for the moods
     */
    @FXML
    private TableColumn<Garment, Mood> tableColumnMood;
    /**
     * The columns for the types
     */
    @FXML
    private TableColumn<Garment, GarmentType> tableColumnType;
    /**
     * The columns for the body part
     */
    @FXML
    private TableColumn<Garment, BodyPart> tableColumnPart;
    /**
     * The columns for the promotion requests
     */
    @FXML
    private TableColumn<Garment, Boolean> tableColumnRequested;
    /**
     * The columns for the promotions
     */
    @FXML
    private TableColumn<Garment, Boolean> tableColumnPromoted;
    /**
     * The columns for the availability
     */
    @FXML
    private TableColumn<Garment, Boolean> tableColumnAvailable;
    /**
     * The columns for the colors
     */
    @FXML
    private TableColumn<Garment, ComboBox> tableColumnColors;
    /**
     * The columns for the materials
     */
    @FXML
    private TableColumn<Garment, ComboBox> tableColumnMaterials;
    /**
     * The list of garments of the company
     */
    private ObservableList<Garment> garmentList;
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
    public Button getBtnPromote() {
        return btnPromote;
    }

    /**
     * Setter for the promote button
     *
     * @param btnPromote
     */
    public void setBtnPromote(Button btnPromote) {
        this.btnPromote = btnPromote;
    }

    /**
     * Getter of the add button
     *
     * @return Button
     */
    public Button getBtnAdd() {
        return btnAdd;
    }

    /**
     * Setter for the add button
     *
     * @param btnAdd
     */
    public void setBtnAdd(Button btnAdd) {
        this.btnAdd = btnAdd;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    /**
     * Setter for the delete button
     *
     * @param btnDelete
     */
    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    /**
     * Getter of the modify button
     *
     * @return Button
     */
    public Button getBtnModify() {
        return btnModify;
    }

    /**
     * Setter for the modify button
     *
     * @param btnModify
     */
    public void setBtnModify(Button btnModify) {
        this.btnModify = btnModify;
    }

    /**
     * Getter for the cancel button
     *
     * @return Button
     */
    public Button getBtnCancel() {
        return btnCancel;
    }

    /**
     * Setter for the cancel button
     *
     * @param btnCancel
     */
    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
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
        enableDisableButtons(true);
        setColumnFactories();
        fillTable();
        setMnemonicText();
        setOnAction();
        setTooltips();
    }

    /**
     * Enables or disables some buttons depending on the boolean sent
     *
     * @param disable
     */
    private void enableDisableButtons(boolean disable) {
        btnDelete.setDisable(disable);
        btnModify.setDisable(disable);
        btnPromote.setDisable(disable);
    }

    /**
     * Fills the table of garments with the data of the company's garments
     *
     * @throws ClientErrorException
     */
    private void fillTable() throws ClientErrorException {
        GarmentClient garmentClient = new ClientFactory().getGarmentClient();
        garmentList = FXCollections.observableArrayList(garmentClient.findGarmentGarmentsByCompany(List.class, company.getNif()));
        tableGarments.setItems(garmentList);
    }

    /**
     * Sets the cell value factories for the table columns
     */
    private void setColumnFactories() {
        tableColumnAvailable.setCellValueFactory(new PropertyValueFactory("available"));
        tableColumnBarcode.setCellValueFactory(new PropertyValueFactory("barcode"));
        tableColumnDesigner.setCellValueFactory(new PropertyValueFactory("designer"));
        tableColumnPromoted.setCellValueFactory(new PropertyValueFactory("promoted"));
        tableColumnRequested.setCellValueFactory(new PropertyValueFactory("promotionRequest"));
        tableColumnPrice.setCellFactory(new PropertyValueFactory("price"));
        tableColumnMood.setCellValueFactory(new PropertyValueFactory("mood"));
        tableColumnPart.setCellValueFactory(new PropertyValueFactory("bodyPart"));
        tableColumnType.setCellValueFactory(new PropertyValueFactory("garmentType"));
        // Use an ObservableList? but how will i get one for the set of materials and colors of the garment of that cell?
        tableColumnMaterials.setCellFactory(new ComboBoxTableCell(garmentList.));
        tableColumnColors.setCellFactory(new ComboBoxTableCell(new PropertyValueFactory("colors")));
        //What do?
        //tableColumnPicture.setCellValueFactory(new PropertyValueFactory("available"));
    }

    /**
     * Sets the text used for mnemonic parsing
     */
    private void setMnemonicText() {
        btnAdd.setText("_Add");
        btnCancel.setText("_Cancel");
        btnDelete.setText("_Delete");
        btnModify.setText("_Modify");
        btnPromote.setText("_Promote");
    }

    /**
     * Sets the tooltip text for the elements of the window
     */
    private void setTooltips() {
        btnPromote.setTooltip(new Tooltip("Request the promotion of a garment"));
        btnAdd.setTooltip(new Tooltip("Add a new garment"));
        btnCancel.setTooltip(new Tooltip("Go back to the main menu"));
        btnDelete.setTooltip(new Tooltip("Delete the garment chosen"));
        btnModify.setTooltip(new Tooltip("Modify the data of a garment"));
        choiceTheme.setTooltip(new Tooltip("Choose the theme you like the most"));
        tableGarments.setTooltip(new Tooltip("List of garments owned by the company"));
    }

    /**
     * Sets what happens when the buttons are pressed
     */
    private void setOnAction() {
        choiceTheme.setOnAction(this::onThemeChosen);
        btnAdd.setOnAction(this::onBtnAddPress);
        btnCancel.setOnAction(this::onBtnCancelPress);
        btnDelete.setOnAction(this::onBtnDeletePress);
        btnModify.setOnAction(this::onBtnModifyPress);
        btnPromote.setOnAction(this::onBtnPromotePress);
        tableGarments.getSelectionModel().selectedItemProperty().addListener(this::onSelectingAGarment);
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
            createExceptionDialog(ex);
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
            garmentCreationView.setGarment(((Garment) tableGarments.getSelectionModel().getSelectedItem()));
            garmentCreationView.setStage(stage);
            garmentCreationView.initStage(choiceTheme.getValue(), stageModify, root);
        } catch (IOException ex) {
            createExceptionDialog(ex);
            LOG.severe(ex.getMessage());
        }
    }

    /**
     * Deletes the chosen garment when the delete button is pressed
     *
     * @param event
     */
    private void onBtnDeletePress(ActionEvent event) {
        Garment garment = ((Garment) tableGarments.getSelectionModel().getSelectedItem());
        GarmentClient garmentClient = new ClientFactory().getGarmentClient();
        try {
            garmentClient.remove(Long.toString(garment.getId()));
        } catch (ClientErrorException e) {
            createExceptionDialog(e);
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
        Garment garment = ((Garment) tableGarments.getSelectionModel().getSelectedItem());
        GarmentClient garmentClient = new ClientFactory().getGarmentClient();
        try {
            garment.setPromoted(true);
            garmentClient.editGarment(garment);
        } catch (ClientErrorException e) {
            createExceptionDialog(e);
        }
    }

    private void onSelectingAGarment(ObservableValue observable, Object oldValue, Object newValue) {
        if (newValue != null) {
            enableDisableButtons(false);
        } else {
            enableDisableButtons(true);
        }
    }
}
