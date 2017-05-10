package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.client.controller.ServerQueryController;
import be.ac.ulb.infof307.g01.common.controller.MarkerQueryController;
import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
import java.security.InvalidParameterException;
import java.sql.Timestamp;

/** Model of a marker. A marker contains the location of a spotted pokemon,
 * the pokemon, the timestamp of the spot, etc...
 */
public class MarkerModel extends MarkerSendableModel {
    
    private MarkerQueryController _serverQuery;
    
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
            String username, int lifePoint, int attack, int defense, Timestamp date) 
            throws InvalidParameterException {
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
            _serverQuery = (MarkerQueryController) ServerQueryController.getInstance();
        }
    }
    
    public MarkerModel(MarkerSendableModel marker) {
        super(marker);
        _serverQuery = (MarkerQueryController) ServerQueryController.getInstance();
        _pokemon = new PokemonModel(_pokemon);
    }
    
    /**
     * Get the ratio of positive and negative votes
     * 
     * @return the ration
     */
    public int getReputationScore() {
        int res = 0;
        
        for(ReputationVoteSendableModel reputationVote : _reputation) {
            res += reputationVote.getIsUpVote() ? 1 : -1;
        }
        
        return res;
    }
    
    /**
     * Add a vote reputation to this marker
     * 
     * @param username the user who add a vote
     * @param isUpVote True if it's an up vote
     */
    public void addVote(String username, boolean isUpVote) throws InvalidParameterException {
        ReputationVoteSendableModel reputation = new ReputationVoteSendableModel(username, isUpVote, _databaseId);
        for(ReputationVoteSendableModel vote : _reputation) {
            if(vote.getUsername().equals(username)) {
                _reputation.remove(vote);
                break;
            }
        }
        _reputation.add(reputation);
        _serverQuery.updateMarkerReputation(reputation);
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
        return ((PokemonModel) _pokemon).getImagePath();
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
    
    /**
     * Get the reputation vote that a specific user have set to this marker
     * 
     * @param username the specific user
     * @return the ReputationVote or null if not exist
     */
    public ReputationVoteSendableModel getReputationVote(String username) {
        for(ReputationVoteSendableModel vote : _reputation) {
            if(vote.getUsername().equals(username)) {
                return vote;
            }
        }
        return null;
    }
    
    public void update(PokemonModel pokemon, int lifePoints, int attack, int defense,
            Timestamp timestamp) throws IllegalArgumentException {
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
    
    /**
     * Get a google url in link with the coords
     * 
     * @return the url
     */
    private String getGoogleMapsLink() {
        CoordinateSendableModel coordinate = getCoordinate();
        return "https://www.google.com/maps/place/" + coordinate.getLatitude() + 
                "," + coordinate.getLongitude();
    }
    
    /**
     * Get link to tweet about this marker
     * 
     * @return the url
     */
    public String getTwitterLink() {
        return "http://twitter.com/home?status=I%20see%20a%20" + 
                _pokemon.getName() + "%20" + getGoogleMapsLink();
    }
    
}
