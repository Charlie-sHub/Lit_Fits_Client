package lit_fits_client;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            LOG.severe(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        List<Theme> themes = new ArrayList<Theme>();
        // This should read and put all the themes from a certain folder into a List
        try (Stream<Path> filePathStream = Files.walk(Paths.get("themes"))) {
            filePathStream.forEach((Path filePath) -> {
                if (Files.isRegularFile(filePath)) {
                    Theme newTheme = new Theme();
                    newTheme.setThemeCss(filePath.toString());
                    themes.add(newTheme);
                }
            });
        }
        for (Theme theme : themes) {
            System.out.println(theme.getThemeCss());
        }
        // Should get the last theme 
        String previousThemePath = ResourceBundle.getBundle("lit_fits_client.views.themes.theme").getString("theme");
        Theme previousThemeAux = new Theme(previousThemePath);
        System.out.println(previousThemeAux.getThemeCss());
        Optional<Theme> previousTheme = themes.stream().filter(theme -> theme.toString().equals(previousThemeAux.toString())).findAny();
        String uri = ResourceBundle.getBundle("lit_fits_client.connection").getString("hostUrl");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/fxml/ViewLogin.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLViewLoginController loginView = ((FXMLViewLoginController) fxmlLoader.getController());
        loginView.setStage(stage);
        loginView.initStage((List) themes, previousTheme.get(), root, uri);
    }
}
