package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.db.DatabaseModel;
import be.ac.ulb.infof307.g01.db.MarkerDatabaseModel;
import be.ac.ulb.infof307.g01.gui.MapView;

/** Controller of the map. This class is responsible of creating markers at
 * startup and when the user adds one. For that, it holds an instance of
 * MarkerController, and forwards marker creation messages to the marker
 * controller.
 * It also holds the map view.
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