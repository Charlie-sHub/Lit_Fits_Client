package lit_fits_client.views;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lit_fits_client.views.themes.Theme;

/**
 * Core of the document controllers from which all other document controllers inherit
 *
 * @author Ander & Carlos Mendez
 */
public class FXMLDocumentController {
    /**
     * The Choice box with the different themes
     */
    @FXML
    protected ChoiceBox<Theme> choiceTheme;
    /**
     * The theme used
     */
    protected Theme theme;
    /**
     * The address of the server
     */
    protected String uri;
    /**
     * List of all the themes to choose from
     */
    protected List<Theme> themeList;
    private static final Logger LOG = Logger.getLogger(FXMLDocumentController.class.getName());

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
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
     * Fills the choice box with the themes
     *
     * @author Carlos Mendez
     */
    public void fillChoiceBoxTheme() {
        choiceTheme.setItems(FXCollections.observableArrayList(themeList));
    }

    /**
     * This method checks the happiness of the program
     *
     * @param event
     * @author Carlos Mendez
     */
    public void onThemeChosen(ActionEvent event) {
        setStylesheet(((ChoiceBox) event.getSource()).getScene(), choiceTheme.getValue().getThemeCss());
    }

    /**
     * Based on the happiness of the window is set the correct mood
     *
     * @author Carlos Rafael Mendez Gonzalez
     * @param scene scene to be loaded with the stylesheet
     * @param themePath
     */
    public void setStylesheet(Scene scene, String themePath) {
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
     * @author Carlos Mendez
     */
    public void createDialog(String text) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setContentText(text);
        alert.showAndWait();
    }

    /**
     * Creates a dialog to confirm if the user wants to continue
     *
     *
     * @return boolean
     * @author Carlos Mendez
     */
    public boolean createConfirmationDialog() {
        boolean result = false;
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setContentText("This action is irreversible, are you sure?");
        Optional<ButtonType> buttonPressed = alert.showAndWait();
        if (buttonPressed.get() == ButtonType.OK) {
            result = true;
        }
        return result;
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
            properties.setProperty("theme", theme.getThemeCss());
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

    /**
     * Open the help window
     *
     * @throws IOException
     * @author Carlos Mendez
     */
    protected void openHelpView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ViewHelp.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stageHelp = new Stage();
        FXMLHelpController helpView = ((FXMLHelpController) fxmlLoader.getController());
        helpView.initStage(theme, stageHelp, root);
    }

    /**
     * Opens the help window when the help button is pressed
     *
     * @param event
     * @author Carlos Mendez
     */
    protected void onHelpPressed(ActionEvent event) {
        try {
            openHelpView();
        } catch (IOException e) {
            createExceptionDialog(e);
        }
    }
}
