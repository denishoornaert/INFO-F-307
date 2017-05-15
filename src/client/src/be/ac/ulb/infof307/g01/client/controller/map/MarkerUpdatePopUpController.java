package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.model.map.PokemonCache;
import be.ac.ulb.infof307.g01.client.model.map.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.map.MarkerUpdatePopUp;
import java.sql.Timestamp;

/**
 * Creates and controls a MarkerUpdatePopUp used to update existing marker's informations.
 * Used when the user clicks on a marker he created.
 */
public class MarkerUpdatePopUpController extends AbstractMarkerChangePopUpController {
    
    private final int _markerId;
    
    public MarkerUpdatePopUpController(final MarkerController markerController,
            final int markerId) throws InstantiationException {
        super(markerController, markerId);
        _markerId = markerId;
        _markerPopUp = new MarkerUpdatePopUp(this, PokemonCache.getInstance().getAllPokemonNames(), markerId);
        final String imagePath = _marker.getImagePath();
        _markerPopUp.setPokemonView(imagePath);
    }
    
    @Override
    public void endPopUpMarker(final String pokemonName, final int lifePoint,
            final int attack, final int defense, final Timestamp dateView) {
        super.endPopUpMarker(pokemonName, lifePoint, attack, defense, dateView);
        if(isPokemonNameNotEmpty(pokemonName)) { 
            final PokemonModel pokemon = PokemonCache.getInstance().getPokemonByName(pokemonName);
            _markerController.updateMarker(_markerId, pokemon, lifePoint, attack, defense, dateView);
        } 
    }
    
}
