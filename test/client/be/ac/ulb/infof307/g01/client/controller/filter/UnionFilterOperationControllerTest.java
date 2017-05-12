package be.ac.ulb.infof307.g01.client.controller.filter;

import static be.ac.ulb.infof307.g01.client.controller.filter.AbstractFilterTest._allMarkers;
import java.text.ParseException;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

/**
 *
 * Test the union (OR) filtler operation
 */
public class UnionFilterOperationControllerTest extends AbstractFilterTest {
    
    @Test
    public void test_evaluateFilter_result() throws ParseException {
        UnionFilterOperationController filterTree = new UnionFilterOperationController("NAME(P1),TYPE(FIRE)");
        assertEquals(filterTree.evaluateFilter(_allMarkers).size(), 2);
    }
    
    @Test
    public void test_evaluateFilter_multipleOperands() throws ParseException {
        String expression = "TYPE(WATER),TYPE(FIRE),TYPE(BUG)";
        UnionFilterOperationController filterTree = new UnionFilterOperationController(expression);
        assertEquals(_allMarkers.size(), filterTree.evaluateFilter(_allMarkers).size());
    }
}
