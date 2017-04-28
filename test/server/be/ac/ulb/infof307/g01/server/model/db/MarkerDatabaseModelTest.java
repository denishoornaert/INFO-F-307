package test.java.be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.server.model.MarkerModel;
import be.ac.ulb.infof307.g01.server.model.PokemonModel;

import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import org.junit.*;

public class MarkerDatabaseModelTest extends AbstractDatabaseTest {
    private MarkerModel _markerToInsert;
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
        PokemonModel Abomasnow = PokemonModel.getPokemonByName("Abomasnow");
        
        final int id = 0;
        final String username = "bidon";
        final double latitude = 250;
        final double longitude = 500;
        final Timestamp timestamp = new Timestamp(0);
        final String pokemonName = Abomasnow.getName();
        final int upVotes = 0;
        final int downVotes = 0;
        final int lifePoint = 0;
        final int attack = 0;
        final int defense = 0;
        _markerToInsert = new MarkerModel(id, username, pokemonName, latitude, longitude,
                timestamp, upVotes, downVotes,lifePoint,attack,defense);
    }
    
    /**
     * Test that insertMarker increments the count of markers.
     */
    @Test
    public void test_insertMarker_incrementsAmountsOfMarkers() {
        System.out.println(_database);
        final int initialAmountOfMarkers = _database.getAllMarkers().size();
        _database.insertMarker(_markerToInsert);
        assertEquals(initialAmountOfMarkers+1, _database.getAllMarkers().size());
    }

    /**
     * Test that when a marker is inserted, it is the last one returned by
     * getAllMarkers.
     */
    @Test
    public void test_getAllMarkers_returnsLastInserted() {
        _database.insertMarker(_markerToInsert);
        ArrayList<MarkerSendableModel> markers = _database.getAllMarkers();
        MarkerSendableModel loadedMarker = markers.get(markers.size()-1);
        assertTrue(_markerToInsert.equals(loadedMarker));
    }
    
}
