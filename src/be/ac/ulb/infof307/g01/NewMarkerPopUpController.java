/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.NewMarkerPopUp;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author hoornaert
 */
public class NewMarkerPopUpController {
    
    private PokemonListModel _pokemonListModel;
    private NewMarkerPopUp _newMarkerPopUp;
    /** Coordinates associated to the current popup. */
    private CoordinateModel _newMarkerCoordinate;
    private MapController _mapController;
 
    public NewMarkerPopUpController(MapController map) {
        //_pokemonListModel = new PokemonListModel();
        _pokemonListModel = new PokemonListModel("Pikachu", "pikata", "Dracaufeu", "bullbizar");
        _mapController = map;
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
        if(researchPattern.length() > 2) {
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
    
    public void endPopUpCreateMarker(String pokemonName, Timestamp dateView) {
        _newMarkerPopUp.close();
        _newMarkerPopUp = null;
        // TODO relier popUp pokemon avec la creation des pokemons 
        PokemonModel pokemon = new PokemonModel(pokemonName, PokemonTypeModel.DARK,-1,-1,-1,-1,-1,-1);
        MarkerController newMarker = new MarkerController(pokemon, _newMarkerCoordinate);
        _newMarkerCoordinate = null;
        _mapController.addMarkerController(newMarker);
        _mapController.getMapView().createPin(newMarker);
    }
    
}
