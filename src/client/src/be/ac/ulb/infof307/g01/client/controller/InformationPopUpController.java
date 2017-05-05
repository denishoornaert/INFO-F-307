/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.Main;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;

/**
 *
 * @author Groupe01
 */
public class InformationPopUpController {
    
    protected MarkerController _markerController;
    protected MarkerModel _marker;
    protected static int DEFAULT_MARKER_ID = 0;
    
    public InformationPopUpController(MarkerModel marker) {
        _marker = marker;
    }
    
    public InformationPopUpController(MarkerController controller, int markerid) {
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
