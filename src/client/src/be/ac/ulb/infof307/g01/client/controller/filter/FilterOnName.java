package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import java.util.HashSet;

/**
 *
 * Unique filter on pokemon name
 */
public class FilterOnName extends AbstractFilterController {

    private final String _name;
    
    public FilterOnName(String expression) {
        super(expression);
        _name = expression;
    }

    @Override
    public HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers) {
        HashSet<MarkerModel> markersToReturn = new HashSet<>();
        for(MarkerModel marker : allMarkers) {
            if(marker.getPokemonName().equals(_name)) {
                markersToReturn.add(marker);
            }
        }
        return markersToReturn;
    }
    
}
