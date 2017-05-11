package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 *
 * Filter on pokemon type
 */
public class FilterOnType extends AbstractFilterController {
    
    /**
     * Type to have in order to be in the filter result
     */
    private final String _type;

    /**
     * Constructor
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
