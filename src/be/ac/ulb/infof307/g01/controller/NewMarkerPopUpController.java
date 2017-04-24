/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.controller;

import static be.ac.ulb.infof307.g01.controller.AbstractMarkerPopUpController._defaultImagePath;
import be.ac.ulb.infof307.g01.model.CoordinateModel;
import be.ac.ulb.infof307.g01.model.PokemonModel;
import be.ac.ulb.infof307.g01.view.NewMarkerPopUp;
import java.sql.Timestamp;

/**
 *
 * @author hoornaert
 */
public class NewMarkerPopUpController extends AbstractMarkerPopUpController {
    
    protected CoordinateModel _newMarkerCoordinate;
    
    public NewMarkerPopUpController(MarkerController markerController, double coordinateX, double coordinateY) {
        super(markerController);
        _markerPopUp = new NewMarkerPopUp(this, PokemonModel.getAllPokemonName());
        _markerPopUp.setPokemonView(_defaultImagePath);
        _newMarkerCoordinate = new CoordinateModel(coordinateX, coordinateY);
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
    
    @Override
    public void askForCreateMarker(double coordinateX, double coordinateY) {
        if(_markerPopUp == null) {
            // Converts from event coordinate (centered in the upper left corner)
            // to marker coordinate (centered in the middle of the image)
            _newMarkerCoordinate = new CoordinateModel(coordinateX, coordinateY);
            _markerPopUp = new NewMarkerPopUp(this, PokemonModel.getAllPokemonName());
            _markerPopUp.setPokemonView(_defaultImagePath);
        }
    }
    
}
