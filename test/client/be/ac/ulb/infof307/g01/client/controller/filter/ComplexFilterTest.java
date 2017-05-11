package be.ac.ulb.infof307.g01.client.controller.filter;

import java.text.ParseException;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 *
 * Test of several complicated filter expressions
 */
public class ComplexFilterTest extends AbstractFilterTest {
    
    @Test
    public void test_ComplexFilter() throws ParseException {
        String complexExpression = "OR(NOT(ID(NOT(AND(NOT(AND(TYPE(WATER),TYPE(FIRE))),NOT(NAME(P1)))))),NAME(P1))";
        AbstractFilterExpressionController complexFilter = AbstractFilterExpressionController.parse(complexExpression);
        assertTrue(complexFilter.evaluateFilter(_allMarkers).equals(_allMarkers));
    }
}
