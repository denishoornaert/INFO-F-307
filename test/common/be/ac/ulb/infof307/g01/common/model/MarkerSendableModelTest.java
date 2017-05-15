package be.ac.ulb.infof307.g01.common.model;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 *
 * 
 */
public class MarkerSendableModelTest {
    
    @Test
    public void test_equals_MakerSendableModel() {
        final MarkerSendableModel original = new MarkerSendableModel();
        final MarkerSendableModel copy = new MarkerSendableModel(original);
        assertEquals(original, copy);
    }
}
