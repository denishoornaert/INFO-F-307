package be.ac.ulb.infof307.g01.client.model.db;

import be.ac.ulb.infof307.g01.client.Main;
import be.ac.ulb.infof307.g01.client.model.DatabaseModel;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.model.PokemonTypeModel;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import org.junit.*;
import be.ac.ulb.infof307.g01.common.model.MarkerQueryModel;

public class MarkerDatabaseModelTest {
    
    private static MarkerQueryModel _database;
    private MarkerModel _markerToInsert;
    
    @BeforeClass
    public static void setUpClass() {
        try {
            _database = new DatabaseModel(Main.getTestDatabasePath());
        } catch (SQLException | FileNotFoundException ex) {
            System.err.println("SQL database not init");
        }
    }
    
    @AfterClass
    public static void teamDownClass() {
        DatabaseModel.closeDatabase();
        PokemonModel.clearAllPokemon();
        PokemonTypeModel.resetAllPokemonType();
    }
    
    @Before
    public void setUp() throws Exception {
        
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
