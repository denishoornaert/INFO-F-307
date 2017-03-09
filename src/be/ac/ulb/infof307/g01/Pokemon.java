/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import java.io.File;
import java.util.Arrays;

/**
 *
 * @author remy
 */
public class Pokemon {
    
    private final String _name;
    private final PokemonType[] _type;
    private final String _pathImage;
    
    
    public Pokemon(String name, PokemonType type) {
        this(name,type,PokemonType.NONE);
    }
    
    public Pokemon(String name, PokemonType type1, PokemonType type2) {
        _name = name;
        _type = new PokemonType[]{type1, type2};
        _pathImage = findCorrespondingImagePath();
    }
    
    private String findCorrespondingImagePath() {
        return "assets" + File.separator + "sprites" + File.separator + _name + ".png";
    }
    
    
    public String getName() {
        return _name;
    }
    
    public PokemonType[] getType() {
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
    public boolean equals(Pokemon otherPokemon) {
        return otherPokemon._name.equals(_name) && 
                Arrays.equals(otherPokemon._type, _type);
    }
    
}
