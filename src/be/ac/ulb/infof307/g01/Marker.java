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
    private Pin _pin = null;
    
    
    public Marker(Pokemon pokemon, Coordinate coordinate, boolean createPin) {
        _pokemon = pokemon;
        _coordinate = coordinate;
        
        Long currentTime = System.currentTimeMillis();
        _timestamp = new Timestamp(currentTime);
        
        if (createPin) {
            createPin();
        }
    }
    
    public Marker(Pokemon pokemon, Coordinate coordinate) {
        this(pokemon, coordinate, true);
    }
    
    private void createPin() {
        if(_pin == null) {
            _pin = new Pin(this);
        } else {
            throw new RuntimeException("pin allready created");
        }
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
