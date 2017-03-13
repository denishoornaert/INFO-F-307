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
    /**
     * Indicates whether this marker contains valid information.
     * If false, the display shows input fields to select the pokemon
     * and the date.
     */
    private boolean _isValid = false;
    private Pin _pin = null;

    public Marker() {
        this(null, null);
    }
    
    public Marker(Pokemon pokemon, Coordinate coordinate) {
        this(pokemon, coordinate, true);
    }
    
    public Marker(Pokemon pokemon, Coordinate coordinate, boolean createPin) {
        _pokemon = pokemon;
        _coordinate = coordinate;
        
        Long currentTime = System.currentTimeMillis();
        _timestamp = new Timestamp(currentTime);
        _isValid = true;
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

    void setPin(Pin newPin) {
        _pin = newPin;
    }
    
}
