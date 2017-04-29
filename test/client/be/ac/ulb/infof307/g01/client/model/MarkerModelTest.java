package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import java.sql.Timestamp;
import org.junit.Test;


import org.junit.Before;
import static junit.framework.TestCase.assertEquals;
import org.junit.After;


public class MarkerModelTest {
    
    private static final String TYPE_NAME = "FIRE";
    private static final PokemonModel TEST_POKEMON = new PokemonModel("arceus","", 
                PokemonTypeModel.getPokemonTypeByTypeName(TYPE_NAME));
    private static final int LIFE_POINT = 10;
    private static final int DEFENSE = 3;
    private static final int ATTACK = 8;
    private static final int VOTE_UP = 0;
    private static final int VOTE_DOWN = 0;
    private static final Timestamp TIME = new Timestamp(System.currentTimeMillis());
    private static final String USERNAME = "bidon";
    private static final CoordinateSendableModel TEST_COORDINATE = new CoordinateSendableModel(69, 42);
    
    private static MarkerModel TEST_MARKER;
    
    @Before
    public void setUp() {
        TEST_MARKER = new MarkerModel(TEST_POKEMON, 
               TEST_COORDINATE, USERNAME, LIFE_POINT, ATTACK, DEFENSE, TIME, VOTE_UP, VOTE_DOWN);
    }
    
    @After
    public void tearDown() {
        PokemonModel.clearAllPokemon();
    }
    
    @Test
    public void test_getPathImage() {
        assertEquals(TEST_MARKER.getImagePath(), TEST_POKEMON.getImagePath());        
    }
    
    @Test
    public void test_getLifePoint(){
       assertEquals(TEST_MARKER.getPokemonLife(), LIFE_POINT);
    }
    
   @Test
    public void test_getAttack(){
        assertEquals(TEST_MARKER.getPokemonAttack(), ATTACK);
    }  
    
    @Test
    public void test_getDefense(){
        assertEquals(TEST_MARKER.getPokemonDefense(), DEFENSE);
    }
    
    @Test
    public void test_getTime(){
        assertEquals(TEST_MARKER.getTimestamp(),TIME);
    }
    
    @Test
    public void test_getCoordinate(){
        assertEquals(TEST_MARKER.getCoordinate(),TEST_COORDINATE);
    }
    
    @Test
    public void test_Equals(){
        MarkerModel OtherTestMarker = new MarkerModel(TEST_POKEMON, 
               TEST_COORDINATE, USERNAME, LIFE_POINT, ATTACK, DEFENSE, TIME, VOTE_UP, VOTE_DOWN);
        assertEquals(TEST_MARKER.equals(OtherTestMarker),true);
    }
    
}