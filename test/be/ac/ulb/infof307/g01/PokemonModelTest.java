package be.ac.ulb.infof307.g01;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*; 
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author remy
 */
public class PokemonModelTest {
    
    private static PokemonModel arceus;
    
    @Rule
    public ExpectedException expected = ExpectedException.none();
    
    
    public PokemonModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        new PokemonTypeModel("DARK");
        new PokemonTypeModel("FIRE");
        new PokemonTypeModel("ELECTRIC");
        
        
        arceus = new PokemonModel("arceus", "assets/sprites/arceus.png",
                PokemonTypeModel.getPokemonTypeByTypeName("DARK"));
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
        File file = new File(arceus.getPathImage());
        assertTrue(file.exists());
    }
    
    @Test
    public void test_pokemonModelAlreadyExistThrowIllegalStateException() {
        String pokemonName = "test";
        new PokemonModel(pokemonName);
        expected.expect(IllegalStateException.class);
        new PokemonModel(pokemonName);
    }
    
}
