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
    
    private NewMarkerPopUp _newMarkerPopUp;
    private Coordinate _newMarkerCoordinate;
    
    
    public MapController() {
        _imagePath = new File("assets/Map.jpg").toURI().toString();
        _markers = new ArrayList<>();
        _mapView = new MapView(this);
        
        _newMarkerPopUp = null;
        _newMarkerCoordinate = null;
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
        if(_newMarkerPopUp == null) {
            _newMarkerPopUp = new NewMarkerPopUp(this);
            
            _newMarkerCoordinate = new Coordinate((int) coordinateX, (int) coordinateY);
        }
    }
    
    public void cancelPopUpCreateMarker() {
        _newMarkerPopUp.close();
        _newMarkerPopUp = null;
    }
    
    public void endPopUpCreateMarker(String pokemonName, Timestamp dateView) {
        _newMarkerPopUp.close();
        _newMarkerPopUp = null;
        
        // TODO create real marker
        Pokemon pokemon = new Pokemon(pokemonName, PokemonType.DARK);
        new Marker(pokemon, _newMarkerCoordinate);
        _newMarkerCoordinate = null;
    }
    
}
