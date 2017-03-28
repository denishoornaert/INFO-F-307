package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.MapController;
import java.net.URL;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 * TODO: add description
 */
public class MapView extends StackPane {
    
    private MapController _mapController;
    
    /** Displays the webpage with the embedded Google Map. */
    private WebView _webView = new WebView();
    
    /** Manipulates the JavaScript code in the WebView. */
    private WebEngine _webEngine = _webView.getEngine();
    
    /** Allows JavaScript code to call Java functions. */
    private JavaBridge _bridge = new JavaBridge();

    public MapView(MapController mapController) {     
        super();
        _mapController = mapController;
        
        bindJavaBridge();
        
        // Load Webpage
        final URL urlGoogleMaps = getClass().getResource("/googleMap/mapPage.html");
        _webEngine.load(urlGoogleMaps.toExternalForm());

        // Add the webview to ourselves
        getChildren().add(_webView);
        // Add ourselves to the main layout
        Main.getStackPane().getChildren().add(this);
    }
    
    /** Sets bridge member so that JavaScript event handlers can call Java functions. */
    private void bindJavaBridge() {
        _webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    JSObject jsobj = (JSObject) _webEngine.executeScript("window");
                    jsobj.setMember("bridge", _bridge);
                }
            }
        });
    }
    
    public void createPin(double latitude, double longitude, String pokemonName, Integer pinId) {
        JSObject window = (JSObject) _webEngine.executeScript("window");
        window.call("addMarker", latitude, longitude, pokemonName, pinId);
    }

    /** Allows JavaScript code to call Java functions. */
    public class JavaBridge {
        /**
         * Displays a message on the Java console.
         * @param message is the log content.
         */
        public void log(String message) {
            System.out.println("JS Console: " + message);
        }

        /**
         * Called when the map receives a click event.
         * Calls createMarker function on MapView.
         * @param coordinates 
         */
        public void onMapRightClick(JSObject coordinates) {
            double latitude = (double) coordinates.call("lat");
            double longitude = (double) coordinates.call("lng");
            _mapController.askForCreateMarker(latitude, longitude);
        }
        
        public void onMarkerLeftClick(int markerId) {
            _mapController.displayPinPopUp(markerId);
        }
        
        public String getClusterImagesPath() {
            URL urlClusterImages = getClass().getResource("/googleMap");
            return urlClusterImages.getPath().substring(1);
        }
    }
}
