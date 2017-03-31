package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.db.DatabaseModel;
import be.ac.ulb.infof307.g01.db.MarkerDatabaseModel;
import java.sql.Timestamp;

/** Model of a marker. A marker contains the location of a spotted pokemon,
 * the pokemon, the timestamp of the spot, etc...
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

    public MarkerModel(int databaseId, String pokemonName, double latitude, double longitude, 
            Timestamp timestamp, int upVotes, int downVotes) {
        this(databaseId, PokemonModel.getPokemonByName(pokemonName), 
                new CoordinateModel(latitude, longitude), timestamp, upVotes, downVotes);
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
        _database.updateMarkerReputation(this);
    }
    
    public void voteDown() {
        _reputation.vote(ReputationVoteModel.DOWN);
        _database.updateMarkerReputation(this);
    }
    
    public void setTimestamp(Timestamp newTimestamp) {
        _timestamp = newTimestamp;
        // TODO Set timestamp in DB
    }
    
    public Timestamp getTimestamp() {
        return _timestamp;
    }
    
    public String getPokemonName() {
        return _pokemon.getName();
    }
    
    public String getImagePath() {
        return _pokemon.getImagePath();
    }
    
    public String getImageName() {
        return _pokemon.getImageName();
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

    public int getUpVotes() {
        return _reputation.getUpVotes();
    }

    public int getDownVotes() {
        return _reputation.getDownVotes();
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
