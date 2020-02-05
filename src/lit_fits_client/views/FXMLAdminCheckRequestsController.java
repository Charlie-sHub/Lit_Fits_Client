package lit_fits_client.views;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import lit_fits_client.entities.BodyPart;
import lit_fits_client.entities.Garment;
import lit_fits_client.entities.GarmentType;
import lit_fits_client.entities.Mood;
import lit_fits_client.entities.User;
import lit_fits_client.views.themes.Theme;

/**
 * 
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
    
    @FXML
    private Button btnBack;
    
    @FXML
    private Menu menuFile;
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
    
    private ArrayList<String> filterByList;
    
    // ----------------- Garments Table ----------------------
    
    @FXML
    private TableView<Garment> tableViewGarment;
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
    private TableColumn<Garment, Boolean> availableColumn;
    @FXML
    private TableColumn<Garment, Boolean> promotedColumn;
    @FXML
    private TableColumn<Garment, Boolean> promotionRequestColumn;
    
    private ObservableList<Garment> garmentList;
    
    // ---------------- Modificaciones DIN -------------------
        
        private ObservableList<Garment> requestedGarmentList;
        private ObservableList<Garment> promotedGarmentList;
    
        @FXML
        private MenuItem menuItemCreateTestGarments;
        
        private String filter = "";
    //--------------------------------------------------------
    
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
        this.choiceTheme.setValue(theme);
        this.setElements();
        
        stage.setOnCloseRequest(this::onClosing);
    }
    
    /**
     * Fills the table of garments that is shown in the view.
     * It will also be used to refresh the table.
     * 
     * @throws ClientErrorException 
     */
    /*
    private void fillTable() throws ClientErrorException {
        GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
        
        garmentList = FXCollections.observableArrayList(garmentClient.findGarmentAll(new GenericType<List<Garment>>(){}));
        
        garmentsTable.setItems(garmentList);
    }
    */
    private void fillTable() {
        
        try {
            switch(this.filter) {

                case "Promoted":
                    this.tableViewGarment.setItems(FXCollections.observableArrayList(promotedGarmentList));
                    break;

                case "Promotion request":
                    this.tableViewGarment.setItems(FXCollections.observableArrayList(requestedGarmentList));
                    break;

                case "Not promoted/requested":
                    this.tableViewGarment.setItems(FXCollections.observableArrayList(garmentList));
                    break;

                default:
                    break;
            }
        } catch (NullPointerException ex) {
            
            this.createDialog("There are no garments.");
        }
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
        availableColumn.setCellValueFactory(new PropertyValueFactory("available"));
        promotedColumn.setCellValueFactory(new PropertyValueFactory("promoted"));
        promotionRequestColumn.setCellValueFactory(new PropertyValueFactory("promotionRequest"));
    }
    
    /**
     * This method was created to group another methods. 
     * That makes reading the code easier.
     */
    private void setElements() {
        
        this.setMnemonicText();
        this.setTooltips();
        this.setOnAction();
        
        this.fillChoiceBoxTheme();
        this.setChoiceBoxEntities();
        
        this.setColumnFactories();
        this.fillTable();
    }
    
    /**
     * This method sets the entities that can be shown on the tables.
     */
    private void setChoiceBoxEntities () {
        filterByList = new ArrayList<String>();
        
        filterByList.add("Promoted");
        filterByList.add("Promotion request");
        filterByList.add("Not promoted/requested");
        
        choiceBoxFilterBy.setItems(FXCollections.observableArrayList(filterByList));
    }
    
    /**
     * The event that will happen when an option is selected in the 
     * <i>Filter By</i> choice box.
     * @param event 
     */
    private void onFilterChosen (Event event) {
        
        this.filter = this.choiceBoxFilterBy.getSelectionModel().getSelectedItem().toString();
        
       this.fillTable();
    }
    
    /**
     * This method sets the texts of the controllers so the user can press 
     * <i>Alt + the first letter of the word</i> as a shortcut.
     */
    private void setMnemonicText() {
        
        menuFile.setText("_File");
        menuFile.setMnemonicParsing(true);
        menuItemBack.setText("_Back");
        menuItemBack.setMnemonicParsing(true);
        menuItemCreateTestGarments.setText("Create _test garments");
        menuItemCreateTestGarments.setMnemonicParsing(true);
        menuEdit.setText("_Edit");
        menuEdit.setMnemonicParsing(true);
        menuItemPromote.setText("_Promote");
        menuItemPromote.setMnemonicParsing(true);
        menuItemDeletePromotion.setText("_Delete promotion");
        menuItemDeletePromotion.setMnemonicParsing(true);
        menuItemCancelRequest.setText("_Cancel request");
        menuItemCancelRequest.setMnemonicParsing(true);
        
        btnBack.setText("_Back");
        btnBack.setMnemonicParsing(true);
    }
    
    /**
     * Sets the tooltips for the buttons on the view.
     */
    private void setTooltips() {
        
        btnBack.setTooltip(new Tooltip("Go to the previous window"));
    }
    
    /**
     * Sets the actions for all the controllers on the view.
     */
    private void setOnAction() {
        
        choiceTheme.setOnAction(this::onThemeChosen);
        
        menuItemBack.setOnAction(this::onBtnBackPress);
        menuItemCreateTestGarments.setOnAction(this::createTestGarments);
        menuItemPromote.setOnAction(this::onMenuItemPromotePress);
        menuItemDeletePromotion.setOnAction(this::onMenuItemDeletePromotionPress);
        menuItemCancelRequest.setOnAction(this::onMenuItemCancelRequestPress);
        
        btnBack.setOnAction(this::onBtnBackPress);
        
        choiceTheme.setOnAction(this::onThemeChosen);
        
        choiceBoxFilterBy.setOnAction(this::onFilterChosen);
    }
    
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
    /*
    private void onMenuItemPromotePress(ActionEvent event) {
        Garment promoteGarment = garmentsTable.getSelectionModel().getSelectedItem();
        
        promoteGarment.setPromoted(true);
        promoteGarment.setPromotionRequest(false);
        
        GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
        garmentClient.editGarment(promoteGarment);
        garmentClient.close();
        this.fillTable();
    }
    */
    private void onMenuItemPromotePress (ActionEvent event) {
        
        try {
            if (this.filter == "Promotion request") {
                Garment promotedGarment = tableViewGarment.getSelectionModel().getSelectedItem();

                this.requestedGarmentList.remove(promotedGarment);

                promotedGarment.setPromoted(true);
                promotedGarment.setPromotionRequest(false);

                this.promotedGarmentList.add(promotedGarment);

                this.fillTable();

            } else {
                this.createDialog("This action is only available for \"Promotion request\" garments.");

            }
        } catch (NullPointerException ex) {
            this.createDialog("Nothing is selected.");
        }
    }
    
    /**
     * The event that will happen when the <i>Delete promotion</i> menu item is pressed.
     * 
     * @param event The action event of the view.
     */
    /*
    private void onMenuItemDeletePromotionPress(ActionEvent event) {
        Garment deletePromoteGarment = garmentsTable.getSelectionModel().getSelectedItem();
        
        deletePromoteGarment.setPromoted(false);
        
        GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
        garmentClient.editGarment(deletePromoteGarment);
        garmentClient.close();
        this.fillTable();
    }
    */
    private void onMenuItemDeletePromotionPress(ActionEvent event) {
        
        try {
            if (filter == "Promoted") {
                Garment deletePromotionGarment = tableViewGarment.getSelectionModel().getSelectedItem();

                this.promotedGarmentList.remove(deletePromotionGarment);
                deletePromotionGarment.setPromoted(false);

                this.garmentList.add(deletePromotionGarment);

                this.fillTable();
            }else {
                this.createDialog("This action is only available for \"Promoted\" garments.");
            }
            
        } catch (NullPointerException ex) {
            this.createDialog("Nothing is selected.");
        }
    }
    
    /**
     * The event that will happen when the <i>Cancel request</i> menu item is pressed.
     * 
     * @param event The action event of the view.
     */
    /*
    private void onMenuItemCancelRequestPress(ActionEvent event) {
        Garment cancelPromoteGarment = garmentsTable.getSelectionModel().getSelectedItem();
        
        cancelPromoteGarment.setPromotionRequest(false);
        
        GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
        garmentClient.editGarment(cancelPromoteGarment);
        garmentClient.close();
        this.fillTable();
    }
    */
    private void onMenuItemCancelRequestPress(ActionEvent event) {
        
        try {
            if (this.filter == "Promotion request") {
                Garment cancelPromoteGarment = tableViewGarment.getSelectionModel().getSelectedItem();

                this.requestedGarmentList.remove(cancelPromoteGarment);

                cancelPromoteGarment.setPromotionRequest(false);

                this.garmentList.add(cancelPromoteGarment);
            
                this.fillTable();
                
            } else {
                this.createDialog("This action is only available for \"Promotion request\" garments.");
            }
        } catch (NullPointerException ex) {
            this.createDialog("Nothing is selected.");
        }
    }
    
    /**
     * This method creates garments to test the app. Is a modification 
     * to avoid calling the server.
     */
    private void createTestGarments (ActionEvent event) {
        
        if (this.requestedGarmentList != null){
            this.requestedGarmentList.clear();
        } else {
            this.requestedGarmentList = FXCollections.observableArrayList(new ArrayList<Garment>());
        }
        
        if (this.promotedGarmentList != null){
            this.promotedGarmentList.clear();
        } else {
            this.promotedGarmentList = FXCollections.observableArrayList(new ArrayList<Garment>());
        }
        
        if (this.garmentList != null){
            this.garmentList.clear();
        } else {
            this.garmentList = FXCollections.observableArrayList(new ArrayList<Garment>());
        }
        
        for (int i = 0; i<=4; i++) {
            Garment requestedGarment = new Garment();
            
            requestedGarment.setBarcode(String.valueOf(1001*i));
            requestedGarment.setMood(Mood.FORMAL);
            requestedGarment.setDesigner("DIN - Requested garments");
            
            switch (i) {
                case 0:
                    requestedGarment.setBodyPart(BodyPart.TOP);
                    break;                    
                case 1:
                    requestedGarment.setBodyPart(BodyPart.BOTTOM);
                    break;                    
                case 2:
                    requestedGarment.setBodyPart(BodyPart.SHOE);
                    break;                    
                case 3:
                    requestedGarment.setBodyPart(BodyPart.SHOE);
                    break;                    
                case 4:
                    requestedGarment.setBodyPart(BodyPart.OTHER);
                    break;                    
            }
            
            switch (i) {
                case 0:
                    requestedGarment.setGarmentType(GarmentType.SHIRT);
                    break;
                case 1:
                    requestedGarment.setGarmentType(GarmentType.PANTS);
                    break;
                case 2:
                    requestedGarment.setGarmentType(GarmentType.BOOTS);
                    break;
                case 3:
                    requestedGarment.setGarmentType(GarmentType.SNEAKERS);
                    break;
                case 4:
                    requestedGarment.setGarmentType(GarmentType.HAT);
                    break;    
            }
            requestedGarment.setAvailable(i%2 == 0);
            requestedGarment.setPromotionRequest(true);
            requestedGarment.setPromoted(false);
            
            this.requestedGarmentList.add(requestedGarment);
        }
        
        for (int i = 0; i<=4; i++) {
            Garment promotedGarment = new Garment();
            
            promotedGarment.setBarcode(String.valueOf(11*i));
            promotedGarment.setMood(Mood.FORMAL);
            promotedGarment.setDesigner("DIN - Promoted garments");
            
            switch (i) {
                case 0:
                    promotedGarment.setBodyPart(BodyPart.TOP);
                    break;                    
                case 1:
                    promotedGarment.setBodyPart(BodyPart.BOTTOM);
                    break;                    
                case 2:
                    promotedGarment.setBodyPart(BodyPart.SHOE);
                    break;                    
                case 3:
                    promotedGarment.setBodyPart(BodyPart.SHOE);
                    break;                    
                case 4:
                    promotedGarment.setBodyPart(BodyPart.OTHER);
                    break;                    
            }
            
            switch (i) {
                case 0:
                    promotedGarment.setGarmentType(GarmentType.SHIRT);
                    break;
                case 1:
                    promotedGarment.setGarmentType(GarmentType.PANTS);
                    break;
                case 2:
                    promotedGarment.setGarmentType(GarmentType.BOOTS);
                    break;
                case 3:
                    promotedGarment.setGarmentType(GarmentType.SNEAKERS);
                    break;
                case 4:
                    promotedGarment.setGarmentType(GarmentType.HAT);
                    break;    
            }
            promotedGarment.setAvailable(i%2 == 0);
            promotedGarment.setPromotionRequest(false);
            promotedGarment.setPromoted(true);
            
            this.promotedGarmentList.add(promotedGarment);
        }
        
        for (int i = 0; i<=4; i++) {
            Garment garment = new Garment();
            
            garment.setBarcode(String.valueOf(110*i));
            garment.setMood(Mood.FORMAL);
            garment.setDesigner("DIN - All garments");

            switch (i) {
                case 0:
                    garment.setBodyPart(BodyPart.TOP);
                    break;                    
                case 1:
                    garment.setBodyPart(BodyPart.BOTTOM);
                    break;                    
                case 2:
                    garment.setBodyPart(BodyPart.SHOE);
                    break;                    
                case 3:
                    garment.setBodyPart(BodyPart.SHOE);
                    break;                    
                case 4:
                    garment.setBodyPart(BodyPart.OTHER);
                    break;                    
            }
            
            switch (i) {
                case 0:
                    garment.setGarmentType(GarmentType.SHIRT);
                    break;
                case 1:
                    garment.setGarmentType(GarmentType.PANTS);
                    break;
                case 2:
                    garment.setGarmentType(GarmentType.BOOTS);
                    break;
                case 3:
                    garment.setGarmentType(GarmentType.SNEAKERS);
                    break;
                case 4:
                    garment.setGarmentType(GarmentType.HAT);
                    break;
            }
            garment.setAvailable(i%2 == 0);
            garment.setPromotionRequest(false);
            garment.setPromoted(false);
            
            this.garmentList.add(garment);
        }
        
        this.fillTable();
    }
}
