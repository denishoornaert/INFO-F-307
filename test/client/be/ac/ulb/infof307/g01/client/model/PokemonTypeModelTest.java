package be.ac.ulb.infof307.g01.client.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PokemonTypeModelTest {
    @Test
    public void test_getTypeName() {
        String name = "TEST"; // Must be upperCase
        PokemonTypeModel pokemonType = new PokemonTypeModel(name);
        assertEquals(pokemonType.getTypeName(), name);
    }
}
