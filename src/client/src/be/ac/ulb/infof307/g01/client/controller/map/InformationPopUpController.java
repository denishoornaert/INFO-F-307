package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.controller.map.MarkerController;
import be.ac.ulb.infof307.g01.client.Main;
import be.ac.ulb.infof307.g01.client.controller.app.PopUpController;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;

/**
 *
 * @author Groupe01
 */
public class InformationPopUpController extends PopUpController {
    
    protected MarkerController _markerController;
    protected MarkerModel _marker;
    protected static int DEFAULT_MARKER_ID = 0;
    
    public InformationPopUpController(MarkerModel marker) throws InstantiationException {
        super();
        _marker = marker;
    }
    
    public InformationPopUpController(MarkerController controller, int markerid) throws InstantiationException {
        _markerController = controller;
        _marker = controller.getMarkerModelFromId(markerid);
    }

    public MarkerModel getMarker() {
        return _marker;
    }

    public void sendTwitterPost() {
        String link = _marker.getTwitterLink();
        Main.openInBrowser(link);
    }

}
