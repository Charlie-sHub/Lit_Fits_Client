/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lit_fits_client.entities.Company;

/**
 * The "Warehouse" window for companies
 *
 * @author Carlos Mendez
 */
public class FXMLCompanyGarmentsController extends FXMLDocumentController {
    @FXML
    private Button buttonPromote;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonModify;
    @FXML
    private Button buttonCancel;
    @FXML
    private TableView tableGarments;
    private Stage stage;
    private Company company;
    private static final Logger LOG = Logger.getLogger(FXMLViewCompanyMainMenuController.class.getName());

    public Button getButtonPromote() {
        return buttonPromote;
    }

    public void setButtonPromote(Button buttonPromote) {
        this.buttonPromote = buttonPromote;
    }

    public Button getButtonAdd() {
        return buttonAdd;
    }

    public void setButtonAdd(Button buttonAdd) {
        this.buttonAdd = buttonAdd;
    }

    public Button getButtonDelete() {
        return buttonDelete;
    }

    public void setButtonDelete(Button buttonDelete) {
        this.buttonDelete = buttonDelete;
    }

    public Button getButtonModify() {
        return buttonModify;
    }

    public void setButtonModify(Button buttonModify) {
        this.buttonModify = buttonModify;
    }

    public Button getButtonCancel() {
        return buttonCancel;
    }

    public void setButtonCancel(Button buttonCancel) {
        this.buttonCancel = buttonCancel;
    }

    public TableView getTableGarments() {
        return tableGarments;
    }

    public void setTableGarments(TableView tableGarments) {
        this.tableGarments = tableGarments;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    /**
     * This method initializes the window
     *
     * @param happyMode It receives a boolean to change to happy mode css or not
     *
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

    private void setElements() {
        
    }
}
