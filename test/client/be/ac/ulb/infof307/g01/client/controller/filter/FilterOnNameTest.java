package be.ac.ulb.infof307.g01.client.controller.filter;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Test the leaf name filter class
 */
public class FilterOnNameTest extends AbstractFilterTest {
    
    @Test
    public void test_evaluateFilter_countP1() {
        FilterOnName filter = new FilterOnName("P1");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 2);
    }
    
    @Test
    public void test_evaluateFilter_countP2() {
        FilterOnName filter = new FilterOnName("P2");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 1);
    }
    
    @Test
    public void test_evaluateFilter_countP3() {
        FilterOnName filter = new FilterOnName("P3");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 2);
    }
    
    @Test
    public void test_evaluateFilter_countP4() {
        FilterOnName filter = new FilterOnName("P4");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 3);
    }
}
