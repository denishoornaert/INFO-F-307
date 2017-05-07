package be.ac.ulb.infof307.g01.common.controller;

import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
import java.util.ArrayList;

/** Interface to the data access point related to Marker manipulation. */
public interface MarkerQueryController {
    
    public void insertMarker(MarkerSendableModel marker);
    public boolean updateMarker(MarkerSendableModel marker);
    public ArrayList<MarkerSendableModel> getAllMarkers();
    public void updateMarkerReputation(ReputationVoteSendableModel marker);
}
