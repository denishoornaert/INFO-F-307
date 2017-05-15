package be.ac.ulb.infof307.g01.client.model.filter;

import java.text.ParseException;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Test the intersection (AND) filter operation
 */
public class IntersectionFilterOperationControllerTest extends AbstractFilterTest {
    
    @Test
    public void test_evaluateFilter_result() throws ParseException {
        final IntersectionFilterOperationModel filterTree =
                new IntersectionFilterOperationModel("TYPE(FIRE),TYPE(WATER)");
        assertEquals(filterTree.evaluateFilter(_allMarkers).size(), 0);
    }
}
