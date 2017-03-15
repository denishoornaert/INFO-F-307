/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.bdd;

import be.ac.ulb.infof307.g01.MarkerModel;
import be.ac.ulb.infof307.g01.PokemonModel;
import be.ac.ulb.infof307.g01.PokemonTypeModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Bdd implements PokemonInterfaceBdd, PokemonTypeInterfaceBdd, MarkerInterfaceBdd {
    
    private String _path;
    private Connection connection;
    private Statement statement;
    
    public Bdd(String path) {
        _path = path;
        this.connect();
    }
    
    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" +_path);
            statement = connection.createStatement();
            System.out.println("Connexion a " + _path + " avec succès");
        } catch (ClassNotFoundException | SQLException notFoundException) {
            notFoundException.printStackTrace();
        }
    }
    
    private ResultSet query(String requet) {
       ResultSet resultat = null;
       try {
           resultat = statement.executeQuery(requet);
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return resultat;
    }

    @Override
    // TODO Pokemon type doit être changé en classé
    // TODO Récupérer le type du pokémon
    public PokemonModel getPokemonByName(String name) {
        ResultSet resultSet = this.query("SELECT * FROM Pokemon WHERE Name='"+name+"'");
        String nomPokemon = "";
        try {
            resultSet.next();
            nomPokemon = resultSet.getString("Name");
        } catch (SQLException ex) {
            Logger.getLogger(Bdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new PokemonModel(nomPokemon, PokemonTypeModel.DARK);
    }

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
    
}
