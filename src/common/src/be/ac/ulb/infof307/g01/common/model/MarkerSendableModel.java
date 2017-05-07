package be.ac.ulb.infof307.g01.common.model;

import java.util.ArrayList;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MarkerSendableModel {
    
    /** The ID of this marker in the database. With this attribute,
     * DatabaseModel can know to which database entry this object corresponds.
     */
    protected int _databaseId;
    protected String _username; // TODO may be final
    protected PokemonSendableModel _pokemon;
    protected CoordinateSendableModel _coordinate;
    protected Long _longTimestamp;
    protected ArrayList<ReputationVoteSendableModel> _reputation;
    protected int _lifePoints, _attack, _defense;
    
    public MarkerSendableModel() {} // Should not be removed
    
    /**
     * Constructor to create marker in memory (not in database)
     * 
     * @param databaseId id of the pokemon in database (0 if not exist)
     * @param username of user who create this marker
     * @param pokemon pokemon
     * @param latitude x coordinate
     * @param longitude y coordinate
     * @param timestamp time when the pokemon has been witnessed
     * @param reputation
     * @param lifePoints pokemon life point
     * @param attack pokemon attack stat
     * @param defense pokemon defense stat
     */
    public MarkerSendableModel(int databaseId, String username, 
            PokemonSendableModel pokemon, double latitude, double longitude, 
            Long timestamp, ArrayList<ReputationVoteSendableModel> reputation, 
            int lifePoints, int attack, int defense) {
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
     * @param timestamp time when the pokemon has been witnessed
     * @param lifePoints pokemon life point
     * @param attack pokemon attack stat
     * @param defense pokemon defense stat
     */
    protected MarkerSendableModel(int databaseId, String username, 
            PokemonSendableModel pokemon, CoordinateSendableModel coordinate, 
            Long timestamp, int lifePoints, int attack, int defense) {
        this(databaseId, username, pokemon, coordinate, timestamp, 
                new ArrayList<>(), lifePoints, attack, defense);
    }
    
    /**
     * Constructor (not in database)
     * 
     * @param databaseId id of the pokemon in database (0 if not exist)
     * @param username of user who create this marker
     * @param pokemon pokemon
     * @param coordinate location
     * @param timestamp time when the pokemon has been witnessed
     * @param reputation all reputation about this marker
     * @param lifePoints pokemon life point
     * @param attack pokemon attack stat
     * @param defense pokemon defense stat
     */
    protected MarkerSendableModel(int databaseId, String username, 
            PokemonSendableModel pokemon, CoordinateSendableModel coordinate, 
            Long timestamp, ArrayList<ReputationVoteSendableModel> reputation, 
            int lifePoints, int attack, int defense) {
        
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
    
    protected MarkerSendableModel(MarkerSendableModel other) {
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
        this._databaseId = databaseId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * @param _username the username to set
     */
    public void setUsername(String _username) {
        this._username = _username;
    }

    /**
     * @return the pokemon
     */
    public PokemonSendableModel getPokemon() {
        return _pokemon;
    }
    
    /**
     * @return the pokemon name
     */
    public String getPokemonName() {
        return _pokemon.getName();
    }
    
    /**
     * @param pokemon the pokemon to set
     */
    public void setPokemon(PokemonSendableModel pokemon) {
        this._pokemon = pokemon;
    }

    /**
     * @return the coordinate
     */
    public CoordinateSendableModel getCoordinate() {
        return _coordinate;
    }

    /**
     * @param coordinate the coordinate to set
     */
    public void setCoordinate(CoordinateSendableModel coordinate) {
        this._coordinate = coordinate;
    }
    
    public Long getLongTimestamp() {
        return _longTimestamp;
    }
    
    public void setLongTimestamp(Long newLongTimestamp) {
        _longTimestamp = newLongTimestamp;
    }
    
    /**
     * All reputation about this marker
     * 
     * @return the reputation
     */
    public ArrayList<ReputationVoteSendableModel> getReputation() {
        return _reputation;
    }
    
    public int getUpVotes() {
        int reputation = 0;
        for(ReputationVoteSendableModel reputationVote : _reputation) {
            if(reputationVote.getIsUpVote()) {
                ++reputation;
            }
        }
        
        return reputation;
    }

    public int getDownVotes() {
        int reputation = 0;
        for(ReputationVoteSendableModel reputationVote : _reputation) {
            if(!reputationVote.getIsUpVote()) {
                ++reputation;
            }
        }
        return reputation;
    }
    
    /**
     * @param reputation the reputation to set
     */
    public void setReputation(ArrayList<ReputationVoteSendableModel> reputation) {
        this._reputation = reputation;
    }

    /**
     * @return the lifePoints
     */
    public int getLifePoints() {
        return _lifePoints;
    }

    /**
     * @param lifePoints the lifePoints to set
     */
    public void setLifePoints(int lifePoints) {
        this._lifePoints = lifePoints;
    }

    /**
     * @return the attack
     */
    public int getAttack() {
        return _attack;
    }

    /**
     * @param attack the _attack to set
     */
    public void setAttack(int attack) {
        _attack = attack;
    }

    /**
     * @return the defense
     */
    public int getDefense() {
        return _defense;
    }

    /**
     * @param defense the defense to set
     */
    public void setDefense(int defense) {
        _defense = defense;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
    
    public boolean hasDatabaseId() {
        return _databaseId != 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MarkerSendableModel other = (MarkerSendableModel) obj;
        if (this._databaseId != other._databaseId && this.hasDatabaseId() && other.hasDatabaseId()) {
            return false;
        }
        if (this._lifePoints != other._lifePoints) {
            return false;
        }
        if (this._attack != other._attack) {
            return false;
        }
        if (this._defense != other._defense) {
            return false;
        }
        if (!Objects.equals(this._username, other._username)) {
            return false;
        }
        if (!Objects.equals(this._pokemon, other._pokemon)) {
            return false;
        }
        if (!Objects.equals(this._coordinate, other._coordinate)) {
            return false;
        }
        if (!Objects.equals(this._longTimestamp, other._longTimestamp)) {
            return false;
        }
        return true;
    }

    
}