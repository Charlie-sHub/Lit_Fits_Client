package lit_fits_client.views;

import java.util.List;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.GarmentClient;
import lit_fits_client.entities.Garment;
import lit_fits_client.entities.User;
import lit_fits_client.views.themes.Theme;

/**
 * The view controller in wich the admin can check 
 * the promotion requests for a garment and the 
 * garments that are already promoted.
 * 
 * @author Asier
 */
public class FXMLAdminCheckRequestsController extends FXMLDocumentController{
    
    private static final Logger LOG = Logger.getLogger(FXMLAdminCheckRequestsController.class.getName());
    
    private User admin;
    private Stage stage;
    private Stage previousStage;
    private String uri;
    
//    @FXML
//    private Button btnSave;
    @FXML
    private Button btnBack;
    
    @FXML
    private Menu menuFile;
//    @FXML
//    private MenuItem menuItemSave;
    @FXML
    private MenuItem menuItemBack;
    @FXML
    private Menu menuEdit;
    @FXML
    private MenuItem menuItemPromote;
    @FXML
    private MenuItem menuItemDeletePromotion;
    @FXML
    private MenuItem menuItemCancelRequest;
    
    @FXML
    private ChoiceBox choiceBoxFilterBy;
    
    // ----------------- Garments Table ----------------------
    
    @FXML
    private TableView<Garment> garmentsTable;
    @FXML
    private TableColumn<Garment, String> barcodeColumn;
    @FXML
    private TableColumn<Garment, String> designerColumn;
    @FXML
    private TableColumn<Garment, Object> garmentTypeColumn;
    @FXML
    private TableColumn<Garment, Object> bodyPartColumn;
    @FXML
    private TableColumn<Garment, Object> moodColumn;
    @FXML
    private TableColumn<Garment, Double> priceColumn;
    @FXML
    private TableColumn<Garment, Boolean> availableColumn;
    @FXML
    private TableColumn<Garment, Boolean> promotedColumn;
    @FXML
    private TableColumn<Garment, Boolean> promotionRequestColumn;
    
    private ObservableList<Garment> garmentList;
    
    /**
     * Returns the admin that is currently logged.
     * 
     * @return The admin that is using the view (the admin that should be logged).
     */
    public User getAdmin () {
        return admin;
    }

    /**
     * Sets the admin that is currently logged.
     * 
     * @param admin The admin that will be set.
     */
    public void setAdmin (User admin) {
        this.admin = admin;
    }

    /**
     * Returns the actual stage.
     * 
     * @return The actual stage.
     */
    public Stage getStage () {
        return stage;
    }

    /**
     * Sets the actual stage.
     * 
     * @param stage The stage that will be set.
     */
    public void setStage (Stage stage) {
        this.stage = stage;
    }
    
    /**
     * Sets the previous stage.
     * 
     * @param previousStage The previous Stage that will be set.
     */
    public void setPreviousStage(Stage previousStage) {
        this.previousStage = previousStage;
    }
    
    /**
     * Returns the previous stage.
     * 
     * @return The previous Stage.
     */
    public Stage getPreviousStage() {
        return this.previousStage;
    }
    
    /**
     * The method that initiates the view.
     * 
     * @param themes
     * @param theme
     * @param stage
     * @param root 
     * @param uri 
     */
    public void initStage(List<Theme> themes, Theme theme, Stage stage, Parent root, String uri) {
        
        this.stage = stage;
        this.theme = theme;
        this.themeList = themes;
        this.uri = uri;
        
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.setTitle("Administrator - Check promotion requests");
        this.stage.show();
        
        this.setStylesheet(scene, this.theme.getThemeCssPath());
        this.setElements();
        this.choiceTheme.setValue(theme);
        
        stage.setOnCloseRequest(this::onClosing);
    }
    
    /**
     * Fills the table of garments that is shown in the view.
     * 
     * @throws ClientErrorException 
     */
    private void fillTable() throws ClientErrorException {
        GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
        
        garmentList = FXCollections.observableArrayList(garmentClient.findGarmentAll(new GenericType<List<Garment>>(){}));
        
        garmentsTable.setItems(garmentList);
    }
    
