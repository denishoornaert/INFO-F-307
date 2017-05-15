package be.ac.ulb.infof307.g01.client.model.filter;

import be.ac.ulb.infof307.g01.client.model.map.MarkerModel;
import java.text.ParseException;
import java.util.HashSet;

/**
 * NOT operator applied on filters.
 * The negation of a filter includes every marker except for the ones included
 * in that filter.
 */
class NegationFilterOperationModel extends AbstractFilterExpressionModel {

    /**
     * @param expression the expression to parse
     * @throws ParseException if the expression has an incorrect syntax
     */
    public NegationFilterOperationModel(String expression) throws ParseException {
        super(expression);
    }

    @Override
    public HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers) {
        AbstractFilterExpressionModel expression = _expressions.get(0);
        
        HashSet<MarkerModel> markersCopy = new HashSet<>(allMarkers);
        markersCopy.removeAll(expression.evaluateFilter(allMarkers));
        return markersCopy;
    }
    
}
