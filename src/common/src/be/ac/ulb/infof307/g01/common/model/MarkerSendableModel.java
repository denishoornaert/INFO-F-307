package be.ac.ulb.infof307.g01.common.model;

import java.sql.Timestamp;
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
    protected Timestamp _timestamp;
    protected ReputationScoreSendableModel _reputation;
    protected int _lifePoint, _attack, _defense;
    
    public MarkerSendableModel() {} // Should not be remove
    
    
    /**
     * Constructor (not in database)
     * 
     * @param databaseId id of the pokemon in database (0 if not exist)
     * @param username of user who create this marker
     * @param pokemon pokemon
     * @param coordinate location
     * @param timestamp time when the pokemon has been witnessed
     * @param reputation all votes about this marker
     * @param lifePoint pokemon life point
     * @param attack pokemon attack stat
     * @param defense pokemon defense stat
     */
    protected MarkerSendableModel(int databaseId, String username, 
            PokemonSendableModel pokemon, CoordinateSendableModel coordinate, 
            Timestamp timestamp, ReputationScoreSendableModel reputation, int lifePoint, 
            int attack, int defense) {
        
    	_username = username;
        _databaseId = databaseId;
        _pokemon = pokemon;
        _coordinate = coordinate;
        _timestamp = timestamp;
        _reputation = reputation;
        _lifePoint = lifePoint;
        _attack = attack;
        _defense = defense;
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
     * @return the _username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * @param _username the _username to set
     */
    public void setUsername(String _username) {
        this._username = _username;
    }

    /**
     * @return the _pokemon
     */
    public PokemonSendableModel getPokemon() {
        return _pokemon;
    }

    /**
     * @param _pokemon the _pokemon to set
     */
    public void setPokemon(PokemonSendableModel _pokemon) {
        this._pokemon = _pokemon;
    }

    /**
     * @return the _coordinate
     */
    public CoordinateSendableModel getCoordinate() {
        return _coordinate;
    }

    /**
     * @param _coordinate the _coordinate to set
     */
    public void setCoordinate(CoordinateSendableModel _coordinate) {
        this._coordinate = _coordinate;
    }

    /**
     * @return the _timestamp
     */
    public Timestamp getTimestamp() {
        return _timestamp;
    }

    /**
     * @param _timestamp the _timestamp to set
     */
    public void setTimestamp(Timestamp _timestamp) {
        this._timestamp = _timestamp;
    }

    /**
     * @return the _reputation
     */
    public ReputationScoreSendableModel getReputation() {
        return _reputation;
    }
    
    public int getUpVotes() {
        return getReputation().getUpVotes();
    }

    public int getDownVotes() {
        return getReputation().getDownVotes();
    }
    
    /**
     * @param _reputation the _reputation to set
     */
    public void setReputation(ReputationScoreSendableModel _reputation) {
        this._reputation = _reputation;
    }

    /**
     * @return the _lifePoint
     */
    public int getLifePoint() {
        return _lifePoint;
    }

    /**
     * @param _lifePoint the _lifePoint to set
     */
    public void setLifePoint(int _lifePoint) {
        this._lifePoint = _lifePoint;
    }

    /**
     * @return the _attack
     */
    public int getAttack() {
        return _attack;
    }

    /**
     * @param _attack the _attack to set
     */
    public void setAttack(int _attack) {
        this._attack = _attack;
    }

    /**
     * @return the _defense
     */
    public int getDefense() {
        return _defense;
    }

    /**
     * @param _defense the _defense to set
     */
    public void setDefense(int _defense) {
        this._defense = _defense;
    }

}