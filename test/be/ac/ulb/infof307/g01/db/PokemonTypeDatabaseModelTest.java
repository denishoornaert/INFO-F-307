package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.PokemonTypeModel;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;

public class PokemonTypeDatabaseModelTest extends TestCase {
    
    private static PokemonTypeDatabaseModel _database;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        try {
            _database = (PokemonTypeDatabaseModel) new DatabaseModel(Main.getTestDatabasePath());
        } catch(IllegalStateException ex) {
            // ignore
        }
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        PokemonTypeModel.resetAllPokemonType();
    }
    
    @Test
    public void test_getAllPokemonTypesDefaultValue() {
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
