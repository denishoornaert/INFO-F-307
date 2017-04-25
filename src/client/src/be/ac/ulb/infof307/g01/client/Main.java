package be.ac.ulb.infof307.g01.client;

import be.ac.ulb.infof307.g01.client.controller.AuthenticationController;
import be.ac.ulb.infof307.g01.client.controller.MapController;
import be.ac.ulb.infof307.g01.client.model.Configuration;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import be.ac.ulb.infof307.g01.client.model.DatabaseModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
  
    private static StackPane _layout;
    private static Stage _stage;    
    private Scene _scene;

    @Override
    public void init() {
        _layout = new StackPane();
    }
  
    @Override
    public void start(Stage stage) {
        _stage = stage;
        
        // Create MapController
        new MapController();
        
        _scene = new Scene(_layout);
        _scene.getStylesheets().add(Configuration.getInstance().getStylePath());
        stage.setScene(_scene);
        
        stage.show();
        
        // Create login controller
        AuthenticationController.getInstance();
    }
    
    public static StackPane getStackPane() {
        return _layout;
    }
    
    public static Stage getStage() {
        return _stage;
    }
    
    /** Loads and inits the database. */
    private static void loadDatabase() throws SQLException, FileNotFoundException {
        new DatabaseModel(Configuration.getInstance().getDataBasePath());
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
