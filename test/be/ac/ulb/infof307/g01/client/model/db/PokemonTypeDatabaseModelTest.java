package be.ac.ulb.infof307.g01.client.model.db;

import be.ac.ulb.infof307.g01.client.model.PokemonTypeModel;

import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

public class PokemonTypeDatabaseModelTest extends AbstractDatabaseTest {
    
    @Test
    public void test_getAllPokemonTypesDefaultValue() {
        PokemonTypeModel.resetAllPokemonType();
        ArrayList<PokemonTypeModel> allPokemonTypes = PokemonTypeModel.getAllPokemonTypes();
        assertEquals(allPokemonTypes.size(), 1);
        assertEquals(allPokemonTypes.get(0).getTypeName(), "NONE");
    }
    
    @Test 
    public void test_getPokemonTypeByTypeName() {
        String testString = "SuperTest";
        PokemonTypeModel pokemonType = new PokemonTypeModel(testString);
        
        assertEquals(PokemonTypeModel.getPokemonTypeByTypeName(testString), 
                pokemonType);
    }
    
    @Test
    public void test_getTypeName() {
        String testString = "TYPE_NAME"; // Must be uppercase
        PokemonTypeModel pokemonType = new PokemonTypeModel(testString);
        
        assertEquals(pokemonType.getTypeName(), testString);
    }
    
    @Test
    public void test_typeNameIsUpper() {
        String testString = "isUpper";
        PokemonTypeModel pokemonType = new PokemonTypeModel(testString);
        
        assertEquals(pokemonType.getTypeName(), testString.toUpperCase());
    }
    
}
