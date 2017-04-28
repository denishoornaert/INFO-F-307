package be.ac.ulb.infof307.g01.server.model;

import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.common.model.MarkerQueryModel;
import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonQueryModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeQueryModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import java.io.File;
import java.io.FileNotFoundException;
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

    /**
     * The database connection
     */
    private Connection _connection;

    public static DatabaseModel getInstance() {
        if(_instance == null) {
            _instance = new DatabaseModel("../../assets/Database.db");
        }
        return _instance;
    }
    
    public static DatabaseModel getTestInstance() {
        if(_instance == null) {
            _instance = new DatabaseModel("../../assets/TestDatabase.db");
        }
        return _instance;
    }
    
    /**
     * Init database
     *
     * @param pathToDatabase path to database
     */
    protected DatabaseModel(String pathToDatabase) {
        try {
            // stops the function if file doesn't exist
            assertDatabaseFileExists(pathToDatabase);
            connectToSqlite(pathToDatabase);
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);   // TODO: gérer proprement les codes d'erreur avec une énum
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
     * Returns whether *.db file exists
     *
     * @param path path to database
     * @return true if file exists and false otherwise
     */
    private void assertDatabaseFileExists(String path) throws FileNotFoundException {
        File file = new File(path);
        if(!file.exists()) {
            throw new FileNotFoundException("File " + path + " not found!");
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
            System.err.println(exception.getMessage());
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
     */
    @Override
    public void insertMarker(MarkerSendableModel marker) {
        String query = "INSERT INTO Marker(PokemonId, Username, Latitude, Longitude, TimeStamp, UpVotes, DownVotes, LifePoint, Attack, Defense) "
                + "VALUES("
                    + "(SELECT Id "
                    + "FROM Pokemon "
                    + "WHERE Name=?), "
                + "?, ?, ?, ?, 0, 0, ?, ?, ?);";
        try {
            CoordinateSendableModel markerCoordinate = marker.getCoordinate();
            PreparedStatement statement = _connection.prepareStatement(query);
            String timestampString = marker.getTimestamp().toString();
            
            statement.setString(1, marker.getPokemonName());
            statement.setString(2, marker.getUsername());
            statement.setDouble(3, markerCoordinate.getLatitude());
            statement.setDouble(4, markerCoordinate.getLongitude());
            statement.setString(5, timestampString);
            statement.setInt(6, marker.getLifePoint());
            statement.setInt(7, marker.getAttack());
            statement.setInt(8, marker.getDefense());
            statement.execute();
            

            final int generatedId = statement.getGeneratedKeys().getInt(1);
            marker.setDatabaseId(generatedId);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Return all the markers that exist in database
     * @return a list of markers that are in database
     */
    @Override
    public ArrayList<MarkerSendableModel> getAllMarkers() {
        ArrayList<MarkerSendableModel> allMarkers = new ArrayList<>();
        String query = "SELECT M.Id, M.Username, P.Name, M.Latitude, M.Longitude, M.TimeStamp, M.UpVotes, M.DownVotes, M.LifePoint, M.Attack, M.Defense "
                + "FROM Marker M "
                + "JOIN Pokemon P "
                + "    ON P.Id=M.PokemonId;";
        try {
            ResultSet allMarkersCursor = executeQuery(query);
            while(allMarkersCursor.next()) {
                allMarkers.add(createMarker(allMarkersCursor));
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
        final int upVotes = cursor.getInt(++i);
        final int downVotes = cursor.getInt(++i);
        final int lifePoint = cursor.getInt(++i);
        final int attack = cursor.getInt(++i);
        final int defense = cursor.getInt(++i);
        
        /*
        int databaseId, String username, 
            PokemonSendableModel pokemon, CoordinateSendableModel coordinate, 
            Timestamp timestamp, ReputationScoreSendableModel reputation, int lifePoint, 
            int attack, int defense
        */
        
        return new MarkerSendableModel(id, username, pokemonName, latitude, longitude,
                Timestamp.valueOf(timestampString), upVotes, downVotes, lifePoint, attack, defense);
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

    ///////////////////// STATIC /////////////////////

    /**
     * Return the instance of database (to make queries)
     *
     * @return the DatabaseModel instance
     */
    public static DatabaseModel getDatabase() {
        return _instance;
    }

}