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
    
    private static HashMap<String, PokemonTypeModel> _allPokemonTypes = new HashMap<>();
    private final String _typeName;
    
    static {
        _allPokemonTypes.put("NONE", new PokemonTypeModel("NONE"));
    }
    
    
    public PokemonTypeModel(String typeName) {
        if(_allPokemonTypes.containsKey(typeName)) {
            throw new IllegalStateException("PokemonType " + typeName + 
                    " already created");
        }
        
        _typeName = typeName.toUpperCase();
        _allPokemonTypes.put(typeName, this);
    }
    
    public String getTypeName() {
        return _typeName;
    }
    
    /**
     * Return the pokemonTypeModel instance by name
     * 
     * @param typeName the desired name
     * @return the PokemonTypeModel instance or null if not found
     */
    public static PokemonTypeModel getPokemonTypeByTypName(String typeName) {
        return _allPokemonTypes.get(typeName.toUpperCase());
    }
    
    public static ArrayList<PokemonTypeModel> getAllPokemonTypes() {
        return new ArrayList<>(_allPokemonTypes.values());
    }
    
    /*NORMAL,FIRE,WATER,ELECTRIC,GRASS,ICE,FIGHTING,POISON,GROUND,FLYING,PSYCHIC,
    BUG,ROCK,GHOST,DRAGON,DARK,STEEL,FAIRY,NONE*/
}
