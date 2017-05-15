package be.ac.ulb.infof307.g01.client.model.filter;

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
    public UnionFilterOperationModel(final String expression) throws ParseException {
        super(expression);
    }

    @Override
    public HashSet<MarkerModel> evaluateFilter(final HashSet<MarkerModel> allMarkers) {
        final HashSet<MarkerModel> markersToReturn = new HashSet<>();
        for(final AbstractFilterExpressionModel expression : _expressions) {
            markersToReturn.addAll(expression.evaluateFilter(allMarkers));
        }
        return markersToReturn;
    }
}
