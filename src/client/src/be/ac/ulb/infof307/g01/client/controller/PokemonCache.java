package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.model.PokemonTypeModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton which holds the client pokemon information sent by the server.
 * Since these pokemons are not subject to change, we just need to store them
 * once, and all classes needing info about a pokemon ask us.
 */
public class PokemonCache {
    private static PokemonCache _instance = null;
    private Map<String, PokemonModel> _allPokemons = new HashMap();
    private Map<String, PokemonTypeModel> _allPokemonTypes = new HashMap();

    private PokemonCache() {
    }
    
    public static PokemonCache getInstance() {
        if(_instance == null) {
            _instance = new PokemonCache();
        }
        return _instance;
    }
    
    public void loadAllPokemons(List<PokemonSendableModel> allPokemons) {
        clearAllPokemons();
        for (PokemonSendableModel pokemon : allPokemons) {
            _allPokemons.put(pokemon.getName(), new PokemonModel(pokemon));
        }
    }
    
    public void loadAllPokemonTypes(List<PokemonTypeSendableModel> allPokemonTypes) {
        _allPokemonTypes.clear();
        for (PokemonTypeSendableModel pokemonType : allPokemonTypes) {
            _allPokemonTypes.put(pokemonType.getTypeName().toUpperCase(), new PokemonTypeModel(pokemonType));
        }
    }
        
    public ArrayList<String> getAllPokemonNames() {
        return new ArrayList(_allPokemons.keySet());
    }
    
    public ArrayList<PokemonTypeModel> getAllPokemonTypes() {
        return new ArrayList(_allPokemonTypes.values());
    }
    
    public PokemonModel getPokemonByName(String pokemonName) {
        PokemonModel result = _allPokemons.get(pokemonName);
        if(result == null) {
            throw new RuntimeException("No pokemon with such name: \"" + pokemonName + "\"");
        }
        return result;
    }
    
    /**
     * Returns the pokemonTypeModel instance by name
     * 
     * @param typeName the desired name
     * @return the PokemonTypeModel instance or null if not found
     */
    public PokemonTypeModel getPokemonTypeByTypeName(String typeName) {
        PokemonTypeModel result = _allPokemonTypes.get(typeName);
        if(result == null) {
            throw new RuntimeException("No pokemon type with such name: \"" + typeName + "\"");
        }
        return result;
    }
    
    public void clearAllPokemons() {
        _allPokemons.clear();
    }
    
    public void clearAllPokemonTypes() {
        _allPokemonTypes.clear();
    }
    
    /**
     * Gets the number of loaded pokemon.
     * 
     * @return number of pokemon 
     */
    public int getNumberLoadedPokemons(){
        return _allPokemons.size();
    }
}
