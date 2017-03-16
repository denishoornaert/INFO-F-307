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
        PokemonModel testPokemon = new PokemonModel("arceus", PokemonTypeModel.FLYING,120,120,0,1,2,3);
        assertEquals(testPokemon.getName(), "arceus");
    }
    
    @Test
    public void test_getType() {
        PokemonModel testPokemon = new PokemonModel("arceus", PokemonTypeModel.FIRE,120,120,0,1,2,3);
        assertEquals(testPokemon.getType()[0], PokemonTypeModel.FIRE);
        assertEquals(testPokemon.getType()[1], PokemonTypeModel.NONE);
    }
    
    @Test
    public void test_equals() {
        PokemonModel testPokemon1 = new PokemonModel("arceus", PokemonTypeModel.FIRE,120,120,0,1,2,3);
        PokemonModel testPokemon2 = new PokemonModel("arceus", PokemonTypeModel.FIRE,PokemonTypeModel.NONE,120,120,0,1,2,3);
        
        assertTrue(testPokemon1.equals(testPokemon2));
    }
    
    @Test
    public void test_getPathImage() {
        PokemonModel testPokemon = new PokemonModel("pikachu", PokemonTypeModel.ELECTRIC,120,120,0,1,2,3);
        File file = new File(testPokemon.getPathImage());
        assertTrue(file.exists());
    }
    @Test
    public void test_getLifePoint(){
        PokemonModel testPokemon = new PokemonModel("raichua", PokemonTypeModel.PSYCHIC,PokemonTypeModel.ELECTRIC,80,90,40,60,20,120);
        assertEquals(testPokemon.getLifePoint(),80);
    }
    
    @Test
    public void test_getAttack(){
        PokemonModel testPokemon = new PokemonModel("raichua",PokemonTypeModel.PSYCHIC,PokemonTypeModel.ELECTRIC,80,90,40,60,20,120);
        assertEquals(testPokemon.getAttack(),90);
    }
    @Test
    public void test_getDefence(){
        PokemonModel testPokemon = new PokemonModel("raichua",PokemonTypeModel.PSYCHIC,PokemonTypeModel.ELECTRIC,80,90,40,60,20,120);
        assertEquals(testPokemon.getDefence(),40);
    }
    @Test
    public void test_getSpecialAttack(){
        PokemonModel testPokemon = new PokemonModel("raichua",PokemonTypeModel.PSYCHIC,PokemonTypeModel.ELECTRIC,80,90,40,60,20,120);
        assertEquals(testPokemon.getSpecialAttack(),60);
    }
    @Test
    public void test_getSpeed(){
        PokemonModel testPokemon = new PokemonModel("raichua",PokemonTypeModel.PSYCHIC,PokemonTypeModel.ELECTRIC,80,90,40,60,20,120);
        assertEquals(testPokemon.getSpeed(),120);
    }
    
    @Test
    public void test_getSpecialDefence(){
        PokemonModel testPokemon = new PokemonModel("raichua",PokemonTypeModel.PSYCHIC,PokemonTypeModel.ELECTRIC,80,90,40,60,20,120);
        assertEquals(testPokemon.getSpecialDefence(),20);
    }
    
}
