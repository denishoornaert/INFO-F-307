package be.ac.ulb.infof307.g01.server.model.db;

import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class PokemonDatabaseModelTest extends AbstractDatabaseTest  {
    @Test
    public void test_getAllPokemons_loadPokemonNotEmpty(){
        assertFalse(_database.getAllPokemons().isEmpty());
    }
    
}
