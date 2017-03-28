package be.ac.ulb.infof307.g01;

import java.util.ArrayList;

/**
 * TODO: add description
 */
public class PokemonListModel {
    
    ArrayList<String> _allPokemonNames;

    public PokemonListModel(String ... names) {
        _allPokemonNames = new ArrayList<String>();
        for (String name : names) {
            _allPokemonNames.add(name);
        }
    }
    
    public ArrayList<String> getAllNames() {
        return _allPokemonNames;
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
