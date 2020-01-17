package lit_fits_client.views;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.GarmentClient;
import lit_fits_client.entities.Color;
import lit_fits_client.entities.Garment;
import lit_fits_client.entities.Material;
import lit_fits_client.entities.User;

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
    
    // ------------------ Garment table ----------------------
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
    
    // ------------------ User table ----------------------
    @FXML
    private TableView<Garment> usersTable;
    @FXML
    private TableColumn<Garment, String> usernameColumn;
    @FXML
    private TableColumn<Garment, String> passwordColumn;
    @FXML
    private TableColumn<Garment, String> fullnameColumn;
    @FXML
    private TableColumn<Garment, String> phoneNumberColumn;
    @FXML
    private TableColumn<Garment, String> emailColumn;
    @FXML
    private TableColumn<Garment, Object> userTypeColumn;
    @FXML
    private TableColumn<Garment, Set<Material>> likedMaterialsColumn;
    @FXML
    private TableColumn<Garment, Set<Color>> likedColorsColumn;
    
    private ObservableList<User> userList;
    
    // ---------------- Getters and Setters --------------------
    
    /**
     * @return the admin
     */
    public User getAdmin () {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin (User admin) {
        this.admin = admin;
    }

    /**
     * @return the stage
     */
    public Stage getStage () {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage (Stage stage) {
        this.stage = stage;
    }
    
    public void setPreviousStage(Stage previousStage) {
        this.previousStage = previousStage;
    }
    
    public Stage getPreviousStage() {
        return this.previousStage;
    }
    
    // --------------- Methods -------------------
    
    /**
     * The method that initiates the view.
     * 
     * @param theme
     * @param stage
     * @param root 
     */
    public void initStage (String theme, Stage stage, Parent root, String uri) {
        
        this.stage = stage;
        this.theme = theme;
        this.uri = uri;
        
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.setTitle("Administrator - Check promotion requests");
        this.stage.show();
        
        this.setStylesheet(scene, theme);
        this.setElements();
        this.choiceTheme.setValue(theme);
        
        stage.setOnCloseRequest(this::onClosing);
        
    }
    
    /**
     * This method was created to group another 3 different methods. 
     * That makes reading the code easier.
     */
    private void setElements() {
        
        this.setMnemonicText();
        this.setTooltips();
        this.setOnAction();
    }
    
    /**
     * This method sets the texts of the controllers so the user can press 
     * <i>Alt + the first letter of the word</i> as a shortcut.
     */
    private void setMnemonicText() {
        
        menuFile.setText("_File");
        menuItemDeleteItem.setText("_Delete item");
        menuItemBack.setText("_Back");
        
        btnDeleteItem.setText("_Delete item");
        btnBack.setText("_Back");
    }

    private void setTooltips() {
        
        btnDeleteItem.setTooltip(new Tooltip("Delete the selected item"));
        btnBack.setTooltip(new Tooltip("Go back"));
    }

    private void setOnAction() {
        menuItemDeleteItem.setOnAction(this::onBtnDeleteItemPress);
        menuItemBack.setOnAction(this::onBtnBackPress);
        
        btnDeleteItem.setOnAction(this::onBtnDeleteItemPress);
        btnBack.setOnAction(this::onBtnBackPress);
    }
    
    /**
     * The event that will happen when the <i>Back</i> button is pressed.
     * 
     * @param event 
     */
    private void onBtnBackPress(ActionEvent event) {
        
    }
    
    /**
     * The event that will happen when the <i>Delete item</i> button is pressed.
     * 
     * @param event 
     */
    private void onBtnDeleteItemPress (ActionEvent event) {
        
    }
    
    private void fillTableGarment() throws ClientErrorException {
        GarmentClient garmentClient = ClientFactory.getGarmentClient(uri);
        
        garmentList = FXCollections.observableArrayList(garmentClient.findGarmentAll(new GenericType<List<Garment>>(){}));

        garmentsTable.setItems(garmentList);
    }
    
    private void fillTableUser() throws ClientErrorException {
        UserClient userClient = ClientFactory.getUserClient(uri);
        
        userList = FXCollections.observableArrayList(userClient.findAllUsers(new GenericType<List<User>>(){}));
        
        usersTable.setItems(userList);
    }
    
}
