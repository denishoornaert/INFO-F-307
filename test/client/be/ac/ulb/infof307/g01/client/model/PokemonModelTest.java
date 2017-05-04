package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class PokemonModelTest {
    
    private static PokemonSendableModel arceus;
    private static PokemonTypeSendableModel darkType;
    
    @Rule
    public ExpectedException expected = ExpectedException.none();
    
    @BeforeClass
    public static void setUpClass() {
        darkType = new PokemonTypeModel("DARK");
        arceus = new PokemonModel(new PokemonSendableModel("arceus", "arceus.png", darkType));
    }
    
    @Test
    public void test_getName() {
        assertEquals(arceus.getName(), "arceus");
    }
    
    @Test
    public void test_getType() {
        assertEquals(1, arceus.getType().length);
        assertEquals(darkType, arceus.getType()[0]);
    }
    
    @Test
    public void test_equals() {
        // ARCEOUUUUUS
        assertTrue(arceus.equals(arceus));
    }
    
    @Test
    public void test_getPathImage() {
        File file = new File(arceus.getImagePath());
        assertTrue(file.exists());
    }
    
}
