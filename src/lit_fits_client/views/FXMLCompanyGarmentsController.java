package lit_fits_client.views;

import java.io.IOException;
import java.util.List;
import java.util.Set;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.GarmentClient;
import lit_fits_client.entities.BodyPart;
import lit_fits_client.entities.Color;
import lit_fits_client.entities.Company;
import lit_fits_client.entities.Garment;
import lit_fits_client.entities.GarmentType;
import lit_fits_client.entities.Material;
import lit_fits_client.entities.Mood;
import lit_fits_client.views.themes.Theme;

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
    private TableView<Garment> tableGarments;
    /**
     * The columns for the pictures
     */
    @FXML
    private TableColumn<Garment, Image> tableColumnPicture;
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
     * The columns for the Colors
     */
    @FXML
    private TableColumn<Garment, Set<Color>> tableColumnColors;
    /**
     * The columns for the Materials
     */
    @FXML
    private TableColumn<Garment, Set<Material>> tableColumnMaterials;
    /**
     * Context menu of the table of Garments
     */
    @FXML
    private ContextMenu contextMenuTable;
    /**
     * MenuItem to add, does the same as the btnAdd
     */
    @FXML
    private MenuItem menuItemAdd;
    /**
     * MenuItem to modify, does the same as the btnModify
     */
    @FXML
    private MenuItem menuItemModify;
    /**
     * MenuItem to promote, does the same as the btnPromote
     */
    @FXML
    private MenuItem menuItemPromote;
    /**
     * MenuItem to delete, does the same as the btnDelete
     */
    @FXML
    private MenuItem menuItemDelete;
    /**
     * The menu bar on top
     */
    @FXML
    private MenuBar menuBar;
    /**
     * The submenu related to "File"
     */
    @FXML
    private Menu menuFile;
    /**
     * The submenu related to editing
     */
    @FXML
    private Menu menuEdit;
    /**
     * The submenu related to Help
     */
    @FXML
    private Menu menuHelp;
    /**
     * The option of the menu to add a new garment
     */
    @FXML
    private MenuItem menuFileAdd;
    /**
     * The option of the menu to delete a garment
     */
    @FXML
    private MenuItem menuFileDelete;
    /**
     * The option of the menu to promote a garment
     */
    @FXML
    private MenuItem menuEditPromote;
    /**
     * The option of the menu to modify a garment
     */
    @FXML
    private MenuItem menuEditModify;
    /**
     * The option of the menu to open the help window
     */
    @FXML
    private MenuItem menuHelpOpenHelp;
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
    private static final Logger LOG = Logger.getLogger(FXMLCompanyMainMenuController.class.getName());

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
     * @param uri
     */
    public void initStage(Theme theme, Stage stage, Parent root, String uri) {
        try {
            this.uri = uri;
            this.stage = stage;
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Warehouse");
            stage.setMinWidth(1280);
            stage.setMinHeight(720);
            stage.show();
            setStylesheet(scene, theme.getThemeCss());
            setElements();
            stage.setOnCloseRequest(this::onClosing);
        } catch (Exception e) {
            createExceptionDialog(e);
        }
    }

    /**
     * Sets the options for different elements of the window
     */
    private void setElements() {
        // Fill the ChoiceBox of themes or take an alredy filled box?
        // Set the chosen choice of theme
        contextMenuTable.hide();
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
        menuItemDelete.setDisable(disable);
        menuItemModify.setDisable(disable);
        menuItemPromote.setDisable(disable);
    }

    /**
     * Fills the table of garments with the data of the company's garments
     *
     * @throws ClientErrorException
     */
    private void fillTable() throws ClientErrorException {
        GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
        garmentList = FXCollections.observableArrayList(garmentClient.findGarmentsByCompany(new GenericType<List<Garment>>() {
        }, company.getNif()));
        tableGarments.setItems(garmentList);
    }

    /**
     * Sets the cell value factories for the table columns
     */
    private void setColumnFactories() {
        // How to set the correct image if instead of showing the image directly  the image was shown when hovering over the cell?
        tableColumnPicture.setCellValueFactory(new PropertyValueFactory("picture"));
        tableColumnAvailable.setCellValueFactory(new PropertyValueFactory("available"));
        tableColumnBarcode.setCellValueFactory(new PropertyValueFactory("barcode"));
        tableColumnDesigner.setCellValueFactory(new PropertyValueFactory("designer"));
        tableColumnPromoted.setCellValueFactory(new PropertyValueFactory("promoted"));
        tableColumnRequested.setCellValueFactory(new PropertyValueFactory("promotionRequest"));
        tableColumnPrice.setCellFactory(new PropertyValueFactory("price"));
        tableColumnMood.setCellValueFactory(new PropertyValueFactory("mood"));
        tableColumnPart.setCellValueFactory(new PropertyValueFactory("bodyPart"));
        tableColumnType.setCellValueFactory(new PropertyValueFactory("garmentType"));
        tableColumnMaterials.setCellFactory((TableColumn<Garment, Set<Material>> tableColumnParam) -> new ComboBoxTableCell());
        tableColumnMaterials.setCellValueFactory((CellDataFeatures<Garment, Set<Material>> cellDataParameter) -> (ObservableValue<Set<Material>>) cellDataParameter.getValue().getMaterials());
        tableColumnColors.setCellFactory((TableColumn<Garment, Set<Color>> tableColumnParam) -> new ComboBoxTableCell());
        // Yet to implement the value factory fot eh colors
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
        tableGarments.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                contextMenuTable.show(tableGarments, event.getScreenX(), event.getScreenY());
            }
        });
        menuItemAdd.setOnAction(this::onBtnAddPress);
        menuItemDelete.setOnAction(this::onBtnDeletePress);
        menuItemModify.setOnAction(this::onBtnModifyPress);
        menuItemPromote.setOnAction(this::onBtnPromotePress);
        menuFileAdd.setOnAction(this::onBtnAddPress);
        menuFileDelete.setOnAction(this::onBtnDeletePress);
        menuEditModify.setOnAction(this::onBtnModifyPress);
        menuEditPromote.setOnAction(this::onBtnPromotePress);
        menuHelpOpenHelp.setOnAction(this::onHelpPressed);
    }

    /**
     * Opens the garment creation/modification window with a new garment
     *
     * @param event
     */
    private void onBtnAddPress(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/GarmentCreationModification.fxml"));
            Stage stageCreate = new Stage();
            Parent root = (Parent) fxmlLoader.load();
            FXMLCreateModifyGarmentController garmentCreationView = ((FXMLCreateModifyGarmentController) fxmlLoader.getController());
            Garment newGarment = null;
            garmentCreationView.setGarment(newGarment);
            garmentCreationView.setCompany(company);
            garmentCreationView.setStage(stage);
            garmentCreationView.initStage(choiceTheme.getValue(), stageCreate, root, uri);
        } catch (IOException ex) {
            createExceptionDialog(ex);
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
            fxmlLoader = new FXMLLoader(getClass().getResource("fxml/GarmentCreationModification.fxml"));
            Stage stageModify = new Stage();
            Parent root = (Parent) fxmlLoader.load();
            FXMLCreateModifyGarmentController garmentCreationView = ((FXMLCreateModifyGarmentController) fxmlLoader.getController());
            garmentCreationView.setGarment(((Garment) tableGarments.getSelectionModel().getSelectedItem()));
            garmentCreationView.setCompany(company);
            garmentCreationView.setStage(stage);
            garmentCreationView.initStage(choiceTheme.getValue(), stageModify, root, uri);
        } catch (IOException ex) {
            createExceptionDialog(ex);
        }
    }

    /**
     * Deletes the chosen garment when the delete button is pressed
     *
     * @param event
     */
    private void onBtnDeletePress(ActionEvent event) {
        if (createConfirmationDialog()) {
            Garment garment = ((Garment) tableGarments.getSelectionModel().getSelectedItem());
            GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
            try {
                garmentClient.remove(Long.toString(garment.getId()));
            } catch (ClientErrorException e) {
                createExceptionDialog(e);
            }
            garmentClient.close();
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
        GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
        try {
            garment.setPromoted(true);
            garmentClient.editGarment(garment);
        } catch (ClientErrorException e) {
            createExceptionDialog(e);
        }
        garmentClient.close();
    }

    private void onSelectingAGarment(ObservableValue observable, Object oldValue, Object newValue) {
        if (newValue != null) {
            enableDisableButtons(false);
        } else {
            enableDisableButtons(true);
        }
    }
}
