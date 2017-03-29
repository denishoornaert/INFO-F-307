package be.ac.ulb.infof307.g01;

import java.sql.Timestamp;

/**
 * TODO: add description
 */
public class MarkerModel {
    
    private final PokemonModel _pokemon;
    private final CoordinateModel _coordinate;
    private Timestamp _timestamp;
    private ReputationScore _reputation;
    private int _lifePoint, _attack, _defense; // TODO init in constructor
    
    public MarkerModel(PokemonModel pokemon, CoordinateModel coordinate) {
        this(pokemon, coordinate, 0, 0);
    }
    
    public MarkerModel(PokemonModel pokemon, CoordinateModel coordinate, int upVote, int downVote) {
        _pokemon = pokemon;
        _coordinate = coordinate;
        
        Long currentTime = System.currentTimeMillis();
        _timestamp = new Timestamp(currentTime);
        
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
