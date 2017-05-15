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

/** 
 * Class that interacts with the database.
 * 
 * This class implements all queries needed in the application. It is not
 * intended to be used directly, but rather through one of the implemented
 * interfaces. This way, a class needing a database access is only allowed
 * to call a subset of all the database methods, according to the responsibility
 * of this class.
 * 
 * For example, if a class need to access only pokemon types in the database:
 * {@code
 * PokemonTypeDatabaseModel database = (PokemonTypeDatabaseModel) DatabaseModel.getDatabase();
 * // Now database allows to call only queries related to pokemon types.
 * }
 * 
 * Note: This class doesn't implement ConnectionQueryController, since this
 * interface requires a "signup" method taking a UserSendableModel, but the
 * database requires a token as well in order to register a user.
 */
public class DatabaseModel implements PokemonQueryController, PokemonTypeQueryController,
        MarkerQueryController, ConnectionQueryController, FilterQueryController {

    private static DatabaseModel _instance = null;
    private static final Logger LOG = Logger.getLogger(DatabaseModel.class.getName());
    private static ServerConfiguration CONFIG;
    
    /**
     * The database connection
     */
    private Connection _connection;

    public static DatabaseModel getInstance() throws IllegalAccessError {
        if(_instance == null) {
            CONFIG = ServerConfiguration.getInstance();
            _instance = new DatabaseModel(CONFIG.getDatabasePath(),
                    CONFIG.getSqlPath());
        }
        return _instance;
    }
    
    public static DatabaseModel getTestInstance() throws IllegalAccessError {
        if(_instance == null) {
            CONFIG = ServerConfiguration.getTestInstance();
            _instance = new DatabaseModel(CONFIG.getTestDatabasePath(), 
                    CONFIG.getTestSqlPath());
        }
        return _instance;
    }
    
    /**
     * Initializes database
     *
     * @param pathToDatabase path to database
     * @param pathToSql path to SQL file
     * @throws IllegalAccessError if database haven't be load
     */
    protected DatabaseModel(final String pathToDatabase, final String pathToSql) 
            throws IllegalAccessError {
        try {
            createDatabase(pathToDatabase, pathToSql);
            connectToSqlite(pathToDatabase);
        } catch(IOException | SQLException exception) {
            final String errorMessage = "Could not create Database: " + 
                    exception.getMessage();
            LOG.log(Level.SEVERE, errorMessage);
            System.exit(1);
            throw new IllegalAccessError(errorMessage);
        }
    }
    
    /**
     * Create the database file (.db from .sql).
     *
     * @param databasePath path to database
     * @param sqlPath sql file to create database
     * @return false if file already exist, true if file have been juste created
     */
    private void createDatabase(final String databasePath, final String sqlPath) 
            throws IOException, SQLException {
        final File file = new File(databasePath);
        if(!file.exists()) {
            file.createNewFile();
            LOG.log(Level.INFO, "Created Database to {0}", databasePath);
            connectToSqlite(databasePath);
            fillDatabase(sqlPath);
        }
    }

    /**
     * Initialize the connection to the SQLite database
     *
     * @param pathToDatabase path to database
     * @return true if database was properly loaded and false otherwise
     */
    private void connectToSqlite(final String pathToDatabase) throws SQLException {
        _connection = DriverManager.getConnection(
                "jdbc:sqlite:" + pathToDatabase);
    }
    
    /**
     * Get the content of .sql file
     * 
     * @return the file content
     */
    private String[] getSQLQueriesFromFile(final String filePath) 
            throws FileNotFoundException, IOException {
        final Path sqlPath = Paths.get(filePath);
        final List<String> lines = Files.readAllLines(sqlPath);
        String query = "";
        for(final String line : lines) {
            query += line;
        }
        query = query.replace("\n", "");
        return query.split(";");
    }
    
    private void fillDatabase(final String pathToSqlFile) {
        String[] content;
        try {
            content = getSQLQueriesFromFile(pathToSqlFile);
        } catch (IOException exception) {
            LOG.log(Level.SEVERE, "Could not read sql file <{0}>: {1}",
                    new Object[]{pathToSqlFile, exception});
            return;
        }
        
        for(final String query : content) {
            try {
                executeQuery(query);
            } catch (SQLException exception) {
                LOG.log(Level.SEVERE, "Could not execute sql query <{0}>: {1}",
                        new Object[]{content, exception});
            }
        }
    }
    
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
    private ResultSet executeQuery(final String query) throws SQLException {
       final Statement statement = _connection.createStatement();
       final ResultSet resultat = statement.executeQuery(query);
       return resultat;
    }
    
    /**
     * Retrieves a pokemon from its name in the database.
     * @param name The name of the pokemon to get
     * @return The pokemon object
     * @throws IndexOutOfBoundsException If no pokemon has been found with this name
     */
    public PokemonSendableModel getPokemonByName(final String name) {
        final List<PokemonSendableModel> allPokemons = getAllPokemons();
        for(final PokemonSendableModel pokemon : allPokemons) {
            if(pokemon.getName().equals(name)) {
                return pokemon;
            }
        }
        throw new IndexOutOfBoundsException("No such pokemon in database : " + name);
    }
    
    /**
     * Gets a pokemon type from its name in the database.
     * 
     * @param name The type name
     * @return an pokemon type object
     * @throws IndexOutOfBoundsException If no type has been found with this name
     */
    public PokemonTypeSendableModel getPokemonTypeByTypeName(final String name) {
        /* TODO This *may* be a serious performance issue when getting all markers:
        * getAllMarkers loads all markers
        * -> for each marker, all pokemons are retrieved in order to find the right one
        *    -> for each pokemon, all types are loaded for the same reason...
        */
        final List<PokemonTypeSendableModel> types = getAllPokemonTypes();
        for(final PokemonTypeSendableModel type : types) {
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
        final String query = "SELECT DISTINCT(Name) FROM PokemonType;";
        final List<PokemonTypeSendableModel> types = new ArrayList<>();
        
        try {
            final ResultSet result = executeQuery(query);
            while(result.next()) {
                types.add(new PokemonTypeSendableModel(result.getString("Name")));
            }
        } catch (SQLException exception) {
            LOG.log(Level.SEVERE, "Could not load PokemonTypes: {0}", exception);
        }
        return types;
    }

    @Override
    /**
     * Query to get allPokemons from the database.
     * 
     * The function is called when the application is started and loads all the 
     * pokemons on a PokemonSendableModel list.
     */
    public List<PokemonSendableModel> getAllPokemons() {
        final String query = "SELECT Pokemon.Name AS PName, Pokemon.ImagePath, "
                    + "FirstType.Name as T1Name, SecondType.Name as T2Name FROM Pokemon "
                + "JOIN PokemonType FirstType ON FirstType.Id = Pokemon.TypeFirst "
                + "LEFT OUTER JOIN PokemonType SecondType ON SecondType.Id = Pokemon.TypeSecond;";
        final List<PokemonSendableModel> allPokemons = new ArrayList<>();
        
        try {
            final ResultSet result = executeQuery(query);
            while(result.next()) {
                final String pokemonName = result.getString("PName");
                final String imagePath = result.getString("ImagePath");
                final String firstType = result.getString("T1Name");
                final String secondType = result.getString("T2Name");

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
     * Create a new marker in database.
     * @param marker the marker to create in database
     * @throws InvalidParameterException if an error occurred
     */
    @Override
    public void insertMarker(final MarkerSendableModel marker) 
            throws InvalidParameterException {
        final String query = "INSERT INTO Marker(UserId, PokemonId, Latitude, "
                + "Longitude, TimeStamp, LifePoints, "
                + "Attack, Defense) "
                + "VALUES("
                    + "(SELECT Id FROM User WHERE Username=?), "
                    + "(SELECT Id FROM Pokemon WHERE Name=?), "
                + "?, ?, ?, ?, ?, ?);";
        try {
            final PreparedStatement statement = _connection.prepareStatement(query);

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
     * Return all the markers that exist in database.
     * @return a list of markers that are in database
     */
    @Override
    public ArrayList<MarkerSendableModel> getAllMarkers() 
            throws InvalidParameterException {
        final ArrayList<MarkerSendableModel> allMarkers = new ArrayList<>();
        final String query = "SELECT M.Id, U.Username, P.Name, M.Latitude, M.Longitude, " +
            "M.TimeStamp, M.LifePoints, " +
            "M.Attack, M.Defense " +
            "FROM Marker M " +
            "JOIN User U ON U.Id=M.UserId " +
            "JOIN Pokemon P ON P.Id=M.PokemonId;";
        
        try {
            final ResultSet allMarkersCursor = executeQuery(query);
            while(allMarkersCursor.next()) {
                final MarkerSendableModel newMarker = createMarker(allMarkersCursor);
                if(newMarker != null) {
                    allMarkers.add(newMarker);
                }
            }
        } catch (SQLException exception) {
            LOG.log(Level.SEVERE, "Could not load markers: {0}", exception);
        }
        return allMarkers;
    }

    /**
     * Create Marker objects from ResultSet.
     * 
     * The function create a new MarkerSendableModel based on the result of a 
     * database query.
     * @param cursor result from a query
     * @return a new MarkerSendableModel
     * @throws InvalidParameterException 
     */
    private MarkerSendableModel createMarker(final ResultSet cursor) throws InvalidParameterException {
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

            final ArrayList<ReputationVoteSendableModel> allReputation = getMarkerVotes(id);
            final MarkerSendableModel newMarker = new MarkerSendableModel(id, username, 
                    pokemon, latitude, longitude, 
                    Timestamp.valueOf(timestampString).getTime(), allReputation, 
                    lifePoints, attack, defense);

            return newMarker;
        } catch (SQLException exception) {
            throw new InvalidParameterException("could not create marker: " + exception);
        }
    }
    
    /**
     * Gets all votes directly linked with a marker.
     * @param markerId to get the marker inside db
     * @return a list of reputation votes
     */
    private ArrayList<ReputationVoteSendableModel> getMarkerVotes(final int markerId) {
        final ArrayList<ReputationVoteSendableModel> res = new ArrayList<>();
        final String query = "SELECT U.Username AS Username, V.IsUp AS IsUp FROM MarkerVote V "
                + "JOIN User U ON U.Id = V.UserId "
                + "WHERE V.MarkerId=?;";
        
        try {
            final PreparedStatement statement = _connection.prepareStatement(query);
            
            int i = 0; // index of statement
            statement.setInt(++i, markerId);
            final ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                final String username = resultSet.getString("Username");
                final boolean isUp = resultSet.getBoolean("IsUp");
                res.add(new ReputationVoteSendableModel(username, isUp, markerId));
            }
        } catch (SQLException exception) {
            LOG.log(Level.SEVERE, "Could not get marker votes: {0}", exception);
        }
        
        return res;
    }

    /**
     * Changes or inserts a vote in the database. 
     * If the user did not yet voted on the marker, the vote is created. 
     * Otherwise, the vote is replaced.
     * @param reputationVote The vote model to add or update.
     */
    @Override
    public void updateMarkerReputation(final ReputationVoteSendableModel reputationVote) 
            throws InvalidParameterException {
        final String query = "REPLACE INTO MarkerVote (UserId, MarkerId, IsUP) "
                + "VALUES((SELECT User.Id FROM User WHERE User.Username = ?), ?,  ?);";
        try {
            final PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, reputationVote.getUsername());
            statement.setInt(2, reputationVote.getMarkerId());
            statement.setInt(3, reputationVote.getIsUpVote() ? 1 : 0);
            statement.execute();
        } catch (SQLException exception) {
            throw new InvalidParameterException("Could not update marker reputation: " + exception);
        }
    }

    /**
     * Updates the information about a Marker. Doesn't update the reputation !
     * @param marker the marker to update
     */
    @Override
    public void updateMarker(final MarkerSendableModel marker) {
        final String query = "UPDATE Marker SET PokemonId=(SELECT Id FROM Pokemon "
                + "WHERE Name=?), TimeStamp=?, LifePoints=?, Attack=?, Defense=? "
                + "WHERE Id=?;";
        
        try {
            final PreparedStatement statement = _connection.prepareStatement(query);
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
     * @param user all user informations
     */
    @Override
    public void signin(final UserSendableModel user) throws InvalidParameterException {
        final String query = "SELECT Password, Email FROM User WHERE Username = ? " + 
                "AND Token = '';";
        try {
            PreparedStatement statement;
            statement = _connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            final ResultSet result = statement.executeQuery();
            if(result.next()) {
                if(!result.getString("Password").equals(user.getPassword())) {
                    throw new SQLException("Wrong password: " + user.getPassword());
                }
                user.setEmail(result.getString("Email"));
            } else {
                throw new SQLException("Unknown username: " + user.getUsername());
            }
        } catch (SQLException exception) {
            throw new InvalidParameterException("Could not sign in: " + exception);
        }
    }
    
    /**
     * Sign Up (register) a new user.
     * @param user all user informations
     */
    @Override
    public void signup(final UserSendableModel user) throws InvalidParameterException {
        final String query = "INSERT INTO User (Username, Email, Password) "
                    + "VALUES (?, ?, ?);";
        final String userInfo = String.join(", ", user.getUsername(), user.getEmail(), 
                user.getPassword());
        
        try {
            final PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.execute();
            LOG.log(Level.INFO, "Create user: {0}", userInfo);
            
        } catch (SQLException ex) {
            throw new InvalidParameterException("Invalid signup information "+ 
                userInfo + " (error: " + ex.getMessage() + ")");
        }
    }
    
    public void addTokenToUser(final UserSendableModel user, final String token) 
            throws InvalidParameterException {
        final String query = "UPDATE User SET Token = ? "
                    + "WHERE Username = ?;";
        
        try {
            final PreparedStatement statement = _connection.prepareStatement(query);
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
        final ArrayList<FilterSendableModel> allFilter = new ArrayList<>();
        final String query = "SELECT Name, Expression FROM Filter;";
        
        try {
            final ResultSet allFilterCursor = executeQuery(query);
            while(allFilterCursor.next()) {
                final String name = allFilterCursor.getString("Name");
                final String expression = allFilterCursor.getString("Expression");
                allFilter.add(new FilterSendableModel(name, expression));
            }
        } catch (SQLException exception) {
            LOG.log(Level.SEVERE, "Could not load markers: {0}", exception);
        }
        return allFilter;
    }

    @Override
    public void insertFilter(final FilterSendableModel filter) 
            throws InvalidParameterException {
        final String query = "INSERT INTO Filter(Name, Expression) "
                + "VALUES(?, ?);";
        try {
            final PreparedStatement statement = _connection.prepareStatement(query);
            
            statement.setString(1, filter.getName());
            statement.setString(2, filter.getExpression());
            statement.execute();
        } catch(SQLException exception) {
            throw new InvalidParameterException("Cannot insert filter " + 
                    filter.getName() + " (error: " + exception.getMessage() + ")");
        }
    }

    /**
     * Confirms a user account from a token, and its username.
     * 
     * @param username The username
     * @param token the token who confirms account
     */
    public void confirmAccount(final String username, final String token) 
            throws InvalidParameterException {
        final String query = "UPDATE User SET Token = '' WHERE Username = ? " + 
                "AND Token = ?";
        try {
            final PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, token);
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
    public void deleteTable(final String table) {
        final String query = "DELETE FROM "+table+";";
        try {
            final PreparedStatement statement = _connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException exception) {
            LOG.log(Level.SEVERE, "Could not execute query <{0}>: {1}",
                    new Object[]{query, exception});
        }
    }
    
}