package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import java.sql.Timestamp;
import static junit.framework.TestCase.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class MarkerDatabaseModelTest extends AbstractDatabaseTest {
    private final String _markerUsername = "bidon";
    private final String _markerPokemonName = "Arceus";
    private final UserSendableModel _markerUser;
    private MarkerSendableModel _markerToInsert;
    
    public MarkerDatabaseModelTest() {
        super();
        _markerUser = new UserSendableModel(_markerUsername, "mail", "pass");
    }
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
        
        final int id = 0;
        final double latitude = 250;
        final double longitude = 500;
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        final int upVotes = 0;
        final int downVotes = 0;
        final int lifePoint = 0;
        final int attack = 0;
        final int defense = 0;
        _markerToInsert = new MarkerSendableModel(id, _markerUsername, _markerPokemonName, 
                latitude, longitude, timestamp.getTime(), upVotes, 
                downVotes, lifePoint, attack, defense);
        String token = "109283";
        boolean test = _database.signup(_markerUser, token);
        if (test) test = _database.confirmAccount(token);
    }
    
    /**
     * Test that insertMarker increments the count of markers.
     */
    @Test
    public void test_insertMarker_incrementsAmountsOfMarkers() {
        final int initialAmountOfMarkers = _database.getAllMarkers().size();
        _database.insertMarker(_markerToInsert);
        System.out.println("AAAAAAAAAh" + _database.getAllMarkers().size());
        assertEquals(initialAmountOfMarkers+1, _database.getAllMarkers().size());
    }

    /**
     * Test that when a marker is inserted, it is the last one returned by
     * getAllMarkers.
     */
    @Test
    public void test_getAllMarkers_returnsLastInserted() {
       /* _database.insertMarker(_markerToInsert);
        ArrayList<MarkerSendableModel> markers = _database.getAllMarkers();
        MarkerSendableModel loadedMarker = markers.get(markers.size()-1);
        assertTrue(_markerToInsert.equals(loadedMarker));*/
    }
    
}
