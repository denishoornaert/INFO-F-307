package be.ac.ulb.infof307.g01.client.model.filter;

import be.ac.ulb.infof307.g01.client.model.filter.FilterOnName;
import java.text.ParseException;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Test the leaf name filter class
 */
public class FilterOnNameTest extends AbstractFilterTest {
    
    @Test
    public void test_evaluateFilter_countP1() throws ParseException {
        FilterOnName filter = new FilterOnName("P1");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 2);
    }
    
    @Test
    public void test_evaluateFilter_countP2() throws ParseException {
        FilterOnName filter = new FilterOnName("P2");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 1);
    }
    
    @Test
    public void test_evaluateFilter_countP3() throws ParseException {
        FilterOnName filter = new FilterOnName("P3");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 2);
    }
    
    @Test
    public void test_evaluateFilter_countP4() throws ParseException {
        FilterOnName filter = new FilterOnName("P4");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 3);
    }
}
