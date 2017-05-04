package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class PokemonModelTest {
    
    private static PokemonModel arceus;
    private static PokemonTypeModel darkType;
    
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
        assertEquals(2, arceus.getType().length);
        assertEquals(darkType.getTypeName(), arceus.getType()[0].getTypeName());
        assertEquals(PokemonTypeSendableModel.getNoneType().getTypeName(), arceus.getType()[1].getTypeName());
    }
    
    @Test
    public void test_equals() {
        // ARCEOUUUUUS
        // TODO: Why would we use that equals function ? Why testing it ?
        // assertTrue(arceus.equals(arceus));
    }
    
    @Test
    public void test_getPathImage() {
        File file = new File(arceus.getImagePath());
        assertTrue(file.exists());
    }
    
}
