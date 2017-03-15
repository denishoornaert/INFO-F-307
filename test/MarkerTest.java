/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import be.ac.ulb.infof307.g01.CoordinateModel;
import be.ac.ulb.infof307.g01.MarkerModel;
import be.ac.ulb.infof307.g01.PokemonModel;
import be.ac.ulb.infof307.g01.PokemonTypeModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author remy
 */
public class MarkerTest {
    
    public MarkerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test_getPathImage() {
        PokemonModel testPokemon = new PokemonModel("arceus", PokemonTypeModel.FIRE);
        CoordinateModel testCoordinate = new CoordinateModel(69, 42);
        
        MarkerModel testMarker = new MarkerModel(testPokemon, testCoordinate, false); // For test we must don't use the picture
        assertEquals(testMarker.getPathImage(), testPokemon.getPathImage());        
    }
    
}
