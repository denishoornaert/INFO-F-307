package be.ac.ulb.infof307.g01.client.model.filter;

import be.ac.ulb.infof307.g01.client.model.filter.AbstractFilterExpressionModel;
import java.text.ParseException;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 *
 * Test of several complicated filter expressions
 */
public class ComplexFilterTest extends AbstractFilterTest {
    
    @Test
    public void test_parse_complexFilter() throws ParseException {
        String complexExpression = "OR(NOT(ID(NOT(AND(NOT(AND(TYPE(WATER),TYPE(FIRE))),NOT(NAME(P1)))))),NAME(P1))";
        AbstractFilterExpressionModel complexFilter = AbstractFilterExpressionModel.parse(complexExpression);
        assertTrue(complexFilter.evaluateFilter(_allMarkers).equals(_allMarkers));
    }
}
