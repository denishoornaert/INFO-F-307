package be.ac.ulb.infof307.g01.client;

import be.ac.ulb.infof307.g01.client.controller.WindowController;
import be.ac.ulb.infof307.g01.client.model.Configuration;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import be.ac.ulb.infof307.g01.client.model.DatabaseModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  
    private static Stage _stage;
    private static Scene _scene;
  
    @Override
    public void start(Stage stage) {
        _stage = stage;
        
        new WindowController();
        
        stage.show();
    }
    
    public static void setScene(Scene scene) {
        _scene = scene;
        _stage.setScene(_scene);
        _scene.getStylesheets().add(Configuration.getInstance().getStylePath());
    }
    
    public static Stage getStage() {
        return _stage;
    }
    
    /** Loads and inits the database. */
    private static void loadDatabase() throws SQLException, FileNotFoundException {
        new DatabaseModel(ClientConfiguration.getInstance().getDataBasePath());
    }
    
    public static void main(String[] args) {
        try {
            loadDatabase();
        } catch (SQLException | FileNotFoundException exception) {
            System.err.println(exception.getMessage());
            System.err.println("Error loading the database. Application aborted");
            return;
        }
        launch(args);
    }

}