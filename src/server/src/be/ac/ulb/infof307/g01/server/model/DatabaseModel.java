package be.ac.ulb.infof307.g01.server.model;

import be.ac.ulb.infof307.g01.common.controller.ConnectionQueryController;
import be.ac.ulb.infof307.g01.common.controller.FilterQueryController;
import be.ac.ulb.infof307.g01.common.controller.MarkerQueryController;
import be.ac.ulb.infof307.g01.common.controller.PokemonQueryController;
import be.ac.ulb.infof307.g01.common.controller.PokemonTypeQueryController;
import be.ac.ulb.infof307.g01.common.model.FilterSendableModel;
import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Class that interacts with the database.
 * This class implements all queries needed in the application. It is not
 * inteded to be used directly, but rather through one of the implemented
 * interfaces. This way, a class needing a database access is only allowed
 * to call a subset of all the database methods, according to the responsability
 * of this class.
 * 
 * For example, if a class need to access only pokemon types in the database:
 * {@code
 * PokemonTypeDatabaseModel database = (PokemonTypeDatabaseModel) DatabaseModel.getDatabase();
 * // Now database allows to call only queries related to pokemon types.
 * }
 * 
 * Note: This class doesn't implements ConnectionQueryController, since this
 * interface requires a `signup` method taking a UserSendableModel, but the
 * database requires a token as well in order to register a user.
 */
