package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;

import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

public class PokemonTypeDatabaseModelTest extends AbstractDatabaseTest {
    
    @Test
    public void test_getAllPokemonTypesDefaultValue() {
        PokemonTypeSendableModel.resetAllPokemonType();
        ArrayList<PokemonTypeSendableModel> allPokemonTypes = PokemonTypeSendableModel.getAllPokemonTypes();
        System.out.println("SIZE : "+allPokemonTypes.size());
        assertEquals(allPokemonTypes.size(), 1);
        assertEquals(allPokemonTypes.get(0).getTypeName(), "NONE");
    }
    
    @Test 
    public void test_getPokemonTypeByTypeName() {
        String testString = "bidon_type";  // arbitrary
        PokemonTypeSendableModel pokemonType = new PokemonTypeSendableModel(testString);
        assertEquals(PokemonTypeSendableModel.getPokemonTypeByTypeName(testString), 
                pokemonType);
    }
    
    @Test
    public void test_getTypeName() {
        String testString = "BIDON"; // Must be uppercase  // arbitrary
        PokemonTypeSendableModel pokemonType = new PokemonTypeSendableModel(testString);
        
        assertEquals(pokemonType.getTypeName(), testString);
    }
    
    @Test
    public void test_typeNameIsUpper() {
        String testString = "bidon"; // arbitrary
        PokemonTypeSendableModel pokemonType = new PokemonTypeSendableModel(testString);
        
        assertEquals(pokemonType.getTypeName(), testString.toUpperCase());
    }
    
}
