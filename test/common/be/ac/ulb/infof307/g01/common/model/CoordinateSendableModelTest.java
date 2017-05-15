package be.ac.ulb.infof307.g01.common.model;

import junit.framework.TestCase;
import org.junit.Test;

public class CoordinateSendableModelTest extends TestCase {
    
    @Test
    public void test_getLatitude() {
        final CoordinateSendableModel tmp = new CoordinateSendableModel(1, 0);
        assertEquals(tmp.getLatitude(), 1.0);
    }
    
    @Test
    public void test_getLongitude() {
        final CoordinateSendableModel tmp = new CoordinateSendableModel(1, 0);
        assertEquals(tmp.getLongitude(), 0.0);
    }
}
