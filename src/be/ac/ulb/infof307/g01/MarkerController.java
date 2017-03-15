/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import java.sql.Timestamp;

/**
 *
 * @author hoornaert
 */
public class MarkerController {
    
    MarkerModel _marker;
    
    public MarkerController(PokemonModel pokemon, CoordinateModel _newMarkerCoordinate) {
        _marker = new MarkerModel(pokemon, _newMarkerCoordinate);
    }

    public CoordinateModel getCoordinate() {
        return _marker.getCoordinate();
    }

    public Timestamp getTimestamp() {
        return _marker.getTimestamp();
    }

    public String getPokemonName() {
        return _marker.getPokemonName();
    }
    
}
