package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import java.text.ParseException;
import java.util.HashSet;

/**
 *
 * NOT operator applied on filters: returns all markers that are not in children
 * filtering
 */
class NegationFilterOperationController extends AbstractFilterExpressionController {

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
