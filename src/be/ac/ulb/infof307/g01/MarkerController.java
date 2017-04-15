package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.MapView;
import java.util.HashMap;
import java.util.Map;

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
    
    public void createMarker(PokemonModel pokemon, CoordinateModel newMarkerCoordinates) {
        MarkerModel marker = new MarkerModel(pokemon, newMarkerCoordinates);
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