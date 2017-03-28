package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.NewMarkerPopUp;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * TODO: add description
 */
public class NewMarkerPopUpController {
    
    private PokemonListModel _pokemonListModel;
    private NewMarkerPopUp _newMarkerPopUp;
    /** Coordinates associated to the current popup. */
    private CoordinateModel _newMarkerCoordinate;
    private MarkerController _markerController;
 
    public NewMarkerPopUpController(MarkerController markerController) {
        _pokemonListModel = new PokemonListModel();
        //_pokemonListModel = new PokemonListModel("Pikachu", "pikata", "Dracaufeu", "bullbizar");
        _markerController = markerController;
    }
    
    /*
    * Method that aims to return the set of pokemon for which the name match the
    * pattern given in parameters
    *
    * @parameters the pattern
    * @return the list of pokemon names that matches the pattern
    */
    public ArrayList<String> getPokemonByName(String researchPattern) {
        ArrayList<String> res;
        if(researchPattern.length() > 1) {
            res = _pokemonListModel.findPokemonFromPattern(researchPattern);
        }
        else {
            res = _pokemonListModel.getAllNames();
        }
        return res;
    }
    
    public void askForCreateMarker(double coordinateX, double coordinateY) {
        if(_newMarkerPopUp == null) {
            // Converts from event coordinate (centered in the upper left corner)
            // to marker coordinate (centered in the middle of the image)
            _newMarkerCoordinate = new CoordinateModel(coordinateX, coordinateY);
            _newMarkerPopUp = new NewMarkerPopUp(this);
        }
    }
    
    public void cancelPopUpCreateMarker() {
        _newMarkerPopUp.close();
        _newMarkerPopUp = null;
    }
    
    public void endPopUpCreateMarker(String pokemonName, int lifePoint, int attack, int defense, Timestamp dateView) {
        _newMarkerPopUp.close();
        _newMarkerPopUp = null;
        // TODO relier popUp pokemon avec la creation des pokemons 
        PokemonModel pokemon = PokemonModel.getPokemonByName(pokemonName);
        _newMarkerCoordinate = null;
        _markerController.createMarker(pokemon, _newMarkerCoordinate);
        //_mapController.addMarkerController(newMarker);
        //_mapController.getMapView().createPin(newMarker);
    }
    
}
