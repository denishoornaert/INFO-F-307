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
    private List<Marker> _markers;
    private MapView _mapView;
    
    /** Instance of the current popup. Is equal to null if no popup is open,
     *  and is set to null when the popup is closed.
     */
    private NewMarkerPopUp _newMarkerPopUp;
    
    /** Coordinates associated to the current popup. */
    private Coordinate _newMarkerCoordinate;
    
    
    public MapController() {
        _imagePath = new File("assets/Brussels_map.jpg").toURI().toString();
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
    
    public void askForCreateMarker(double coordinateX, double coordinateY) {
        if(_newMarkerPopUp == null) {
            // Converts from event coordinate (centered in the upper left corner)
            // to marker coordinate (centered in the middle of the image)
            _newMarkerCoordinate = new Coordinate(coordinateX, coordinateY);
            _newMarkerPopUp = new NewMarkerPopUp(this);
        }
    }
    
    public void cancelPopUpCreateMarker() {
        _newMarkerPopUp.close();
        _newMarkerPopUp = null;
    }
    
    public void endPopUpCreateMarker(String pokemonName, Timestamp dateView) {
        _newMarkerPopUp.close();
        _newMarkerPopUp = null;
        
        Pokemon pokemon = new Pokemon(pokemonName, PokemonType.DARK);
        Marker newMarker = new Marker(pokemon, _newMarkerCoordinate);
        _newMarkerCoordinate = null;
        _markers.add(newMarker);
        
        getMapView().createPin(newMarker);
    }
    
}
