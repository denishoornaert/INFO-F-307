package be.ac.ulb.infof307.g01.client.model.map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PokemonTypeModelTest {
    @Test
    public void test_getTypeName() {
        final String name = "TEST"; // Must be upperCase
        final PokemonTypeModel pokemonType = new PokemonTypeModel(name);
        assertEquals(pokemonType.getTypeName(), name);
    }
}
