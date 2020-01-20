package lit_fits_client.views;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lit_fits_client.views.themes.Theme;

/**
 * Controller for the help window
 * @author Carlos Mendez
 */
public class FXMLHelpController extends FXMLDocumentController {
    /**
     * Button to go back
     */
    @FXML
    private Button btnBack;
    /**
     * WebView to show the help html
     */
    @FXML
    private WebView helpView;
    /**
     * Stage to be used by the current controller
     */
    private Stage stage;

    /**
     * Initializes the register window
     *
     * @param mode It will receive a boolean that says if it is happy or not
     * @param stage The stage to be used
     * @param root The Parent created in the previous window
     */
    public void initStage(Theme theme, Stage stage, Parent root) {
        this.stage = stage;
        Scene scene = new Scene(root);
        setStylesheet(scene, theme.getThemeCss());
        setElements();
        stage.setScene(scene);
        stage.setTitle("Help");
        stage.setOnCloseRequest(this::onClosing);
        stage.setMinWidth(850);
        stage.setMinHeight(650);
        stage.show();
    }

    /**
     * Sets the properties of the elements in the view
     */
    private void setElements() {
        WebEngine webEngine;
        webEngine = helpView.getEngine();
        webEngine.load(getClass().getResource("help.html").toExternalForm());
        btnBack.setOnMouseClicked(this::onBackPressed);
    }

    /**
     * Closes the help window if the back button is pressed
     *
     * @param event
     */
    private void onBackPressed(MouseEvent event) {
        stage.close();
    }
}
