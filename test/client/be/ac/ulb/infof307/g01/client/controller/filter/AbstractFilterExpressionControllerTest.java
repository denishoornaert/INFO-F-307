package be.ac.ulb.infof307.g01.client.controller.filter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * Test parsing method
 */
public class AbstractFilterExpressionControllerTest {
    
    @Rule
    public ExpectedException expected = ExpectedException.none();
    
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
        final String DOUBLE_PARENTHESIS_EXPRESSION = "((" + CONTENT + "), 5)";
        assertEquals(AbstractFilterExpressionController.getParenthesisContent(DOUBLE_PARENTHESIS_EXPRESSION, 1), CONTENT);
    }
    
    @Test
    public void test_parseOrStatement() throws ParseException {
        final String orStatement = "OR(NAME(X),TYPE(Y))";
        AbstractFilterExpressionController filterTree = AbstractFilterExpressionController.parse(orStatement);
        assertTrue(filterTree instanceof UnionFilterOperationController);
    }
    
    @Test
    public void test_parseAndStatement() throws ParseException {
        final String andStatement = "AND(NAME(X),TYPE(Y))";
        AbstractFilterExpressionController filterTree = AbstractFilterExpressionController.parse(andStatement);
        assertTrue(filterTree instanceof IntersectionFilterOperationController);
    }
    
    @Test
    public void test_parseThrowsParseErrorIfOperationNotRecognized() throws ParseException {
        final String wrongStatement = "IF(A)";
        expected.expect(java.text.ParseException.class);
        AbstractFilterController.parse(wrongStatement);
    }
    
    @Test
    public void test_splitParameters() {
        final String[] initialParameters = {"A", "B", "CD", "E"};
        final String expression = String.join(",", initialParameters);
        ArrayList<String> parameters = AbstractFilterExpressionController.splitParameters(expression);
        assertEquals(Arrays.asList(initialParameters), parameters);
    }
    
    @Test
    public void test_splitParametersWithConfusingParenthesis() throws Exception {
        final String[] initialParameters = {"(A,B)", "C", "(D,(E))", "((F,G),H)"};
        final String expression = String.join(",", initialParameters);
        ArrayList<String> parameters = AbstractFilterExpressionController.splitParameters(expression);
        assertEquals(Arrays.asList(initialParameters), parameters);
    }
}
