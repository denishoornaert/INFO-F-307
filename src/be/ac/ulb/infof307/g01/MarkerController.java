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
    
    public double getLatitude() {
        return _marker.getCoordinate().getLatitude();
    }
    
    public double getLongitude() {
        return _marker.getCoordinate().getLongitude();
    }

    public Timestamp getTimestamp() {
        return _marker.getTimestamp();
    }

    public String getPokemonName() {
        return _marker.getPokemonName();
    }
    
    public int getPokemonAttack() {
        return _marker.getPokemonAttack();
    }
    
    public int getPokemonDefense() {
        return _marker.getPokemonDefense();
    }
    
    public int getPokemonLife() {
        return _marker.getPokemonLife();
    }
}
