package be.ac.ulb.infof307.g01.controller;

import be.ac.ulb.infof307.g01.model.DatabaseModel;
import be.ac.ulb.infof307.g01.model.MarkerDatabaseModel;
import be.ac.ulb.infof307.g01.model.MarkerModel;
import be.ac.ulb.infof307.g01.view.ClusterPopUp;
import be.ac.ulb.infof307.g01.view.MapView;
import java.util.ArrayList;

/** Controller of the map. This class is responsible of creating markers at
 * startup and when the user adds one. For that, it holds an instance of
 * MarkerController, and forwards marker creation messages to the marker
 * controller.
 * It also holds the map view.
 */
public class MapController {
    private final MarkerController _markerController;
    private final MapView _mapView;
    private final MarkerDatabaseModel _database;
    
    /** Instance of the current popup. Is equal to null if no popup is open,
     *  and is set to null when the popup is closed.
     */
    private NewMarkerPopUpController _newMarkerPopUpController;    
    
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
        new PinPopUpController(marker);
    }
    
    public void clusterClicked(ArrayList<Integer> markersIds) {
        new ClusterPopUpController(_markerController, markersIds);
    }
}