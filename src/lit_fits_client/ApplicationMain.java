/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit_fits_client;

import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import lit_fits_client.views.FXMLViewLoginController;
//import thebestprogramlogiclibrary.logic.ApplicationLogicFactory;
//import thebestprogramlogiclibrary.logic.ApplicationLogicImplementation;

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
        String host = ResourceBundle.getBundle("thebestprogramdesktop.connection").getString("host");
        int port = Integer.parseInt(ResourceBundle.getBundle("thebestprogramdesktop.connection").getString("port"));
        String theme = ResourceBundle.getBundle("lit_fits_client.views.theme").getString("theme");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/fxml/ViewLogin.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLViewLoginController loginView = ((FXMLViewLoginController) fxmlLoader.getController());
        loginView.setStage(stage);
        loginView.initStage(theme, root);
    }
}
