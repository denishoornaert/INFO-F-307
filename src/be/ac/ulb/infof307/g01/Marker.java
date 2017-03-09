/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import java.sql.Timestamp;

/**
 *
 * @author remy
 */
public class Marker {
    
    private final Pokemon _pokemon;
    private final Coordinate _coordinate;
    private Timestamp _timestamp;
    
    public Marker(Pokemon pokemon, Coordinate coordinate) {
        _pokemon = pokemon;
        _coordinate = coordinate;
        
        Long currentTime = System.currentTimeMillis();
        _timestamp = new Timestamp(currentTime);
    }
    
    public void setTimestamp(Timestamp newTimestamp) {
        _timestamp = newTimestamp;
    }
    
    public Timestamp getTimestamp() {
        return _timestamp;
    }
    
    public String getPokemonName() {
        return _pokemon.getName();
    }
    
    public String getPathImage() {
        return _pokemon.getPathImage();
    }
    
    public Coordinate getCoordinate() {
        return _coordinate;
    }
    
}
