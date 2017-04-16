package be.ac.ulb.infof307.g01;

import java.io.File;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*; 
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import be.ac.ulb.infof307.g01.controller.PokemonModel;
import be.ac.ulb.infof307.g01.controller.PokemonTypeModel;


public class PokemonModelTest {
    
    private static PokemonModel arceus;
    
    @Rule
    public ExpectedException expected = ExpectedException.none();
    
    @BeforeClass
    public static void setUpClass() {
        new PokemonTypeModel("DARK");
        new PokemonTypeModel("FIRE");
        new PokemonTypeModel("ELECTRIC");
        
        
        arceus = new PokemonModel("arceus", "arceus.png",
                PokemonTypeModel.getPokemonTypeByTypeName("DARK"));
    }
    
    @After
    public void tearDown() {
        PokemonModel.clearAllPokemon();
    }
    
    @Test
    public void test_getName() {
        assertEquals(arceus.getName(), "arceus");
    }
    
    @Test
    public void test_getType() {
        assertEquals(arceus.getTypes().length, 1);
        assertEquals(arceus.getTypes()[0], 
                PokemonTypeModel.getPokemonTypeByTypeName("DARK"));
    }
    
    @Test
    public void test_equals() {
        assertEquals(arceus.equals(arceus), true);
    }
    
    @Test
    public void test_getPathImage() {
        File file = new File(arceus.getImagePath());
        assertTrue(file.exists());
    }
    
    @Test
    public void test_pokemonModelAlreadyExistThrowIllegalStateException() {
        String pokemonName = "testException";
        new PokemonModel(pokemonName, "");
        expected.expect(IllegalStateException.class);
        new PokemonModel(pokemonName, "");
    }
    
}
