package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.db.DatabaseModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

public class Main extends Application {
  
    private static StackPane _layout;
    private static Stage _stage;
    private static final String DATABASE_PATH = "./assets/Database.db";
    
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
    
    private static boolean loadDatabase() {
        boolean isOk = true;
        try {
            new DatabaseModel(DATABASE_PATH);
        } catch (SQLException | FileNotFoundException exception) {
            System.err.println(exception.getMessage());
            isOk = false;
        }
        return isOk;
    }
    
    public static void main(String[] args) {
        if(loadDatabase()) {
            launch(args);
        }
    }  
    
    public static String getDatabasePath() {
        return DATABASE_PATH;
    }

}
