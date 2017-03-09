package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.MapView;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sun.swing.SwingUtilities2;

public class Main extends Application {
  
    private MapView _map;
    private static StackPane _layout;

    @Override
    public void init() {
        _layout = new StackPane();
        _map = new MapView();
    }
  
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(_map);
        stage.setScene(scene);
        stage.show();
        //_map.adaptToScene(scene.widthProperty()); Center image
        
        /* GUI tests
        new Marker(new Pokemon("AA", "BB"), new Coordinate(0, 0));
        new Marker(new Pokemon("AA", "BB"), new Coordinate(10, 0));
        Circle circle = new Circle();
        circle.setRadius(10.0f);
        circle.setCenterX(0);
        circle.setCenterY(0);
        getStackPane().getChildren().add(circle);
        */
    }
    
    public static StackPane getStackPane() {
        return _layout;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
