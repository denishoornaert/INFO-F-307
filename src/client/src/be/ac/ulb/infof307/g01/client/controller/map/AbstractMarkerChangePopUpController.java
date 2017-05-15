package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.model.app.ClientConfiguration;
import be.ac.ulb.infof307.g01.client.model.map.PokemonCache;
import be.ac.ulb.infof307.g01.client.model.map.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.map.AbstractMarkerChangePopUp;
import java.sql.Timestamp;

/**
 * Abstract class that controls marker popups used to change or create markers.
 */
public abstract class AbstractMarkerChangePopUpController extends AbstractMarkerPopUpController {
    
    protected AbstractMarkerChangePopUp _markerPopUp;
    protected String _defaultImagePath;
    
    public AbstractMarkerChangePopUpController(final MarkerController markerController)
            throws InstantiationException {
        this(markerController, DEFAULT_MARKER_ID);
    }
    
    public AbstractMarkerChangePopUpController(final MarkerController markerController,
            final int markerid) throws InstantiationException  {
        super(markerController, markerid);
        _defaultImagePath = ClientConfiguration.getInstance().getUnknownPokemonSpritePath();
    }
    
    /**
     * Checks if a string is neither null nor empty.
     * @param pokemonName the string to check
     * @return true if the string isn't empty nor null, false otherwise
     */
    protected boolean isPokemonNameNotEmpty(final String pokemonName) {
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
    public void endPopUpMarker(final String pokemonName, final int lifePoint,
            final int attack, final int defense, final Timestamp dateView) {
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
    public void selectPokemon(final String selectedName) {
        final PokemonModel pokemon = PokemonCache.getInstance().getPokemonByName(selectedName);
        final String path = pokemon.getImagePath();
        _markerPopUp.setPokemonView(path);
    }
    
    public MarkerController getMarkerController() {
        return _markerController;
    }
    
}