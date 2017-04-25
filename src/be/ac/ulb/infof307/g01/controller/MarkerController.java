package be.ac.ulb.infof307.g01.controller;

import java.util.HashMap;
import java.util.Map;

import be.ac.ulb.infof307.g01.model.CoordinateModel;
import be.ac.ulb.infof307.g01.model.MarkerModel;
import be.ac.ulb.infof307.g01.model.PokemonModel;
import be.ac.ulb.infof307.g01.view.MapView;
import java.sql.Timestamp;

/** Controller of the markers. This class should be instanciated only once, as
 * it manages all markers.
 */
public class MarkerController {
    Map<Integer, MarkerModel> _markerMap = new HashMap<>();
    MapView _mapView;
    Integer _newMarkerId = 0;
    
    public MarkerController(MapView mapView) {
        _mapView = mapView;
    }
    
    public void createMarker(PokemonModel pokemon, CoordinateModel newMarkerCoordinates, int lifePoint, int attack, int defense, Timestamp date) {
    	String username = SigninPopUpController.getInstance().getUsername();
        MarkerModel marker = new MarkerModel(pokemon, newMarkerCoordinates, username, lifePoint, attack, defense, date);
        displayMarker(marker);
    }
    
    public void displayMarker(MarkerModel marker) {
        _markerMap.put(_newMarkerId, marker);
        _mapView.createPin(marker, _newMarkerId);
        
        _newMarkerId += 1;
    }
    
    public MarkerModel getMarkerModelFromId(int markerId) {
        return _markerMap.get(markerId);
    }
}