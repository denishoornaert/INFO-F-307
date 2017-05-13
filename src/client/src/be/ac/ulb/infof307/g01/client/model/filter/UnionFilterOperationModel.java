package be.ac.ulb.infof307.g01.client.model.filter;

import be.ac.ulb.infof307.g01.client.model.filter.AbstractFilterExpressionModel;
import be.ac.ulb.infof307.g01.client.model.map.MarkerModel;
import java.text.ParseException;
import java.util.HashSet;

/**
 * OR operator applied on filters.
 */
public class UnionFilterOperationModel extends AbstractFilterExpressionModel {
    
    /**
     * @param expression the expression to parse
     * @throws ParseException if the expression has an incorrect syntax
     */
    public UnionFilterOperationModel(String expression) throws ParseException {
        super(expression);
    }

    @Override
    public HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers) {
        HashSet<MarkerModel> markersToReturn = new HashSet<>();
        for(AbstractFilterExpressionModel expression : _expressions) {
            markersToReturn.addAll(expression.evaluateFilter(allMarkers));
        }
        return markersToReturn;
    }
}
