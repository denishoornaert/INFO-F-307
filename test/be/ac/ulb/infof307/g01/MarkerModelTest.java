package be.ac.ulb.infof307.g01;

import java.sql.Timestamp;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class MarkerModelTest {
    
    @BeforeClass
    public static void setUpClass() {
        new PokemonTypeModel("FIRE");
    }
    
    @Test
    public void test_getPathImage() {
        PokemonModel testPokemon = new PokemonModel("arceus","", 
                PokemonTypeModel.getPokemonTypeByTypeName("FIRE"));
        CoordinateModel testCoordinate = new CoordinateModel(69, 42);
        final String username = "bidon";
        MarkerModel testMarker = new MarkerModel(testPokemon, testCoordinate, username); 
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