package lit_fits_client;

import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import lit_fits_client.views.FXMLViewLoginController;
import lit_fits_client.views.themes.Theme;

/**
 * Main class of the desktop program
 *
 * @author Carlos Rafael Mendez Gonzalez
 */
public class ApplicationMain extends Application {
    private static final Logger LOG = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        try {
            launch();
        } catch (Exception e) {
            LOG.severe(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Change it so it reads the theme from a file outside of the jar
        String theme = ResourceBundle.getBundle("lit_fits_client.views.themes").getString("theme");
        Theme previousTheme = new Theme();
        previousTheme.setThemeCss(theme);
        String uri = ResourceBundle.getBundle("connection").getString("hostUrl");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/fxml/ViewLogin.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLViewLoginController loginView = ((FXMLViewLoginController) fxmlLoader.getController());
        loginView.setStage(stage);
        loginView.initStage(previousTheme, root, uri);
    }
}
