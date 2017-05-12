package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;

import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

public class PokemonTypeDatabaseModelTest extends AbstractDatabaseTest {
    
    @Test 
    public void test_getPokemonTypeByTypeName() {
        String testString = "FIRE";  // must be an existing one
        PokemonTypeSendableModel pokemonType = new PokemonTypeSendableModel(testString);
        
        assertEquals(_database.getPokemonTypeByTypeName(testString).getTypeName(), pokemonType.getTypeName());
    }
    
    @Test
    public void test_getTypeName_typeNameIsUpper() {
        String testString = "bidon"; // arbitrary
        PokemonTypeSendableModel pokemonType = new PokemonTypeSendableModel(testString);
        
        assertEquals(pokemonType.getTypeName(), testString.toUpperCase());
    }
    
}
