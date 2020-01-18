package lit_fits_client.views;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

/**
 * Core of the document controllers from which all other document controllers inherit
 *
 * @author Ander Rodriguez & Carlos Mendez
 */
public class FXMLDocumentController {
    /**
     * The Choice box with the different themes
     */
    @FXML
    protected ChoiceBox<String> choiceTheme;
    /**
     * The theme used
     */
    protected String theme;
    /**
     * The address of the server
     */
    protected String uri;
    private static final Logger LOG = Logger.getLogger(FXMLDocumentController.class.getName());

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
     * Gets the address of the server used by the controller
     *
     * @return
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the address of the server used by the controller
     *
     * @param uri
     */
    public void setUri(String uri) {
        this.uri = uri;
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
    public void createExceptionDialog(Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
        LOG.severe(e.getMessage());
    }

    /**
     * Creates a dialog with information for the user
     *
     * @param text
     */
    public void createDialog(String text) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setContentText(text);
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
