package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * Abstract class used for composite design pattern
 */
public abstract class AbstractFilterExpressionController {

    protected ArrayList<AbstractFilterExpressionController> _expressions;
    
    public AbstractFilterExpressionController(String expression) {
        _expressions = new ArrayList<>();
    }
    
    public abstract HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers);
    
    ///// static
    
    static protected String getParenthesisContent(String expression) {
        return AbstractFilterExpressionController.getParenthesisContent(expression, 0);
    }
    
    static protected String getParenthesisContent(String expression, int toIgnore) {
        int leftParenthesisIdx = -1;
        int rightParenthesisIdx = expression.length();
        for(int i = 0; i <= toIgnore; ++i) {
            leftParenthesisIdx = expression.indexOf('(', leftParenthesisIdx+1);
            rightParenthesisIdx = expression.lastIndexOf(')', rightParenthesisIdx-1);
        }
        return expression.substring(leftParenthesisIdx+1, rightParenthesisIdx);
    }
    
    static public String getOperationName(String expression) {
        return expression.substring(0, expression.indexOf('(')).toUpperCase();
    }
    
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
        }
        if(ret == null) {
            throw new ParseException(expression, 0);
        } else {
            return ret;
        }
    }
}
 