package be.ac.ulb.infof307.g01.client.model.filter;

import be.ac.ulb.infof307.g01.client.model.map.MarkerModel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Abstract composite class used to control filter expressions.
 */
public abstract class AbstractFilterExpressionModel {

    /** List containing all of the children expressions */
    protected ArrayList<AbstractFilterExpressionModel> _expressions;
    
    /**
     * Default constructor.
     * Use if no child is supposed to be created
     */
    public AbstractFilterExpressionModel() {
        _expressions = new ArrayList<>();
    }
    
    /**
     * @param expression The expression to parse
     * @throws ParseException if given expression has incorrect syntax
     */
    public AbstractFilterExpressionModel(String expression) throws ParseException {
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
        return AbstractFilterExpressionModel.getParenthesisContent(expression, 0);
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
    static public AbstractFilterExpressionModel parse(String expression) throws ParseException {
        String operation;
        try {
            operation = AbstractFilterExpressionModel.getOperationName(expression);
        } catch(StringIndexOutOfBoundsException ex) {
            throw new ParseException(expression, 0);
        }
        AbstractFilterExpressionModel ret = null;
        String parenthesisContent = AbstractFilterExpressionModel.getParenthesisContent(expression);
        switch(operation) {
            case "AND":
                ret = new IntersectionFilterOperationModel(parenthesisContent);
                break;
            case "OR":
                ret = new UnionFilterOperationModel(parenthesisContent);
                break;
            case "NOT":
                ret = new NegationFilterOperationModel(parenthesisContent);
                break;
            case "ID":
                ret = new IdentityFilterOperationModel(parenthesisContent);
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
                //parameters.add(expression.substring(substringStart, idx));
                parameters.add(expression.substring(substringStart, idx).trim());
                substringStart = idx + 1;
            }
        }
        parameters.add(expression.substring(substringStart, expression.length()).trim());
        return parameters;
    }
}
 