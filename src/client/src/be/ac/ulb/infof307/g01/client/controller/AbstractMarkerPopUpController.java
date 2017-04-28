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
    /** Coordinates associated to the current pop-up. */
    protected static String _defaultImagePath;
    protected MarkerController _markerController;
    protected MarkerModel _marker;
 
    public AbstractMarkerPopUpController(MarkerController markerController) {
        this(markerController, 0);
    }
    
    public AbstractMarkerPopUpController(MarkerController markerController, int markerid) {
        _markerController = markerController;
        _marker = _markerController.getMarkerModelFromId(markerid);
        _defaultImagePath = ClientConfiguration.getInstance().getUnknownPokemonImagePath();
    }
    
    public abstract void askForCreateMarker(double coordinateX, double coordinateY);
    
    public void cancelPopUpCreateMarker() {
        _markerPopUp.close();
        _markerPopUp = null;
    }
    
    public abstract void endPopUpMarker(String pokemonName, int lifePoint, int attack, int defense, Timestamp dateView);
    
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