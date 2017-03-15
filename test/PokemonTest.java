/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import be.ac.ulb.infof307.g01.PokemonModel;
import be.ac.ulb.infof307.g01.PokemonTypeModel;
import java.io.File;
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
public class PokemonTest {
    
    public PokemonTest() {
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
    public void test_getName() {
        PokemonModel testPokemon = new PokemonModel("arceus", PokemonTypeModel.FLYING);
        assertEquals(testPokemon.getName(), "arceus");
    }
    
    @Test
    public void test_getType() {
        PokemonModel testPokemon = new PokemonModel("arceus", PokemonTypeModel.FIRE);
        assertEquals(testPokemon.getType()[0], PokemonTypeModel.FIRE);
        assertEquals(testPokemon.getType()[1], PokemonTypeModel.NONE);
    }
    
    @Test
    public void test_equals() {
        PokemonModel testPokemon1 = new PokemonModel("arceus", PokemonTypeModel.FIRE);
        PokemonModel testPokemon2 = new PokemonModel("arceus", PokemonTypeModel.FIRE,PokemonTypeModel.NONE);
        
        assertTrue(testPokemon1.equals(testPokemon2));
    }
    
    @Test
    public void test_getPathImage() {
        PokemonModel testPokemon = new PokemonModel("pikachu", PokemonTypeModel.ELECTRIC);
        File file = new File(testPokemon.getPathImage());
        assertTrue(file.exists());
    }
    
    
}
