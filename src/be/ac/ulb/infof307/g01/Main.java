package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.db.DatabaseModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

public class Main extends Application {
  
    private static StackPane _layout;
    private static Stage _stage;
    
    /**
     * Path to application database
     */
    private static final String DATABASE_PATH = "./assets/Database.db";
    
    /**
     * Path to application tests  database
     * 
     * Note: another database is needed for tests in order to not pollute the
     * initial one in case any database test goes wrong
     */
    private static final String TEST_DATABASE_PATH = "./assets/TestDatabase.db";
    
    private Scene _scene;

    @Override
    public void init() {
        _layout = new StackPane();
    }
  
    @Override
    public void start(Stage stage) {
        _stage = stage;
        // create MapController
        MapController mapController = new MapController();
        
        _scene = new Scene(_layout);
        _scene.getStylesheets().add(new File("assets/bootstrap.css").toURI().toString());
        stage.setScene(_scene);
        
        stage.show();
    }
    
    public static StackPane getStackPane() {
        return _layout;
    }
    
    public static Stage getStage() {
        return _stage;
    }
    
    /**
     * Load and init the database
     * 
     * @return false if anything went wrong and true otherwise
     */
    private static boolean loadDatabase() {
        boolean isOk = true;
        try {
            DatabaseModel databaseModel = new DatabaseModel(getDatabasePath());
        } catch (SQLException | FileNotFoundException exception) {
            System.err.println(exception.getMessage());
            isOk = false;
        }
        return isOk;
    }
    
    public static void main(String[] args) {
        if(!loadDatabase()) {
            System.err.println("Error loading the database. Application aborted");
            return;
        }
        launch(args);
    }  
    
    /**
     * Return the path of the application database
     * @return the path to the database to be used in the application
     */
    public static String getDatabasePath() {
        return DATABASE_PATH;
    }
    
    /**
     * Return the path or the tests database
     * @return the path of the database to be used in the tests
     */
    public static String getTestDatabasePath() {
        return TEST_DATABASE_PATH;
    }

}
