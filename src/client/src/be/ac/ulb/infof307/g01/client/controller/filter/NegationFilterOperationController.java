/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import java.util.HashSet;

/**
 *
 * @author robin
 */
class NegationFilterOperationController extends AbstractFilterExpressionController {

    public NegationFilterOperationController(String expression) {
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
