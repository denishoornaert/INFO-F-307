package be.ac.ulb.infof307.g01.client.model.db;

import be.ac.ulb.infof307.g01.client.Main;
import be.ac.ulb.infof307.g01.client.model.DatabaseModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.model.PokemonTypeModel;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;

/**
 * Class to implements test with database
 * 
 * @author Groupe 1
 */
public abstract class AbstractDatabaseTest {
    
    protected DatabaseModel _database;
    
    @Before
    public void setUp() {
        try {
            _database = new DatabaseModel(Main.getTestDatabasePath());
        } catch (SQLException | FileNotFoundException ex) {
            // TODO que faire ?
            System.err.println("Error with database: " + ex.getMessage());
        }
    }
    
    @After
    public void tearDown() {
        DatabaseModel.closeDatabase();
        PokemonModel.clearAllPokemon();
        PokemonTypeModel.resetAllPokemonType();
    }
    
    
}
