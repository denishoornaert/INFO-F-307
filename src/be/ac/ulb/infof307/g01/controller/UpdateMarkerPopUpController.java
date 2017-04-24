/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.controller;

import static be.ac.ulb.infof307.g01.controller.AbstractMarkerPopUpController._defaultImagePath;
import be.ac.ulb.infof307.g01.model.CoordinateModel;
import be.ac.ulb.infof307.g01.model.PokemonModel;
import be.ac.ulb.infof307.g01.view.UpdateMarkerPopUp;
import java.sql.Timestamp;

/**
 *
 * @author hoornaert
 */
public class UpdateMarkerPopUpController extends AbstractMarkerPopUpController {
    
    int _markerId;
    
    public UpdateMarkerPopUpController(MarkerController markerController, int markerId) {
        super(markerController);
        System.out.println("1");
        _markerId = markerId;
        System.out.println("2");
        _markerPopUp = new UpdateMarkerPopUp(this, PokemonModel.getAllPokemonName(), markerId);
        System.out.println("3");
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
    
    @Override
    public void askForCreateMarker(double coordinateX, double coordinateY) {
        System.out.println("be.ac.ulb.infof307.g01.controller.UpdateMarkerPopUpController.askForCreateMarker()");
    }
    
}
