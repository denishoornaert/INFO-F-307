package be.ac.ulb.infof307.g01;

import java.util.ArrayList;

/**
 * Constant list of the pokemons name.
 */
public class PokemonListModel {
    
    private final ArrayList<String> _allPokemonNames;
            
    public PokemonListModel() {
        this(PokemonModel.getAllPokemonName());
    }
    
    public PokemonListModel(ArrayList<String> pokemonsName) {
        _allPokemonNames = pokemonsName;
    }
    
    public ArrayList<String> getAllNames() {
        return new ArrayList<String>(_allPokemonNames);
    }
    
    public ArrayList<String> findPokemonFromPattern(String pattern) {
        ArrayList<String> res = new ArrayList<>();
        for (String str : _allPokemonNames) {
            if(str.contains(pattern)) {
                res.add(str);
            }
        }
        return res;
    }
    
}
