/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.MapView;
import be.ac.ulb.infof307.g01.gui.NewMarkerPopUp;
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
    private List<Marker> _markers;
    private MapView _mapView;
    
    private boolean _newMarkerPopUpOpen;
    
    
    public MapController() {
        _imagePath = new File("assets/Map.jpg").toURI().toString();
        _markers = new ArrayList<>();
        _mapView = new MapView(this);
        
        _newMarkerPopUpOpen = false;
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
    
    
    public void actionWhenPlayerRightClick(double coordinateX, double coordinateY) {
        if(!_newMarkerPopUpOpen) {
            NewMarkerPopUp popUp = new NewMarkerPopUp(this);
            _newMarkerPopUpOpen = true;
        }
    }
    
    public void cancelPopUpCreateMarker() {
        _newMarkerPopUpOpen = false;
    }
    
    public void endPopUpCreateMarker(String pokemonName, Timestamp date, 
            int coordinateX, int coordinateY) {
        _newMarkerPopUpOpen = false;
        
        // TODO create real marker
        Pokemon pokemon = new Pokemon(pokemonName, PokemonType.DARK);
        Coordinate coordinate = new Coordinate(coordinateX, coordinateY);
        new Marker(pokemon, coordinate);
    }
    
}
