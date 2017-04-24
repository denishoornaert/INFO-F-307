package be.ac.ulb.infof307.g01.model;

import be.ac.ulb.infof307.g01.controller.Main;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;

import org.junit.Before;


public class MarkerModelTest {
    private static PokemonDatabaseModel _database;
    
    private static final String TYPE_NAME = "FIRE";
    private static final PokemonTypeModel TYPE_TEST =  new PokemonTypeModel(TYPE_NAME);
    private static final PokemonModel TEST_POKEMON = new PokemonModel("arceus","", 
                PokemonTypeModel.getPokemonTypeByTypeName(TYPE_NAME));
    private static final int LIFE_POINT = 10;
    private static final int DEFENSE = 3;
    private static final int ATTACK = 8;
    private static final Timestamp TIME = new Timestamp(System.currentTimeMillis());
    private static final String USERNAME = "bidon";
    private static final CoordinateModel TEST_COORDINATE = new CoordinateModel(69, 42);
    
    private static MarkerModel TEST_MARKER;
   
    @BeforeClass
    public static void setUpClass() throws SQLException, FileNotFoundException {
        
        try {
            _database = (PokemonDatabaseModel) new DatabaseModel(Main.getTestDatabasePath());
        } catch(IllegalStateException ex) {
            // ignore
        }
        TEST_MARKER = new MarkerModel(TEST_POKEMON, 
               TEST_COORDINATE,USERNAME,LIFE_POINT,ATTACK,DEFENSE,TIME);

    }

    @Before
    public void setUp() {
       
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
    public void test_getDefence(){
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
               TEST_COORDINATE,USERNAME,LIFE_POINT,ATTACK,DEFENSE,TIME);
        assertEquals(TEST_MARKER.equals(OtherTestMarker),true);
    }
    
    
    
}