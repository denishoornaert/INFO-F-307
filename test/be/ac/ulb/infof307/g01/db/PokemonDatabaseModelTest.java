/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.PokemonModel;
<<<<<<< 81133b3536a5e592d875a1bdbdac9422cfcde92e
import java.io.FileNotFoundException;
import java.sql.SQLException;
=======
>>>>>>> Add some tests + Remove unused import
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author Nathan
 */
public class PokemonDatabaseModelTest extends TestCase {
    
    private static PokemonDatabaseModel _database;
    
    public PokemonDatabaseModelTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        try {
            _database = (PokemonDatabaseModel) new DatabaseModel(Main.getTestDatabasePath());
        } catch(IllegalStateException ex) {
            // ignore
        }
    }
    @Test public void test_loadPokemonNotEmpty(){
        _database.loadAllPokemons();
        int size = PokemonModel.getSizePokemonModel();
        assertFalse(size == 0);
        // size must be > 0
    }
    
}
