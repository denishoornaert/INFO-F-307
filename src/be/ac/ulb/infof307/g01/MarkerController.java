package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.MapView;
import java.util.Map;

/**
 * TODO: add description
 */
public class MarkerController {
    Map<Integer, MarkerModel> _markerMap;
    MapView _mapView;
    Integer _newMarkerId = 0;
    
    public MarkerController(MapView mapView) {
        _mapView = mapView;
    }
    
    public void createMarker(PokemonModel pokemon, CoordinateModel newMarkerCoordinates) {
        _markerMap.put(_newMarkerId, new MarkerModel(pokemon, newMarkerCoordinates));
        double latitude = newMarkerCoordinates.getLatitude();
        double longitude = newMarkerCoordinates.getLongitude();
        _mapView.createPin(latitude, longitude, pokemon.getName(), _newMarkerId);
        _newMarkerId += 1;
    }
    
    public MarkerModel getMarkerModelFromId(int markerId) {
        return _markerMap.get(markerId);
    }
}
