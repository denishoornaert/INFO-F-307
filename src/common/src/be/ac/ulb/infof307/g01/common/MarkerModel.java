package be.ac.ulb.infof307.g01.common;

import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class MarkerModel {
    
    private int _databaseId;
    private String _username; // TODO may be final
    private PokemonModel _pokemon;
    private CoordinateModel _coordinate;
    private Timestamp _timestamp;
    private ReputationScoreModel _reputation;
    private int _lifePoint, _attack, _defense;

    /**
     * @return the _databaseId
     */
    public int getDatabaseId() {
        return _databaseId;
    }

    /**
     * @param _databaseId the _databaseId to set
     */
    public void setDatabaseId(int _databaseId) {
        this._databaseId = _databaseId;
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
    public PokemonModel getPokemon() {
        return _pokemon;
    }

    /**
     * @param _pokemon the _pokemon to set
     */
    public void setPokemon(PokemonModel _pokemon) {
        this._pokemon = _pokemon;
    }

    /**
     * @return the _coordinate
     */
    public CoordinateModel getCoordinate() {
        return _coordinate;
    }

    /**
     * @param _coordinate the _coordinate to set
     */
    public void setCoordinate(CoordinateModel _coordinate) {
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
    public ReputationScoreModel getReputation() {
        return _reputation;
    }

    /**
     * @param _reputation the _reputation to set
     */
    public void setReputation(ReputationScoreModel _reputation) {
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