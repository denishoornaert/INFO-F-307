/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.db;

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
import java.util.ArrayList;


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
    public PokemonModel getPokemonByName(String pokemonName) {
        PokemonModel pokemonToReturn = null;
        PokemonTypeModel[] pokemonTypes = getPokemonTypesByName(pokemonName);
        pokemonToReturn = new PokemonModel(pokemonName, pokemonTypes);
        return pokemonToReturn;
    }
    
    @Override
    public PokemonTypeModel[] getPokemonTypesByName(String pokemonName) {
        PokemonTypeModel[] typesToReturn = null;
        String query = "SELECT T.Name "
                + "FROM PokemonType T "
                + "JOIN PokemonPokemonTypeLink L "
                + "  ON L.TypeId=T.Id "
                + "JOIN Pokemon P "
                + "  ON P.Id=L.PokemonId "
                + "WHERE P.Name=?";
        try {
            ArrayList<PokemonTypeModel> typesList = new ArrayList<>();
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, pokemonName);
            ResultSet results = statement.executeQuery();
            while(results.next()) {
                typesList.add(PokemonTypeModel.getPokemonTypeByTypeName(results.getString(1)));
            }
            try {
                typesToReturn = (PokemonTypeModel[])typesList.toArray(new PokemonTypeModel[typesList.size()]);
            } catch(NullPointerException ex) {
                System.err.println(ex);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return typesToReturn;
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
