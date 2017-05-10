package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.model.PokemonCache;
import java.sql.Timestamp;

import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.map.NewMarkerPopUp;

/**
 * class that manage and create a NewMarkerPopUp to announce a certain Pokemon
 * to a specific location
 * 
 * @author Groupe01
 */
public class NewMarkerPopUpController extends AbstractMarkerPopUpController {
    
    protected CoordinateSendableModel _newMarkerCoordinate;
    
    public NewMarkerPopUpController(MarkerController markerController, double coordinateX, double coordinateY) throws InstantiationException {
        super(markerController);
        _markerPopUp = new NewMarkerPopUp(this, PokemonCache.getInstance().getAllPokemonNames());
        _markerPopUp.setPokemonView(_defaultImagePath);
        _newMarkerCoordinate = new CoordinateSendableModel(coordinateX, coordinateY);
    }
    
    @Override
    public void endPopUpMarker(String pokemonName, int lifePoint, int attack, int defense, Timestamp dateView) {
        super.endPopUpMarker(pokemonName, lifePoint, attack, defense, dateView);
        if(isPokemonNameNotEmpty(pokemonName)) { 
            PokemonModel pokemon = PokemonCache.getInstance().getPokemonByName(pokemonName);
            _markerController.createMarker(pokemon, _newMarkerCoordinate, lifePoint, attack, defense, dateView);
        } 
    }
    
    public void askForCreateMarker(double coordinateX, double coordinateY) {
        if(_markerPopUp == null) {
            // Converts from event coordinate (centered in the upper left corner)
            // to marker coordinate (centered in the middle of the image)
            _newMarkerCoordinate = new CoordinateSendableModel(coordinateX, coordinateY);
            _markerPopUp = new NewMarkerPopUp(this, PokemonCache.getInstance().getAllPokemonNames());
            _markerPopUp.setPokemonView(_defaultImagePath);
        }
    }
    
}
