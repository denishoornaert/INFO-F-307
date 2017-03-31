package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.MarkerModel;
import be.ac.ulb.infof307.g01.PokemonModel;
import java.sql.Timestamp;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.*;

public class MarkerDatabaseModelTest extends TestCase {
    
    private static MarkerDatabaseModel _database;
    private MarkerModel _markerToInsert;
    
    @Before
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        try {
            _database = (MarkerDatabaseModel) new DatabaseModel(Main.getTestDatabasePath());
        } catch(IllegalStateException exception) {
            _database = (MarkerDatabaseModel) DatabaseModel.getDatabase();
        }
        
        // Reset the PokemonModel cache and put Pikachu in it
        PokemonModel.clearAllPokemon();
        new PokemonModel("Pikachu", "");
        
        final int id = 0;
        final double latitude = 250;
        final double longitude = 500;
        final String pokemonName = "Pikachu";
        final Timestamp timestamp = new Timestamp(0);
        final int upVotes = 0;
        final int downVotes = 0;
        _markerToInsert = new MarkerModel(id, pokemonName, latitude, longitude,
                timestamp, upVotes, downVotes);
    }
    
    @After
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        PokemonModel.clearAllPokemon();
    }
    

    /**
     * Test that insertMarker increments the count of markers.
     */
    @Test
    public void test_insertMarker_incrementsAmountsOfMarkers() {
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
        ArrayList<MarkerModel> markers = _database.getAllMarkers();
        MarkerModel loadedMarker = markers.get(markers.size()-1);
        assertTrue(_markerToInsert.equals(loadedMarker));
    }
    
}
