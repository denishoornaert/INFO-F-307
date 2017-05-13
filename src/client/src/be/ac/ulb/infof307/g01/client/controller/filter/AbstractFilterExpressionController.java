package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Abstract composite class used to control filter expressions.
 */
public abstract class AbstractFilterExpressionController {

    /** List containing all of the children expressions */
    protected ArrayList<AbstractFilterExpressionController> _expressions;
    
    /**
     * Default constructor.
     * Use if no child is supposed to be created
     */
    public AbstractFilterExpressionController() {
        _expressions = new ArrayList<>();
    }
    
    /**
     * @param expression The expression to parse
     * @throws ParseException if given expression has incorrect syntax
     */
    public AbstractFilterExpressionController(String expression) throws ParseException {
        this();
        ArrayList<String> splitParam = splitParameters(expression);
        if(splitParam == null || splitParam.isEmpty()) {
            throw new ParseException("Could not split null/empty element", 0);
        }
        for(String parameter : splitParam) {
            _expressions.add(parse(parameter));
        }
    }
    
    /**
     * Applies the filters to the complete set of existing markers
     * @param allMarkers the set of all markers on map
     * @return a subset of allMarkers fitting the filter
     */
    public abstract HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers);
    
    /**
     * Returns the content of the first parenthesis found in an expression.
     * @param expression the textual expression to parse
     * @return the content of the first parenthesis
     */
    static protected String getParenthesisContent(String expression) {
        return AbstractFilterExpressionController.getParenthesisContent(expression, 0);
    }
    
    /**
     * Return the content of a given parenthesis found in an expression
     * @param expression the textual expression to parse
     * @param index the index of the parenthesis to get (starts at 0)
     * @return the content of the requested parenthesis
     */
    protected static String getParenthesisContent(String expression, int index) {
        int leftParenthesisIdx = -1;
        int rightParenthesisIdx = expression.length();
        for(int i = 0; i <= index; ++i) {
            leftParenthesisIdx = expression.indexOf('(', leftParenthesisIdx+1);
            rightParenthesisIdx = expression.lastIndexOf(')', rightParenthesisIdx-1);
        }
        return expression.substring(leftParenthesisIdx+1, rightParenthesisIdx);
    }
    
    /**
     * Returns the name of the the first found operator in an expression.
     * @param expression the textual expression to parse
     * @return the name of the first operation found in the expression
     */
    static public String getOperationName(String expression) {
        return expression.substring(0, expression.indexOf('(')).toUpperCase();
    }
    
    /**
     * Creates a filter tree based on a string representing a filter expression.
     * @param expression the textual expression to parse
     * @return a filter object representing the whole filter
     * @throws ParseException if the string has an incorrect syntax
     */
    static public AbstractFilterExpressionController parse(String expression) throws ParseException {
        String operation = AbstractFilterExpressionController.getOperationName(expression);
        AbstractFilterExpressionController ret = null;
        String parenthesisContent = AbstractFilterExpressionController.getParenthesisContent(expression);
        switch(operation) {
            case "AND":
                ret = new IntersectionFilterOperationController(parenthesisContent);
                break;
            case "OR":
                ret = new UnionFilterOperationController(parenthesisContent);
                break;
            case "NOT":
                ret = new NegationFilterOperationController(parenthesisContent);
                break;
            case "ID":
                ret = new IdentityFilterOperationController(parenthesisContent);
                break;
            case "NAME":
                ret = new FilterOnName(parenthesisContent);
                break;
            case "TYPE":
                ret = new FilterOnType(parenthesisContent);
                break;
        }
        if(ret == null) {
            throw new ParseException(expression, 0);
        }
        return ret;
    }
    
    /**
     * Return a list of parameters in an expression separated by a coma
     * @param expression the textual expression to parse
     * @return the list of parameters contained in the expression
     */
    protected static ArrayList<String> splitParameters(String expression) {
        if(expression == null || expression.isEmpty()) {
            return null;
        }
        ArrayList<String> parameters = new ArrayList<>();
        int substringStart = 0;
        int parenthesisCounter = 0;
        for(int idx = 0; idx < expression.length(); ++idx) {
            if(expression.charAt(idx) == '(') {
                ++parenthesisCounter;
            } else if(expression.charAt(idx) == ')') {
                --parenthesisCounter;
            } else if(expression.charAt(idx) == ',' && parenthesisCounter == 0) {
                parameters.add(expression.substring(substringStart, idx));
                substringStart = idx + 1;
            }
        }
        parameters.add(expression.substring(substringStart, expression.length()));
        return parameters;
    }
}
 