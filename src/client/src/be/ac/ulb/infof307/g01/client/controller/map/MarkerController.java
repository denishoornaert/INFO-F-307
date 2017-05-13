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
import java.util.HashSet;
import java.util.logging.Level;


/** 
 * Controls all the markers located on the map.
 * Holds a list of markers that are located on the map, with a controller-specific
 * id used to identify them.
 * Holds a reference to the MapView, which enables it to manipulate markers.
 */
public class MarkerController {
    
    private final Map<Integer, MarkerModel> _markerMap;
    private final MapView _mapView;
    private Integer _newMarkerId;
    
    public MarkerController(MapView mapView) {
        this._markerMap = new HashMap<>();
        this._newMarkerId = 0;
        _mapView = mapView;
    }
    
    /**
     * Creates a new marker with the provided parameters, and displays it.
     * @param pokemon the PokemonModel representing the spotted pokemon
     * @param newMarkerCoordinates the coordinates of the spotted pokemon
     * @param lifePoint the life points of the spotted pokemon
     * @param attack the attack of the spotted pokemon
     * @param defense the defense of the spotted pokemon
     * @param date the date at which the pokemon was spotted
     */
    public void createMarker(PokemonModel pokemon, CoordinateSendableModel newMarkerCoordinates, 
            int lifePoint, int attack, int defense, Timestamp date) {
    	String username = UserController.getInstance().getUsername();
        try {
            MarkerModel marker = new MarkerModel(pokemon, newMarkerCoordinates, username, 
                    lifePoint, attack, defense, date);
            displayAndAddMarker(marker);
        } catch(InvalidParameterException exception){
            MessagePopUpController.createPopUpOrLog(Level.SEVERE, exception.getMessage());
        }
    }
    
    /**
     * Displays a list of markers and hides the remaining ones.
     * @param listMarker the list of markers to display.
     */
    public void displaySelectMarkers(HashSet<MarkerModel> listMarker) {
        for(Integer id : _markerMap.keySet()) {
            MarkerModel marker = _markerMap.get(id);
            if(listMarker.contains(marker)) {
                _mapView.displayMarker(id);
            } else {
                _mapView.hideMaker(id);
            }
        }
    }
    
    /**
     * Adds a marker to the list of controlled markers and displays it.
     * @param marker the marker to add
     */
    public void displayAndAddMarker(MarkerModel marker) {
        _markerMap.put(_newMarkerId, marker);
        _mapView.createMarker(marker, _newMarkerId);
        
        _newMarkerId++;
    }
    
    /**
     * Gets the markers from its controller-specific id.
     * @param markerId the marker Id
     * @return the marker whose id corresponds to the provided id
     */
    public MarkerModel getMarkerModelFromId(int markerId) {
        return _markerMap.get(markerId);
    }

    /**
     * Updates a marker's information.
     * The marker is identified by a controller-specific id.
     * @param markerId the marker Id
     * @param pokemon the PokemonModel representing the spotted pokemon
     * @param lifePoint the life points of the spotted pokemon
     * @param attack the attack of the spotted pokemon
     * @param defense the defense of the spotted pokemon
     * @param dateView the timestamp at which the pokemon was spotted
     */
    public void updateMarker(int markerId, PokemonModel pokemon, int lifePoint, 
            int attack, int defense, Timestamp dateView) {
        MarkerModel marker = _markerMap.get(markerId);
        try{
            marker.update(pokemon, lifePoint, attack, defense, dateView);
        } catch(InvalidParameterException exception){
            MessagePopUpController.createPopUpOrLog(Level.SEVERE, exception.getMessage());
        }
    }

    /**
     * Returns the list of markers located on the map.
     * @return a HashSet containing every MarkerModel on the map
     */
    public HashSet<MarkerModel> getAllMarkers() {
        HashSet<MarkerModel> result = new HashSet<>();
        result.addAll(_markerMap.values());
        return result;
    }
}