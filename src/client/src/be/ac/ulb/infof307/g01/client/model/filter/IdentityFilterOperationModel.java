package be.ac.ulb.infof307.g01.client.model.filter;

import be.ac.ulb.infof307.g01.client.model.map.MarkerModel;
import java.text.ParseException;
import java.util.HashSet;

/**
 * Identity operator applied on filters.
 * ID(.) is equivalent to NOT(NOT(.))
 */
public class IdentityFilterOperationModel extends AbstractFilterExpressionModel {
    
    /**
     * @param expression the expression to parse
     * @throws ParseException if the expression has an incorrect syntax
     */
    public IdentityFilterOperationModel(final String expression) throws ParseException {
        super(expression);
    }
    @Override
    public HashSet<MarkerModel> evaluateFilter(final HashSet<MarkerModel> allMarkers) {
        return _expressions.get(0).evaluateFilter(allMarkers);
    }
    
}
