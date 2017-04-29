package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.UpdateMarkerPopUp;
import java.sql.Timestamp;

/**
 * TODO description
 * 
 * @author Groupe01
 */
public class UpdateMarkerPopUpController extends AbstractMarkerPopUpController {
    
    int _markerId;
    
    public UpdateMarkerPopUpController(MarkerController markerController, int markerId) {
        super(markerController, markerId);
        _markerId = markerId;
        _markerPopUp = new UpdateMarkerPopUp(this, PokemonModel.getAllPokemonName(), markerId);
        _markerPopUp.setPokemonView(_marker.getImagePath());
    }
    
    @Override
    public void endPopUpMarker(String pokemonName, int lifePoint, int attack, int defense, Timestamp dateView) {
        if(pokemonName != "" && pokemonName != null) { // better safe than sorry
            _markerPopUp.close();
            _markerPopUp = null;
            PokemonModel pokemon = PokemonModel.getPokemonByName(pokemonName);
            _markerController.updateMarker(_markerId, pokemon, lifePoint, attack, defense, dateView);
        }
        else {
            _markerPopUp.errorInPokemonName();
        }
    }
    
    // TODO refactor
    @Override
    public void askForCreateMarker(double coordinateX, double coordinateY) {}
    
}
