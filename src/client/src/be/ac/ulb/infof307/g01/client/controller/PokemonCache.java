package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Singleton which holds the client pokemon information sent by the server.
 * Since these pokemons are not subject to change, we just need to store them
 * once, and all classes needing info about a pokemon ask us.
 */
class PokemonCache {
    private static PokemonCache _instance = null;
    private Map<String, PokemonModel> _allPokemons;

    private PokemonCache() {
    }
    
    public static PokemonCache getInstance() {
        if(_instance == null) {
            _instance = new PokemonCache();
        }
        return _instance;
    }
    
    public void loadAllPokemons(List<PokemonSendableModel> allPokemons) {
        _allPokemons.clear();
        for (PokemonSendableModel pokemon : allPokemons) {
            _allPokemons.put(pokemon.getName(), new PokemonModel(pokemon));
        }
    }
        
    public ArrayList<String> getAllPokemonNames() {
        return new ArrayList<>(_allPokemons.keySet());
    }
    
    public PokemonModel getPokemonByName(String pokemonName) {
        return _allPokemons.get(pokemonName);
    }
    
    public void clearAllPokemon() {
        _allPokemons.clear();
    }
    
    /**
     * Get the number of loaded pokemon
     * 
     * @return number of pokemon 
     */
    public int getNumberLoadedPokemons(){
        return _allPokemons.size();
    }
}
