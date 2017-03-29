/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.CoordinateModel;
import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.MarkerModel;
import be.ac.ulb.infof307.g01.PokemonModel;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author robin
 */
public class MarkerDatabaseModelTest extends TestCase {
    
    private static MarkerDatabaseModel _database;
    
    public MarkerDatabaseModelTest(String testName) {
        super(testName);
    }
    
    
    @BeforeClass
    public static void setUpClass() throws SQLException, FileNotFoundException {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
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
            new PokemonModel("Mystherbe", "");
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
        final int xCoordinate = 150;
        final int yCoordinate = 25;
        String pokemonName = "Pikachu";
        MarkerModel marker = MarkerDatabaseModelTest.makeMarker(
                pokemonName, xCoordinate, yCoordinate, 0, false);
        final int initialAmountOfMarkers = _database.getAllMarkers().size();
        _database.insertMarker(marker);
        assertEquals(initialAmountOfMarkers+1, _database.getAllMarkers().size());
    }

    /**
     * Test of getAllMarkers method, of class MarkerDatabaseModel.
     */
    @Test
    public void test_getAllMarkers() {
        final int xCoordinate = 250;
        final int yCoordinate = 500;
        String pokemonName = "Mystherbe";
        MarkerModel marker = MarkerDatabaseModelTest.makeMarker(
                pokemonName, xCoordinate, yCoordinate, 0, false);
        
        _database.insertMarker(marker);
        ArrayList<MarkerModel> markers = _database.getAllMarkers();
        MarkerModel loadedMarker = markers.get(markers.size()-1);
        assertTrue(marker.equals(loadedMarker));
    }
    
    /**
     * Return a marker object
     * @param pokemonName the name of the pokemon to set in the marker
     * @param x the x coordinate of the marker
     * @param y the y coordinate of the marker
     * @param timeStamp the timestamp associated with the marker
     * @param changeTimestamp true if \param timeStamp must be set to marker and
     * false if timestamp doesn't need to be changed
     * @return a new instance of MarkerModel
     */
    private static MarkerModel makeMarker(String pokemonName, int x, int y,
            int timeStamp, boolean changeTimestamp) {
        PokemonModel pokemon = PokemonModel.getPokemonByName(pokemonName);
        if(pokemon == null) {
            System.out.println("Pokemon name: " + pokemonName);
        }
        
        CoordinateModel coordinate = new CoordinateModel(x, y);
        MarkerModel marker = new MarkerModel(pokemon, coordinate);
        if(changeTimestamp)
            marker.setTimestamp(new Timestamp(timeStamp));
        return marker;
    }
    
}
