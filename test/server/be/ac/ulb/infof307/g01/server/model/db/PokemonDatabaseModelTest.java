package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import static junit.framework.TestCase.assertFalse;

import org.junit.Test;

public class PokemonDatabaseModelTest extends AbstractDatabaseTest  {
    
    @Test
    public void test_loadPokemonNotEmpty(){
        _database.loadAllPokemons();
        int size = PokemonSendableModel.getSizePokemonModel();
        assertFalse(size == 0);
        // size must be > 0
    }
    
}
