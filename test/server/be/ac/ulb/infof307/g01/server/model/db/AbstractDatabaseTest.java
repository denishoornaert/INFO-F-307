package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import be.ac.ulb.infof307.g01.server.model.DatabaseModel;
import org.junit.After;
import org.junit.Before;

/**
 * Class to implement test with database
 * 
 * @author Groupe 1
 */
public abstract class AbstractDatabaseTest {
    
    protected static DatabaseModel _database;
    
    @Before
    public void setUp() {
        _database = DatabaseModel.getTestInstance();
        _database.loadAllPokemonTypes();
        _database.loadAllPokemons();
    }
    
    @After
    public void tearDown() {
        _database.deleteTable("User");
        _database.deleteTable("Marker");
        _database.deleteTable("MarkerVote");
        DatabaseModel.closeDatabase();
        PokemonSendableModel.clearAllPokemon();
        PokemonTypeSendableModel.resetAllPokemonType();
    }
    
    
}
