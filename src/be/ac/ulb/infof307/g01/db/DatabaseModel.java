/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.MarkerModel;
import be.ac.ulb.infof307.g01.PokemonTypeModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.SQLiteErrorCode;
import org.sqlite.SQLiteException;


public class DatabaseModel implements PokemonDatabaseModel, PokemonTypeDatabaseModel, 
        MarkerDatabaseModel {
    
    private static DatabaseModel _instance = null;
    
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

    // TODO refaire ici (pas de logique)
    @Override
    public MarkerModel getMarkerByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PokemonTypeModel[] getTypesByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
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
    
    /**
     * Return the instance of database (to make queries)
     * 
     * @return the DatabaseModel instance
     */
    public static DatabaseModel getDatabase() {
        return _instance;
    }
    
}
