package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.server.model.DatabaseModel;
import be.ac.ulb.infof307.g01.server.model.PokemonModel;
import be.ac.ulb.infof307.g01.server.model.PokemonTypeModel;
import org.junit.After;
import org.junit.Before;

/**
 * Class to implement test with database
 * 
 * @author Groupe 1
 */
public abstract class AbstractDatabaseTest {
    
    protected DatabaseModel _database;
    
    @Before
    public void setUp() {
        _database = DatabaseModel.getTestInstance();
    }
    
    @After
    public void tearDown() {
        DatabaseModel.closeDatabase();
        PokemonModel.clearAllPokemon();
        PokemonTypeModel.resetAllPokemonType();
        // TODO: supprimer la db et recréer par le script d'init
    }
    
    
}
