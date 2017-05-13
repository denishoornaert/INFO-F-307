package be.ac.ulb.infof307.g01.client.model.filter;

import be.ac.ulb.infof307.g01.client.model.filter.FilterOnName;
import be.ac.ulb.infof307.g01.client.model.filter.FilterOnType;
import be.ac.ulb.infof307.g01.client.model.filter.AbstractFilterModel;
import be.ac.ulb.infof307.g01.client.model.filter.AbstractFilterExpressionModel;
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
        assertEquals(AbstractFilterExpressionModel.getOperationName(EXPRESSION), OPERATION);
    }
    
    @Test
    public void test_getParenthesisContent() {
        assertEquals(AbstractFilterExpressionModel.getParenthesisContent(EXPRESSION), PARENTHESIS_CONTENT);
    }
    
    @Test
    public void test_getParenthesisContent_withIgnore() {
        final String CONTENT = "T, T";
        final String DOUBLE_PARENTHESIS_EXPRESSION = "((" + CONTENT + "), 5)";
        assertEquals(AbstractFilterExpressionModel.getParenthesisContent(DOUBLE_PARENTHESIS_EXPRESSION, 1), CONTENT);
    }
    
    @Test
    public void test_parse_orStatement() throws ParseException {
        final String orStatement = "OR(NAME(X),TYPE(Y))";
        AbstractFilterExpressionModel filterTree = AbstractFilterExpressionModel.parse(orStatement);
        assertTrue(filterTree instanceof UnionFilterOperationModel);
    }
    
    @Test
    public void test_parse_andStatement() throws ParseException {
        final String andStatement = "AND(NAME(X),TYPE(Y))";
        AbstractFilterExpressionModel filterTree = AbstractFilterExpressionModel.parse(andStatement);
        assertTrue(filterTree instanceof IntersectionFilterOperationModel);
    }
    
    @Test
    public void test_parse_notStatement() throws ParseException {
        final String notStatement = "NOT(NAME(X))";
        AbstractFilterExpressionModel filterTree = AbstractFilterExpressionModel.parse(notStatement);
        assertTrue(filterTree instanceof NegationFilterOperationModel);
    }
    
    @Test
    public void test_parse_idStatement() throws ParseException {
        final String idStatement = "ID(NAME(X))";
        AbstractFilterExpressionModel filterTree = AbstractFilterExpressionModel.parse(idStatement);
        assertTrue(filterTree instanceof IdentityFilterOperationModel);
    }
    
    @Test
    public void test_parse_nameStatement() throws ParseException {
        final String nameStatement = "NAME(X)";
        AbstractFilterExpressionModel filterTree = AbstractFilterExpressionModel.parse(nameStatement);
        assertTrue(filterTree instanceof FilterOnName);
    }
    
    @Test
    public void test_parse_typeStatement() throws ParseException {
        final String typeStatement = "TYPE(Y)";
        AbstractFilterExpressionModel filterTree = AbstractFilterExpressionModel.parse(typeStatement);
        assertTrue(filterTree instanceof FilterOnType);
    }
    
    @Test
    public void test_parse_throwsParseErrorIfOperationNotRecognized() throws ParseException {
        final String wrongStatement = "IF(A)";
        expected.expect(java.text.ParseException.class);
        AbstractFilterModel.parse(wrongStatement);
    }
    
    @Test
    public void test_splitParameters() {
        final String[] initialParameters = {"A", "B", "CD", "E"};
        final String expression = String.join(",", initialParameters);
        ArrayList<String> parameters = AbstractFilterExpressionModel.splitParameters(expression);
        assertEquals(Arrays.asList(initialParameters), parameters);
    }
    
    @Test
    public void test_splitParametersWithConfusingParenthesis() throws Exception {
        final String[] initialParameters = {"(A,B)", "C", "(D,(E))", "((F,G),H)"};
        final String expression = String.join(",", initialParameters);
        ArrayList<String> parameters = AbstractFilterExpressionModel.splitParameters(expression);
        assertEquals(Arrays.asList(initialParameters), parameters);
    }
}
