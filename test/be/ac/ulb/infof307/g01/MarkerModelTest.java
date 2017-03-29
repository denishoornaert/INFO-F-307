package be.ac.ulb.infof307.g01;

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
public class MarkerModelTest {
    
    public MarkerModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        new PokemonTypeModel("FIRE");
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
        PokemonModel testPokemon = new PokemonModel("arceus","", 
                PokemonTypeModel.getPokemonTypeByTypeName("FIRE"));
        CoordinateModel testCoordinate = new CoordinateModel(69, 42);
        
        // For test we must not use the picture
        MarkerModel testMarker = new MarkerModel(testPokemon, testCoordinate); 
        assertEquals(testMarker.getPathImage(), testPokemon.getPathImage());        
    }
    
    // TODO uncomment and adapt test when contrustor up to date
//    @Test
//    public void test_getLifePoint(){
//        MarkerModel testMarker = new MarkerModel(PokemonModel.getPokemonByName("raichua"), new CoordinateModel(0, 0));
//        assertEquals(testMarker.getPokemonLife(), 0);
//    }
//    
//    @Test
//    public void test_getAttack(){
//        MarkerModel testMarker = new MarkerModel(PokemonModel.getPokemonByName("raichua"), new CoordinateModel(0, 0));
//        assertEquals(testMarker.getPokemonAttack(), 0);
//    }
//    
//    @Test
//    public void test_getDefence(){
//        MarkerModel testMarker = new MarkerModel(PokemonModel.getPokemonByName("raichua"), new CoordinateModel(0, 0));
//        assertEquals(testMarker.getPokemonDefense(), 0);
//    }
    
}
