package be.ac.ulb.infof307.g01.client.controller;

import java.sql.Timestamp;

import be.ac.ulb.infof307.g01.client.model.CoordinateModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.NewMarkerPopUp;

/**
 * Controller the NewMarkerPopUp that enable the user to create a new Marker.
 */
public class NewMarkerPopUpController {
    
    private NewMarkerPopUp _newMarkerPopUp;
    /** Coordinates associated to the current pop-up. */
    private CoordinateModel _newMarkerCoordinate;
    private final static String _defaultImagePath = "assets/unknown_pokemon.png";
    private MarkerController _markerController;
 
    public NewMarkerPopUpController(MarkerController markerController) {
        _markerController = markerController;
    }
    
    public void askForCreateMarker(double coordinateX, double coordinateY) {
        if(_newMarkerPopUp == null) {
            // Converts from event coordinate (centered in the upper left corner)
            // to marker coordinate (centered in the middle of the image)
            _newMarkerCoordinate = new CoordinateModel(coordinateX, coordinateY);
            _newMarkerPopUp = new NewMarkerPopUp(this, PokemonModel.getAllPokemonName());
            _newMarkerPopUp.setPokemonView(_defaultImagePath);
        }
    }
    
    public void cancelPopUpCreateMarker() {
        _newMarkerPopUp.close();
        _newMarkerPopUp = null;
    }
    
    public void endPopUpCreateMarker(String pokemonName, int lifePoint, int attack, int defense, Timestamp dateView) {
        if(pokemonName != "" && pokemonName != null) { // better safe than sorry
            _newMarkerPopUp.close();
            _newMarkerPopUp = null;
            PokemonModel pokemon = PokemonModel.getPokemonByName(pokemonName);
            _markerController.createMarker(pokemon, _newMarkerCoordinate);
            _newMarkerCoordinate = null;
        }
        else {
            _newMarkerPopUp.errorInPokemonName();
        }
    }
    
    /*
    * Method called when the user has selected a pokemon name listed among the combobox.
    */
    public void selectedPokemonName(String selectedString) {
        PokemonModel pokemon = PokemonModel.getPokemonByName(selectedString);
        String path = pokemon.getImagePath();
        _newMarkerPopUp.setPokemonView(path);
    }
    
}