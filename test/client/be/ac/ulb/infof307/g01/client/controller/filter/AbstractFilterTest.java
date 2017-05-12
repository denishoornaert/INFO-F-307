package be.ac.ulb.infof307.g01.client.controller.filter;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.model.PokemonTypeModel;
import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.junit.BeforeClass;

/**
 *
 * Abstract class for filter testing
 * 
 * Instanciate all the required variables
 */
public class AbstractFilterTest {
    // TODO (@Loan) : éventuellement renommer la classe étant donné qu'elle ne test strictement rien
    
    protected static final PokemonTypeModel FIRE_TYPE = new PokemonTypeModel("FIRE");
    protected static final PokemonTypeModel WATER_TYPE = new PokemonTypeModel("WATER");
    protected static final PokemonTypeModel BUG_TYPE = new PokemonTypeModel("BUG");
    
    protected static final String[] POKEMON_NAMES = {"P1", "P2", "P3", "P4"};
    protected static final PokemonTypeModel[][] POKEMON_TYPES = {{FIRE_TYPE, BUG_TYPE}, {BUG_TYPE}, {WATER_TYPE}, {WATER_TYPE, BUG_TYPE}};
    protected static List<PokemonModel> _pokemons;
    
    protected static HashSet<MarkerModel> _allMarkers;
    
    protected static final CoordinateSendableModel COORD = new CoordinateSendableModel();
    
    
    @BeforeClass
    public static void setUpClass() {
        createPokemons();
        createMarkers();
    } 
    
    private static void createPokemons() {
        _pokemons = new ArrayList<>();
        for(int i = 0; i < POKEMON_NAMES.length; ++i) {
            PokemonSendableModel pokemon;
            if(POKEMON_TYPES[i].length == 1) {
                pokemon = new PokemonSendableModel(POKEMON_NAMES[i], "",
                        POKEMON_TYPES[i][0]);
            } else {
                pokemon = new PokemonSendableModel(POKEMON_NAMES[i], "",
                        POKEMON_TYPES[i][0], POKEMON_TYPES[i][1]);
            }
            _pokemons.add(new PokemonModel(pokemon));
        }
    }
    
    private static int lifeCounter = 1;  // used so that all markers are different
    
    private static MarkerModel makeMarkerModel(PokemonModel pokemon) {
        return new MarkerModel(pokemon, COORD, "", ++lifeCounter, 0, 0, new Timestamp(new Date().getTime()), false);
    }
    
    private static void createMarkers() {
        ArrayList<MarkerModel> markersList = new ArrayList<>();
        // two pokemons P1
        markersList.add(makeMarkerModel(_pokemons.get(0)));
        markersList.add(makeMarkerModel(_pokemons.get(0)));
        // one pokemon P2
        markersList.add(makeMarkerModel(_pokemons.get(1)));
        // two pokemons P3
        markersList.add(makeMarkerModel(_pokemons.get(2)));
        markersList.add(makeMarkerModel(_pokemons.get(2)));
        // three pokemons P4
        markersList.add(makeMarkerModel(_pokemons.get(3)));
        markersList.add(makeMarkerModel(_pokemons.get(3)));
        markersList.add(makeMarkerModel(_pokemons.get(3)));
        
        _allMarkers = new HashSet<>(markersList);
    }
}
