/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.MarkerModel;
import be.ac.ulb.infof307.g01.PokemonTypeModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseModel implements PokemonDatabaseModel, PokemonTypeDatabaseModel, 
        MarkerDatabaseModel {
    
    private static DatabaseModel _instance = null;
    
    private Connection _connection;
    
    /**
     * Init database
     * 
     * @param pathToDatabase path to database
     */
    public DatabaseModel(String pathToDatabase) {
        if(_instance != null) {
            throw new IllegalStateException("DatabaseModel was already instanciated");
        }
        connectToSqlite(pathToDatabase);
        
        _instance = this;
    }
    
    private void connectToSqlite(String pathToDatabase) {
        try {
            //Class.forName("org.sqlite.JDBC");
            _connection = DriverManager.getConnection(
                    "jdbc:sqlite:" + pathToDatabase);
            System.out.println("Connection to " + pathToDatabase + " successful");
            
        } catch (SQLException exception) {
            // TODO afficher erreur
            System.err.println(exception.getMessage());
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
