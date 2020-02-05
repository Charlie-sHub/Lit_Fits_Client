package lit_fits_client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.stream.Stream;
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
            e.printStackTrace();
            LOG.severe(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        List<Theme> themes = new ArrayList<>();
        try (Stream<Path> filePathStream = Files.walk(Paths.get("themes"))) {
            filePathStream.forEach((Path filePath) -> {
                if (Files.isRegularFile(filePath)) {
                    Theme newTheme = new Theme();
                    newTheme.setThemeCssPath(filePath.getFileName().toString());
                    themes.add(newTheme);
                }
            });
        }
        Theme previousThemeAux = new Theme(getThemeProperty());
        String uri = ResourceBundle.getBundle("lit_fits_client.connection").getString("hostUrl");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/fxml/ViewLogin.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLViewLoginController loginView = ((FXMLViewLoginController) fxmlLoader.getController());
        loginView.setStage(stage);
        loginView.initStage((List) themes, previousThemeAux, root, uri);
    }

    /**
     * Gets the property value of a properties file outside the jar
     *
     * @return String
     * @throws IOException
     */
    public String getThemeProperty() throws IOException {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("theme.properties");
            properties.load(input);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.severe(e.getMessage());
                }
            }
        }
        return properties.getProperty("theme");
    }
}
