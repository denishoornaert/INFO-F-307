package be.ac.ulb.infof307.g01;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertEquals;

public class PokemonTypeModelTest {
    
    @Test
    public void test_getTypeName() {
        String name = "TEST"; // Must be upperCase
        PokemonTypeModel pokemonType = new PokemonTypeModel(name);
        assertEquals(pokemonType.getTypeName(), name);
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void test_insertNewTypeNameThrowsExceptionIfNameAlreadyExists() {
        String name = "Test2";
        new PokemonTypeModel(name);
        exception.expect(IllegalStateException.class);
        new PokemonTypeModel(name);
    }
    
}
