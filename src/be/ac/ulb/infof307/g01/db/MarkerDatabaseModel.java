package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.MarkerModel;
import java.util.ArrayList;
import be.ac.ulb.infof307.g01.ReputationVoteModel;

/**
 * TODO: add description
 */
public interface MarkerDatabaseModel {
    
    public void insertMarker(MarkerModel marker);
    public ArrayList<MarkerModel> getAllMarkers();
    public void updateMarkerReputation(MarkerModel marker);
}
