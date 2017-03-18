/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mytra
 */
public class PokemonTypeModel {
    
    /**
     * HashMap mapping the name (String) of the type to its only instance in the
     * application
     */
    private static HashMap<String, PokemonTypeModel> _allPokemonTypes = new HashMap<>();
    private final String _typeName;
    
    static {
        _allPokemonTypes.put("NONE", new PokemonTypeModel("NONE"));
    }
    
    /**
     * Create a new PokemonType
     * @param typeName the name of the brand new type
     * @throws IllegalStateException if type already exists
     */
    public PokemonTypeModel(String typeName) {
        if(_allPokemonTypes.containsKey(typeName.toUpperCase())) {
            throw new IllegalStateException("PokemonType " + typeName + 
                    " already created");
        }
        
        _typeName = typeName.toUpperCase();
        _allPokemonTypes.put(typeName.toUpperCase(), this);
    }
    
    /**
     * Return the name of the type
     * @return the name of the type
     */
    public String getTypeName() {
        return _typeName;
    }
    
    /**
     * Return the pokemonTypeModel instance by name
     * 
     * @param typeName the desired name
     * @return the PokemonTypeModel instance or null if not found
     */
    public static PokemonTypeModel getPokemonTypeByTypeName(String typeName) {
        return _allPokemonTypes.get(typeName.toUpperCase());
    }
    
    /**
     * Return all of the PokemonType created so far
     * @return an ArrayList of PokemonTypeModel instances
     */
    public static ArrayList<PokemonTypeModel> getAllPokemonTypes() {
        return new ArrayList<>(_allPokemonTypes.values());
    }
    
    @Override
    public String toString() {
        return "PokemonTypeModel(" + _typeName + ")";
    }
    
    /*NORMAL,FIRE,WATER,ELECTRIC,GRASS,ICE,FIGHTING,POISON,GROUND,FLYING,PSYCHIC,
    BUG,ROCK,GHOST,DRAGON,DARK,STEEL,FAIRY,NONE*/
}
