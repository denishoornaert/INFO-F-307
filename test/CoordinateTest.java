/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import be.ac.ulb.infof307.g01.CoordinateModel;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author hoornaert
 */
public class CoordinateTest extends TestCase {
    
    public CoordinateTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

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
