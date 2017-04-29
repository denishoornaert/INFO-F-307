package be.ac.ulb.infof307.g01.client;

import be.ac.ulb.infof307.g01.client.controller.MapController;
import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import be.ac.ulb.infof307.g01.client.model.DatabaseModel;
import be.ac.ulb.infof307.g01.client.controller.PanelController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
  
    private static BorderPane _layout;
    private static Stage _stage;
    private Scene _scene;

    @Override
    public void init() {
        _layout = new BorderPane();
    }
  
    @Override
    public void start(Stage stage) {
        _stage = stage;
        
        // Create MapController & PanelController
        new MapController();
        new PanelController();
        
        _scene = new Scene(_layout);
        _scene.getStylesheets().add(ClientConfiguration.getInstance().getStylePath());
        stage.setScene(_scene);
        
        stage.show();
    }
    
    public static BorderPane getStackPane() {
        return _layout;
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
