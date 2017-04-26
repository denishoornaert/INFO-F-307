package be.ac.ulb.infof307.g01.common;

import java.util.ArrayList;

/** Interface to the database related to Marker manipulation. */
public interface MarkerQueryModel {
    
    public void insertMarker(MarkerModel marker);
    public ArrayList<MarkerModel> getAllMarkers();
    public void updateMarkerReputation(MarkerModel marker);
}
