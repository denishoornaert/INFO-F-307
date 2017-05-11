package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import java.util.HashSet;

/**
 *
 * Unique filter on pokemon name
 */
public class FilterOnName extends AbstractFilterController {

    /**
     * Name to be equal in order to be in the filter result
     */
    private final String _name;
    
    /**
     * Constructor 
     * @param name The pokemon name to filter
     */
    public FilterOnName(String name) {
        super();
        _name = name;
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
