/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The main menu of the program. For the moment, it only has a logout button.
 *
 * @author Asier Vila Dominguez
 */
public class FXMLViewCompanyMainMenuController extends FXMLDocumentController {

    @FXML
    private Button btnLogout;
    @FXML
    private Label lblUserFullname;

    private Stage stage;
    private Stage loginStage;

    private static final Logger LOG = Logger.getLogger(FXMLViewCompanyMainMenuController.class.getName());

    /**
     * @return the btnLogout
     */
    public Button getBtnLogout() {
        return btnLogout;
    }

    /**
     * @param btnLogout the btnLogout to set
     */
    public void setBtnLogout(Button btnLogout) {
        this.btnLogout = btnLogout;
    }

    /**
     * @return the lblUserFullname
     */
    public Label getLblUserFullname() {
        return lblUserFullname;
    }

    /**
     * @param lblUserFullname the lblUserFullname to set
     */
    public void setLblUserFullname(Label lblUserFullname) {
        this.lblUserFullname = lblUserFullname;
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
     * @return the loginStage
     */
    public Stage getLogin() {
        return loginStage;
    }

    /**
     * @param loginStage the login to set
     */
    public void setLogin(Stage loginStage) {
        this.loginStage = loginStage;
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
    public void initStage(boolean happyMode, Stage stage, Parent root) {

        this.stage = stage;

        Scene scene = new Scene(root);

        this.happyMode = happyMode;

        stage.setScene(scene);
        stage.setTitle("Home");
        stage.setMinWidth(1400);
        stage.setMinHeight(800);
        stage.show();

        // Check if the happyMode is true, and change the CSS.
        setStylesheet(scene);

        setElements();

        // Replace the label lblUserFullname with current user's full name
        lblUserFullname.setText(user.getFullName());

        // Also make sure that the checkBox is correctly set
        chkHappyMode.setSelected(happyMode);

        btnLogout.setDisable(false);

        stage.setOnCloseRequest(this::onClosing);
    }

    /**
     * This method makes a logout for the current user. It also closes the
     * window and returns to the login one
     *
     * @param actionEvent
     */
    private void onBtnLogoutPress(ActionEvent event) {

        // Open the login window
        loginStage.show();

        // Close the current window
        stage.close();
    }

    /**
     * This method initializes the elements in the window, setting listeners or
     * enabling/disabling elements.
     */
    private void setElements() {

        chkHappyMode.setOnAction(this::onThemeChosen);

        getBtnLogout().setOnAction(this::onBtnLogoutPress);

        setFocusTraversable();
    }

    /**
     * This method allows to change the focus between the elements of the
     * window.
     */
    private void setFocusTraversable() {

        btnLogout.setFocusTraversable(true);

        chkHappyMode.setFocusTraversable(true);
    }
}
