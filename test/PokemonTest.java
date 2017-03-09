/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import be.ac.ulb.infof307.g01.Pokemon;
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
        Pokemon testPokemon = new Pokemon("test", "typeTest");
        assertEquals(testPokemon.getName(), "test");
    }
    
    @Test
    public void test_getType() {
        Pokemon testPokemon = new Pokemon("test", "typeTest");
        assertEquals(testPokemon.getType(), "typeTest");
    }
    
    @Test
    public void test_equals() {
        Pokemon testPokemon1 = new Pokemon("test", "typeTest");
        Pokemon testPokemon2 = new Pokemon("test", "typeTest");
        
        assertTrue(testPokemon1.equals(testPokemon2));
    }
    
    @Test
    public void test_getPathImage() {
        Pokemon testPokemon = new Pokemon("pikachu", "typeTest");
        File file = new File(testPokemon.getPathImage());
        assertTrue(file.exists());
    }
    
    
}
