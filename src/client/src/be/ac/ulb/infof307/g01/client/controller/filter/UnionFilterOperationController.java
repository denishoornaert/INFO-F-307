package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import java.text.ParseException;
import java.util.HashSet;

/**
 * OR operator applied on filters.
 */
public class UnionFilterOperationController extends AbstractFilterExpressionController {
    
    /**
     * @param expression the expression to parse
     * @throws ParseException if the expression has an incorrect syntax
     */
    public UnionFilterOperationController(String expression) throws ParseException {
        super(expression);
    }

    @Override
    public HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers) {
        HashSet<MarkerModel> markersToReturn = new HashSet<>();
        for(AbstractFilterExpressionController expression : _expressions) {
            markersToReturn.addAll(expression.evaluateFilter(allMarkers));
        }
        return markersToReturn;
    }
}
