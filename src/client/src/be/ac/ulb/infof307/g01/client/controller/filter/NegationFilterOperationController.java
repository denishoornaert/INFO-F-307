package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import java.text.ParseException;
import java.util.HashSet;

/**
 * NOT operator applied on filters.
 * The negation of a filter includes every marker except for the ones included
 * in that filter.
 */
class NegationFilterOperationController extends AbstractFilterExpressionController {

    /**
     * @param expression the expression to parse
     * @throws ParseException if the expression has an incorrect syntax
     */
    public NegationFilterOperationController(String expression) throws ParseException {
        super(expression);
    }

    @Override
    public HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers) {
        AbstractFilterExpressionController expression = _expressions.get(0);
        
        HashSet<MarkerModel> markersCopy = new HashSet<>(allMarkers);
        markersCopy.removeAll(expression.evaluateFilter(allMarkers));
        return markersCopy;
    }
    
}
