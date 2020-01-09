package lit_fits_client;

import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import lit_fits_client.views.FXMLViewLoginController;

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
        String theme = ResourceBundle.getBundle("lit_fits_client.views.theme").getString("theme");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/fxml/ViewLogin.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLViewLoginController loginView = ((FXMLViewLoginController) fxmlLoader.getController());
        loginView.setStage(stage);
        loginView.initStage(theme, root);
    }
}
