/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.ColorClient;
import lit_fits_client.RESTClients.ExpertClient;
import lit_fits_client.RESTClients.MaterialClient;
import lit_fits_client.entities.Color;
import lit_fits_client.entities.FashionExpert;
import lit_fits_client.entities.Material;
import lit_fits_client.views.themes.Theme;

/**
 *
 * @author Ander
 */
public class FXMLViewExpertEditRecommendationController extends FXMLDocumentController {
    /**
     * Button Save
     */
    @FXML
    private Button btnSave;
    /**
     * Button for cancel
     */
    @FXML
    private Button btnCancel;
    /**
     * Table View of colors
     */
    @FXML
    private TableView<Color> tableColor;
    /**
     * Table column name
     */
    @FXML
    private TableColumn<Color, String> columnNameColor;
    /**
     * Table view of materials
     */
    @FXML
    private TableView<Material> tableMaterial;
    /**
     * Table column material
     */
    @FXML
    private TableColumn<Material, String> columnNameMaterial;
    /**
     * Menu Bar recommendations
     */
    @FXML
    private MenuBar menuBar;
    /**
     * menu File
     */
    @FXML
    private Menu menuFile;
    /**
     * Menu edit
     */
    @FXML
    private Menu menuEdit;
    /**
     * Menu Help
     */
    @FXML
    private Menu menuHelp;
    /**
     * Menu item Save
     */
    @FXML
    private MenuItem menuItemSave;
    /**
     * Menu Item Quit
     */
    @FXML
    private MenuItem menuItemQuit;
    /**
     * Menu Item Select All
     */
    @FXML
    private MenuItem menuItemSelectAll;
    /**
     * Menu Item UnSelect All
     */
    @FXML
    private MenuItem menuItemUnSelectAll;
    /**
     * Menu Item Help
     */
    @FXML
    private MenuItem menuItemHelp;
    /**
     * List of colors
     */
    public ObservableList<Color> colorList = FXCollections.observableArrayList();
    /**
     * List of Materials
     */
    public ObservableList<Material> materialList = FXCollections.observableArrayList();
    
    /**
     * Context menu of the table of colors
     */
    @FXML
    private ContextMenu contextMenuTableColors;
    
    /**
     * Context menu of the table of Materials
     */
    @FXML
    private ContextMenu contextMenuTableMaterials;
        
    private Stage stage;
    private Stage stageMainMenu;
    
    private FashionExpert expert;
    private static final Logger LOG = Logger.getLogger(FXMLViewExpertEditRecommendationController.class.getName());

    /**
     * @return the btnSave
     */
    public Button getBtnSave() {
        return btnSave;
    }

