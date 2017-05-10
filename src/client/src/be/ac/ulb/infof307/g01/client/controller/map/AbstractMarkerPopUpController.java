package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.controller.app.PokemonCache;
import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import java.sql.Timestamp;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.map.AbstractMarkerPopUp;

/**
 * Controller the NewMarkerPopUp that enable the user to create a new Marker.
 */
public abstract class AbstractMarkerPopUpController extends InformationPopUpController {
    
    protected AbstractMarkerPopUp _markerPopUp;
    protected static String _defaultImagePath; // TODO : static ???
    
 
    public AbstractMarkerPopUpController(MarkerController markerController) throws InstantiationException {
        this(markerController, DEFAULT_MARKER_ID);
    }
    
    public AbstractMarkerPopUpController(MarkerController markerController, int markerid) throws InstantiationException  {
        super(markerController, markerid);
        _defaultImagePath = ClientConfiguration.getInstance().getUnknownPokemonSpritePath();
    }
        
    public void cancelPopUpCreateMarker() {
        close(_markerPopUp);
    }
    
    protected boolean isPokemonNameNotEmpty(String pokemonName) {
        return (!"".equals(pokemonName) && pokemonName != null);
    }
        
    public void endPopUpMarker(String pokemonName, int lifePoint, int attack, int defense, Timestamp dateView) {
        if(isPokemonNameNotEmpty(pokemonName)) { 
            cancelPopUpCreateMarker();
        }
        else {
            _markerPopUp.errorInPokemonName();
        }
    }
    
    /*
    * Method called when the user has selected a pokemon name listed among the combobox.
    */
    public void selectedPokemonName(String selectedString) {
        PokemonModel pokemon = PokemonCache.getInstance().getPokemonByName(selectedString);
        String path = pokemon.getImagePath();
        _markerPopUp.setPokemonView(path);
    }
    
    public MarkerController getMarkerController() {
        return _markerController;
    }
    
}