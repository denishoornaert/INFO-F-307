package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.Main;
import be.ac.ulb.infof307.g01.client.controller.app.PopUpController;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;

/**
 * Abstract class that controls any marker popup.
 */
public abstract class AbstractMarkerPopUpController extends PopUpController {
    
    protected MarkerController _markerController;
    protected MarkerModel _marker;
    protected static int DEFAULT_MARKER_ID = 0;
    
    public AbstractMarkerPopUpController(MarkerModel marker) throws InstantiationException {
        super();
        _marker = marker;
    }
    
    public AbstractMarkerPopUpController(MarkerController controller, int markerid) throws InstantiationException {
        _markerController = controller;
        _marker = controller.getMarkerModelFromId(markerid);
    }

    public MarkerModel getMarker() {
        return _marker;
    }

    /**
     * Shares the marker's information and location on twitter.
     */
    public void sendTwitterPost() {
        String link = _marker.getTwitterLink();
        Main.openInBrowser(link);
    }

}