    /**
     * @param btnSave the btnSave to set
     */
    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }

    /**
     * @return the btnCancel
     */
    public Button getBtnCancel() {
        return btnCancel;
    }

    /**
     * @param btnCancel the btnCancel to set
     */
    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    /**
     * @return the tableColor
     */
    public TableView<Color> getTableColor() {
        return tableColor;
    }

    /**
     * @param tableColor the tableColor to set
     */
    public void setTableColor(TableView<Color> tableColor) {
        this.tableColor = tableColor;
    }

    /**
     * @return the columnNameColor
     */
    public TableColumn<Color, String> getColumnNameColor() {
        return columnNameColor;
    }

    /**
     * @param columnNameColor the columnNameColor to set
     */
    public void setColumnNameColor(TableColumn<Color, String> columnNameColor) {
        this.columnNameColor = columnNameColor;
    }
    

    
    /**
     * @return the tableMaterial
     */
    public TableView<Material> getTableMaterial() {
        return tableMaterial;
    }

    /**
     * @param tableMaterial the tableMaterial to set
     */
    public void setTableMaterial(TableView<Material> tableMaterial) {
        this.tableMaterial = tableMaterial;
    }

    /**
     * @return the columnNameMaterial
     */
    public TableColumn<Material, String> getColumnNameMaterial() {
        return columnNameMaterial;
    }

    /**
     * @param columnNameMaterial the columnNameMaterial to set
     */
    public void setColumnNameMaterial(TableColumn<Material, String> columnNameMaterial) {
        this.columnNameMaterial = columnNameMaterial;
    }


    /**
     * @return the colorList
     */
    public ObservableList<Color> getColorList() {
        return colorList;
    }

    /**
     * @param colorList the colorList to set
     */
    public void setColorList(ObservableList<Color> colorList) {
        this.colorList = colorList;
    }

    /**
     * @return the materialList
     */
    public ObservableList<Material> getMaterialList() {
        return materialList;
    }

    /**
     * @param materialList the materialList to set
     */
    public void setMaterialList(ObservableList<Material> materialList) {
        this.materialList = materialList;
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
     * @return the stageMainMenu
     */
    public Stage getStageMainMenu() {
        return stageMainMenu;
    }

    /**
     * @param stageMainMenu the stageMainMenu to set
     */
    public void setStageMainMenu(Stage stageMainMenu) {
        this.stageMainMenu = stageMainMenu;
    }

    /**
     * @return the expert
     */
    public FashionExpert getExpert() {
        return expert;
    }

    /**
     * @param expert the expert to set
     */
    public void setExpert(FashionExpert expert) {
        this.expert = expert;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public Menu getMenuFile() {
        return menuFile;
    }

    public void setMenuFile(Menu menuFile) {
        this.menuFile = menuFile;
    }

    public Menu getMenuEdit() {
        return menuEdit;
    }

    public void setMenuEdit(Menu menuEdit) {
        this.menuEdit = menuEdit;
    }

    public Menu getMenuHelp() {
        return menuHelp;
    }

    public void setMenuHelp(Menu menuHelp) {
        this.menuHelp = menuHelp;
    }

    public MenuItem getMenuItemSave() {
        return menuItemSave;
    }

    public void setMenuItemSave(MenuItem menuItemSave) {
        this.menuItemSave = menuItemSave;
    }

    public MenuItem getMenuItemQuit() {
        return menuItemQuit;
    }

    public void setMenuItemQuit(MenuItem menuItemQuit) {
        this.menuItemQuit = menuItemQuit;
    }

    public MenuItem getMenuItemSelectAll() {
        return menuItemSelectAll;
    }

    public void setMenuItemSelectAll(MenuItem menuItemSelectAll) {
        this.menuItemSelectAll = menuItemSelectAll;
    }

    public MenuItem getMenuItemUnSelectAll() {
        return menuItemUnSelectAll;
    }

    public void setMenuItemUnSelectAll(MenuItem menuItemUnSelectAll) {
        this.menuItemUnSelectAll = menuItemUnSelectAll;
    }

    public MenuItem getMenuItemHelp() {
        return menuItemHelp;
    }

    public void setMenuItemHelp(MenuItem menuItemHelp) {
        this.menuItemHelp = menuItemHelp;
    }

    public ContextMenu getContextMenuTableColors() {
        return contextMenuTableColors;
    }

    public void setContextMenuTableColors(ContextMenu contextMenuTableColors) {
        this.contextMenuTableColors = contextMenuTableColors;
    }

    public ContextMenu getContextMenuTableMaterials() {
        return contextMenuTableMaterials;
    }

    public void setContextMenuTableMaterials(ContextMenu contextMenuTableMaterials) {
        this.contextMenuTableMaterials = contextMenuTableMaterials;
    }
    
    
    /**
     * This method initializes the window
     * 
     * @param themes
     * @param theme
     * @param stage
     * @param root
     * @param uri 
     */
    void initStage(List<Theme> themes, Theme theme, Stage stage, Parent root, String uri) {
        try{
            this.uri = uri;
            this.stage = stage;
            Scene scene = new Scene(root);
            this.theme = theme;
            stage.setScene(scene);
            stage.setTitle("Edit recommendations");
            stage.show();
            setStylesheet(scene, theme.getThemeCssPath());
            themeList = themes;
            setElements();
            stage.setOnCloseRequest(this::onClosing);
        }catch(Exception e){
            
            LOG.severe(e.getMessage());
        }
    }
    /**
     * Sets the options for different elements of the window
     */
    private void setElements() {
        
        fillChoiceBoxTheme();
        setColumnFactories();
        fillTable();
        setSelectedReccomendations();
        setOnAction();
        setMenu();
        setContextMenus();
        contextMenuTableColors.hide();
        contextMenuTableMaterials.hide();
        
    }
    /**
     * Set the cell value Factory
     */
    private void setColumnFactories() {
        columnNameColor.setCellValueFactory(new PropertyValueFactory("name"));
        columnNameMaterial.setCellValueFactory(new PropertyValueFactory("name"));
    }
    
    /**
     * Function to preselect the recomended colors and materials
     */
    private void setSelectedReccomendations() {
       
       List<Color> colorsSelected = expert.getRecommendedColors();
        if (colorsSelected.isEmpty()) {
            tableColor.getSelectionModel().clearSelection();
        }else{
            colorsSelected.forEach((colorExpert) -> {
                colorList.stream().filter((colorDB) -> (colorExpert.getName().equals(colorDB.getName()))).forEachOrdered((colorDB) -> {
                    tableColor.getSelectionModel().select(colorDB);
                });
             });
        }
       List<Material> materialsSelected = expert.getRecommendedMaterials();
        if (materialsSelected.isEmpty()) {
            tableMaterial.getSelectionModel().clearSelection();
        }else{
            materialsSelected.forEach((materialExpert) -> {
                materialList.stream().filter((materialDB) -> (materialExpert.getName().equals(materialDB.getName()))).forEachOrdered((materialDB) -> {
                    tableMaterial.getSelectionModel().select(materialDB);
                });
            });
        }
        
    }
    /**
     * Function to fill the table
     */
    private void fillTable() {
        
        MaterialClient materialClient = ClientFactory.getMaterialClient(uri);
        materialList = tableMaterial.getItems();
        materialList = FXCollections.observableArrayList(materialClient.findAll(new GenericType<List<Material>>(){
        }));
        tableMaterial.setItems(materialList);
        
        ColorClient colorClient = ClientFactory.getColorClient(uri);
        colorList = tableColor.getItems();
        colorList = FXCollections.observableArrayList(colorClient.findAll(new GenericType<List<Color>>(){
        }));
        tableColor.setItems(colorList);   
        tableColor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    /**
     * Implementation of the actions
     */
    private void setOnAction() {
        btnSave.setOnAction(this::onSavePress);
        btnCancel.setOnAction(this::onCancelPress);
        tableColor.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
           if (event.getButton() == MouseButton.SECONDARY) {
               contextMenuTableColors.show(tableColor, event.getScreenX(), event.getScreenY());
           }
        });
        tableMaterial.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
           if (event.getButton() == MouseButton.SECONDARY) {
               contextMenuTableMaterials.show(tableMaterial, event.getScreenX(), event.getScreenY());
           }
        });
        menuItemSave.setOnAction(this::onSavePress);
        menuItemQuit.setOnAction(this::onCancelPress);
        menuItemSelectAll.setOnAction(this::selectAllTable);
        menuItemUnSelectAll.setOnAction(this::unSelectAllTable);
        menuItemHelp.setOnAction(this::onHelpPressed);
    }
    
    /**
     * This function is for when the user clicks on save, first saves the data and the sends you to the main window
     * @param event 
     */
    private void onSavePress(ActionEvent event) {
        ExpertClient expertClient = ClientFactory.getExpertClient(uri);
        try {
            List<Color> colorsSelected = tableColor.getSelectionModel().getSelectedItems();
            expert.setRecommendedColors(colorsSelected);
            
            List<Material> materialsSelected = tableMaterial.getSelectionModel().getSelectedItems();
            expert.setRecommendedMaterials(materialsSelected);
            
            expertClient.edit(expert);
             openMainWindow();
        } catch (IOException ex) {
            Logger.getLogger(FXMLViewExpertEditRecommendationController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            expertClient.close();
        }
        
    }
    /**
     * This function is for when the user clicks on cancel send you the main window
     * @param event 
     */
    private void onCancelPress(ActionEvent event) {
        try {
            openMainWindow();
        } catch (IOException ex) {
            Logger.getLogger(FXMLViewExpertEditRecommendationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This function select all values on the table
     * @param event 
     */
    private void selectAllTable(ActionEvent event) {
        tableColor.getSelectionModel().selectAll();
        tableMaterial.getSelectionModel().selectAll();
    }
    /**
     * This function unselect all selected values on the table
     * @param event 
     */
    private void unSelectAllTable(ActionEvent event) {
        tableColor.getSelectionModel().clearSelection();
        tableMaterial.getSelectionModel().clearSelection();
    }
    /**
     * This function opens the main window
     * @throws IOException 
     */
    private void openMainWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ExpertMainMenu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stageProgramMain = new Stage();
        FXMLViewExpertMainMenuController mainView = ((FXMLViewExpertMainMenuController) fxmlLoader.getController());
        mainView.setExpert(expert);
        mainView.setLoginStage(stageMainMenu);
        mainView.initStage(themeList, theme, stageProgramMain, root, uri);
        stage.hide();
    }
    /**
     * This function set the menu bar with all the menus and menu items
     */
    private void setMenu() {
        menuBar.getMenus().addAll(menuFile, menuEdit, menuHelp);
        menuFile.getItems().addAll(menuItemSave, menuItemQuit);
        menuEdit.getItems().addAll(menuItemSelectAll, menuItemUnSelectAll);
        menuHelp.getItems().addAll(menuItemHelp);
    }
    /**
     * This function put the menu items in the contextMenu
     */
    private void setContextMenus() {
        contextMenuTableColors.getItems().addAll(menuItemSave, menuItemHelp);
        contextMenuTableMaterials.getItems().addAll(menuItemSave, menuItemHelp);
    }



    
}
