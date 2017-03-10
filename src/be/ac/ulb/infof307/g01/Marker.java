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
    /**
     * Indicates whether this marker contains valid information.
     * If false, the display shows input fields to select the pokemon
     * and the date.
     */
    private boolean _isValid = false;
    
    public Marker(Pokemon pokemon, Coordinate coordinate) {
        _pokemon = pokemon;
        _coordinate = coordinate;
        
        Long currentTime = System.currentTimeMillis();
        _timestamp = new Timestamp(currentTime);
        _isValid = true;
    }

    public Marker() {
        _pokemon = null;
        _coordinate = null;
    }
    
    public void setTimestamp(Timestamp newTimestamp) {
        _timestamp = newTimestamp;
    }
    
    public String getPathImage() {
        return _pokemon.getPathImage();
    }
    
}
