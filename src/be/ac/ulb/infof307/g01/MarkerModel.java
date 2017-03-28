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
    private ReputationScore _reputation;
    private int _lifePoint, _attack, _defense; // TODO init in constructor


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
    
    public MarkerModel(PokemonModel pokemon, CoordinateModel coordinate, boolean createPin) {
        this(pokemon, coordinate, createPin, 0, 0);
    }
    
    public MarkerModel(PokemonModel pokemon, CoordinateModel coordinate,
            boolean createPin, int upVote, int downVote) {
        _pokemon = pokemon;
        _coordinate = coordinate;
        
        Long currentTime = System.currentTimeMillis();
        _timestamp = new Timestamp(currentTime);
        _isValid = true;
        
        _reputation = new ReputationScore(upVote, downVote);
    }
    
    public int getReputationScore() {
        return _reputation.getScore();
    }
    
    public void voteUp() {
        _reputation.voteUp();
    }
    
    public void voteDown() {
        _reputation.voteDown();
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

    public int getPokemonAttack() {
        return _attack;
    }
    
    public int getPokemonDefense() {
        return _defense;
    }
    
    public int getPokemonLife() {
        return _lifePoint;
    }

    public int getVoteScore() {
        return _reputation.getScore();
    }
   
}
