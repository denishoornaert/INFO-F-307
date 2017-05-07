package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.view.MapView;
import java.util.ArrayList;
import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import be.ac.ulb.infof307.g01.common.controller.MarkerQueryController;

/** Controller of the map. This class is responsible of creating markers at
 * startup and when the user adds one. For that, it holds an instance of
 * MarkerController, and forwards marker creation messages to the marker
 * controller.
 * It also holds the map view.
 */
public class MapController {
    private final MarkerController _markerController;
    private final MapView _mapView;
    private final MarkerQueryController _query;
    
    /** Instance of the current popup. Is equal to null if no popup is open,
     *  and is set to null when the popup is closed.
     */
    
    public MapController() {
        _query = (MarkerQueryController) ServerQueryController.getInstance();
        _mapView = new MapView(this);
        _markerController = new MarkerController(_mapView);
        initMarkers(); //display the existing markers from the db
    }
    
    private void initMarkers() {
        for(MarkerSendableModel markerSendableModel : _query.getAllMarkers()) {
            _markerController.displayMarker(new MarkerModel(markerSendableModel));
        }
    }
    
    public MapView getMapView() {
        return _mapView;
    }

    public void askForCreateMarker(double latitude, double longitude) {
        try {
            new NewMarkerPopUpController(_markerController, latitude, longitude);
        } catch (InstantiationException ex) {
            Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displayPinPopUp(int markerId) {
        MarkerModel marker = _markerController.getMarkerModelFromId(markerId);
        try {
            if (marker.getUsername().equals(UserController.getInstance().getUsername())) {
                new UpdateMarkerPopUpController(_markerController, markerId);
            }
            else {
                new PinPopUpController(marker);
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clusterClicked(ArrayList<Integer> markersIds) {
        try {
            new ClusterPopUpController(_markerController, markersIds);
        } catch (InstantiationException ex) {
            Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MapView getView() {
        return _mapView;
    }
    
}