package be.ac.ulb.infof307.g01.client.controller;

import java.sql.Timestamp;

import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.NewMarkerPopUp;

/**
 * class that manage and create a NewMarkerPopUp.
 * 
 * @author Groupe01
 */
public class NewMarkerPopUpController extends AbstractMarkerPopUpController {
    
    protected CoordinateSendableModel _newMarkerCoordinate;
    
    public NewMarkerPopUpController(MarkerController markerController, double coordinateX, double coordinateY) {
        super(markerController);
        _markerPopUp = new NewMarkerPopUp(this, PokemonModel.getAllPokemonName());
        _markerPopUp.setPokemonView(_defaultImagePath);
        _newMarkerCoordinate = new CoordinateSendableModel(coordinateX, coordinateY);
    }
    
    @Override
    public void endPopUpMarker(String pokemonName, int lifePoint, int attack, int defense, Timestamp dateView) {
        if(pokemonName != "" && pokemonName != null) { // better safe than sorry
            _markerPopUp.close();
            _markerPopUp = null;
            PokemonModel pokemon = PokemonModel.getPokemonByName(pokemonName);
            _markerController.createMarker(pokemon, _newMarkerCoordinate, lifePoint, attack, defense, dateView);
        }
        else {
            _markerPopUp.errorInPokemonName();
        }
    }
    
    public void askForCreateMarker(double coordinateX, double coordinateY) {
        if(_markerPopUp == null) {
            // Converts from event coordinate (centered in the upper left corner)
            // to marker coordinate (centered in the middle of the image)
            _newMarkerCoordinate = new CoordinateSendableModel(coordinateX, coordinateY);
            _markerPopUp = new NewMarkerPopUp(this, PokemonModel.getAllPokemonName());
            _markerPopUp.setPokemonView(_defaultImagePath);
        }
    }
    
}
