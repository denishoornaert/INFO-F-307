/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import be.ac.ulb.infof307.g01.Coordinate;
import be.ac.ulb.infof307.g01.Marker;
import be.ac.ulb.infof307.g01.Pokemon;
import be.ac.ulb.infof307.g01.PokemonType;
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
        Pokemon testPokemon = new Pokemon("arceus", PokemonType.FIRE);
        Coordinate testCoordinate = new Coordinate(69, 42);
        
        Marker testMarker = new Marker(testPokemon, testCoordinate);
        assertEquals(testMarker.getPathImage(), testPokemon.getPathImage());        
    }
    
}
