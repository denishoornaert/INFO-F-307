package be.ac.ulb.infof307.g01.common.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/** Interface to the data access point related to Marker manipulation. */
public interface MarkerQueryModel {
    
    public void insertMarker(MarkerSendableModel marker) throws InvalidParameterException;
    public boolean updateMarker(MarkerSendableModel marker);
    public ArrayList<MarkerSendableModel> getAllMarkers();
    public void updateMarkerReputation(MarkerSendableModel marker);
}
