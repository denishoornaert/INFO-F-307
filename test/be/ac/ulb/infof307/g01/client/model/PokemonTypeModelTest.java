package be.ac.ulb.infof307.g01.client.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import be.ac.ulb.infof307.g01.client.model.PokemonTypeModel;

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
