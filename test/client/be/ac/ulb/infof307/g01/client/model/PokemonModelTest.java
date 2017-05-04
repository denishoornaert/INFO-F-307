package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class PokemonModelTest {
    
    private static PokemonSendableModel arceus;
    
    @Rule
    public ExpectedException expected = ExpectedException.none();
    
    @BeforeClass
    public static void setUpClass() {
        new PokemonTypeModel("DARK");
        new PokemonTypeModel("FIRE");
        new PokemonTypeModel("ELECTRIC");
        
        arceus = new PokemonModel(new PokemonSendableModel("arceus", "arceus.png",
                PokemonTypeModel.getPokemonTypeByTypeName("DARK")));
    }
    
    @Test
    public void test_getName() {
        assertEquals(arceus.getName(), "arceus");
    }
    
    @Test
    public void test_getType() {
        assertEquals(arceus.getType().length, 1);
        assertEquals(arceus.getType()[0], 
                PokemonTypeModel.getPokemonTypeByTypeName("DARK"));
    }
    
    @Test
    public void test_equals() {
        assertEquals(arceus.equals(arceus), true);
    }
    
    @Test
    public void test_getPathImage() {
        File file = new File(arceus.getImagePath());
        assertTrue(file.exists());
    }
    
}
