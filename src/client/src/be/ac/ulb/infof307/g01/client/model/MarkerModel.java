package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.client.controller.ServerQueryController;
import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.common.model.MarkerQueryModel;
import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
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
    public MarkerModel(PokemonSendableModel pokemon, CoordinateSendableModel coordinate, 
            String username, int lifePoint, int attack, int defense, Timestamp date) {
        this(pokemon, coordinate, username, lifePoint, attack, defense, date, 
                0, 0, true);
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
     * @param upVotes number of positives votes
     * @param downVotes number of negatives votes
     * @param loadDatabase True to load database
     */
    public MarkerModel(PokemonSendableModel pokemon, CoordinateSendableModel coordinate, 
            String username, int lifePoint, int attack, int defense, Timestamp date,
            int upVotes, int downVotes, boolean loadDatabase) {
        this(0, username, pokemon, coordinate, date, upVotes, downVotes, lifePoint, 
                attack, defense, loadDatabase);
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
     * @param loadDatabase True to load database
     */
    public MarkerModel(int databaseId, String username, PokemonSendableModel pokemon, 
            CoordinateSendableModel coordinate, Timestamp timestamp, int upVotes, 
            int downVotes, int lifePoint, int attack, int defense, boolean loadDatabase) {
        super(databaseId, username, pokemon, coordinate, timestamp.getTime(), 
                new ReputationScoreModel(upVotes, downVotes), lifePoint, attack, defense);
        if(loadDatabase) {
            _serverQuery = (MarkerQueryModel) ServerQueryController.getInstance();
        }
    }
    
    public MarkerModel(MarkerSendableModel marker) {
        super(marker);
        _serverQuery = (MarkerQueryModel) ServerQueryController.getInstance();
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
    public void setTimestamp(Timestamp newTimestamp) {
        // TODO Set timestamp in DB
        // So for the moment :
        newTimestamp = new Timestamp(System.currentTimeMillis());
        super.setLongTimestamp(newTimestamp.getTime());
    }
    
    public Timestamp getTimestamp() {
        Timestamp timestamp;
        Long longTimestamp = super.getLongTimestamp();
        if(longTimestamp == null || longTimestamp == 0) {
            longTimestamp = System.currentTimeMillis();
        }
        timestamp = new Timestamp(longTimestamp);
        return timestamp;
    }
    
    @Override
    public PokemonModel getPokemon() {
        return (PokemonModel) _pokemon;
    }
    
    @Override
    public String getPokemonName() {
        return _pokemon.getName();
    }
    
    public String getImagePath() {
        return _pokemon.getImagePath();
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

    public void update(PokemonModel pokemon, int lifePoint, int attack, int defense, 
            Timestamp timestamp) {
        setPokemon(pokemon);
        setTimestamp(timestamp);
        setLifePoint(lifePoint);
        setAttack(attack);
        setDefense(defense);
        _serverQuery.updateMarker(this);
    }
}
