package lit_fits_client.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.GarmentClient;
import lit_fits_client.RESTClients.UserClient;
import lit_fits_client.entities.Garment;
import lit_fits_client.entities.User;
import lit_fits_client.views.themes.Theme;

/**
 * The controller for the view in wich the admin can check all the 
 * entities in the database.
 * 
 * @author Asier
 */
public class FXMLAdminCheckDatabaseController extends FXMLDocumentController {
    
    private User admin;
    private Stage stage;
    private Stage previousStage;
    
    private String uri;
    
    private ArrayList<String> entitiesList;
    
    @FXML
    private Menu menuFile;
    @FXML
    private MenuItem menuItemDeleteItem;
    @FXML
    private MenuItem menuItemBack;
    
    @FXML
    private Button btnDeleteItem;
    @FXML
    private Button btnBack;
    
    @FXML
    private ChoiceBox choiceBoxEntities;
    
    // ------------------ Garment table ----------------------
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
    
    /**
     * The list of garments that will be loaded into the table.
     */
    private ObservableList<Garment> garmentList;
    
    // ------------------ User table ----------------------
    @FXML
    private TableView<User> tableViewUser;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;
    @FXML
    private TableColumn<User, String> fullnameColumn;
    @FXML
    private TableColumn<User, String> phoneNumberColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, Object> userTypeColumn;
    
    /**
     * The list of users that will be loaded into the table.
     */
    private ObservableList<User> userList;
    
    // ---------------- Getters and Setters --------------------
    
    /**
     * Gets the admin that is logged.
     * 
     * @return the admin
     */
    public User getAdmin () {
        return admin;
    }

    /**
     * Sets the admin that is logged.
     * 
     * @param admin The admin to set
     */
    public void setAdmin (User admin) {
        this.admin = admin;
    }

    /**
     * Gets the actual stage.
     * 
     * @return The stage.
     */
    public Stage getStage () {
        return stage;
    }

    /**
     * Sets the actual stage.
     * 
     * @param stage the stage to set
     */
    public void setStage (Stage stage) {
        this.stage = stage;
    }
    
    /**
     * Sets the previous stage.
     * 
     * @param previousStage The previous stage to set.
     */
    public void setPreviousStage(Stage previousStage) {
        this.previousStage = previousStage;
    }
    
    /**
     * Gets the previous stage.
     * @return The previous stage.
     */
    public Stage getPreviousStage() {
        return this.previousStage;
    }
    
    // --------------- Methods -------------------
    
    /**
     * The method that initiates the view.
     * 
     * @param themes All the themes that can be set to the view.
     * @param theme The theme that was selected in the previous window.
     * @param stage The stage that will be used for the view.
     * @param root The root of the view.
     * @param uri The uri for the clients.
     */
    public void initStage (List<Theme> themes, Theme theme, Stage stage, Parent root, String uri) {
        
        this.stage = stage;
        this.theme = theme;
        this.themeList = themes;
        this.uri = uri;
        
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.setTitle("Administrator - Check database");
        
        this.tableViewUser.setVisible(false);
        this.tableViewGarment.setVisible(false);
        
        this.stage.show();
        
        this.setStylesheet(scene, this.theme.getThemeCssPath());
        this.setElements();
        this.choiceTheme.setValue(this.theme);
        
        stage.setOnCloseRequest(this::onClosing);
    }
    
    /**
     * This method was created to group another methods. 
     * That makes reading the code easier.
     */
    private void setElements() {
        
        this.setMnemonicText();
        this.setTooltips();
        this.setOnAction();
        
        this.setChoiceBoxEntities();
        this.fillChoiceBoxTheme();
        
        // The methods to fill the tables. They dont work
        
        //this.setGarmentTableFactories();
        //this.fillTableGarment();
        
        //this.setUserTableFactories();
        //this.fillTableUser();
    }

    /**
     * This method sets the entities that can be shown on the tables.
     */
    private void setChoiceBoxEntities () {
        
        entitiesList = new ArrayList<String>();
        entitiesList.add("Garments");
        entitiesList.add("Users");
        
        choiceBoxEntities.setItems(FXCollections.observableArrayList(entitiesList));
    }
    
    /**
     * This method changes the visibility of the tables when the choiceBox value changes.
     * 
     * @param Event 
     */
    private void onEntitiesChosen (Event Event) {
        
        String selected = this.choiceBoxEntities.getSelectionModel().getSelectedItem().toString();
        
       switch (selected) {
           case "Garments":
               this.tableViewUser.setSelectionModel(null);
               this.tableViewUser.setVisible(false);
               this.tableViewGarment.setVisible(true);
            break;
               
           case "Users":
               this.tableViewGarment.setSelectionModel(null);
               this.tableViewGarment.setVisible(false);
               this.tableViewUser.setVisible(true);
            break;
               
           default:
               
            break;
        }
    }
    
