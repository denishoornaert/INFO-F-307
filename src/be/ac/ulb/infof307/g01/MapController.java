package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.db.DatabaseModel;
import be.ac.ulb.infof307.g01.db.MarkerDatabaseModel;
import be.ac.ulb.infof307.g01.gui.MapView;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: add description
 */
public class MapController {
    private List<MarkerController> _markers;
    private MapView _mapView;
    private MarkerDatabaseModel _database;
    
    /** Instance of the current popup. Is equal to null if no popup is open,
     *  and is set to null when the popup is closed.
     */
    private NewMarkerPopUpController _newMarkerPopUpController;    
    
    public MapController() {
        _database = (MarkerDatabaseModel) DatabaseModel.getDatabase();
        _markers = new ArrayList<>();
        for(MarkerModel markerModel : _database.getAllMarkers()) {
            addMarkerController(new MarkerController(markerModel));
        }
        _mapView = new MapView(this);
    }
    
    public MapView getMapView() {
        return _mapView;
    }
    
    public void addMarkerController(MarkerController newMarker) {
        _markers.add(newMarker);
        _mapView.createPin(newMarker);
    }

    public void askForCreateMarker(double latitude, double longitude) {
        _newMarkerPopUpController = new NewMarkerPopUpController(this);
        _newMarkerPopUpController.askForCreateMarker(latitude, longitude);
    }
    
}