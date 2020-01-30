/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.io.IOException;
import java.util.List;
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
import lit_fits_client.views.themes.Theme;

/**
 *
 * @author Ander
 */
public class FXMLViewExpertMainMenuController extends FXMLDocumentController {
    /**
     * Button Logout
     */
    @FXML
    private Button btnLogOut;
    /**
     * Button Modify account
     */
    @FXML
    private Button btnModify;
    /**
     * Button for edit recommendations
     */
    @FXML
    private Button btnRecommendations;
    /**
     * The stage used by this view
     */
    private Stage stage;
    /**
     * The stage used by this login view
     */
    private Stage loginStage;
    /**
     * Expert that logged in
     */
    private FashionExpert expert;
    private static final Logger LOG = Logger.getLogger(FXMLViewExpertMainMenuController.class.getName());

    public Button getBtnLogOut() {
        return btnLogOut;
    }

    public void setBtnLogout(Button btnLogout) {
        this.btnLogOut = btnLogout;
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

    /**
     * This method initializes the window
     *
     * @param themes
     * @param theme the chosen css theme
     * @param root The Parent used in previous windows
     * @param stage
     * @param uri
     */  
    public void initStage (List<Theme> themes, Theme theme, Stage stage, Parent root, String uri) {
        try{
            this.uri = uri;
            this.stage = stage;
            Scene scene = new Scene(root);
            this.theme = theme;
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.show();
            setStylesheet(scene, theme.getThemeCssPath());
            themeList = themes;
            setElements();
            choiceTheme.setValue(theme);
            stage.setOnCloseRequest(this::onClosing);
        }catch(Exception e){
            e.printStackTrace();
            createExceptionDialog(e);
            LOG.severe(e.getMessage());
        }
    }
    /**
     * Sets the options for different elements of the window
     */
    private void setElements() {
        fillChoiceBoxTheme();
        setOnAction();
        setTooltips();
        setFocusTraversable();
        
    }
    /**
     * Implements the actions
     */
    private void setOnAction() {
        btnLogOut.setOnAction(this::onBtnLogoutPress);
        btnRecommendations.setOnAction(this::onBtnRecommendationPress);
        btnModify.setOnAction(this::onBtnModifyAccountPress);
        
    }
    /**
     * Sets the tooltips of different elements
     */
    private void setTooltips() {
        btnLogOut.setTooltip(new Tooltip("Log out of the program"));
        btnRecommendations.setTooltip(new Tooltip("Edit your fashion recommendations"));
        btnModify.setTooltip(new Tooltip("Modify account data"));
    }
    /**
     * This method allows to change the focus between the elements of the window.
     */
    private void setFocusTraversable() {
        btnModify.setFocusTraversable(true);
        btnRecommendations.setFocusTraversable(true);
        btnLogOut.setFocusTraversable(true);
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
            modifyAccount.initStage(themeList, theme, stageProgramMain, root, uri);
            stage.hide();
        } catch (IOException ex) {
            createExceptionDialog(ex);
            LOG.severe(ex.getMessage());
        }
    }
    /**
     * This function opens the recommendations view
     * @param event 
     */
    private void onBtnRecommendationPress(ActionEvent event) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("fxml/ExpertEditRecommendations.fxml"));
            Stage stageMainProgram = new Stage();
            Parent root = (Parent) fXMLLoader.load();
            FXMLViewExpertEditRecommendationController editRecommendations = ((FXMLViewExpertEditRecommendationController) fXMLLoader.getController());
            editRecommendations.setExpert(expert);
            editRecommendations.setStageMainMenu(stage);
            editRecommendations.initStage(themeList, theme, stageMainProgram, root, uri);
            stage.hide();
        } catch (IOException ex) {
            createExceptionDialog(ex);
            LOG.severe(ex.getMessage());
        }
    }
    
}   
