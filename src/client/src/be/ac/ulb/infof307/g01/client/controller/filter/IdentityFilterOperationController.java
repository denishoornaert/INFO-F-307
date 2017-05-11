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
class IdentityFilterOperationController extends AbstractFilterExpressionController {

    public IdentityFilterOperationController(String expression) {
        super(expression);
    }

    @Override
    public HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers) {
        return _expressions.get(0).evaluateFilter(allMarkers);
    }
    
}
