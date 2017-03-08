package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.MapView;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  
    private MapView _map;

    @Override
    public void init() {
        _map = new MapView();
    }
  
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(_map);
        stage.setScene(scene);
        stage.show();
        _map.adatpToScene(scene.widthProperty());
    }
  
    public static void main(String[] args) {
        launch(args);
    }  
}
