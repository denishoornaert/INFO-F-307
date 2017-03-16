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
    public void test_getX() {
        CoordinateModel tmp = new CoordinateModel(1, 0);
        assertEquals(tmp.getX(), 1.0);
    }
    
    @Test
    public void test_getY() {
        CoordinateModel tmp = new CoordinateModel(1, 0);
        assertEquals(tmp.getY(), 0.0);
    }

    @Test
    public void test_add() {
        CoordinateModel tmp1 = new CoordinateModel(34, 34);
        CoordinateModel tmp2 = new CoordinateModel(23, -36);
        CoordinateModel result = tmp1.add(tmp2);
        CoordinateModel expectedResult = new CoordinateModel(57, -2);
        assertEquals(result, expectedResult);
        
        tmp2 = new CoordinateModel(23, -32);
        result = tmp1.add(tmp2);
        expectedResult = new CoordinateModel(57, 2);
        assertEquals(result, expectedResult);
    }
    
    @Test
    public void test_multiply() {
        CoordinateModel tmp1 = new CoordinateModel(10, 20);
        CoordinateModel result = tmp1.multiply(1.5);
        CoordinateModel expectedResult = new CoordinateModel(15, 30);
        assertEquals(result, expectedResult);
    }
    
}
