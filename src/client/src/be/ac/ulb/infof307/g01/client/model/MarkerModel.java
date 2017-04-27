package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.common.MarkerQueryModel;
import be.ac.ulb.infof307.g01.common.MarkerSendableModel;
import java.sql.Timestamp;

/** Model of a marker. A marker contains the location of a spotted pokemon,
 * the pokemon, the timestamp of the spot, etc...
 */
public class MarkerModel extends MarkerSendableModel {
    
    private MarkerQueryModel _serverQuery;
    
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
    public MarkerModel(PokemonModel pokemon, CoordinateSendableModel coordinate, 
            String username, int lifePoint, int attack, int defense, Timestamp date) {
        this(pokemon, coordinate, username, lifePoint, attack, defense, date, 
                0, 0);
        _serverQuery.insertMarker(this);
    }
    
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
     * @param reputation of the marker
     */
    private MarkerModel(PokemonModel pokemon, CoordinateSendableModel coordinate, 
            String username, int lifePoint, int attack, int defense, Timestamp date,
            int upVotes, int downVotes) {
        this(0, username, pokemon, coordinate, date, upVotes, downVotes, lifePoint, 
                attack, defense);
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
        this(databaseId, username, PokemonModel.getPokemonByName(pokemonName), latitude,
                longitude, timestamp, upVotes, downVotes, lifePoint, attack,
                defense);
    }
    
    /**
     * Constructor to create marker in memory (not in database)
     * 
     * @param databaseId id of the pokemon in database (0 if not exist)
     * @param username of user who create this marker
     * @param pokemon pokemon
     * @param latitude x coordinate
     * @param longitude y coordinate
     * @param timestamp time when the pokemon has been witnessed
     * @param upVotes positif votes about this maker
     * @param downVotes negatif votes about this marker
     * @param lifePoint pokemon life point
     * @param attack pokemon attack stat
     * @param defense pokemon defense stat
     */
    public MarkerModel(int databaseId, String username, PokemonModel pokemon, double latitude, double longitude, 
            Timestamp timestamp, int upVotes, int downVotes, int lifePoint, int attack, int defense) {
        super(databaseId, username, pokemon, new CoordinateSendableModel(latitude, longitude), timestamp, 
                new ReputationScoreModel(upVotes, downVotes), lifePoint, attack, defense);
    }
    
    
    /**
     * Constructor to create marker in memory (not in database)
     * 
     * @param databaseId id of the pokemon in database (0 if not exist)
     * @param username of user who create this marker
     * @param pokemon pokemon
     * @param coordinate coordinate of marker
     * @param timestamp time when the pokemon has been witnessed
     * @param upVotes positif votes about this maker
     * @param downVotes negatif votes about this marker
     * @param lifePoint pokemon life point
     * @param attack pokemon attack stat
     * @param defense pokemon defense stat
     */
    public MarkerModel(int databaseId, String username, PokemonModel pokemon, CoordinateSendableModel coordinate, 
            Timestamp timestamp, int upVotes, int downVotes, int lifePoint, int attack, int defense) {
        super(databaseId, username, pokemon, coordinate, timestamp, 
                new ReputationScoreModel(upVotes, downVotes), lifePoint, attack, defense);
    }
    
    public int getReputationScore() {
        return getReputation().getScore();
    }
    
    @Override
    public ReputationScoreModel getReputation() {
        return (ReputationScoreModel) _reputation;
    }
    
    /**
     * Add a positif vote reputation to this marker
     */
    public void voteUp() {
        getReputation().vote(ReputationVoteModel.UP);
        _serverQuery.updateMarkerReputation(this);
    }
    
    /**
     * Add a negatif vote reputation to this marker
     */
    public void voteDown() {
        getReputation().vote(ReputationVoteModel.DOWN);
        _serverQuery.updateMarkerReputation(this);
    }
    
    // TODO Check to call Super
    @Override
    public void setTimestamp(Timestamp newTimestamp) {
        _timestamp = newTimestamp;
        // TODO Set timestamp in DB
        // So for the moment :
        _timestamp = new Timestamp(System.currentTimeMillis());
    }
    
    @Override
    public Timestamp getTimestamp() {
        if(_timestamp == null) {
            return new Timestamp(System.currentTimeMillis());
        }
        return _timestamp;
    }
    
    @Override
    public PokemonModel getPokemon() {
        return (PokemonModel) _pokemon;
    }
    
    public String getPokemonName() {
        return _pokemon.getName();
    }
    
    public String getImagePath() {
        return _pokemon.getImagePath();
    }
    
    public boolean equals(MarkerModel other) {
        return _pokemon.getName().equals(other.getPokemonName())
                && _timestamp.equals(other.getTimestamp())
                && _coordinate.equals(other.getCoordinate());
    }
    
    // TODO check method name
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
        return getReputation().getScore();
    }

    public int getUpVotes() {
        return getReputation().getUpVotes();
    }

    public int getDownVotes() {
        return getReputation().getDownVotes();
    }

    public void update(PokemonModel pokemon, int lifePoint, int attack, int defense, 
            Timestamp timestamp) {
        // TODO : call database
        _pokemon = pokemon;
        _timestamp = timestamp;
        _lifePoint = lifePoint;
        _attack = attack;
        _defense = defense;
    }
}
