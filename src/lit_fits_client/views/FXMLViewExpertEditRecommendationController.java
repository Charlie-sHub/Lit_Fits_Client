/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.util.List;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import lit_fits_client.RESTClients.ClientFactory;
import lit_fits_client.RESTClients.ColorClient;
import lit_fits_client.RESTClients.MaterialClient;
import lit_fits_client.entities.Color;
import lit_fits_client.entities.FashionExpert;
import lit_fits_client.entities.Garment;
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
    private TableColumn<Color, Boolean> columnSelectColor;
    @FXML
    private TableView<Material> tableMaterial;
    @FXML
    private TableColumn<Material, String> columnNameMaterial;
    @FXML
    private TableColumn<Material, Boolean> columnSelectMaterial;

    private ObservableList<Color> colorList;
    private ObservableList<Material> materialList;
    
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
     * @return the columnSelectMaterial
     */
    public TableColumn<Color, Boolean> getColumnSelectMaterial() {
        return columnSelectColor;
    }

    /**
     * @param columnSelectMaterial the columnSelectMaterial to set
     */
    public void setColumnSelectMaterial(TableColumn<Color, Boolean> columnSelectMaterial) {
        this.columnSelectColor = columnSelectMaterial;
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
     * @return the columnSelectMaterial
     */
    public TableColumn<Material, Boolean> getColumnSelectMMaterial() {
        return columnSelectMaterial;
    }

    /**
     * @param columnSelectMMaterial the columnSelectMaterial to set
     */
    public void setColumnSelectMMaterial(TableColumn<Material, Boolean> columnSelectMMaterial) {
        this.columnSelectMaterial = columnSelectMMaterial;
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

    void initStage(List<Theme> themes, Theme theme, Stage stageMainProgram, Parent root, String uri) {
        try{
            this.uri = uri;
            this.stage = stageMainProgram;
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Edit recommendations");
            stage.setMinWidth(1400);
            stage.setMinHeight(800);
            stage.show();
            setStylesheet(scene, theme.getThemeCss());
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
        setColumnFactories();
        fillTable();
        
    }

    private void setColumnFactories() {
        columnNameColor.setCellFactory(new PropertyValueFactory("color_name"));
        columnSelectColor.setCellFactory(new PropertyValueFactory("color select"));
        columnNameMaterial.setCellFactory(new PropertyValueFactory("material name"));
        columnSelectMaterial.setCellFactory(new PropertyValueFactory("material select"));
    }

    private void fillTable() {
        /*
        MaterialClient materialClient = ClientFactory.getMaterialClient(uri);
        materialList = FXCollections.observableArrayList(materialClient.findAll(new GenericType(<List<Material>>)));
        tableMaterial.setItems(materialList);
        ColorClient colorClient = ClientFactory.getColorClient(uri);
        colorList = FXCollections.observableArrayList(colorClient.findAll(new GenericType(<List<Color>>)));
        tableColor.setItems(colorList);        
    */
    }

    
}
