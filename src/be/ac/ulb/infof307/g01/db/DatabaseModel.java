/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.CoordinateModel;
import be.ac.ulb.infof307.g01.MarkerModel;
import be.ac.ulb.infof307.g01.PokemonModel;
import be.ac.ulb.infof307.g01.PokemonTypeModel;
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


public class DatabaseModel implements PokemonDatabaseModel, PokemonTypeDatabaseModel, 
        MarkerDatabaseModel {
    
    private static DatabaseModel _instance = null;
    
    /**
     * The database connection
     */
    private Connection _connection;
    
    /**
     * Init database
     * 
     * @param pathToDatabase path to database
     * @throws java.sql.SQLException
     * @throws java.io.FileNotFoundException
     */
    public DatabaseModel(String pathToDatabase) throws SQLException, FileNotFoundException {
        if(_instance != null) {
            throw new IllegalStateException("DatabaseModel was already instanciated");
        }
        // stops the function if file doesn't exist
        assertDatabaseFileExists(pathToDatabase);
        connectToSqlite(pathToDatabase);
        loadAllPokemonTypes();
        
        _instance = this;
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
        System.out.println("Connection to " + pathToDatabase + " successful");
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
        String query = "SELECT DISTINCT(Name) FROM PokemonType";
        ResultSet result = executeQuery(query);
        
        try {
            while(result.next()) {
                try {
                    new PokemonTypeModel(result.getString("Name"));
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
                    + "PokemonType.Name AS TypeName "
                + "FROM Pokemon "
                + "JOIN PokemonPokemonTypeLink "
                    + "ON PokemonPokemonTypeLink.PokemonId = Pokemon.Id "
                + "JOIN PokemonType "
                    + "ON PokemonType.Id = PokemonPokemonTypeLink.TypeId";
        ResultSet result = executeQuery(query);
        
        try {
            while(result.next()) {
                try {
                    String pokemonName = result.getString("PName");
                    String imagePath = result.getString("ImagePath");
                    String pokemonType = result.getString("TypeName");
                    new PokemonModel(pokemonName,imagePath,
                            PokemonTypeModel.getPokemonTypeByTypeName(pokemonType));
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
     * Return the instance of database (to make queries)
     * 
     * @return the DatabaseModel instance
     */
    public static DatabaseModel getDatabase() {
        return _instance;
    }

    /**
     * Create a new marker in database
     * @param marker the marker to create in database
     */
    @Override
    public void insertMarker(MarkerModel marker) {
        String query = "INSERT INTO Marker(PokemonId, X, Y, TimeStamp) "
                + "VALUES("
                    + "(SELECT Id "
                    + "FROM Pokemon "
                    + "WHERE Name=?),"
                + "?, ?, ?)";
        try {
            CoordinateModel markerCoordinate = marker.getCoordinate();
            PreparedStatement statement = _connection.prepareStatement(query);
            String timestampString = marker.getTimestamp().toString();
            
            statement.setString(1, marker.getPokemonName());
            statement.setInt(2, markerCoordinate.getX());
            statement.setInt(3, markerCoordinate.getY());
            statement.setString(4, timestampString);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Return all the markers that exist in database
     * @return a list of markers that are in database
     */
    @Override
    public ArrayList<MarkerModel> getAllMarkers() {
        ArrayList<MarkerModel> allMarkers = new ArrayList<>();
        String query = "SELECT P.Name, M.X, M.Y, M.TimeStamp "
                + "FROM Marker M "
                + "JOIN Pokemon P "
                + "    ON P.Id=M.PokemonId ";

        try {
            ResultSet allMarkersCursor = executeQuery(query);
            while(allMarkersCursor.next()) {
                final String pokemonName = allMarkersCursor.getString(1);
                final int xCoordinate = allMarkersCursor.getInt(2);
                final int yCoordinate = allMarkersCursor.getInt(3);
                final String timestampString = allMarkersCursor.getString(4);
                
                PokemonModel pokemon = PokemonModel.getPokemonByName(pokemonName);
                CoordinateModel coordinate = new CoordinateModel(xCoordinate, yCoordinate);
                MarkerModel currentMarker = new MarkerModel(pokemon, coordinate);
                currentMarker.setTimestamp(Timestamp.valueOf(timestampString));
                allMarkers.add(currentMarker);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allMarkers;
    }
    
}
