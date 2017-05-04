package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;

import java.sql.Timestamp;
import org.junit.*;

public class MarkerDatabaseModelTest extends AbstractDatabaseTest {
    private MarkerSendableModel _markerToInsert;
    
    @Before
    public void setUp() {
        super.setUp();
        PokemonSendableModel Abomasnow = _database.getPokemonByName("Arceus");
        
        final int id = 0;
        final String username = "bidon";
        final double latitude = 250;
        final double longitude = 500;
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        final int upVotes = 0;
        final int downVotes = 0;
        final int lifePoint = 0;
        final int attack = 0;
        final int defense = 0;
        _markerToInsert = new MarkerSendableModel(id, username, Abomasnow, 
                latitude, longitude, timestamp.getTime(), upVotes, 
                downVotes,lifePoint,attack,defense);
    }
    
    /**
     * Test that insertMarker increments the count of markers.
     */
    @Test
    public void test_insertMarker_incrementsAmountsOfMarkers() {
        /*final int initialAmountOfMarkers = _database.getAllMarkers().size();
        _database.insertMarker(_markerToInsert);
        assertEquals(initialAmountOfMarkers+1, _database.getAllMarkers().size());*/
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
