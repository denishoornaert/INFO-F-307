package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
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
        this(pokemon, coordinate, username, lifePoint, attack, defense, date, true);
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
     * @param loadDatabase True to load database
     */
    public MarkerModel(PokemonSendableModel pokemon, CoordinateSendableModel coordinate,
            String username, int lifePoint, int attack, int defense, Timestamp date,
            boolean loadDatabase) {
        this(0, username, pokemon, coordinate, date, lifePoint,
                attack, defense, loadDatabase);
    }
    
    /**
     * Constructor to create marker in memory, not sending it to the server
     *
     * @param databaseId id of the pokemon in database (0 if not exist)
     * @param username of user who create this marker
     * @param pokemon pokemon
     * @param coordinate coordinate of marker
     * @param timestamp time when the pokemon has been witnessed
     * @param lifePoint pokemon life point
     * @param attack pokemon attack stat
     * @param defense pokemon defense stat
     * @param loadDatabase True to load database
     */
    public MarkerModel(int databaseId, String username, PokemonSendableModel pokemon,
            CoordinateSendableModel coordinate, Timestamp timestamp, 
            int lifePoint, int attack, int defense, boolean loadDatabase) {
        super(databaseId, username, pokemon, coordinate, timestamp.getTime(), 
                lifePoint, attack, defense);
        if(loadDatabase) {
            _serverQuery = (MarkerQueryModel) ServerQueryController.getInstance();
        }
    }
    
    public MarkerModel(MarkerSendableModel marker) {
        super(marker);
        _serverQuery = (MarkerQueryModel) ServerQueryController.getInstance();
    }
    
    /**
     * Get the ratio of positive and negative votes
     * 
     * @return the ration
     */
    public int getReputationScore() {
        int res = 0;
        
        for(ReputationVoteSendableModel reputationVote : _reputation) {
            res += reputationVote.isUpVote() ? 1 : -1;
        }
        
        return res;
    }
    
    /**
     * Add a vote reputation to this marker
     * 
     * @param username the user who add a vote
     * @param isUpVote True if it's an up vote
     */
    public void addVote(String username, boolean isUpVote) {
        _reputation.add(new ReputationVoteSendableModel(username, isUpVote));
        _serverQuery.updateMarkerReputation(this);
    }
    
    // TODO Check to call Super
    public void setTimestamp(Timestamp newTimestamp) {
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
    
    public int getMarkerAttack() {
        return _attack;
    }
    
    public int getMarkerDefense() {
        return _defense;
    }
    
    public int geMarkerLife() {
        return _lifePoints;
    }
    
    public void update(PokemonModel pokemon, int lifePoints, int attack, int defense,
            Timestamp timestamp) {
        setPokemon(pokemon);
        setTimestamp(timestamp);
        setLifePoints(lifePoints);
        setAttack(attack);
        setDefense(defense);
        _serverQuery.updateMarker(this);
    }
    
    public MarkerSendableModel getSendable() {
        return new MarkerSendableModel(_databaseId, _username, _pokemon,
                _coordinate.getLatitude(), _coordinate.getLongitude(),
                _longTimestamp, _reputation, _lifePoints, _attack, _defense);
    }
    
    
}
