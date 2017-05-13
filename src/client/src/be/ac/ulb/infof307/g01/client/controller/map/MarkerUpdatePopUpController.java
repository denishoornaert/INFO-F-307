package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.model.PokemonCache;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.map.MarkerUpdatePopUp;
import java.sql.Timestamp;

/**
 * Creates and controls a MarkerUpdatePopUp used to update existing marker's informations.
 * Used when the user clicks on a marker he created.
 */
public class MarkerUpdatePopUpController extends AbstractMarkerChangePopUpController {
    
    private int _markerId;
    
    public MarkerUpdatePopUpController(MarkerController markerController, int markerId) throws InstantiationException {
        super(markerController, markerId);
        _markerId = markerId;
        _markerPopUp = new MarkerUpdatePopUp(this, PokemonCache.getInstance().getAllPokemonNames(), markerId);
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
