package be.ac.ulb.infof307.g01.common.model;

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
    protected ReputationScoreSendableModel _reputation;
    protected int _lifePoint, _attack, _defense;
    
    public MarkerSendableModel() {} // Should not be removed
    
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
    public MarkerSendableModel(int databaseId, String username, String pokemonName, double latitude, double longitude, 
            Long timestamp, int upVotes, int downVotes, int lifePoint, int attack, int defense) {
        this(databaseId, username, PokemonSendableModel.getPokemonByName(pokemonName), latitude,
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
    public MarkerSendableModel(int databaseId, String username, 
            PokemonSendableModel pokemon, double latitude, double longitude, 
            Long timestamp, int upVotes, int downVotes, int lifePoint, 
            int attack, int defense) {
        this(databaseId, username, pokemon, new CoordinateSendableModel(latitude, longitude), timestamp, 
                new ReputationScoreSendableModel(upVotes, downVotes), lifePoint, attack, defense);
    }
    
    
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
            Long timestamp, ReputationScoreSendableModel reputation, int lifePoint, 
            int attack, int defense) {
        
    	_username = username;
        _databaseId = databaseId;
        _pokemon = pokemon;
        _coordinate = coordinate;
        _longTimestamp = timestamp;
        _reputation = reputation;
        _lifePoint = lifePoint;
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
        _lifePoint = other._lifePoint;
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
     * @return the reputation
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
     * @param reputation the reputation to set
     */
    public void setReputation(ReputationScoreSendableModel reputation) {
        this._reputation = reputation;
    }

    /**
     * @return the lifePoint
     */
    public int getLifePoints() {
        return _lifePoint;
    }

    /**
     * @param lifePoint the lifePoint to set
     */
    public void setLifePoint(int lifePoint) {
        this._lifePoint = lifePoint;
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
    
    public boolean equals(MarkerSendableModel other) {
        return _pokemon.getName().equals(other.getPokemonName())
                && _longTimestamp.equals(other.getLongTimestamp())
                && _coordinate.equals(other.getCoordinate());
    }

}