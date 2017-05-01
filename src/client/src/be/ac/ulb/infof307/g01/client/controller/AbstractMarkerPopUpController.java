package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import java.sql.Timestamp;

import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.AbstractMarkerPopUp;

/**
 * Controller the NewMarkerPopUp that enable the user to create a new Marker.
 */
public abstract class AbstractMarkerPopUpController {
    
    protected AbstractMarkerPopUp _markerPopUp;
    protected static String _defaultImagePath;
    protected MarkerController _markerController;
    protected MarkerModel _marker;
    private static int DEFAULT_MARKER_ID = 0;
 
    public AbstractMarkerPopUpController(MarkerController markerController) {
        this(markerController, DEFAULT_MARKER_ID);
    }
    
    public AbstractMarkerPopUpController(MarkerController markerController, int markerid) {
        _markerController = markerController;
        _marker = _markerController.getMarkerModelFromId(markerid);
        _defaultImagePath = ClientConfiguration.getInstance().getUnknownPokemonSpritePath();
    }
        
    public void cancelPopUpCreateMarker() {
        _markerPopUp.close();
        _markerPopUp = null;
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
        PokemonModel pokemon = PokemonModel.getPokemonByName(selectedString);
        String path = pokemon.getImagePath();
        _markerPopUp.setPokemonView(path);
    }
    
    public MarkerController getMarkerController() {
        return _markerController;
    }
    
}