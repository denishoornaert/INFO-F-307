package be.ac.ulb.infof307.g01;

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
public class PokemonModelTest {
    
    private static PokemonModel arceus;
    
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
    
    
}