public class DatabaseModel implements PokemonQueryController, PokemonTypeQueryController,
        MarkerQueryController, ConnectionQueryController, FilterQueryController {

    private static DatabaseModel _instance = null;
    private static final Logger LOG = Logger.getLogger(DatabaseModel.class.getName());
    private static final ServerConfiguration CONFIG = ServerConfiguration.getInstance();
    
    /**
     * The database connection
     */
    private Connection _connection;

    public static DatabaseModel getInstance() {
        if(_instance == null) {
            _instance = new DatabaseModel(CONFIG.getDataBasePath());
        }
        return _instance;
    }
    
    public static DatabaseModel getTestInstance() {
        if(_instance == null) {
            _instance = new DatabaseModel(CONFIG.getTestDataBasePath());
        }
        return _instance;
    }
    
    /**
     * Initializes database
     *
     * @param pathToDatabase path to database
     */
    protected DatabaseModel(String pathToDatabase) {
        try {
            createDatabase(pathToDatabase);
            connectToSqlite(pathToDatabase);
        } catch(IOException | SQLException exception) {
            LOG.log(Level.SEVERE, "Could not create Database: {0}", exception);
            System.exit(1);
        }
    }
    
    /**
     * Create the database file (.db from .sql)
     *
     * @param path path to database
     * @return false if file already exist, true if file have been juste created
     */
    private void createDatabase(String path) throws IOException, SQLException {
        File file = new File(path);
        if(!file.exists()) {
            file.createNewFile();
            LOG.log(Level.INFO, "Created Database to {0}", path);
            connectToSqlite(path);
            fillDatabase();
        }
    }

    /**
     * Initialize the connection to the SQLite database
     *
     * @param pathToDatabase path to database
     * @return true if database was properly loaded and false otherwise
     */
    private void connectToSqlite(String pathToDatabase) throws SQLException {
        _connection = DriverManager.getConnection(
                "jdbc:sqlite:" + pathToDatabase);
    }
    
    /**
     * Get the content of .sql file
     * 
     * @return the file content
     */
    private String[] getSQLQueriesFromFile(String filePath) throws FileNotFoundException, IOException {
        Path sqlPath = Paths.get(filePath);
        List<String> lines = Files.readAllLines(sqlPath);
        String query = "";
        for(String line : lines) {
            query += line;
        }
        query = query.replace("\n", "");
        return query.split(";");
    }
    
    private void fillDatabase() {
        String pathToSqlFile = CONFIG.getSqlPath();
        String[] content;
        try {
            content = getSQLQueriesFromFile(pathToSqlFile);
        } catch (IOException exception) {
            LOG.log(Level.SEVERE, "Could not read sql file <{0}>: {1}",
                    new Object[]{pathToSqlFile, exception});
            return;
        }
        
        for(String query : content) {
            try {
                executeQuery(query);
            } catch (SQLException exception) {
                LOG.log(Level.SEVERE, "Could not execute sql query <{0}>: {1}",
                        new Object[]{content, exception});
            }
        }
    }
    
    /**
     * Properly close the connection to the database
     */
    public void close() {
        try {
            _connection.close();
            _instance = null;
        } catch (SQLException exception) {
            LOG.log(Level.WARNING, "Could not close sql connection: {0}", exception);
        }
    }

    public static void closeDatabase() {
        if(_instance != null) {
            _instance.close();
        }
    }
    
    /**
     * Executes an SQL query.
     * @param query the string query
     * @return a resultset with the result
     * @throws SQLException when the query can not be executed
     */
    private ResultSet executeQuery(String query) throws SQLException {
       Statement statement = _connection.createStatement();
       ResultSet resultat = statement.executeQuery(query);
       return resultat;
    }
    
    /**
     * Retrieves a pokemon from its name in the database.
     * @param name The name of the pokemon to get
     * @return The pokemon object
     * @throws IndexOutOfBoundsException If no pokemon has been found with this name
     */
    public PokemonSendableModel getPokemonByName(String name) {
        final List<PokemonSendableModel> allPokemons = getAllPokemons();
        for(PokemonSendableModel pokemon : allPokemons) {
            if(pokemon.getName().equals(name)) {
                return pokemon;
            }
        }
        throw new IndexOutOfBoundsException("No such pokemon in database : " + name);
    }
    
    /**
     * Gets a pokemon type from its name in the database.
     * /TODO This *may* be a serious performance issue when getting all markers:
     * getAllMarkers loads all markers
     * -> for each marker, all pokemons are retrieved in order to find the right one
     *    -> for each pokemons, all types are loaded for the same reason...
     * @param name The type name
     * @return An pokemon type object
     * @throws IndexOutOfBoundsException If no type has been found with this name
     */
    public PokemonTypeSendableModel getPokemonTypeByTypeName(String name) {
        final List<PokemonTypeSendableModel> types = getAllPokemonTypes();
        for(PokemonTypeSendableModel type : types) {
            if(type.getTypeName().equals(name)) {
                return type;
            }
        }
        throw new IndexOutOfBoundsException("No such pokemon type in database : " + name);
    }

    /**
     * Gets all of the pokemon types from the database.
     * @return The list of all types.
     */
    @Override
    public List<PokemonTypeSendableModel> getAllPokemonTypes() {
        String query = "SELECT DISTINCT(Name) FROM PokemonType;";
        List<PokemonTypeSendableModel> types = new ArrayList<>();
        
        try {
            ResultSet result = executeQuery(query);
            while(result.next()) {
                types.add(new PokemonTypeSendableModel(result.getString("Name")));
            }
        } catch (SQLException exception) {
            LOG.log(Level.SEVERE, "Could not load PokemonTypes: {0}", exception);
        }
        return types;
    }

    @Override
    public List<PokemonSendableModel> getAllPokemons() {
        String query = "SELECT Pokemon.Name AS PName, Pokemon.ImagePath, "
                    + "FirstType.Name as T1Name, SecondType.Name as T2Name FROM Pokemon "
                + "JOIN PokemonType FirstType ON FirstType.Id = Pokemon.TypeFirst "
                + "LEFT OUTER JOIN PokemonType SecondType ON SecondType.Id = Pokemon.TypeSecond;";
        List<PokemonSendableModel> allPokemons = new ArrayList<>();
        
        try {
            ResultSet result = executeQuery(query);
            while(result.next()) {
                String pokemonName = result.getString("PName");
                String imagePath = result.getString("ImagePath");
                String firstType = result.getString("T1Name");
                String secondType = result.getString("T2Name");

                if(secondType == null) {
                    allPokemons.add(new PokemonSendableModel(pokemonName,imagePath,
                            getPokemonTypeByTypeName(firstType)));
                } else {
                    allPokemons.add(new PokemonSendableModel(pokemonName,imagePath,
                            getPokemonTypeByTypeName(firstType),
                            getPokemonTypeByTypeName(secondType)));
                }
            }
        } catch (SQLException exception) {
            LOG.log(Level.SEVERE, "Could not load Pokemons: {0}", exception);
        }
        return allPokemons;
    }

    /**
     * Create a new marker in database
     * @param marker the marker to create in database
     * @throws InvalidParameterException if an error occurred
     */
    @Override
    public void insertMarker(MarkerSendableModel marker) throws InvalidParameterException {
        String query = "INSERT INTO Marker(UserId, PokemonId, Latitude, "
                + "Longitude, TimeStamp, LifePoints, "
                + "Attack, Defense) "
                + "VALUES("
                    + "(SELECT Id FROM User WHERE Username=?), "
                    + "(SELECT Id FROM Pokemon WHERE Name=?), "
                + "?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement statement = _connection.prepareStatement(query);

            int i = 0; // index of statement
            statement.setString(++i, marker.getUsername());
            statement.setString(++i, marker.getPokemonName());
            statement.setDouble(++i, marker.getCoordinate().getLatitude());
            statement.setDouble(++i, marker.getCoordinate().getLongitude());
            statement.setString(++i, new Timestamp(marker.getLongTimestamp()).toString());
            statement.setInt(++i, marker.getLifePoints());
            statement.setInt(++i, marker.getAttack());
            statement.setInt(++i, marker.getDefense());
            statement.execute();

            final int generatedId = statement.getGeneratedKeys().getInt(1);
            marker.setDatabaseId(generatedId);
        } catch(SQLException exception) {
            throw new InvalidParameterException("Cannot insert marker" + marker.toString());
        }
    }

    /**
     * Return all the markers that exist in database
     * @return a list of markers that are in database
     */
    @Override
    public ArrayList<MarkerSendableModel> getAllMarkers() throws InvalidParameterException {
        ArrayList<MarkerSendableModel> allMarkers = new ArrayList<>();
        String query = "SELECT M.Id, U.Username, P.Name, M.Latitude, M.Longitude, " +
            "M.TimeStamp, M.LifePoints, " +
            "M.Attack, M.Defense " +
            "FROM Marker M " +
            "JOIN User U ON U.Id=M.UserId " +
            "JOIN Pokemon P ON P.Id=M.PokemonId;";
        
        try {
            ResultSet allMarkersCursor = executeQuery(query);
            while(allMarkersCursor.next()) {
                MarkerSendableModel newMarker = createMarker(allMarkersCursor);
                if(newMarker != null) {
                    allMarkers.add(newMarker);
                }
            }
        } catch (SQLException exception) {
            LOG.log(Level.SEVERE, "Could not load markers: {0}", exception);
        }
        return allMarkers;
    }

    private MarkerSendableModel createMarker(ResultSet cursor) throws InvalidParameterException {
    	try {
            int i = 0;
            final int id = cursor.getInt(++i);
            final String username = cursor.getString(++i);
            final String pokemonName = cursor.getString(++i);
            final double latitude = cursor.getDouble(++i);
            final double longitude = cursor.getDouble(++i);
            final String timestampString = cursor.getString(++i);
            final int lifePoints = cursor.getInt(++i);
            final int attack = cursor.getInt(++i);
            final int defense = cursor.getInt(++i);
            // This is not optimized (we query all pokemons for one marker), but
            // this is way cleaner in the code, as you can see, we don't have to
            // write any other SQL query.
            final PokemonSendableModel pokemon = getPokemonByName(pokemonName);

            if(timestampString == null || timestampString.isEmpty()) {
                return null;
            }

            ArrayList<ReputationVoteSendableModel> allReputation = getMarkerVotes(id);
            MarkerSendableModel newMarker = new MarkerSendableModel(id, username, 
                    pokemon, latitude, longitude, 
                    Timestamp.valueOf(timestampString).getTime(), allReputation, 
                    lifePoints, attack, defense);

            return newMarker;
        } catch (SQLException exception) {
            throw new InvalidParameterException("could not create marker: " + exception);
        }
    }
    
    private ArrayList<ReputationVoteSendableModel> getMarkerVotes(int markerId) {
        ArrayList<ReputationVoteSendableModel> res = new ArrayList<>();
        String query = "SELECT U.Username AS Username, V.IsUp AS IsUp FROM MarkerVote V "
                + "JOIN User U ON U.Id = V.UserId "
                + "WHERE V.MarkerId=?;";
        
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            
            int i = 0; // index of statement
            statement.setInt(++i, markerId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String username = resultSet.getString("Username");
                boolean isUp = resultSet.getBoolean("IsUp");
                res.add(new ReputationVoteSendableModel(username, isUp, markerId));
            }
        } catch (SQLException exception) {
            LOG.log(Level.SEVERE, "Could not get marker votes: {0}", exception);
        }
        
        return res;
    }

    /**
     * TODO description
     *
     * @param reputationVote 
     */
    @Override
    public void updateMarkerReputation(ReputationVoteSendableModel reputationVote) throws InvalidParameterException {
        String query = "REPLACE INTO MarkerVote (UserId, MarkerId, IsUP) "
                + "VALUES((SELECT User.Id FROM User WHERE User.Username = ?), ?,  ?);";
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, reputationVote.getUsername());
            statement.setInt(2, reputationVote.getMarkerId());
            statement.setInt(3, reputationVote.getIsUpVote() ? 1 : 0);
            statement.execute();
        } catch (SQLException exception) {
            throw new InvalidParameterException("Could not update marker reputation: " + exception);
        }
    }

    /**
     * Updates the information about a Marker.  Doesn't update the reputation !
     * 
     * @param marker the marker to update
     */
    @Override
    public void updateMarker(MarkerSendableModel marker) {
        String query = "UPDATE Marker SET PokemonId=(SELECT Id FROM Pokemon "
                + "WHERE Name=?), TimeStamp=?, LifePoints=?, Attack=?, Defense=? "
                + "WHERE Id=?;";
        
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, marker.getPokemon().getName());
            statement.setString(2, new Timestamp(marker.getLongTimestamp()).toString());
            statement.setInt(3, marker.getLifePoints());
            statement.setInt(4, marker.getAttack());
            statement.setInt(5, marker.getDefense());
            statement.setInt(6, marker.getDatabaseId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new InvalidParameterException("Could not update marker: " + exception);
        }
        
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Id: {0} - {1} - {2} - {3} - {4}", 
                new Object[]{marker.getDatabaseId(), marker.getPokemon().getName(), 
                    new Timestamp(marker.getLongTimestamp()), marker.getAttack(), marker.getDefense()});
    }
    
    /**
     * Signs in (connects) a user.
     * 
     * @param user all user informations
     */
    public void signin(UserSendableModel user) throws InvalidParameterException {
        String query = "SELECT Password FROM User WHERE Username = ? AND Token = '';";
        try {
            PreparedStatement statement;
            statement = _connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                if(!result.getString("Password").equals(user.getPassword())) {
                    throw new SQLException("Wrong password: " + user.getPassword());
                }
            } else {
                throw new SQLException("Unknown username: " + user.getUsername());
            }
        } catch (SQLException exception) {
            throw new InvalidParameterException("Could not sign in: " + exception);
        }
    }
    
    /**
     * Sign Up (register) a new user
     * 
     * @param user all user informations
     */
    @Override
    public void signup(UserSendableModel user) throws InvalidParameterException {
        String query = "INSERT INTO User (Username, Email, Password) "
                    + "VALUES (?, ?, ?);";
        String userInfo = String.join(", ", user.getUsername(), user.getEmail(), user.getPassword());
        
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.execute();
            LOG.log(Level.INFO, "Create user: {0}", userInfo);
            
        } catch (SQLException ex) {
            throw new InvalidParameterException("Invalid signup information "+ userInfo);
        }
    }
    
    public void addTokenToUser(UserSendableModel user, String token) throws InvalidParameterException {
        String query = "UPDATE User SET Token = ? "
                    + "WHERE Username = ?;";
        
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, token);
            statement.setString(2, user.getUsername());
            statement.execute();
            
        } catch (SQLException ex) {
            throw new InvalidParameterException("Invalid add token to user "+ 
                    user.getUsername());
        }
    }
    
    @Override
    public List<FilterSendableModel> getAllFilter() throws InvalidParameterException {
        // TODO
        return new ArrayList<FilterSendableModel>();
    }

    @Override
    public void insertFilter(FilterSendableModel filter) throws InvalidParameterException {
        // TODO
    }

    /**
     * Confirm a user account
     * 
     * @param token the token who confirm account
     */
    public void confirmAccount(String token) throws InvalidParameterException {
        //TODO : use Username to check if we're confirming the right account (duplicate tokens)
        String query = "UPDATE User SET Token = '' WHERE Token = ?";
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, token);
            if(statement.executeUpdate() != 1) {
                throw new SQLException("No user with such token: " + token);
            }
        } catch (SQLException exception) {
            throw new InvalidParameterException("Could not confirm account: " + exception);
        }
    }
    
    /**
     * Method used to delete all the information contained in a table.
     * The name of the latter is given in parameter.
     * 
     * @param table : name of the table that will be deleted. 
     */
    public void deleteTable(String table) {
        String query = "DELETE FROM "+table+";";
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException exception) {
            LOG.log(Level.SEVERE, "Could not execute query <{0}>: {1}",
                    new Object[]{query, exception});
        }
    }
    
}