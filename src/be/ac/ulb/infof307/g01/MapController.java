/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.MapView;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

/**
 *
 * @author Nathan
 */
public class MapController {
    
    private String _imagePath;
    private List<Marker> _markers;
    private MapView _mapView;
    
    
    public MapController() {
        _imagePath = new File("assets/Map.jpg").toURI().toString();
        _markers = new ArrayList<>();
        _mapView = new MapView(this);
    }
    
    public String getImagePath() {
        return _imagePath;
    } 
    
    public void createMarker() {
        Marker newMarker = new Marker();
        _markers.add(newMarker);
    }
    
    
    public MapView getMapView() {
        return _mapView;
    }
    
}
