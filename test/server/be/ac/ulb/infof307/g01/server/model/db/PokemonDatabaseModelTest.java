package be.ac.ulb.infof307.g01.server.model.db;


import static junit.framework.TestCase.assertFalse;
import org.junit.Test;

public class PokemonDatabaseModelTest extends AbstractDatabaseTest  {
    @Test
    public void test_loadPokemonNotEmpty(){
        assertFalse(_database.getAllPokemons().isEmpty());
    }
    
}
