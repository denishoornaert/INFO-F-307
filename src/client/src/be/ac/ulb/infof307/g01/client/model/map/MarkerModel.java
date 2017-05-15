package be.ac.ulb.infof307.g01.client.model.map;

import be.ac.ulb.infof307.g01.client.controller.app.ServerQueryController;
import be.ac.ulb.infof307.g01.common.controller.MarkerQueryController;
import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
import java.security.InvalidParameterException;
import java.sql.Timestamp;

/** 
 * Represents a marker indicating that a pokemon was observed somewhere in space-time. 
 * A marker contains the time and location of a spotted pokemon, its stats ...
 */
public class MarkerModel extends MarkerSendableModel {
    
    private MarkerQueryController _serverQuery;
    
    /**
     * Creates a MarkerModel.
     * By default, will load the database. TODO explain
     * @param pokemon observed pokemon
     * @param coordinate pokemon's location
     * @param username username of the user who created the marker
     * @param life pokemon life points
     * @param attack pokemon attack points
     * @param defense pokemon defense points
     * @param timestamp date and time of the marker
     */
    public MarkerModel(final PokemonSendableModel pokemon, final CoordinateSendableModel coordinate,
            final String username, final int life, final int attack, final int defense,
            final Timestamp timestamp) 
            throws InvalidParameterException {
        this(pokemon, coordinate, username, life, attack, defense, timestamp, true);
        _serverQuery.insertMarker(this);
    }
    
    /**
     * Creates a MarkerModel.
     * 
     * @param pokemon observed pokemon
     * @param coordinate pokemon's location
     * @param username username of the user who created the marker
     * @param life pokemon life points
     * @param attack pokemon attack points
     * @param defense pokemon defense points
     * @param timestamp date and time of the marker
     * @param loadDatabase TODO explain
     */
    public MarkerModel(final PokemonSendableModel pokemon, final CoordinateSendableModel coordinate,
            final String username, final int life, final int attack, final int defense,
            final Timestamp timestamp, final boolean loadDatabase) {
        super(0, username, pokemon, coordinate, timestamp.getTime(), life, attack, defense);
        if (loadDatabase) {
            _serverQuery = (MarkerQueryController) ServerQueryController.getInstance();
        }
    }
    
    /**
     * Creates a MarkerModel from a MarkerSendableModel.
     * @param marker the MarkerSendableModel containing the marker's informations.
     */
    public MarkerModel(final MarkerSendableModel marker) {
        super(marker);
        _serverQuery = (MarkerQueryController) ServerQueryController.getInstance();
        _pokemon = new PokemonModel(_pokemon);
    }
    
    /**
     * Creates a copy of this, with type MarkerSendableModel.
     * @return an equivalent MarkerSendableModel
     */
    public MarkerSendableModel getSendable() {
        return new MarkerSendableModel(_databaseId, _username, _pokemon,
                _coordinate.getLatitude(), _coordinate.getLongitude(),
                _longTimestamp, _reputation, _lifePoints, _attack, _defense);
    }
    
    /**
     * Updates the marker's informations according to parameters.
     * @param pokemon observed pokemon
     * @param life pokemon life points
     * @param attack pokemon attack points
     * @param defense pokemon defense points
     * @param timestamp date and time of the marker
     * @throws IllegalArgumentException if the 
     */
    public void update(final PokemonModel pokemon, final int life, final int attack,
            final int defense, final Timestamp timestamp) throws IllegalArgumentException {
        setPokemon(pokemon);
        setTimestamp(timestamp);
        setLifePoints(life);
        setAttack(attack);
        setDefense(defense);
        _serverQuery.updateMarker(this);
    }
    
    /**
     * Gets the difference between positive and negative votes on the marker.
     * @return (positive votes) - (negative votes)
     */
    public int getReputationScore() {
        int res = 0;
        for(final ReputationVoteSendableModel reputationVote : _reputation) {
            res += reputationVote.getIsUpVote() ? 1 : -1;
        }
        return res;
    }
    
    /**
     * Gets a user's vote on the marker.
     * @param username the user whose vote we retrieve
     * @return a ReputationVote matching the user's vote, or null if the user
     * didn't vote on the marker
     */
    public ReputationVoteSendableModel getUserVote(final String username) {
        for(final ReputationVoteSendableModel vote : _reputation) {
            if(vote.getUsername().equals(username)) {
                return vote;
            }
        }
        return null;
    }
    
    /**
     * Adds a user's vote to the marker.
     * A vote is a user's appreciation of another user's marker, and can be
     * positive or negative.
     * @param username the user who adds the vote
     * @param isUpVote true if the vote is positive, false if negative
     */
    public void addUserVote(final String username, final boolean isUpVote) throws InvalidParameterException {
        final ReputationVoteSendableModel reputation =
                new ReputationVoteSendableModel(username, isUpVote, _databaseId);
        for(final ReputationVoteSendableModel vote : _reputation) {
            if(vote.getUsername().equals(username)) {
                _reputation.remove(vote);
                break;
            }
        }
        _reputation.add(reputation);
        _serverQuery.updateMarkerReputation(reputation);
    }
    
    /**
     * Sets the marker's time and date.
     * @param newTimestamp timestamp representing the new time and date
     */
    public void setTimestamp(final Timestamp newTimestamp) {
        // TODO Check to call Super
        super.setLongTimestamp(newTimestamp.getTime());
    }
    
    public Timestamp getTimestamp() {
        final Timestamp timestamp;
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
     * Generates a Google Maps URL pointing to the marker's coordinates.
     * @return the URL
     */
    private String getGoogleMapsLink() {
        final CoordinateSendableModel coordinate = getCoordinate();
        return "https://www.google.com/maps/place/" + coordinate.getLatitude() + 
                "," + coordinate.getLongitude();
    }
    
    /**
     * Generates a link used to tweet this marker.
     * @return the URL
     */
    public String getTwitterLink() {
        return "http://twitter.com/home?status=I%20see%20a%20" + 
                _pokemon.getName() + "%20" + getGoogleMapsLink();
    }
    
}
