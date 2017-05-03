package be.ac.ulb.infof307.g01.server.model;

import be.ac.ulb.infof307.g01.common.model.MarkerQueryModel;
import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonQueryModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeQueryModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import be.ac.ulb.infof307.g01.server.ServerConfiguration;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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
 */
public class DatabaseModel implements PokemonQueryModel, PokemonTypeQueryModel,
        MarkerQueryModel {

    private static DatabaseModel _instance = null;
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
            boolean justeCreated = createDatabaseFile(pathToDatabase);
            connectToSqlite(pathToDatabase);
            if(justeCreated) {
                createAllTables(pathToDatabase);
            }
        } catch(IOException | SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        
        loadAllTables();

        _instance = this;
    }

    /**
     * Load all Data (Pokemon and PokemonType)
     */
    private void loadAllTables() {
        loadAllPokemonTypes();
        loadAllPokemons();
    }

    /**
     * Create the database file (.db from .sql)
     *
     * @param path path to database
     * @return false if file already exist, true if file have been juste created
     */
    private boolean createDatabaseFile(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()) {
            file.createNewFile();
            return true;
        }
        return false;
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
    private String[] getContentSqlFile(String pathToSqlFile) throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        FileReader fr = new FileReader(new File(pathToSqlFile));
        BufferedReader br = new BufferedReader(fr);
        
        String s;
        while((s = br.readLine()) != null) {
            sb.append(s);
        }
        br.close();
        
        return sb.toString().split(";");
    }
    
    private void createAllTables(String pathToDatabase) {
        String pathToSqlFile = pathToDatabase.substring(0, pathToDatabase.lastIndexOf('.'));
        try {
            String[] content = getContentSqlFile(pathToSqlFile + ".sql");
            for(String query : content) {
                executeQuery(query);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, 
                    null, "Couls not read sql file (" + pathToSqlFile + ")" + ex);
        }
    }
    
    /**
     * Properly close the connection to the database
     */
    public void close() {
        try {
            _connection.close();
            _instance = null;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void closeDatabase() {
        if(_instance != null) {
            _instance.close();
        }
    }
    
    /**
     * Execute a query
     *
     * @param query the string query
     * @return a resultset with the result
     */
    private ResultSet executeQuery(String query) {
       ResultSet resultat = null;
       try {
           Statement statement = _connection.createStatement();
           resultat = statement.executeQuery(query);

       } catch (SQLException e) {
           System.err.println(e.getMessage());
       }
       return resultat;
    }

    /**
     * Load all of the pokemon types from the database
     */
    @Override
    public void loadAllPokemonTypes() {
        String query = "SELECT DISTINCT(Name) FROM PokemonType;";
        ResultSet result = executeQuery(query);

        try {
            while(result.next()) {
                try {
                    new PokemonTypeSendableModel(result.getString("Name"));
                } catch(IllegalStateException exception) {
                    System.err.println(exception.getMessage());
                    break;
                }
            }
        } catch (SQLException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, exception.getMessage());
        }
    }

    @Override
    public void loadAllPokemons() {
        String query = "SELECT Pokemon.Name AS PName, Pokemon.ImagePath, "
                    + "FirstType.Name as T1Name, SecondType.Name as T2Name FROM Pokemon "
                + "JOIN PokemonType FirstType ON FirstType.Id = Pokemon.TypeFirst "
                + "LEFT OUTER JOIN PokemonType SecondType ON SecondType.Id = Pokemon.TypeSecond;";
        ResultSet result = executeQuery(query);

        // TODO Duplicated code here ?
        try {
            while(result.next()) {
                try {
                    String pokemonName = result.getString("PName");
                    String imagePath = result.getString("ImagePath");
                    String firstType = result.getString("T1Name");
                    String secondType = result.getString("T2Name");
                    if(secondType == null) {
                        new PokemonSendableModel(pokemonName,imagePath,
                                PokemonTypeSendableModel.getPokemonTypeByTypeName(firstType));
                    } else {
                        new PokemonSendableModel(pokemonName,imagePath,
                                PokemonTypeSendableModel.getPokemonTypeByTypeName(firstType),
                                PokemonTypeSendableModel.getPokemonTypeByTypeName(secondType));
                    }
                } catch(IllegalStateException exception) {
                    System.err.println(exception.getMessage());
                    break;
                }
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    /**
     * Create a new marker in database
     * @param marker the marker to create in database
     * @return True if all is ok, false otherwise
     */
    @Override
    public boolean insertMarker(MarkerSendableModel marker) {
        boolean result = false;
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
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Return all the markers that exist in database
     * @return a list of markers that are in database
     */
    @Override
    public ArrayList<MarkerSendableModel> getAllMarkers() {
        ArrayList<MarkerSendableModel> allMarkers = new ArrayList<>();
        String query = "SELECT M.Id, U.Username, P.Name, M.Latitude, M.Longitude, " +
            "M.TimeStamp, COUNT(V.IsUp = 1) AS UpVotes, " +
            "COUNT(V.IsUp = 0) AS DownVotes, M.LifePoints, " +
            "M.Attack, M.Defense " +
            "FROM Marker M " +
            "JOIN User U ON U.Id=M.UserId " +
            "JOIN Pokemon P ON P.Id=M.PokemonId " +
            "JOIN MarkerVote V ON V.UserId = U.Id AND V.MarkerId = M.Id;";
        
        try {
            ResultSet allMarkersCursor = executeQuery(query);
            while(allMarkersCursor.next()) {
                MarkerSendableModel newMarker = createMarker(allMarkersCursor);
                if(newMarker != null) {
                    allMarkers.add(newMarker);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allMarkers;
    }

    private MarkerSendableModel createMarker(ResultSet cursor) throws SQLException {
    	int i = 0;
        final int id = cursor.getInt(++i);
        final String username = cursor.getString(++i);
        final String pokemonName = cursor.getString(++i);
        final double latitude = cursor.getDouble(++i);
        final double longitude = cursor.getDouble(++i);
        final String timestampString = cursor.getString(++i);
        final int upVotes = getMarkerUpVotes(id);
        final int downVotes = getMarkerDownVotes(id);
        final int lifePoints = cursor.getInt(++i);
        final int attack = cursor.getInt(++i);
        final int defense = cursor.getInt(++i);
        
        if(timestampString == null || timestampString.isEmpty()) {
            return null;
        }
        
        return new MarkerSendableModel(id, username, pokemonName, latitude, longitude,
                Timestamp.valueOf(timestampString).getTime(), upVotes, 
                downVotes, lifePoints, attack, defense);
    }
    
    public int getMarkerUpVotes(int markerId) {
        return getMarkerVotes(markerId, true);
    }
    
    public int getMarkerDownVotes(int markerId) {
        return getMarkerVotes(markerId, false);
    }
    
    private int getMarkerVotes(int markerId, boolean isUp) {
        int votes = 0;
        String query = "SELECT COUNT(*) FROM MarkerVote V "
                + "WHERE MarkerId=? AND IsUp=?;";
        
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            
            int i = 0; // index of statement
            statement.setInt(++i, markerId);
            statement.setBoolean(++i, isUp);
            statement.execute();
            
            votes = statement.getGeneratedKeys().getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votes;
    }

    @Override
    /**
     * Update in the database the reputation of the given marker.
     * This function is usually called after a vote has been done by a user.
     * This function does not take a vote in parameter, as the marker already
     * know its new reputation value, it would be dumb to compute it again here.
     *
     * @param marker The marker that need to be updated in the database.
     */
    public void updateMarkerReputation(MarkerSendableModel marker) {
        String query = "UPDATE Marker SET UpVotes=?, DownVotes=? WHERE Id=?;";
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setInt(1, marker.getUpVotes());
            statement.setInt(2, marker.getDownVotes());
            statement.setInt(3, marker.getDatabaseId());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean updateMarker(MarkerSendableModel marker) {
        boolean res = false;
        
        String query = "UPDATE Marker SET PokemonId=(SELECT Id FROM Pokemon "
                + "WHERE Name=?), TimeStamp=?, LifePoint=?, Attack=?, Defense=? "
                + "WHERE Id=?;";
        
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, marker.getPokemon().getName());
            statement.setTimestamp(2, new Timestamp(marker.getLongTimestamp()));
            statement.setInt(3, marker.getAttack());
            statement.setInt(3, marker.getDefense());
            statement.setInt(3, marker.getDatabaseId());
            res = (statement.executeUpdate() == 1);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    /**
     * Sign in (connect) a user
     * 
     * @param user all user informations
     * @return True if user have send the good password
     * @throws java.sql.SQLException error with sql
     */
    public boolean signin(UserSendableModel user) throws SQLException {
        String query = "SELECT Password FROM User WHERE Username = ? AND Token = '';";
        
        PreparedStatement statement = _connection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        ResultSet result = statement.executeQuery();
        
        if(result.next()) {
            if(result.getString("Password").equals(user.getPassword())) {
               return true; 
            } else {
                Logger.getLogger(getClass().getName()).log(Level.INFO, 
                    "User: {0} failded password: {1}", 
                    new Object[]{user.getUsername(), user.getPassword()});
            }
        }
        
        return false;
    }
    
    /**
     * Sign Up (register) a new user
     * 
     * @param user all user informations
     * @param token with must be send to email
     * @throws java.sql.SQLException error with sql
     */
    public void signup(UserSendableModel user, String token) throws SQLException {
        String query = "INSERT INTO User (Username, Email, Password, Token) "
                + "VALUES (?, ?, ?, ?);";
        
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Create user: "
                + "{0} - {1} - {2}", new Object[]{user.getUsername(), 
                    user.getEmail(), user.getPassword()});
        
        PreparedStatement statement = _connection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, token);
        statement.execute();
    }

    /**
     * Confirm a user account
     * 
     * @param token the token who confirm account
     * @return True if the token have been confirm
     * @throws java.sql.SQLException an sql error
     */
    public boolean confirmAccount(String token) throws SQLException {
        String query = "UPDATE User SET Token = '' WHERE Token = ?";
        
        PreparedStatement statement = _connection.prepareStatement(query);
        statement.setString(1, token);
        
        return statement.executeUpdate() == 1;
    }
        
}