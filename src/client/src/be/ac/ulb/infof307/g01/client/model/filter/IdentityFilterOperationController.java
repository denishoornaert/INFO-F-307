package be.ac.ulb.infof307.g01.client.model.filter;

import be.ac.ulb.infof307.g01.client.model.filter.AbstractFilterExpressionController;
import be.ac.ulb.infof307.g01.client.model.map.MarkerModel;
import java.text.ParseException;
import java.util.HashSet;

/**
 * Identity operator applied on filters.
 * ID(.) is equivalent to NOT(NOT(.))
 */
public class IdentityFilterOperationController extends AbstractFilterExpressionController {
    
    /**
     * @param expression the expression to parse
     * @throws ParseException if the expression has an incorrect syntax
     */
    public IdentityFilterOperationController(String expression) throws ParseException {
        super(expression);
    }
    @Override
    public HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers) {
        return _expressions.get(0).evaluateFilter(allMarkers);
    }
    
}
