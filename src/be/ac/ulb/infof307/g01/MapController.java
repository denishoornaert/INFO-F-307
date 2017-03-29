package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.db.DatabaseModel;
import be.ac.ulb.infof307.g01.db.MarkerDatabaseModel;
import be.ac.ulb.infof307.g01.gui.MapView;

/**
 * TODO: add description
 */
public class MapController {
    private MarkerController _markerController;
    private MapView _mapView;
    private MarkerDatabaseModel _database;
    
    /** Instance of the current popup. Is equal to null if no popup is open,
     *  and is set to null when the popup is closed.
     */
    private NewMarkerPopUpController _newMarkerPopUpController;    
    private PinPopUpController _pinPopUpController;
    
    public MapController() {
        _database = (MarkerDatabaseModel) DatabaseModel.getDatabase();
        _mapView = new MapView(this);
        _markerController = new MarkerController(_mapView);
        initMarkers(); //display the existing markers from the db
    }
    
    private void initMarkers() {
        for(MarkerModel markerModel : _database.getAllMarkers()) {
            _markerController.displayMarker(markerModel);
        }
    }
    
    public MapView getMapView() {
        return _mapView;
    }

    public void askForCreateMarker(double latitude, double longitude) {
        _newMarkerPopUpController = new NewMarkerPopUpController(_markerController);
        _newMarkerPopUpController.askForCreateMarker(latitude, longitude);
    }
    
    public void displayPinPopUp(int markerId) {
        MarkerModel marker = _markerController.getMarkerModelFromId(markerId);
        _pinPopUpController = new PinPopUpController(marker);
    }
}