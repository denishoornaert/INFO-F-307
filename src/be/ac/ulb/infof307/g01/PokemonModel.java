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
public class PokemonModel {
    
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
    
    /**
     * Init a Pokemon with given name and typess
     * @param name the name of the pokemon
     * @param type a list of types to assign to the Pokemon
     */
    public PokemonModel(String name, PokemonTypeModel... type) {
        _name = name;
        _type = type;
        _pathImage = findCorrespondingImagePath();
    }
    
    /**
     * Return the path of the sprite of the Pokemon
     * @return the path of the sprite of the Pokemon
     */
    private String findCorrespondingImagePath() {
        return "assets" + File.separator + "sprites" + File.separator + _name + ".png";
    }
    
    /**
     * Return the name of the Pokemon
     * @return the name of the Pokemon
     */
    public String getName() {
        return _name;
    }
    
    /**
     * Return the list of types assigned to the pokemon
     * @return a list of types assigned to the pokemon
     */
    public PokemonTypeModel[] getTypes() {
        return _type;
    }
    
    /**
     * Return the path of the sprite of the Pokemon
     * @return the path of the sprite of the Pokemon
     */
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
    
}
