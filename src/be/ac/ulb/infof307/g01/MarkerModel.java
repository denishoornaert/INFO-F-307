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
public class MarkerModel {
    
    private final PokemonModel _pokemon;
    private final CoordinateModel _coordinate;
    private Timestamp _timestamp;
    
    /**
     * Indicates whether this marker contains valid information.
     * If false, the display shows input fields to select the pokemon
     * and the date.<br />
     * TODO: utile ?  A supprime si pas utile
     */
    private boolean _isValid = false;
    
    public MarkerModel(PokemonModel pokemon, CoordinateModel coordinate) {
        this(pokemon, coordinate, true);
    }
    
    public MarkerModel(PokemonModel pokemon, CoordinateModel coordinate, 
            boolean createPin) {
        _pokemon = pokemon;
        _coordinate = coordinate;
        
        Long currentTime = System.currentTimeMillis();
        _timestamp = new Timestamp(currentTime);
        _isValid = true;
    }

    public MarkerModel(String pokemonName, int xCoordinate, int yCoordinate, 
            Timestamp newTimestamp) {
        this(PokemonModel.getPokemonByName(pokemonName), 
                new CoordinateModel(xCoordinate, yCoordinate));
        _timestamp = newTimestamp;
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
    
    public CoordinateModel getCoordinate() {
        return _coordinate;
    }
    
    public boolean equals(MarkerModel other) {
        return _pokemon.getName().equals(other.getPokemonName())
                && _timestamp.equals(other.getTimestamp())
                && _coordinate.equals(other.getCoordinate());
    }
    
}
