package be.ac.ulb.infof307.g01.client.view.map;

import java.net.URL;
import java.util.HashMap;

import be.ac.ulb.infof307.g01.client.controller.map.MapController;
import be.ac.ulb.infof307.g01.client.model.app.ClientConfiguration;
import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.client.model.map.MarkerModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/** 
 * View of the map.
 * This view is based on Google maps: it embeds a WebView (an internet browser
 * widget) and renders a page that embeds Google Maps access.
 * The dynamic interaction with Java, such as marker creation or lookup, is
 * done in Javascript.
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
    

    public MapView(final MapController mapController) {     
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
            public void changed(final ObservableValue ov,
                    final Worker.State oldState, final Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    final JSObject jsobj = (JSObject) _webEngine.executeScript("window");
                    jsobj.setMember("bridge", _bridge);
                }
            }
        });
    }
    
    public void createMarker(final MarkerModel marker, final int pinId) {
        if(!_bridge.isJavaScriptMapLoad()) { // If JavaScript map is not loaded
            _cacheMarkerModel.put(pinId, marker);
            
        } else { // If JavaScript map is loaded
            final CoordinateSendableModel markerCoordinates = marker.getCoordinate();
            final double latitude = markerCoordinates.getLatitude();
            final double longitude = markerCoordinates.getLongitude();
            final String pokemonName = marker.getPokemonName();
            String imagePath = marker.getImagePath();
            
            final JSObject window = (JSObject) _webEngine.executeScript("window");
            imagePath = ClientConfiguration.addJarPrefix(imagePath);
            window.call("addMarker", latitude, longitude, pokemonName, imagePath, pinId);
        }
    }
    
    private void loadAllCachedMarkerModel() {
        for(final int pinId : _cacheMarkerModel.keySet()) {
            final MarkerModel marker = _cacheMarkerModel.get(pinId);
            createMarker(marker, pinId);
        }
        _cacheMarkerModel.clear();
    }

    public void displayMarker(final Integer id) {
        if(_bridge.isJavaScriptMapLoad()) { // If JavaScript map is not loaded
            final JSObject window = (JSObject) _webEngine.executeScript("window");
            window.call("showMarker", id);
        }
    }

    public void hideMaker(final Integer id) {
        if(!_bridge.isJavaScriptMapLoad()) { // If JavaScript map is not loaded
            Logger.getLogger(getClass().getName()).log(Level.WARNING, 
                "Could not hide marker: {0}", id);
        } else {
            final JSObject window = (JSObject) _webEngine.executeScript("window");
            window.call("hideMarker", id);
        }
        
    }

    /** Allows JavaScript code to call Java functions. */
    public class JavaBridge {
        
        private boolean isLoaded = false;
        
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
        
        public void onMarkerClusterClick(final JSObject markersIdArray) {
            final int arraySize = (int) markersIdArray.getMember("length");
            final ArrayList<Integer> markersIds = new ArrayList<Integer>();
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
        public void onMapRightClick(final JSObject coordinates) {
            final double latitude = (double) coordinates.call("lat");
            final double longitude = (double) coordinates.call("lng");
            _mapController.mapClicked(latitude, longitude);
        }
        
        public void onMarkerLeftClick(final int markerId) {
            _mapController.markerClicked(markerId);
        }
        
        public void log(final String message) {
            System.out.println("Message JS: " + message);
        }
    }
}