    /**
     * Sets the factory for each column in garmentTable.
     */
    private void setGarmentTableFactories() {
        
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
     * Fills the garmentTable with data from the database.
     * 
     * @throws ClientErrorException 
     */
    private void fillTableGarment() throws ClientErrorException {
        GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
        
        garmentList = FXCollections.observableArrayList(garmentClient.findGarmentAll(new GenericType<List<Garment>>(){}));
        
        garmentClient.close();
        
        tableViewGarment.setItems(garmentList);
    }
    
    /**
     * Sets the factory for each column in userTable.
     */
    private void setUserTableFactories() {
        
        usernameColumn.setCellValueFactory(new PropertyValueFactory("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory("password"));
        fullnameColumn.setCellValueFactory(new PropertyValueFactory("fullName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory("email"));
        userTypeColumn.setCellValueFactory(new PropertyValueFactory("userType"));
    }
    
    /**
     * Fills the columns for the user's table.
     * 
     * @throws ClientErrorException 
     */
    private void fillTableUser() throws ClientErrorException {
        UserClient userClient = ClientFactory.getUserClient(uri);
        
        userList = FXCollections.observableArrayList(userClient.findAllUser(new GenericType<List<User>>(){}));
        
        userClient.close();
                
        tableViewUser.setItems(userList);
    }
    
    /**
     * This method sets the texts of the controllers so the user can press 
     * <i>Alt + the first letter of the word</i> as a shortcut.
     */
    private void setMnemonicText() {
        
        menuFile.setText("_File");
        menuFile.setMnemonicParsing(true);
        menuItemDeleteItem.setText("_Delete item");
        menuItemDeleteItem.setMnemonicParsing(true);
        menuItemBack.setText("_Back");
        menuItemBack.setMnemonicParsing(true);
        
        btnDeleteItem.setText("_Delete item");
        btnDeleteItem.setMnemonicParsing(true);
        btnBack.setText("_Back");
        btnBack.setMnemonicParsing(true);
    }

    /**
     * Sets the tooltip help for every button in the view.
     */
    private void setTooltips() {
        
        btnDeleteItem.setTooltip(new Tooltip("Delete the selected item"));
        btnBack.setTooltip(new Tooltip("Go back"));
    }

    /**
     * Sets the actions for all the controllers on the view.
     */
    private void setOnAction() {
        menuItemDeleteItem.setOnAction(this::onBtnDeleteItemPress);
        menuItemBack.setOnAction(this::onBtnBackPress);
        
        btnDeleteItem.setOnAction(this::onBtnDeleteItemPress);
        btnBack.setOnAction(this::onBtnBackPress);
        
        choiceTheme.setOnAction(this::onThemeChosen);
        this.choiceBoxEntities.setOnAction(this::onEntitiesChosen);
    }
    
    /**
     * The event that will happen when the <i>Back</i> button is pressed.
     * 
     * @param event 
     */
    private void onBtnBackPress(ActionEvent event) {
        previousStage.show();
        stage.hide();
    }
    
    /**
     * The event that will happen when the <i>Delete item</i> button is pressed.
     * 
     * @param event 
     */
    private void onBtnDeleteItemPress (ActionEvent event) {
        
        if (tableViewUser.isVisible()) {
            User deleteUser = tableViewUser.getSelectionModel().getSelectedItem();
            if (deleteUser != null) {
                
                if (this.deleteConfirmation()){
                    UserClient userClient = ClientFactory.getUserClient(uri);
                    userClient.removeUser(deleteUser.getUsername());
                    userClient.close();
                }
            } else {
                this.nothingToDelete();
            }
            
        } else if (tableViewGarment.isVisible()) {
            Garment deleteGarment = tableViewGarment.getSelectionModel().getSelectedItem();
            if (deleteGarment != null) {
                
                if (this.deleteConfirmation()) {
                    GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
                    garmentClient.remove(String.valueOf(deleteGarment.getId()));
                    garmentClient.close();
                }
            } else {
                this.nothingToDelete();
            }
            
        // In case that there is no visible tables
        } else {
            this.nothingToDelete();
        }
    }
    
    /**
     * This method opens the dialog to confirm the delete operation.
     * 
     * @return A boolean. True if the admin selects Yes, false if not.
     */
    private Boolean deleteConfirmation() {
        boolean delete;
        
        Alert deleteConfirmation = new Alert(AlertType.CONFIRMATION);
        deleteConfirmation.setTitle("Delete selected item");
        deleteConfirmation.setHeaderText("The selected item will be deleted from the database.");
        deleteConfirmation.setContentText("Confirm operation?");

        Optional<ButtonType> result = deleteConfirmation.showAndWait();
        
        if (result.get() == ButtonType.OK){
            delete = true;
        } else {
            delete = false;
        }
        
        return delete;
    }
    
    /**
     * The error dialog that opens when nothing is selected and the
     * <i>Delete item</i> button is pressed.
     */
    private void nothingToDelete() {
        
        Alert nothingToDelete = new Alert(AlertType.ERROR);
            
        nothingToDelete.setTitle("Error deleting");
        nothingToDelete.setHeaderText("Nothing to delete");
        nothingToDelete.setContentText("You have to select at least one item before pressing \"Delete item\". ");

        nothingToDelete.showAndWait();
    }
}
