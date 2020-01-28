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
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TableView<Color> tableColor;
    @FXML
    private TableColumn<Color, String> columnNameColor;
    @FXML
    private TableView<Material> tableMaterial;
    @FXML
    private TableColumn<Material, String> columnNameMaterial;
    
    ObservableList<Color> colorList;
    ObservableList<Material> materialList;
    
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

    void initStage(List<Theme> themes, Theme theme, Stage stage, Parent root, String uri) {
        try{
            this.uri = uri;
            this.stage = stage;
            Scene scene = new Scene(root);
            this.theme = theme;
            stage.setScene(scene);
            stage.setTitle("Edit recommendations");
            stage.setMinWidth(1400);
            stage.setMinHeight(800);
            stage.show();
            setStylesheet(scene, theme.getThemeCssPath());
            themeList = themes;
            setElements();
            stage.setOnCloseRequest(this::onClosing);
        }catch(Exception e){
            createExceptionDialog(e);
            LOG.severe(e.getMessage());
        }
    }
    /**
     * Sets the options for different elements of the window
     */
    private void setElements() {
        fillChoiceBoxTheme();
        setColumnFactories();
        setSelectedReccomendations();
        fillTable();
        setOnAction();
        contextMenuTableColors.hide();
        contextMenuTableMaterials.hide();
        
    }

    private void setColumnFactories() {
        columnNameColor.setCellFactory(new PropertyValueFactory("color_name"));
        columnNameMaterial.setCellFactory(new PropertyValueFactory("material_name"));
    }
    
    private void setSelectedReccomendations() {
       List<Color> colorsSelected = expert.getRecommendedColors();
       colorsSelected.forEach((colorExpert) -> {
           colorList.stream().filter((colorDB) -> (colorExpert.getName().equals(colorDB.getName()))).forEachOrdered((colorDB) -> {
               tableColor.getSelectionModel().select(colorDB);
           });
        });
       
       List<Material> materialsSelected = expert.getRecommendedMaterials();
       materialsSelected.forEach((materialExpert) -> {
           materialList.stream().filter((materialDB) -> (materialExpert.getName().equals(materialDB.getName()))).forEachOrdered((materialDB) -> {
               tableMaterial.getSelectionModel().select(materialDB);
           });
        });
        
    }
    
    private void fillTable() {
        
        MaterialClient materialClient = ClientFactory.getMaterialClient(uri);
        materialList = FXCollections.observableArrayList(materialClient.findAll(new GenericType<List<Material>>(){
        }));
        tableMaterial.setItems(materialList);
        ColorClient colorClient = ClientFactory.getColorClient(uri);
        colorList = FXCollections.observableArrayList(colorClient.findAll(new GenericType<List<Color>>(){
        }));
        tableColor.setItems(colorList);   
        tableColor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

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
    }
    
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
    
    private void onCancelPress(ActionEvent event) {
        try {
            openMainWindow();
        } catch (IOException ex) {
            Logger.getLogger(FXMLViewExpertEditRecommendationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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


    
}
