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
        // We cannot use the PokemonCache, since it is filled by the
        // ServerQueryController, which in turns need a connection to a server,
        // which is not affordable in test. Thanks to the removal of static lists,
        // we can seamlessly instanciates the following objects:
        darkType = new PokemonTypeModel("DARK");
        arceus = new PokemonModel(new PokemonSendableModel("arceus", "arceus.png", darkType));
    }
    
    @Test
    public void test_getName() {
        // ARCEOUUUUUUS
        assertEquals(arceus.getName(), "arceus");
    }
    
    @Test
    public void test_getType() {
        // A pokemon always have two types, the second may be the none type
        assertEquals(2, arceus.getType().length);
        // We cannot compare directly the types, since PokemonModel may (and
        // in fact do) copy them. Thus, we compare them by their names.
        assertEquals(darkType.getTypeName(), arceus.getType()[0].getTypeName());
        assertEquals(PokemonTypeSendableModel.getNoneType().getTypeName(), arceus.getType()[1].getTypeName());
    }
    
    @Test
    public void test_getPathImage() {
        File file = new File(arceus.getImagePath());
        assertTrue(file.exists());
    }
    
}