    /**
     * Sets the factory for the columns of the table.
     */
    private void setColumnFactories() {
        
        barcodeColumn.setCellValueFactory(new PropertyValueFactory("barcode"));
        designerColumn.setCellValueFactory(new PropertyValueFactory("designer"));
        garmentTypeColumn.setCellValueFactory(new PropertyValueFactory("garmentType"));
        bodyPartColumn.setCellValueFactory(new PropertyValueFactory("bodyPart"));
        moodColumn.setCellValueFactory(new PropertyValueFactory("mood"));
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        availableColumn.setCellValueFactory(new PropertyValueFactory("available"));
        promotedColumn.setCellValueFactory(new PropertyValueFactory("promoted"));
        promotionRequestColumn.setCellValueFactory(new PropertyValueFactory("promotionRequest"));
        
        // Materials
        // Colors
    }
    
    /**
     * This method was created to group another methods. 
     * That makes reading the code easier.
     */
    private void setElements() {
        
        this.setMnemonicText();
        this.setTooltips();
        this.setOnAction();
        
        this.setColumnFactories();
        this.fillTable();
    }
    
    /**
     * This method sets the texts of the controllers so the user can press 
     * <i>Alt + the first letter of the word</i> as a shortcut.
     */
    private void setMnemonicText() {
        
        menuFile.setText("_File");
        //menuItemSave.setText("_Save");
        menuItemBack.setText("_Back");
        menuEdit.setText("_Edit");
        menuItemPromote.setText("_Promote");
        menuItemDeletePromotion.setText("_Delete promotion");
        menuItemCancelRequest.setText("_Cancel request");
        
        //btnSave.setText("_Save");
        btnBack.setText("_Back");
    }
    
    /**
     * Sets the tooltips for the buttons on the view.
     */
    private void setTooltips() {
        
        //btnSave.setTooltip(new Tooltip("Save the changes"));
        btnBack.setTooltip(new Tooltip("Go to the previous window"));
    }
    
    /**
     * Sets the actions for all the controllers on the view.
     */
    private void setOnAction() {
        
        choiceTheme.setOnAction(this::onThemeChosen);
        
        //menuItemSave.setOnAction(this::onBtnSavePress);
        menuItemBack.setOnAction(this::onBtnBackPress);
        menuItemPromote.setOnAction(this::onMenuItemPromotePress);
        menuItemDeletePromotion.setOnAction(this::onMenuItemDeletePromotionPress);
        menuItemCancelRequest.setOnAction(this::onMenuItemCancelRequestPress);
        
        //btnSave.setOnAction(this::onBtnSavePress);
        btnBack.setOnAction(this::onBtnBackPress);
    }
    
    /**
     * The event that will happen when the <i>Save</i> button is pressed.
     * 
     * @param event The action event of the view.
     */
//    private void onBtnSavePress(ActionEvent event) {
        // This method was finally deleted because all the actions use the client directly.
//    }
    
    /**
     * The event that will happen when the <i>Back</i> button is pressed.
     * 
     * @param event The action event of the view.
     */
    private void onBtnBackPress(ActionEvent event){
        this.previousStage.show();
        this.stage.hide();
    }
    
    /**
     * The event that will happen when the <i>Promote</i> menu item is pressed.
     * 
     * @param event The action event of the view.
     */
    private void onMenuItemPromotePress(ActionEvent event) {
        Garment promoteGarment = garmentsTable.getSelectionModel().getSelectedItem();
        
        promoteGarment.setPromoted(true);
        promoteGarment.setPromotionRequest(false);
        
        GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
        garmentClient.editGarment(promoteGarment);
        garmentClient.close();
    }
    
    /**
     * The event that will happen when the <i>Delete promotion</i> menu item is pressed.
     * 
     * @param event The action event of the view.
     */
    private void onMenuItemDeletePromotionPress(ActionEvent event) {
        Garment deletePromoteGarment = garmentsTable.getSelectionModel().getSelectedItem();
        
        deletePromoteGarment.setPromoted(false);
        
        GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
        garmentClient.editGarment(deletePromoteGarment);
        garmentClient.close();
    }
    
    /**
     * The event that will happen when the <i>Cancel request</i> menu item is pressed.
     * 
     * @param event The action event of the view.
     */
    private void onMenuItemCancelRequestPress(ActionEvent event) {
        Garment cancelPromoteGarment = garmentsTable.getSelectionModel().getSelectedItem();
        
        cancelPromoteGarment.setPromotionRequest(false);
        
        GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
        garmentClient.editGarment(cancelPromoteGarment);
        garmentClient.close();
    }
}
