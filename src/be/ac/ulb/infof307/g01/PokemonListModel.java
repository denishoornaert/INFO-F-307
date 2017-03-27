/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import java.util.ArrayList;

/**
 *
 * @author hoornaert
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