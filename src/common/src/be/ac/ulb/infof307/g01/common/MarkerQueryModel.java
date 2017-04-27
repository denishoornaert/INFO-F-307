package be.ac.ulb.infof307.g01.common;

import java.util.ArrayList;

/** Interface to the database related to Marker manipulation. */
public interface MarkerQueryModel {
    
    public void insertMarker(MarkerSendableModel marker);
    public ArrayList<MarkerSendableModel> getAllMarkers();
    public void updateMarkerReputation(MarkerSendableModel marker);
}
