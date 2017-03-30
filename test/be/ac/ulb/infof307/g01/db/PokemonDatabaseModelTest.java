package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.PokemonModel;
import junit.framework.TestCase;
import org.junit.Test;

public class PokemonDatabaseModelTest extends TestCase {
    
    private static PokemonDatabaseModel _database;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        try {
            _database = (PokemonDatabaseModel) new DatabaseModel(Main.getTestDatabasePath());
        } catch(IllegalStateException ex) {
            // ignore
        }
    }
    
    @Test
    public void test_loadPokemonNotEmpty(){
        _database.loadAllPokemons();
        int size = PokemonModel.getSizePokemonModel();
        assertFalse(size == 0);
        // size must be > 0
    }
    
}
