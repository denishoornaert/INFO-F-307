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
    
    private static PokemonDatabaseModel _database;
    
    public PokemonDatabaseModelTest(String testName) throws SQLException, FileNotFoundException {
        super(testName);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        try {
            System.out.println("ah " + Main.getDatabasePath());
            _database = (PokemonDatabaseModel) new DatabaseModel(Main.getTestDatabasePath());
        } catch(IllegalStateException ex) {
            // ignore
        }
    }
    @Test public void test_loadPokemonNotEmpty(){
        _database.loadAllPokemon();
        int size = PokemonModel.getSizePokemonModel();
        assertFalse(size == 0);
        // size must be > 0
    }
}
