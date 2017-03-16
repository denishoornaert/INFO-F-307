/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.PokemonTypeModel;

/**
 *
 * @author Nathan
 */
public interface PokemonTypeDatabaseModel {
    
    public PokemonTypeModel[] getTypesByName(String name);
    public void loadAllPokemonTypes();
    
}
