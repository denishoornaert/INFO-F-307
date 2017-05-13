package be.ac.ulb.infof307.g01.client.model.filter;

import be.ac.ulb.infof307.g01.client.model.filter.FilterOnType;
import java.text.ParseException;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

/**
 *
 * @author robin
 */
public class FilterOnTypeTest extends AbstractFilterTest {
    
    @Test
    public void test_evaluateFilter_FIRE() throws ParseException {
        FilterOnType filter = new FilterOnType("FIRE");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 2);
    }
    
    @Test
    public void test_evaluateFilter_WATER() throws ParseException {
        FilterOnType filter = new FilterOnType("WATER");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 5);
    }
    
    @Test
    public void test_evaluateFilter_BUG() throws ParseException {
        FilterOnType filter = new FilterOnType("BUG");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 6);
    }
}
