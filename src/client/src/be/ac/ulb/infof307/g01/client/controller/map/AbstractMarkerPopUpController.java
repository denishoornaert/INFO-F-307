package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import be.ac.ulb.infof307.g01.client.model.PokemonCache;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.map.AbstractMarkerPopUp;
import java.sql.Timestamp;

/**
 * Controls NewMarkerPopUp instances that enable the user to create markers.
 */
public abstract class AbstractMarkerPopUpController extends InformationPopUpController {
    
    protected AbstractMarkerPopUp _markerPopUp;
    protected String _defaultImagePath;
    
    public AbstractMarkerPopUpController(MarkerController markerController) throws InstantiationException {
        this(markerController, DEFAULT_MARKER_ID);
    }
    
    public AbstractMarkerPopUpController(MarkerController markerController, int markerid) throws InstantiationException  {
        super(markerController, markerid);
        _defaultImagePath = ClientConfiguration.getInstance().getUnknownPokemonSpritePath();
    }
    
    /**
     * Checks if a string is neither null nor empty.
     * @param pokemonName the string to check
     * @return true if the string isn't empty nor null, false otherwise
     */
    protected boolean isPokemonNameNotEmpty(String pokemonName) {
        return (!"".equals(pokemonName) && pokemonName != null);
    }
    
    /**
     * Cancels the marker creation and closes the popup.
     */
    public void cancelPopUpCreateMarker() {
        close(_markerPopUp);
    }
    
    /**
     * Tries to create a marker with the provided parameters, 
     * @param pokemonName
     * @param lifePoint
     * @param attack
     * @param defense
     * @param dateView 
     */
    public void endPopUpMarker(String pokemonName, int lifePoint, int attack, int defense, Timestamp dateView) {
        if(isPokemonNameNotEmpty(pokemonName)) { 
            cancelPopUpCreateMarker();
        }
        else {
            _markerPopUp.errorInPokemonName();
        }
    }
    
    /**
     * Selects a Pokemon and displays its sprite in the popup.
     * Called when the user selects a name among the combo box. 
     * @param selectedName the name of the selected pokemon.
     */
    public void selectPokemon(String selectedName) {
        PokemonModel pokemon = PokemonCache.getInstance().getPokemonByName(selectedName);
        String path = pokemon.getImagePath();
        _markerPopUp.setPokemonView(path);
    }
    
    public MarkerController getMarkerController() {
        return _markerController;
    }
    
}