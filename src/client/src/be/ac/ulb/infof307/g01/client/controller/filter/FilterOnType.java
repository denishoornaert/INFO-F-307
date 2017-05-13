package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Unique filter on pokemon type.
 * Its evaluation consists in comparing the provided pokemon type to the
 * marker's pokemon type. Identical types will result in the markers being
 * included in the filter results.
 */
public class FilterOnType extends AbstractFilterController {
    
    /** Type to compare to the marker's pokemon type */
    private final String _type;

    /**
     * Creates a FilterOnType instance for the provided pokemon type.
     * @param type The pokemon type to filter
     */
    public FilterOnType(String type) {
        super();
        _type = type;
    }

    @Override
    public HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers) {
        HashSet<MarkerModel> markersToReturn = new HashSet<>();
        for(MarkerModel marker : allMarkers) {
            PokemonModel pokemon = marker.getPokemon();
            List<String> allTypes = Arrays.asList(pokemon.getTypeNames());
            if(allTypes.contains(_type)) {
                markersToReturn.add(marker);
            }
        }
        return markersToReturn;
    }
    
}
