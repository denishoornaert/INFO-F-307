package be.ac.ulb.infof307.g01.common.model;

import java.util.ArrayList;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * Represents a pokemon marker, indicating a pokemon was spotted on the map.
 * Markers contain many properties, detailing the pokemon that was spotted,
 * as well as when and where it was spotted.
 * The sendable keyword indicates the object can be serialized and sent 
 * over the network
 */
@XmlRootElement
public class MarkerSendableModel {
    
    /** 
     * The ID of this marker in the database. 
     * Allows DatabaseModel to know the instance's corresponding database entry.
     */
    protected int _databaseId;
    protected String _username;
    protected PokemonSendableModel _pokemon;
    protected CoordinateSendableModel _coordinate;
    protected Long _longTimestamp;
    protected ArrayList<ReputationVoteSendableModel> _reputation;
    protected int _lifePoints, _attack, _defense;
    
    /**
     * Default constructor.
     * Required by Jersey.
     */
    public MarkerSendableModel() {}
    
    /**
     * Creates a marker in memory (not in database).
     * 
     * @param databaseId id of the pokemon in database (0 if not in database)
     * @param username of user who create this marker
     * @param pokemon pokemon model representing the spotted pokemon
     * @param latitude latitudinal geographical coordinate
     * @param longitude longitudinal geographical coordinate
     * @param timestamp time when the pokemon was spotted
     * @param reputation represents user's votes on this marker
     * @param lifePoints pokemon life points
     * @param attack pokemon attack points
     * @param defense pokemon defense points
     */
    public MarkerSendableModel(final int databaseId, final String username, 
            final PokemonSendableModel pokemon, final double latitude, final double longitude, 
            final Long timestamp, final ArrayList<ReputationVoteSendableModel> reputation, 
            final int lifePoints, final int attack, final int defense) {
        this(databaseId, username, pokemon, new CoordinateSendableModel(latitude, longitude), 
                timestamp, reputation, lifePoints, attack, defense);
    }
    
    /**
     * Constructor (not in database)
     * 
     * @param databaseId id of the pokemon in database (0 if not exist)
     * @param username of user who create this marker
     * @param pokemon pokemon
     * @param coordinate location
     * @param timestamp time when the pokemon was spotted
     * @param lifePoints pokemon life points
     * @param attack pokemon attack points
     * @param defense pokemon defense points
     */
    protected MarkerSendableModel(final int databaseId, final String username, 
            final PokemonSendableModel pokemon, final CoordinateSendableModel coordinate, 
            final Long timestamp, final int lifePoints, final int attack, final int defense) {
        this(databaseId, username, pokemon, coordinate, timestamp, 
                new ArrayList<>(), lifePoints, attack, defense);
    }
    
    /**
     * Constructor (not in database)
     * 
     * @param databaseId id of the pokemon in database (0 if not exist)
     * @param username of user who create this marker
     * @param pokemon pokemon model representing the spotted pokemon
     * @param coordinate location
     * @param timestamp time when the pokemon has been witnessed
     * @param reputation all reputation about this marker
     * @param lifePoints pokemon life point
     * @param attack pokemon attack stat
     * @param defense pokemon defense stat
     */
    protected MarkerSendableModel(final int databaseId, final String username, 
            final PokemonSendableModel pokemon, final CoordinateSendableModel coordinate, 
            final Long timestamp, final ArrayList<ReputationVoteSendableModel> reputation, 
            final int lifePoints, final int attack, final int defense) {
        
    	_username = username;
        _databaseId = databaseId;
        _pokemon = pokemon;
        _coordinate = coordinate;
        _longTimestamp = timestamp;
        _reputation = reputation;
        _lifePoints = lifePoints;
        _attack = attack;
        _defense = defense;
    }
    
