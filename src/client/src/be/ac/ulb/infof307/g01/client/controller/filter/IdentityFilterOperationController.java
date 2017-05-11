package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import java.text.ParseException;
import java.util.HashSet;

/**
 *
 * Identity operator: equivalent to NOT(NOT(.))
 */
public class IdentityFilterOperationController extends AbstractFilterExpressionController {

    public IdentityFilterOperationController(String expression) throws ParseException {
        super(expression);
    }

    @Override
    public HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers) {
        return _expressions.get(0).evaluateFilter(allMarkers);
    }
    
}
