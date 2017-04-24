package be.ac.ulb.infof307.g01.client.db;

import be.ac.ulb.infof307.g01.client.Main;
import be.ac.ulb.infof307.g01.client.model.DatabaseModel;
import be.ac.ulb.infof307.g01.client.model.PokemonDatabaseModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import static junit.framework.TestCase.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PokemonDatabaseModelTest {
    
    private static PokemonDatabaseModel _database;
    
    @Before
    public void setUp() {
        try {
            _database = (PokemonDatabaseModel) new DatabaseModel(Main.getTestDatabasePath());
            System.out.println(_database);
        } catch(IllegalStateException | SQLException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @After
    public void tearDown() {
        DatabaseModel.closeDatabase();
    }
    
    @Test
    public void test_loadPokemonNotEmpty(){
        _database.loadAllPokemons();
        int size = PokemonModel.getSizePokemonModel();
        assertFalse(size == 0);
        // size must be > 0
    }
    
}
