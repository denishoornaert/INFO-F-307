package be.ac.ulb.infof307.g01.client.model.filter;

import be.ac.ulb.infof307.g01.client.model.map.MarkerModel;
import java.text.ParseException;
import java.util.HashSet;

/**
 * AND operator applied on filters.
 */
public class IntersectionFilterOperationModel extends AbstractFilterExpressionModel {
    
    /**
     * @param expression the expression to parse
     * @throws ParseException if the expression has an incorrect syntax
     */
    public IntersectionFilterOperationModel(String expression) throws ParseException {
        super(expression);
    }

    @Override
    public HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers) {
        HashSet<MarkerModel> markersToReturn = new HashSet<>(allMarkers);
        for(AbstractFilterExpressionModel expression : _expressions) {
            HashSet<MarkerModel> tmp = expression.evaluateFilter(allMarkers);
            markersToReturn.retainAll(tmp);
        }
        return markersToReturn;
    }
    
}
