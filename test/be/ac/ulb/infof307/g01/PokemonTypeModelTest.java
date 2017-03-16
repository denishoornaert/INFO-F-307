package be.ac.ulb.infof307.g01;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import be.ac.ulb.infof307.g01.PokemonTypeModel;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author remy
 */
public class PokemonTypeModelTest {
    
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
    public void test_getTypeName() {
        String name = "TEST";
        PokemonTypeModel pokemonType = new PokemonTypeModel(name);
        assertEquals(pokemonType.getTypeName(), name);
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void test_insertNewTypeNameThrowsExceptionIfNameAlreadyExists() {
        String name = "Test2";
        new PokemonTypeModel(name);
        exception.expect(IllegalStateException.class);
        new PokemonTypeModel(name);
    }
    
}
