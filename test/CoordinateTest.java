/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import be.ac.ulb.infof307.g01.Coordinate;
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
        Coordinate tmp = new Coordinate(1, 0);
        assertEquals(tmp.getX(), 1);
    }
    
    @Test
    public void test_getY() {
        Coordinate tmp = new Coordinate(1, 0);
        assertEquals(tmp.getY(), 0);
    }

    @Test
    public void test_add() {
        Coordinate tmp1 = new Coordinate(34, 34);
        Coordinate tmp2 = new Coordinate(23, -36);
        Coordinate result = tmp1.add(tmp2);
        Coordinate expectedResult = new Coordinate(57, -2);
        assertEquals(result, expectedResult);
        
        tmp2 = new Coordinate(23, -32);
        result = tmp1.add(tmp2);
        expectedResult = new Coordinate(57, 2);
        assertEquals(result, expectedResult);
    }
    
    @Test
    public void test_multiply() {
        Coordinate tmp1 = new Coordinate(10, 15);
        Coordinate result = tmp1.multiply(1.5);
        Coordinate expectedResult = new Coordinate(15, 22);
        assertEquals(result, expectedResult);
    }
    
}
