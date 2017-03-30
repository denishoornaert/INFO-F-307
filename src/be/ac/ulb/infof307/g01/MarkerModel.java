package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.db.DatabaseModel;
import be.ac.ulb.infof307.g01.db.MarkerDatabaseModel;
import java.sql.Timestamp;

/**
 * TODO: add description
 */
public class MarkerModel {
    /** The ID of this marker in the database. With this attribute,
     * DatabaseModel can know to which database entry this object corresponds.
     */
    private int _databaseId;
    private final PokemonModel _pokemon;
    private final CoordinateModel _coordinate;
    private Timestamp _timestamp;
    private ReputationScore _reputation;
    private int _lifePoint, _attack, _defense; // TODO init in constructor
    private MarkerDatabaseModel _database;
    
    public MarkerModel(PokemonModel pokemon, CoordinateModel coordinate) {
        this(0, pokemon, coordinate, new Timestamp(System.currentTimeMillis()), 0, 0);
    }

    public MarkerModel(int databaseId, String pokemonName, int xCoordinate, int yCoordinate, 
            Timestamp timestamp, int upVotes, int downVotes) {
        this(databaseId, PokemonModel.getPokemonByName(pokemonName), 
                new CoordinateModel(xCoordinate, yCoordinate), timestamp, upVotes, downVotes);
    }
    
    public MarkerModel(int databaseId, PokemonModel pokemon, CoordinateModel coordinate, Timestamp timestamp, int upVotes, int downVotes) {
        _databaseId = databaseId;
        _pokemon = pokemon;
        _coordinate = coordinate;
        _timestamp = timestamp;
        _reputation = new ReputationScore(upVotes, downVotes);
        _database = (MarkerDatabaseModel) DatabaseModel.getDatabase();
    }
    
    public int getReputationScore() {
        return _reputation.getScore();
    }
    
    public void voteUp() {
        _reputation.vote(ReputationVoteModel.UP);
        _database.voteOnMarker(this, ReputationVoteModel.UP);
    }
    
    public void voteDown() {
        _reputation.vote(ReputationVoteModel.DOWN);
        _database.voteOnMarker(this, ReputationVoteModel.DOWN);
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

    /** Get the database ID.
     * This function should be used only by DatabaseModel, since the ID is 
     * managed by it.
     */
    public int getDatabaseId() {
        return _databaseId;
    }

    /** Set the database ID.
     * This function should be used only by DatabaseModel, since the ID is 
     * managed by it.
     */
    public void setDatabaseId(int databaseId) {
        _databaseId = databaseId;
    }
}
