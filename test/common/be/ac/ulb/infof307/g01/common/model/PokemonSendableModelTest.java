package be.ac.ulb.infof307.g01.common.model;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Tests the PokemonSendableModel class.
 */
public class PokemonSendableModelTest extends TestCase {
    static final String POKEMON_NAME = "Arceus";
    static final String POKEMON_PATH = "arceus.png";
    static final PokemonTypeSendableModel POKEMON_TYPE = new PokemonTypeSendableModel("FIRE");
    
    @Test
    public void test_createModelWithOneTypeHasNoneSecondType() {
        PokemonSendableModel pokemon = new PokemonSendableModel(
            POKEMON_NAME, POKEMON_PATH, POKEMON_TYPE);
        assertEquals(pokemon.getType()[1], PokemonTypeSendableModel.getNoneType());
    }
    
    @Test
    public void test_modelCopyIsEqualToOriginal() {
        PokemonSendableModel original = new PokemonSendableModel(
            POKEMON_NAME, POKEMON_PATH, POKEMON_TYPE);
        PokemonSendableModel copy = new PokemonSendableModel(original);
        assertEquals(original, copy);
    }
}