    protected MarkerSendableModel(final MarkerSendableModel other) {
        _username = other._username;
        _databaseId = other._databaseId;
        _pokemon = other._pokemon;
        _coordinate = other._coordinate;
        _longTimestamp = other._longTimestamp;
        _reputation = other._reputation;
        if(_reputation == null) {
            _reputation = new ArrayList<>();
        }
        _lifePoints = other._lifePoints;
        _attack = other._attack;
        _defense = other._defense;
    }
    
    /** 
     * Get the database ID.
     * This function should be used only by DatabaseModel, since it manages
     * the IDs.
     * @return the id
     */
    public int getDatabaseId() {
        return _databaseId;
    }

    /** 
     * Sets the database ID.
     * This function should be used only by DatabaseModel, since it manages
     * the IDs.
     * @param databaseId the new database id of this marker
     */
    public void setDatabaseId(final int databaseId) {
        this._databaseId = databaseId;
    }

    public String getUsername() {
        return _username;
    }

    /**
     * @param _username the username to set
     */
    public void setUsername(final String _username) {
        this._username = _username;
    }

    public PokemonSendableModel getPokemon() {
        return _pokemon;
    }
    
    public String getPokemonName() {
        return _pokemon.getName();
    }
    
    /**
     * @param pokemon the pokemon to set
     */
    public void setPokemon(final PokemonSendableModel pokemon) {
        this._pokemon = pokemon;
    }

    public CoordinateSendableModel getCoordinate() {
        return _coordinate;
    }

    /**
     * @param coordinate the coordinate to set
     */
    public void setCoordinate(final CoordinateSendableModel coordinate) {
        this._coordinate = coordinate;
    }
    
    public Long getLongTimestamp() {
        return _longTimestamp;
    }
    
    public void setLongTimestamp(final Long newLongTimestamp) {
        _longTimestamp = newLongTimestamp;
    }
    
    /**
     * Gets the list of user votes on this marker.
     * @return the list of votes defining the marker's reputation
     */
    public ArrayList<ReputationVoteSendableModel> getReputation() {
        return _reputation;
    }
    
    public int getUpVotes() {
        int reputation = 0;
        for(final ReputationVoteSendableModel reputationVote : _reputation) {
            if(reputationVote.getIsUpVote()) {
                ++reputation;
            }
        }
        return reputation;
    }

    public int getDownVotes() {
        int reputation = 0;
        for(final ReputationVoteSendableModel reputationVote : _reputation) {
            if(!reputationVote.getIsUpVote()) {
                ++reputation;
            }
        }
        return reputation;
    }
    
    /**
     * @param reputation the reputation to set
     */
    public void setReputation(final ArrayList<ReputationVoteSendableModel> reputation) {
        this._reputation = reputation;
    }

    public int getLifePoints() {
        return _lifePoints;
    }

    /**
     * @param lifePoints the lifePoints to set
     */
    public void setLifePoints(final int lifePoints) {
        this._lifePoints = lifePoints;
    }

    public int getAttack() {
        return _attack;
    }

    /**
     * @param attack the _attack to set
     */
    public void setAttack(final int attack) {
        _attack = attack;
    }

    public int getDefense() {
        return _defense;
    }

    /**
     * @param defense the defense to set
     */
    public void setDefense(final int defense) {
        _defense = defense;
    }

    @Override
    public int hashCode() {
        final int hash = 3;
        return hash;
    }
    
    /**
     * Determines if the marker has a valid database id.
     * The absence of a database id should indicate the marker has not been
     * inserted into the database (yet).
     * @return true if the marker has a valid database id, false otherwise
     */
    public boolean hasDatabaseId() {
        return _databaseId != 0;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MarkerSendableModel other = (MarkerSendableModel) obj;
        return ((this._databaseId == other._databaseId
                    || !this.hasDatabaseId()
                    || !other.hasDatabaseId())
                && this._lifePoints == other._lifePoints
                && this._attack == other._attack
                && this._defense == other._defense
                && Objects.equals(this._username, other._username)
                && Objects.equals(this._pokemon, other._pokemon)
                && Objects.equals(this._coordinate, other._coordinate)
                && Objects.equals(this._longTimestamp, other._longTimestamp));
    }
}