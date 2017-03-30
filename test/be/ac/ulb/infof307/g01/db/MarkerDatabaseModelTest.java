package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.CoordinateModel;
import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.MarkerModel;
import be.ac.ulb.infof307.g01.PokemonModel;
import java.sql.Timestamp;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MarkerDatabaseModelTest extends TestCase {
    
    private static MarkerDatabaseModel _database;
    
    @Before
    protected void setUp() throws Exception {
        super.setUp();
        
        try {
            _database = (MarkerDatabaseModel) new DatabaseModel(Main.getTestDatabasePath());
        } catch(IllegalStateException ex) {
            // ignore
        }
        
        PokemonModel.clearAllPokemon();
        try {
            new PokemonModel("Pikachu", "");
            new PokemonModel("Piplup", "");
        } catch(IllegalStateException ex) {
            // ignore
        }
    }
    
    @After
    protected void tearDown() throws Exception {
        super.tearDown();
        PokemonModel.clearAllPokemon();
    }
    

    /**
     * Test of insertMarker method, of class MarkerDatabaseModel.
     */
    @Test
    public void test_insertMarker() {
        final int latitude = 150;
        final int longitude = 25;
        String pokemonName = "Pikachu";
        MarkerModel marker = MarkerDatabaseModelTest.makeMarker(
                pokemonName, latitude, longitude, 0, false);
        final int initialAmountOfMarkers = _database.getAllMarkers().size();
        _database.insertMarker(marker);
        assertEquals(initialAmountOfMarkers+1, _database.getAllMarkers().size());
    }

    /**
     * Test of getAllMarkers method, of class MarkerDatabaseModel.
     */
    @Test
    public void test_getAllMarkers() {
        final double latitude = 250;
        final double longitude = 500;
        String pokemonName = "Piplup";
        MarkerModel marker = MarkerDatabaseModelTest.makeMarker(
                pokemonName, latitude, longitude, 0, false);
        
        _database.insertMarker(marker);
        ArrayList<MarkerModel> markers = _database.getAllMarkers();
        MarkerModel loadedMarker = markers.get(markers.size()-1);
        assertTrue(marker.equals(loadedMarker));
    }
    
    /**
     * Returns a marker object
     * @param pokemonName the name of the pokemon to set in the marker
     * @param x the x coordinate of the marker
     * @param y the y coordinate of the marker
     * @param timeStamp the timestamp associated with the marker
     * @param changeTimestamp true if \param timeStamp must be set to marker and
     * false if timestamp doesn't need to be changed
     * @return a new instance of MarkerModel
     */
    private static MarkerModel makeMarker(String pokemonName, double latitude,
            double longitude, int timeStamp, boolean changeTimestamp) {
        PokemonModel pokemon = PokemonModel.getPokemonByName(pokemonName);
        CoordinateModel coordinate = new CoordinateModel(latitude, longitude);
        MarkerModel marker = new MarkerModel(pokemon, coordinate);
        if(changeTimestamp)
            marker.setTimestamp(new Timestamp(timeStamp));
        return marker;
    }
    
}
