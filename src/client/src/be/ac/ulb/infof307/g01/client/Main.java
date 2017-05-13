package be.ac.ulb.infof307.g01.client;

import be.ac.ulb.infof307.g01.client.controller.app.WindowController;
import be.ac.ulb.infof307.g01.client.model.app.ClientConfiguration;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
  
    private static Stage _stage;
    private static Scene _scene;
  
    /**
     * Method called by launch.
     * @param stage the JavaFX stage for the main window
     */
    @Override
    public void start(Stage stage) {
        _stage = stage;
        new WindowController();
        
        stage.show();
    }
    
    /**
     * Sets the stage's scene, as well as the application title and icons.
     * @param scene the JavaFX scene to use.
     */
    public static void setScene(Scene scene) {
        _scene = scene;
        setApplicationTitle();
        setApplicationIcons();
        _stage.setScene(_scene);
    }

    /**
     * Sets the desktop application's title, provided by ClientConfiguration.
     */
    public static void setApplicationTitle() {
        String title = ClientConfiguration.getInstance().getApplicationTitle();
        _stage.setTitle(title);
    }
        
    /**
     * Sets the desktop application's icons, provided by ClientConfiguration.
     */
    private static void setApplicationIcons() {
        ArrayList<String> iconsPaths = ClientConfiguration.getInstance().getApplicationIconsPaths();
        for (String path : iconsPaths) {
            _stage.getIcons().add(new Image(path));
        }
    }
    
    public static Stage getStage() {
        return _stage;
    }
    
    /**
     * Opens a link in the application's web browser.
     * Requires access to Application.
     * @param link the web link to open
     */
    public static void openInBrowser(String link) {
        try {
            new ProcessBuilder("x-www-browser", link).start();
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.INFO, e.getMessage());
        }
    }
    
    /**
     * Main function, called on execution, launches the application.
     * Required by Java.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
