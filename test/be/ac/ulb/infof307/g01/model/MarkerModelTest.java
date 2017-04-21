package be.ac.ulb.infof307.g01.model;

import be.ac.ulb.infof307.g01.controller.Main;
import java.sql.Timestamp;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

import org.junit.Before;


public class MarkerModelTest extends TestCase {
    
    @BeforeClass
    public static void setUpClass() {
        new PokemonTypeModel("FIRE");
    }
    private static PokemonDatabaseModel _database;
    
    @Override
    @Before
    protected void setUp() throws Exception {
        super.setUp();
        
        try {
            _database = (PokemonDatabaseModel) new DatabaseModel(Main.getTestDatabasePath());
        } catch(IllegalStateException ex) {
            // ignore
        }
    }
    @Test
    public void test_getPathImage() {
        PokemonModel testPokemon = new PokemonModel("arceus","", 
                PokemonTypeModel.getPokemonTypeByTypeName("FIRE"));
        CoordinateModel testCoordinate = new CoordinateModel(69, 42);
        final String username = "bidon";
        final int lifePoint = 5;
        final int attack = 3;
        final int defense = 2;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        MarkerModel testMarker = new MarkerModel(testPokemon, testCoordinate, username, lifePoint, attack, defense, time); 
        assertEquals(testMarker.getImagePath(), testPokemon.getImagePath());        
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