package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.MapView;
import be.ac.ulb.infof307.g01.gui.NewMarkerPopUp;
import be.ac.ulb.infof307.g01.gui.PinPopUp;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
  
    private MapView _map;
    private static StackPane _layout;
    private static Stage _stage;
    private Scene _scene;

    @Override
    public void init() {
        _layout = new StackPane();
        _map = new MapView();
    }
  
    @Override
    public void start(Stage stage) {
        _stage = stage;
        _scene = new Scene(_map);
        stage.setScene(_scene);
        stage.show();
        //_map.adaptToScene(scene.widthProperty()); Center image
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
