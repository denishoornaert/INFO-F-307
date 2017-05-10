package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.controller.app.ServerQueryController;
import be.ac.ulb.infof307.g01.client.controller.app.UserController;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.view.map.MapView;
import be.ac.ulb.infof307.g01.common.controller.MarkerQueryController;
import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
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
            // This may occur if the user try to open another popup and if there
            // is already one openened. We just refuse politely (silently) to
            // open this one
        }
    }
    
    public void displayPinPopUp(int markerId) {
        MarkerModel marker = _markerController.getMarkerModelFromId(markerId);
        try {
            if (marker.getUsername().equals(UserController.getInstance().getUsername())) {
                new UpdateMarkerPopUpController(_markerController, markerId);
            } else {
                new DetailsMarkerPopUpController(marker);
            }
        } catch (InstantiationException ex) {
            // This may occur if the user try to open another popup and if there
            // is already one openened. We just refuse politely (silently) to
            // open this one
        }
    }
    
    public void clusterClicked(ArrayList<Integer> markersIds) {
        try {
            new ClusterPopUpController(_markerController, markersIds);
        } catch (InstantiationException ex) {
            // This may occur if the user try to open another popup and if there
            // is already one openened. We just refuse politely (silently) to
            // open this one
        }
    }
    
    public MapView getView() {
        return _mapView;
    }
    
}