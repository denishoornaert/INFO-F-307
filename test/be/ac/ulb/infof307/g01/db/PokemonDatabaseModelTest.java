/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.PokemonModel;
import be.ac.ulb.infof307.g01.PokemonTypeModel;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nathan
 */
public class PokemonDatabaseModelTest extends TestCase {
    
    PokemonDatabaseModel _database;
    
    public PokemonDatabaseModelTest(String testName) {
        super(testName);
    }
    
    @BeforeClass
    protected void setUpClass() throws SQLException, FileNotFoundException {
        _database = (PokemonDatabaseModel) new DatabaseModel(Main.getTestDatabasePath());
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void test_loadPokemonInDatabase() {
        String pokemonName = "Pikachu";
        PokemonModel loadedPokemon = _database.getPokemonByName(pokemonName);
        assertNotNull(loadedPokemon);
    }
    
    @Test
    public void test_loadPokemonTypesByNameForPikachu() {
        String pokemonName = "Pikachu";
        PokemonTypeModel[] pikachuTypes = _database.getPokemonTypesByName(pokemonName);
        assertNotNull(pikachuTypes);
        assertEquals(pikachuTypes.length, 1);
        assertEquals(pikachuTypes[0], PokemonTypeModel.getPokemonTypeByTypeName("Electrik"));
    }
}
