/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.Pin;
import java.sql.Timestamp;

/**
 *
 * @author remy
 */
public class Marker {
    
    private final Pokemon _pokemon;
    private final Coordinate _coordinate;
    private Timestamp _timestamp;
    private final Pin _pin;
    
    public Marker(Pokemon pokemon, Coordinate coordinate) {
        _pokemon = pokemon;
        _coordinate = coordinate;
        _pin = new Pin(this);
        
        Long currentTime = System.currentTimeMillis();
        _timestamp = new Timestamp(currentTime);
    }
    
    public void setTimestamp(Timestamp newTimestamp) {
        _timestamp = newTimestamp;
    }
    
    public String getPathImage() {
        return _pokemon.getPathImage();
    }
    
    public Coordinate getCoordinate() {
        return _coordinate;
    }
    
}
