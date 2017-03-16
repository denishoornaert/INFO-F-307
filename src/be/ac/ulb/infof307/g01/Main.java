package be.ac.ulb.infof307.g01;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
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
  
    public static void main(String[] args) {
        launch(args);
    }  

}
