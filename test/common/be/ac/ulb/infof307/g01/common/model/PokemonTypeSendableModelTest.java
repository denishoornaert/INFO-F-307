package be.ac.ulb.infof307.g01.common.model;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Tests the PokemonTypeSendableModel class.
 */
public class PokemonTypeSendableModelTest extends TestCase {
    
    @Test
    public void test_modelCopyIsEqualToOriginal() {
        PokemonTypeSendableModel original = new PokemonTypeSendableModel("AIR");
        PokemonTypeSendableModel copy = new PokemonTypeSendableModel(original);
        assertEquals(original, copy);
    }
}
