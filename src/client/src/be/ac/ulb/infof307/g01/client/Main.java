package be.ac.ulb.infof307.g01.client;

import be.ac.ulb.infof307.g01.client.controller.WindowController;
import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {
  
    private static Stage _stage;
    private static Scene _scene;
  
    @Override
    public void start(Stage stage) {
        _stage = stage;
        
        new WindowController();
        
        stage.show();
    }
    
    public static void setScene(Scene scene) {
        _scene = scene;
        setApplicationTitle();
        setApplicationIcons();
        _stage.setScene(_scene);
        _scene.getStylesheets().add(ClientConfiguration.getInstance().getStylePath());
    }

    /**
     * Sets the desktop application's title, provided by ClientConfiguration.
     */
    public static void setApplicationTitle() {
        String title = ClientConfiguration.getInstance().getApplicationTitle();
        _stage.setTitle(title);
    }
        
    /**
     * Sets the desktop application's icons, provided by ClientConfiguration.
     */
    private static void setApplicationIcons() {
        ArrayList<String> iconsPaths = ClientConfiguration.getInstance().getApplicationIconsPaths();
        for (String path : iconsPaths) {
            _stage.getIcons().add(new Image("file:" + path));
        }
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

}