/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

/**
 *
 * @author remy
 */
public class Pokemon {
    
    private final String _name;
    private final String _type; // TODO transform to enum ?
    
    
    public Pokemon(String name, String type) {
        _name = name;
        _type = type;
    }
    
    public String getName() {
        return _name;
    }
    
    public String getType() {
        return _type;
    }
    
    
    /**
     * Test if two pokemon have same name and type
     * 
     * @param testPokemon the pokemon which must be tested
     * @return True if it's the same pokemon
     */
    public boolean equals(Pokemon testPokemon) {
        return testPokemon._name.equals(_name) && 
                testPokemon._type.equals(_type);
    }
    
}
