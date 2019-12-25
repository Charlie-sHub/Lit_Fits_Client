/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client.views;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.WindowEvent;

/*import thebestprogramlogiclibrary.User;
import thebestprogramlogiclibrary.exceptions.DisabledAccountException;
import thebestprogramlogiclibrary.exceptions.PasswordMismatchException;
import thebestprogramlogiclibrary.exceptions.ThreadLimitException;
import thebestprogramlogiclibrary.exceptions.UnreachableDatabaseException;
import thebestprogramlogiclibrary.exceptions.UsedUsernameException;
import thebestprogramlogiclibrary.exceptions.UsernameNotFoundException;
import thebestprogramlogiclibrary.logic.ApplicationLogicImplementation;
 */
/**
 * Core of the document controllers
 *
 * @author Ander Rodriguez
 */
public class FXMLDocumentController {
    @FXML
    protected ChoiceBox<String> choiceTheme;
    //protected User user;
    protected String theme;
    private static final Logger LOG = Logger.getLogger(FXMLDocumentController.class.getName());

    /*
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
     */
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * Returns the choice box used to choose the theme
     *
     * @return ChoiceBox
     */
    public ChoiceBox getChoiceTheme() {
        return choiceTheme;
    }

    /**
     * Sets the choice box for the theme
     *
     * @param choiceBox
     */
    public void setChoiceTheme(ChoiceBox choiceBox) {
        this.choiceTheme = choiceBox;
    }

    /**
     * This method checks the happiness of the program
     *
     * @param event
     */
    public void onThemeChosen(ActionEvent event) {
        //pick the path from the ChoiceBox
        String path = "";
        setStylesheet(((CheckBox) event.getSource()).getScene(), path);
    }

    /**
     * Based on the happiness of the window is set the correct mood
     *
     * @author Carlos Rafael Mendez Gonzalez
     * @param scene scene to be loaded with the stylesheet
     * @param themePath
     */
    public void setStylesheet(Scene scene, String themePath) {
        // How should we remove the theme to have the default look? just add an empty css?
        scene.getStylesheets().add(getClass().getResource(themePath).toExternalForm());
    }

    /**
     * Creates the dialogs for a given exception
     *
     * @author Carlos Rafael Mendez Gonzalez
     * @param e exception to be handled
     */
    public void createDialog(Exception e) {
        String errorString = null;
        if (e instanceof IOException) {
            errorString = "IOException ocurred \n" + e.getMessage();
        } else if (e instanceof SocketTimeoutException) {
            errorString = "Server did not respond on time \n" + e.getMessage();
        }
        /*else if (e instanceof DisabledAccountException) {
            errorString = "The account you're trying to log in with is currently disabled \n" + e.getMessage();
        } else if (e instanceof PasswordMismatchException) {
            errorString = "Wrong password \n" + e.getMessage();
        } else if (e instanceof ThreadLimitException) {
            errorString = "Server refused connection due to the connection limit \n" + e.getMessage();
        } else if (e instanceof UnreachableDatabaseException) {
            errorString = "Server couldn't access the database \n" + e.getMessage();
        } else if (e instanceof UsedUsernameException) {
            errorString = "That username is already in use \n" + e.getMessage();
        } else if (e instanceof UsernameNotFoundException) {
            errorString = "Couldn't find the username \n";
        } else {
            errorString = "Unkown error ocurred";
        }*/
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(errorString);
        alert.showAndWait();
    }

    /**
     * Saves the value of the happy mode in the properties file of the program when closing it, so the next time the
     * program is opened the mode stays.
     *
     * @author Carlos Rafael Mendez Gonzalez
     * @param event makes the method a handler
     */
    public void onClosing(WindowEvent event) {
        OutputStream out = null;
        try {
            out = new FileOutputStream("theme.properties");
            Properties properties = new Properties();
            properties.setProperty("theme", theme);
            properties.store(out, null);
        } catch (IOException e) {
            LOG.severe(e.getMessage());
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                LOG.severe(e.getMessage());
            }
        }
    }
}
