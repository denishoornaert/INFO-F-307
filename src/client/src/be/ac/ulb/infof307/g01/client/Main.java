package be.ac.ulb.infof307.g01.client;

import be.ac.ulb.infof307.g01.client.controller.AuthenticationController;
import be.ac.ulb.infof307.g01.client.controller.MapController;
import java.io.File;

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
        _scene.getStylesheets().add(new File("../../assets/client/bootstrap.css").toURI().toString());
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
    
    public static void main(String[] args) {
        
        // TODO remove test
//        ArrayList<MarkerSendableModel> allMarker = ServerQueryController.getInstance().getAllMarkers();
//        for(MarkerSendableModel mark : allMarker) {
//            System.out.println("Marker !");
//        }
        
//        PokemonTypeSendableModel pokemonType = new PokemonTypeSendableModel("typeTest");
//        MarkerSendableModel markerSend = new MarkerSendableModel(12,
//            "test", new PokemonSendableModel("name", "path", pokemonType), 
//                5, 12, new Timestamp(System.currentTimeMillis()).getTime(), 3, 1,
//            5, 7, 9);
//        ServerQueryController.getInstance().insertMarker(markerSend);
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
