package be.ac.ulb.infof307.g01.model;

import junit.framework.TestCase;
import org.junit.Test;

import be.ac.ulb.infof307.g01.model.CoordinateModel;


public class CoordinateModelTest extends TestCase {
    
    @Test
    public void test_getLatitude() {
        CoordinateModel tmp = new CoordinateModel(1, 0);
        assertEquals(tmp.getLatitude(), 1.0);
    }
    
    @Test
    public void test_getLongitude() {
        CoordinateModel tmp = new CoordinateModel(1, 0);
        assertEquals(tmp.getLongitude(), 0.0);
    }
}
