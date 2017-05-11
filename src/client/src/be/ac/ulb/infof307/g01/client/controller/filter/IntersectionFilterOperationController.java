package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import java.text.ParseException;
import java.util.HashSet;

/**
 *
 * AND operator applied on filters
 */
public class IntersectionFilterOperationController extends AbstractFilterController {
    
    public IntersectionFilterOperationController(String expression) throws ParseException {
        super(expression);
    }

    @Override
    public HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
