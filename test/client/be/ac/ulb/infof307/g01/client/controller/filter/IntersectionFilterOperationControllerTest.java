package be.ac.ulb.infof307.g01.client.controller.filter;

import java.text.ParseException;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Test the intersection (AND) filter operation
 */
public class IntersectionFilterOperationControllerTest extends AbstractFilterTest {
    
    @Test
    public void test_evaluateFilterResult() throws ParseException {
        IntersectionFilterOperationController filterTree = new IntersectionFilterOperationController("NAME(P1),TYPE(WATER)");
        assertEquals(filterTree.evaluateFilter(_allMarkers).size(), 7);
    }
}
