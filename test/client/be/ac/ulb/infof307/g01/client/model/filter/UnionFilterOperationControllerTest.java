package be.ac.ulb.infof307.g01.client.model.filter;

import static be.ac.ulb.infof307.g01.client.model.filter.AbstractFilterTest._allMarkers;
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
        final UnionFilterOperationModel filterTree =
                new UnionFilterOperationModel("NAME(P1),TYPE(FIRE)");
        assertEquals(filterTree.evaluateFilter(_allMarkers).size(), 2);
    }
    
    @Test
    public void test_evaluateFilter_multipleOperands() throws ParseException {
        final String expression = "TYPE(WATER),TYPE(FIRE),TYPE(BUG)";
        final UnionFilterOperationModel filterTree =
                new UnionFilterOperationModel(expression);
        assertEquals(_allMarkers.size(), filterTree.evaluateFilter(_allMarkers).size());
    }
}
