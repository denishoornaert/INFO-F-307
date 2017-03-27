/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author remy
 */
public class PokemonModel {
    
    private static HashMap<String, PokemonModel> _allPokemon = new HashMap<>();
    
    
    private final String _name;
    private final PokemonTypeModel[] _type;
    private final String _pathImage;
    
    /**
     * Init a Pokemon with default type (NONE)
     * 
     * @param name of the pokemon
     */
    public PokemonModel(String name) {
        this(name, PokemonTypeModel.getPokemonTypeByTypeName("NONE"));
    }
    
    public PokemonModel(String name, PokemonTypeModel... type) {
        _name = name;
        _type = type;
        _pathImage = findCorrespondingImagePath();
    }
    
    private String findCorrespondingImagePath() {
        return "assets" + File.separator + "sprites" + File.separator + _name + ".png";
    }
    
    
    public String getName() {
        return _name;
    }
    
    public PokemonTypeModel[] getTypes() {
        return _type;
    }
    
    public String getPathImage() {
        return _pathImage;
    }
    
    
    /**
     * Test if two pokemon have same name and type
     * 
     * @param otherPokemon the pokemon which must be compared
     * @return True if it's the same pokemon
     */
    public boolean equals(PokemonModel otherPokemon) {
        return otherPokemon._name.equals(_name) && 
                Arrays.equals(otherPokemon._type, _type);
    }
    
    
    /////////////////// STATIC /////////////////////
    
    /**
     * Return the pokemonModel instance by name
     * 
     * @param name the desired name
     * @return the PokemonModel instance or null if not found
     */
    public static PokemonModel getPokemonByName(String name) {
        return _allPokemon.get(name);
    }
    
    public static ArrayList<PokemonModel> getAllPokemon() {
        return new ArrayList<>(_allPokemon.values());
    }
    
    
}
