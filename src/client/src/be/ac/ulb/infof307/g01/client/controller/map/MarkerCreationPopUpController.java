package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.model.map.PokemonCache;
import java.sql.Timestamp;

import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.client.model.map.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.map.MarkerCreationPopUp;

/**
 * Creates and controls a MarkerCreationPopUp used to create new markers.
 * Used when the user creates a new marker.
 */
public class MarkerCreationPopUpController extends AbstractMarkerChangePopUpController {
    
    protected CoordinateSendableModel _newMarkerCoordinate;
    
    public MarkerCreationPopUpController(final MarkerController markerController,
            final double coordinateX, final double coordinateY) throws InstantiationException {
        super(markerController);
        _markerPopUp = new MarkerCreationPopUp(this, PokemonCache.getInstance().getAllPokemonNames());
        _markerPopUp.setPokemonView(_defaultImagePath);
        _newMarkerCoordinate = new CoordinateSendableModel(coordinateX, coordinateY);
    }
    
    @Override
    public void endPopUpMarker(final String pokemonName, final int lifePoint,
            final int attack, final int defense, final Timestamp dateView) {
        super.endPopUpMarker(pokemonName, lifePoint, attack, defense, dateView);
        if(isPokemonNameNotEmpty(pokemonName)) { 
            final PokemonModel pokemon = PokemonCache.getInstance().getPokemonByName(pokemonName);
            _markerController.createMarker(pokemon, _newMarkerCoordinate, lifePoint, attack, defense, dateView);
        } 
    }
    
    public void askForCreateMarker(final double coordinateX, final double coordinateY) {
        if(_markerPopUp == null) {
            // Converts from event coordinate (centered in the upper left corner)
            // to marker coordinate (centered in the middle of the image)
            _newMarkerCoordinate = new CoordinateSendableModel(coordinateX, coordinateY);
            _markerPopUp = new MarkerCreationPopUp(this, PokemonCache.getInstance().getAllPokemonNames());
            _markerPopUp.setPokemonView(_defaultImagePath);
        }
    }
    
}
