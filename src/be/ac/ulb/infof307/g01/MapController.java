/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.MapView;
import be.ac.ulb.infof307.g01.gui.NewMarkerPopUp;
import be.ac.ulb.infof307.g01.gui.Pin;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.sql.Timestamp;

/**
 *
 * @author Nathan
 */
public class MapController {
    
    private String _imagePath;
    private List<MarkerController> _markers;
    private MapView _mapView;
    
    /** Instance of the current popup. Is equal to null if no popup is open,
     *  and is set to null when the popup is closed.
     */
    private NewMarkerPopUpController _newMarkerPopUpController;    
    
    public MapController() {
        _imagePath = new File("assets/Brussels_map.jpg").toURI().toString();
        _markers = new ArrayList<>();
        _mapView = new MapView(this);
    }
    
    public String getImagePath() {
        return _imagePath;
    }
    
    public MapView getMapView() {
        return _mapView;
    }
    

    public void addMarkerController(MarkerController newMarker) {
        _markers.add(newMarker);
    }

    public void askForCreateMarker(double latitude, double longitude) {
        _newMarkerPopUpController = new NewMarkerPopUpController(this);
        _newMarkerPopUpController.askForCreateMarker(longitude, longitude);
    }
    
}
