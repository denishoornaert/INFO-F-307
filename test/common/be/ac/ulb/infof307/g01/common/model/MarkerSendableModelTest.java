package be.ac.ulb.infof307.g01.common.model;

import java.util.ArrayList;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 *
 * @author davidabraham
 */
public class MarkerSendableModelTest {
    @Test
    public void test_equals_MakerSendableModel() {
        MarkerSendableModel original = new MarkerSendableModel();
        MarkerSendableModel copy = new MarkerSendableModel(original);
        assertEquals(original, copy);
    }
}
