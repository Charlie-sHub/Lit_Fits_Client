/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import lit_fits_client.entities.FashionExpert;

/**
 *
 * @author Ander
 */
public class FXMLViewExpertMainMenuController extends FXMLDocumentController {
    
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnRecommendations;
    @FXML
    private Button btnBlog;
    private Stage stage;
    private Stage loginStage;
    private FashionExpert expert;
    private static final Logger LOG = Logger.getLogger(FXMLViewExpertMainMenuController.class.getName());

    public Button getBtnLogout() {
        return btnLogout;
    }

    public void setBtnLogout(Button btnLogout) {
        this.btnLogout = btnLogout;
    }

    public Button getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(Button btnModify) {
        this.btnModify = btnModify;
    }

    public Button getBtnRecommendations() {
        return btnRecommendations;
    }

    public void setBtnRecommendations(Button btnRecommendations) {
        this.btnRecommendations = btnRecommendations;
    }

    public Button getBtnBlog() {
        return btnBlog;
    }

    public void setBtnBlog(Button btnBlog) {
        this.btnBlog = btnBlog;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getLoginStage() {
        return loginStage;
    }

    public void setLoginStage(Stage LoginStage) {
        this.loginStage = LoginStage;
    }

    public FashionExpert getExpert() {
        return expert;
    }

    public void setExpert(FashionExpert expert) {
        this.expert = expert;
    }
            
    public void initStage (String theme, Stage stage, Parent root, String uri) {
        try{
            this.uri = uri;
            this.stage = stage;
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.setMinWidth(1400);
            stage.setMinHeight(800);
            stage.show();
            setStylesheet(scene, theme);
            setElements();
            stage.setOnCloseRequest(this::onClosing);
        }catch(Exception e){
            createExceptionDialog(e);
            LOG.severe(e.getMessage());
        }
    }

    private void setElements() {
        setOnAction();
        setTooltips();
        setFocusTraversable();
    }

    private void setOnAction() {
        btnLogout.setOnAction(this::onBtnLogoutPress);
        btnRecommendations.setOnAction(this::onBtnRecommendationPress);
        btnModify.setOnAction(this::onBtnModifyAccountPress);
        
    }

    private void setTooltips() {
        btnLogout.setTooltip(new Tooltip("Log out of the program"));
        btnRecommendations.setTooltip(new Tooltip("Edit your fashion recommendations"));
        btnModify.setTooltip(new Tooltip("Modify account data"));
    }

    private void setFocusTraversable() {
        btnModify.setFocusTraversable(true);
        btnRecommendations.setFocusTraversable(true);
        btnLogout.setFocusTraversable(true);
    }
    /**
     * This method makes a logout for the current user. It also closes the window and returns to the login one
     *
     * @param actionEvent
     */
    private void onBtnLogoutPress(ActionEvent event) {
        loginStage.show();
        stage.close();
    }

    
    /**
     * Opens the account modification window and closes the current window
     *
     * @param event
     */
    private void onBtnModifyAccountPress(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ExpertModifyAccount.fxml"));
            Stage stageProgramMain = new Stage();
            Parent root = (Parent) fxmlLoader.load();
            FXMLViewExpertModifyAccountController modifyAccount = ((FXMLViewExpertModifyAccountController) fxmlLoader.getController());
            modifyAccount.setExpert(expert);
            modifyAccount.setStageMainMenu(stage);
            modifyAccount.initStage(choiceTheme.getValue(), stageProgramMain, root, uri);
            stage.hide();
        } catch (IOException ex) {
            createExceptionDialog(ex);
            LOG.severe(ex.getMessage());
        }
    }
    
    private void onBtnRecommendationPress(ActionEvent event) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("fxml/ExpertEditRecommendations"));
            Stage stageMainProgram = new Stage();
            Parent root = (Parent) fXMLLoader.load();
            FXMLViewExpertEditRecommendationController editRecommendations = ((FXMLViewExpertEditRecommendationController) fXMLLoader.getController());
            editRecommendations.setExpert(expert);
            editRecommendations.setStageMainMenu(stage);
            editRecommendations.initStage(choiceTheme.getValue(), stageMainProgram, root, uri);
            stage.hide();
        } catch (IOException ex) {
            createExceptionDialog(ex);
            LOG.severe(ex.getMessage());
        }
    }
    
}   
