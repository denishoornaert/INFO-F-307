package be.ac.ulb.infof307.g01.client.view;

import java.net.URL;
import java.util.HashMap;

import be.ac.ulb.infof307.g01.client.Main;
import be.ac.ulb.infof307.g01.client.controller.MapController;
import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/** View of the map.
 * This view is based on Google maps: it embeds a webview (a internet browser
 * widget) and render a HTML page containing a Google map.
 * The dynamic interaction with Java, such as marker creation or lookup, is
 * done in javascript.
 */
public class MapView extends StackPane {
    
    private final MapController _mapController;
    
    /** Displays the webpage with the embedded Google Map. */
    private final WebView _webView = new WebView();
    
    /** Manipulates the JavaScript code in the WebView. */
    private final WebEngine _webEngine = _webView.getEngine();
    
    /** Allows JavaScript code to call Java functions. */
    private final JavaBridge _bridge = new JavaBridge();
    
    /** Pin could not be loaded befor the webView */
    private final HashMap<Integer, MarkerModel> _cacheMarkerModel = 
            new HashMap<Integer, MarkerModel>();
    

    public MapView(MapController mapController) {     
        super();
        _mapController = mapController;
        
        bindJavaBridge();
        
        // Load Webpage
        final URL urlGoogleMaps = getClass().getResource("/googleMap/mapPage.html");
        _webEngine.load(urlGoogleMaps.toExternalForm());

        // Add the webview to ourselves
        getChildren().add(_webView);
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
    
    public void createPin(MarkerModel marker, int pinId) {
        if(!_bridge.isJavaScriptMapLoad()) { // If JavaScript map is not loaded
            _cacheMarkerModel.put(pinId, marker);
            
        } else { // If JavaScript map is loaded
            CoordinateSendableModel markerCoordinates = marker.getCoordinate();
            double latitude = markerCoordinates.getLatitude();
            double longitude = markerCoordinates.getLongitude();
            String pokemonName = marker.getPokemonName();
            String imageName = marker.getImagePath();
            
            JSObject window = (JSObject) _webEngine.executeScript("window");
            window.call("addMarker", latitude, longitude, pokemonName, imageName, pinId);
        }
    }
    
    private void loadAllCachedMarkerModel() {
        for(int pinId : _cacheMarkerModel.keySet()) {
            MarkerModel marker = _cacheMarkerModel.get(pinId);
            createPin(marker, pinId);
        }
        _cacheMarkerModel.clear();
    }

    /** Allows JavaScript code to call Java functions. */
    public class JavaBridge {
        
        private boolean isLoaded = false;
        
        /**
         * Displays a message on the Java console.
         * @param message is the log content.
         */
        public void log(String message) {
            System.out.println("JS Console: " + message);
        }
        
        /**
         * Indicate that the javascript is loaded
         */
        public void javaScriptMapIsLoad() {
            isLoaded = true;
            loadAllCachedMarkerModel();
        }
        
        public boolean isJavaScriptMapLoad() {
            return isLoaded;
        }
        
        public void onMarkerClusterClick(JSObject markersIdArray) {
            int arraySize = (int) markersIdArray.getMember("length");
            ArrayList<Integer> markersIds = new ArrayList<Integer>();
            for (int index = 0; index < arraySize; index++) {
                markersIds.add((Integer) markersIdArray.getSlot(index));
            }
            _mapController.clusterClicked(markersIds);
        }
        
        /**
         * Called when the map receives a click event.
         * Calls createMarker function on MapView.
         * @param coordinates of the event
         */
        public void onMapRightClick(JSObject coordinates) {
            double latitude = (double) coordinates.call("lat");
            double longitude = (double) coordinates.call("lng");
            _mapController.askForCreateMarker(latitude, longitude);
        }
        
        public void onMarkerLeftClick(int markerId) {
            _mapController.displayPinPopUp(markerId);
        }
    }
}
