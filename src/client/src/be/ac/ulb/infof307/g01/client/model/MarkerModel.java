package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.client.Main;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Timestamp;

/** Model of a marker. A marker contains the location of a spotted pokemon,
 * the pokemon, the timestamp of the spot, etc...
 */
public class MarkerModel {
    /** The ID of this marker in the database. With this attribute,
     * DatabaseModel can know to which database entry this object corresponds.
     */
    private int _databaseId;
    private final String _username;
    private PokemonModel _pokemon;
    private CoordinateModel _coordinate;
    private Timestamp _timestamp;
    private ReputationScoreModel _reputation;
    private int _lifePoint, _attack, _defense;
    private MarkerDatabaseModel _database;
    
    /**
     * Create a marker pokemon (call from gui)
     * 
     * @param pokemon pokemon in link with this marker
     * @param coordinate location of this marker
     * @param username of user who create this marker
     * @param lifePoint pokemon life point
     * @param attack pokemon attack stat
     * @param defense pokemon defense stat
     * @param date the date of the marker
     */
    public MarkerModel(PokemonModel pokemon, CoordinateModel coordinate, 
            String username, int lifePoint, int attack, int defense, Timestamp date) {
        this(0, username, pokemon, coordinate, date, 0, 0, lifePoint, attack, defense);
        _database.insertMarker(this);
    }
    
    /**
     * Constructor to create marker in memory (not in database)
     * 
     * @param databaseId id of the pokemon in database (0 if not exist)
     * @param username of user who create this marker
     * @param pokemonName pokemon name
     * @param latitude x coordinate
     * @param longitude y coordinate
     * @param timestamp time when the pokemon has been witnessed
     * @param upVotes positif votes about this maker
     * @param downVotes negatif votes about this marker
     * @param lifePoint pokemon life point
     * @param attack pokemon attack stat
     * @param defense pokemon defense stat
     */
    public MarkerModel(int databaseId, String username, String pokemonName, double latitude, double longitude, 
            Timestamp timestamp, int upVotes, int downVotes, int lifePoint, int attack, int defense) {
        this(databaseId, username, PokemonModel.getPokemonByName(pokemonName), 
                new CoordinateModel(latitude, longitude), timestamp, upVotes, downVotes, lifePoint, attack, defense);
    }
    
    /**
     * Constructor (not in database)
     * 
     * @param databaseId id of the pokemon in database (0 if not exist)
     * @param username of user who create this marker
     * @param pokemon pokemon
     * @param coordinate location
     * @param timestamp time when the pokemon has been witnessed
     * @param upVotes positif votes about this maker
     * @param downVotes negatif votes about this marker
     * @param lifePoint pokemon life point
     * @param attack pokemon attack stat
     * @param defense pokemon defense stat
     */
    private MarkerModel(int databaseId, String username, PokemonModel pokemon, CoordinateModel coordinate, 
            Timestamp timestamp, int upVotes, int downVotes, int lifepoint, int attack, int defense) {
        try {
            this._database = new DatabaseModel(ClientConfiguration.getInstance().getDataBasePath());
        } catch (IllegalStateException | SQLException | FileNotFoundException ex) {
            // TODO Bonne soirée
        }
        
    	_username = username;
        _databaseId = databaseId;
        _pokemon = pokemon;
        _coordinate = coordinate;
        _timestamp = timestamp;
        _reputation = new ReputationScoreModel(upVotes, downVotes);
        _lifePoint = lifepoint;
        _attack = attack;
        _defense = defense;
        _database = (MarkerDatabaseModel) DatabaseModel.getDatabase();
    }
    
    public int getReputationScore() {
        return _reputation.getScore();
    }
    
    /**
     * Add a positif vote reputation to this marker
     */
    public void voteUp() {
        _reputation.vote(ReputationVoteModel.UP);
        _database.updateMarkerReputation(this);
    }
    
    /**
     * Add a negatif vote reputation to this marker
     */
    public void voteDown() {
        _reputation.vote(ReputationVoteModel.DOWN);
        _database.updateMarkerReputation(this);
    }
    
    public void setTimestamp(Timestamp newTimestamp) {
        _timestamp = newTimestamp;
        // TODO Set timestamp in DB
        // So for the moment :
        _timestamp = new Timestamp(System.currentTimeMillis());
    }
    
    public Timestamp getTimestamp() {
        if(_timestamp == null) {
            return new Timestamp(System.currentTimeMillis());
        }
        return _timestamp;
    }
    
    public String getPokemonName() {
        return _pokemon.getName();
    }

	public String getUsername() {
		return _username;
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
     * 
     * @return the id
     */
    public int getDatabaseId() {
        return _databaseId;
    }

    /** Set the database ID.
     * This function should be used only by DatabaseModel, since the ID is 
     * managed by it.
     * 
     * @param databaseId the database id of this marker
     */
    public void setDatabaseId(int databaseId) {
        _databaseId = databaseId;
    }
    
    public PokemonModel getPokemon() {
        return _pokemon;
    }

    public void update(PokemonModel pokemon, int lifePoint, int attack, int defense, Timestamp timestamp) {
        // TODO : call database
        _pokemon = pokemon;
        _timestamp = timestamp;
        _lifePoint = lifePoint;
        _attack = attack;
        _defense = defense;
    }
}
