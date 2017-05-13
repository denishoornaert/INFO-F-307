package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import java.util.HashSet;

/**
 * Unique filter on pokemon name.
 * Its evaluation consists in comparing the pokemon name to the marker's
 * pokemon name. Identical names will result in the markers being included in 
 * the filter results.
 */
public class FilterOnName extends AbstractFilterController {

    /** Name to compare to the marker's pokemon name */
    private final String _name;
    
    /**
     * Creates a FilterOnName instance for the provided pokemon name.
     * @param name the pokemon name to filter
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
