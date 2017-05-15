package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PokemonTypeDatabaseModelTest extends AbstractDatabaseTest {
    
    @Test 
    public void test_getPokemonTypeByTypeName() {
        final String testString = "FIRE";  // must be an existing one
        final PokemonTypeSendableModel pokemonType = new PokemonTypeSendableModel(testString);
        
        assertEquals(_database.getPokemonTypeByTypeName(testString).getTypeName(), 
                pokemonType.getTypeName());
    }
    
    @Test
    public void test_getTypeName_typeNameIsUpper() {
        final String testString = "bidon"; // arbitrary
        final PokemonTypeSendableModel pokemonType = new PokemonTypeSendableModel(testString);
        
        assertEquals(pokemonType.getTypeName(), testString.toUpperCase());
    }
    
}
