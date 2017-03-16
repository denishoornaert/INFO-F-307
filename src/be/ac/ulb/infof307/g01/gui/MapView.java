/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.Coordinate;
import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.MapController;
import be.ac.ulb.infof307.g01.Marker;
import java.net.URL;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 *
 * @author hoornaert, StanIsAdmin, TheoVerhelst
 */
public class MapView extends Pane {
    
    private MapController _mapController;
    
    /** Displays the webpage with the embedded Google Map. */
    private WebView webView = new WebView();
    
    /** Manipulates the JavaScript code in the WebView. */
    private WebEngine webEngine = webView.getEngine();
    
    /** Allows JavaScript code to call Java functions. */
    private JavaBridge bridge = new JavaBridge();

    public MapView(MapController mapController) {     
        super();
        _mapController = mapController;
        
        bindJavaBridge();
        
        // Load Webpage
        final URL urlGoogleMaps = getClass().getResource("/googleMap/mapPage.html");
        webEngine.load(urlGoogleMaps.toExternalForm());

        // Add the webview to ourselves
        getChildren().add(webView);
        // Add ourselves to the main layout
        Main.getStackPane().getChildren().add(this);
    }
    
    /** Sets bridge member so that JavaScript event handlers can call Java functions. */
    private void bindJavaBridge() {
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    System.out.println("READY");
                    JSObject jsobj = (JSObject) webEngine.executeScript("window");
                    jsobj.setMember("bridge", bridge);
                }
            }
        });

        
    }
    
    private void createMarker(double latitude, double longitude) {
        _mapController.askForCreateMarker(latitude, longitude);
    }
    
    public void createPin(Marker marker) {
        Coordinate coordinates = marker.getCoordinate();
        String pokemonName = marker.getPokemonName();
        JSObject window = (JSObject) webEngine.executeScript("window");
        window.call("addMarker", coordinates.getX(), coordinates.getY(), pokemonName);
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
        public void onMapClick(JSObject coordinates) {
            double latitude = (double) coordinates.call("lat");
            double longitude = (double) coordinates.call("lng");
            createMarker(latitude, longitude);
        }
    }
}
