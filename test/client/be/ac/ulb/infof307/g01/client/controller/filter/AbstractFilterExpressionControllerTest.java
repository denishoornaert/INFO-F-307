package be.ac.ulb.infof307.g01.client.controller.filter;

import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

/**
 *
 * Test parsing method
 */
public class AbstractFilterExpressionControllerTest {
    
    private static final String OPERATION = "OR";
    private static final String PARENTHESIS_CONTENT = "T, T";
    private static final String EXPRESSION =  OPERATION + "(" + PARENTHESIS_CONTENT + ")";
        
    @Test
    public void test_getOperationName() {
        assertEquals(AbstractFilterExpressionController.getOperationName(EXPRESSION), OPERATION);
    }
    
    @Test
    public void test_getParenthesisContent() {
        assertEquals(AbstractFilterExpressionController.getParenthesisContent(EXPRESSION), PARENTHESIS_CONTENT);
    }
    
    @Test
    public void test_getParenthesisContentWithIgnore() {
        final String CONTENT = "T, T";
        final String EXPRESSION = "((" + CONTENT + "), 5)";
        assertEquals(AbstractFilterExpressionController.getParenthesisContent(EXPRESSION, 1), CONTENT);
    }
}
