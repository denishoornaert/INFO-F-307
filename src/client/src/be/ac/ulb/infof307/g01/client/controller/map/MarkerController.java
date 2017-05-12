package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.controller.app.MessagePopUpController;
import be.ac.ulb.infof307.g01.client.controller.app.UserController;
import java.util.HashMap;
import java.util.Map;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.map.MapView;
import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;


/** Controller of the markers. This class should be instanciated only once, as
 * it manages all markers.
 */
public class MarkerController {
    
    private Map<Integer, MarkerModel> _markerMap = new HashMap<>();
    private MapView _mapView;
    private Integer _newMarkerId = 0;
    
    public MarkerController(MapView mapView) {
        _mapView = mapView;
    }
    
    public void createMarker(PokemonModel pokemon, CoordinateSendableModel newMarkerCoordinates, int lifePoint, int attack, int defense, Timestamp date) {
    	String username = UserController.getInstance().getUsername();
        try {
            MarkerModel marker = new MarkerModel(pokemon, newMarkerCoordinates, username, lifePoint, attack, defense, date);
            displayAndAddMarker(marker);
        } catch(InvalidParameterException exception){
            MessagePopUpController.createPopUpOrLog(Level.SEVERE, exception.getMessage());
        }
    }
    
    public void showHideMarker(HashSet<MarkerModel> listMarker) {
        for(Integer id : _markerMap.keySet()) {
            MarkerModel marker = _markerMap.get(id);
            if(listMarker.contains(marker)) {
                _mapView.displayMarker(marker, id);
            } else {
                _mapView.hideMaker(marker, id);
            }
        }
    }
    
    public void displayAndAddMarker(MarkerModel marker) {
        _markerMap.put(_newMarkerId, marker);
        _mapView.createMarker(marker, _newMarkerId);
        
        _newMarkerId++;
    }
    
    public MarkerModel getMarkerModelFromId(int markerId) {
        return _markerMap.get(markerId);
    }

    public void updateMarker(int markerId, PokemonModel pokemon, int lifePoint, int attack, int defense, Timestamp dateView) {
        MarkerModel marker = _markerMap.get(markerId);
        try{
            marker.update(pokemon, lifePoint, attack, defense, dateView);
        } catch(InvalidParameterException exception){
            MessagePopUpController.createPopUpOrLog(Level.SEVERE, exception.getMessage());
        }
    }

    public HashSet<MarkerModel> getAllMarkers() {
        HashSet<MarkerModel> result = new HashSet<>();
        result.addAll(_markerMap.values());
        return result;
    }
}