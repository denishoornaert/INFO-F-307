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
import java.util.Arrays;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author Nathan
 */
public class PokemonDatabaseModelTest extends TestCase {
    
    static PokemonDatabaseModel _database = null;
    
    public PokemonDatabaseModelTest(String testName) throws SQLException, FileNotFoundException {
        super(testName);
        try {
            _database = (PokemonDatabaseModel) new DatabaseModel(Main.getTestDatabasePath());
        } catch (IllegalStateException ex) {
            // ignore
        }
    }

    @Test
    public void test_loadPokemonInDatabase() {
        String pokemonName = "Pikachu";
        PokemonModel loadedPokemon = _database.getPokemonByName(pokemonName);
        assertNotNull(loadedPokemon);
    }
    
    @Test
    public void test_loadPokemonTypesByNameForPikachuAreNotNull() {
        String pokemonName = "Pikachu";
        PokemonTypeModel[] pikachuTypes = _database.getPokemonTypesByName(pokemonName);
        assertNotNull(pikachuTypes);
    }
    
    public void test_loadPokemonTypesByNameForPikachuEqualsElectrik() {
        String pokemonName = "Pikachu";
        PokemonTypeModel[] pikachuTypes = _database.getPokemonTypesByName(pokemonName);
        PokemonTypeModel[] expectedPokemonTypes = {PokemonTypeModel.getPokemonTypeByTypeName("Electrik")};
        assertTrue(Arrays.equals(pikachuTypes, expectedPokemonTypes));
    }
}
