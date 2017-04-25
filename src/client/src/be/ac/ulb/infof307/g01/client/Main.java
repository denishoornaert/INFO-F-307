package be.ac.ulb.infof307.g01.client;

import be.ac.ulb.infof307.g01.client.controller.AuthenticationController;
import be.ac.ulb.infof307.g01.client.controller.MapController;
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
    
    /** Path to application database. */
    private static final String DATABASE_PATH = "../../assets/Database.db";
    
    /**
     * Path to application tests  database.
     * 
     * Note: another database is needed for tests in order to not pollute the
     * initial one in case any database test goes wrong
     */
    private static final String TEST_DATABASE_PATH = "../../assets/TestDatabase.db";
    
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
        _scene.getStylesheets().add(new File("assets/bootstrap.css").toURI().toString());
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
        new DatabaseModel(getDatabasePath());
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
    
    /**
     * Returns the path of the application database.
     * @return the path to the database to be used in the application
     */
    public static String getDatabasePath() {
        return DATABASE_PATH;
    }
    
    /**
     * Returns the path or the tests database.
     * @return the path of the database to be used in the tests
     */
    public static String getTestDatabasePath() {
        return TEST_DATABASE_PATH;
    }

}
