package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.controller.app.ServerQueryController;
import be.ac.ulb.infof307.g01.client.controller.app.UserController;
import be.ac.ulb.infof307.g01.client.model.map.MarkerModel;
import be.ac.ulb.infof307.g01.client.view.map.MapView;
import be.ac.ulb.infof307.g01.common.controller.MarkerQueryController;
import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import java.util.ArrayList;

/** 
 * Creates and controls the map view. 
 * This class is responsible for creating markers at startup and handling
 * map-related events. 
 * It holds an instance of MarkerController, and passes it messages related
 * to marker creation and edition.
 */
public class MapController {
    
    private final MarkerController _markerController;
    private final MapView _mapView;
    private final MarkerQueryController _query;
    
    public MapController() {
        _query = (MarkerQueryController) ServerQueryController.getInstance();
        _mapView = new MapView(this);
        _markerController = new MarkerController(_mapView);
        initMarkers(); //display the existing markers from the db
    }
    
    /**
     * Requests the list of markers from the marker controller and displays them.
     */
    private void initMarkers() {
        for(MarkerSendableModel markerSendableModel : _query.getAllMarkers()) {
            getMarkerController().displayAndAddMarker(new MarkerModel(markerSendableModel));
        }
    }
    
    public MapView getMapView() {
        return _mapView;
    }

    /**
     * Creates a marker creation popup and its controller.
     * If a blocking PopUp already exists, does nothing.
     * @param latitude the latitude at which the marker has to be created
     * @param longitude the longitude at which the marker has to be created
     */
    public void mapClicked(double latitude, double longitude) {
        try {
            new MarkerCreationPopUpController(getMarkerController(), latitude, longitude);
        } catch (InstantiationException ex) {
            // This may occur if the user try to open another popup and if there
            // is already one openened. We just refuse politely (silently) to
            // open this one
        }
    }
    
    /**
     * Creates a marker edition or information popup and its controller.
     * If the marker was created by the current user, an edition popup appears.
     * Otherwise an information popup is used instead.
     * If a blocking PopUp already exists, does nothing.
     * @param markerId the ID of the marker to edit or display
     */
    public void markerClicked(int markerId) {
        MarkerModel marker = getMarkerController().getMarkerModelFromId(markerId);
        try {
            if (marker.getUsername().equals(UserController.getInstance().getUsername())) {
                new MarkerUpdatePopUpController(getMarkerController(), markerId);
            } else {
                new MarkerDetailsPopUpController(marker);
            }
        } catch (InstantiationException ex) {
            // This may occur if the user try to open another popup and if there
            // is already one openened. We just refuse politely (silently) to
            // open this one
        }
    }
    
    /**
     * Created a cluster information popup.
     * @param markersIds the IDs of the markers contained in the cluster
     */
    public void clusterClicked(ArrayList<Integer> markersIds) {
        try {
            new ClusterPopUpController(getMarkerController(), markersIds);
        } catch (InstantiationException ex) {
            // This may occur if the user try to open another popup and if there
            // is already one openened. We just refuse politely (silently) to
            // open this one
        }
    }
    
    public MapView getView() {
        return _mapView;
    }
    
    public MarkerController getMarkerController() {
        return _markerController;
    }
}