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
    
    private final String _type; 

    public FilterOnType(String expression) {
        super(expression);
        _type = expression;
    }

    @Override
    public HashSet<MarkerModel> evaluateFilter(HashSet<MarkerModel> allMarkers) {
        HashSet<MarkerModel> markersToReturn = new HashSet<>();
        for(MarkerModel marker : allMarkers) {
            PokemonModel pokemon = marker.getPokemon();
            List<String> allTypes = Arrays.asList(pokemon.getTypeNames());
            System.out.println(allTypes);
            if(allTypes.contains(_type)) {
                markersToReturn.add(marker);
            }
        }
        return markersToReturn;
    }
    
}
