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
    
    String[] _allPokemonName;

    public PokemonListModel() {
        _all
        
    }
    
    public ArrayList<String> findPokemonFromPattern(String pattern) {
        ArrayList<String> res = new ArrayList<>();
        for (String str : _allPokemonName) {
            res.add(str);
        }
        return res;
    }
    
}
