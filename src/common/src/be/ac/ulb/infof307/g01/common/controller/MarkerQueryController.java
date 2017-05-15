package be.ac.ulb.infof307.g01.common.controller;

import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
import java.util.ArrayList;

/**
 * Interface for the marker-related, network-oriented queries.
 * A Query Controller defines an interface for client and server calls, 
 * that may or may not have different implementation for each.
 */
public interface MarkerQueryController {
    
    public void insertMarker(MarkerSendableModel marker);
    public void updateMarker(MarkerSendableModel marker);
    public ArrayList<MarkerSendableModel> getAllMarkers();
    public void updateMarkerReputation(ReputationVoteSendableModel marker);
}
