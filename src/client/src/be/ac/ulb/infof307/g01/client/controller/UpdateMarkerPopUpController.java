package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.UpdateMarkerPopUp;
import java.sql.Timestamp;

/**
 * Class that manages and creates an UpadateMarkerPopUp.
 * 
 * @author Groupe01
 */
public class UpdateMarkerPopUpController extends AbstractMarkerPopUpController {
    
    private int _markerId;
    
    public UpdateMarkerPopUpController(MarkerController markerController, int markerId) throws InstantiationException {
        super(markerController, markerId);
        _markerId = markerId;
        _markerPopUp = new UpdateMarkerPopUp(this, PokemonCache.getInstance().getAllPokemonNames(), markerId);
        String imagePath = _marker.getImagePath();
        _markerPopUp.setPokemonView(imagePath);
    }
    
    @Override
    public void endPopUpMarker(String pokemonName, int lifePoint, int attack, int defense, Timestamp dateView) {
        super.endPopUpMarker(pokemonName, lifePoint, attack, defense, dateView);
        if(isPokemonNameNotEmpty(pokemonName)) { 
            PokemonModel pokemon = PokemonCache.getInstance().getPokemonByName(pokemonName);
            _markerController.updateMarker(_markerId, pokemon, lifePoint, attack, defense, dateView);
        } 
    }
    
}
