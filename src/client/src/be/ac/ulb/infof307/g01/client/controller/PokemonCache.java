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
 * 
 * The singleton instance is filled by ServerQueryController when this latter is
 * constructed.
 */
public class PokemonCache {
    /**
     * Singleton instance.
     */
    private static PokemonCache _instance = null;
    
    /**
     * All registered pokemons so far, sorted by name.
     */
    private Map<String, PokemonModel> _allPokemons = new HashMap();
    
    /**
     * All registered pokemon types so far, sorted by name.
     */
    private Map<String, PokemonTypeModel> _allPokemonTypes = new HashMap();

    private PokemonCache() {
    }
    
    public static PokemonCache getInstance() {
        if(_instance == null) {
            _instance = new PokemonCache();
        }
        return _instance;
    }
    
    /**
     * Replaces the cache of pokemons with this given one.
     * @param allPokemons The new list of pokemons used to replace the previous
     * one.
     */
    public void loadAllPokemons(List<PokemonSendableModel> allPokemons) {
        _allPokemons.clear();
        for (PokemonSendableModel pokemon : allPokemons) {
            _allPokemons.put(pokemon.getName(), new PokemonModel(pokemon));
        }
    }
    
    /**
     * Replaces the cache of pokemon types with this given one.
     * @param allPokemonTypes The new list of pokemon types used to replace the
     * previous one.
     */
    public void loadAllPokemonTypes(List<PokemonTypeSendableModel> allPokemonTypes) {
        _allPokemonTypes.clear();
        System.out.println("size : "+allPokemonTypes.size());
        for (PokemonTypeSendableModel pokemonType : allPokemonTypes) {
            System.out.println("obj : "+pokemonType);
            //_allPokemonTypes.put(pokemonType.getTypeName().toUpperCase(), new PokemonTypeModel(pokemonType));
        }
    }
        
    /**
     * Returns the list of all names of all cached pokemons.
     * @return the list of all names of all cached pokemons.
     * ^^^ Look how these lines are syncing up, this is beautiful. ^^^
     */
    public ArrayList<String> getAllPokemonNames() {
        return new ArrayList(_allPokemons.keySet());
    }
    
    /**
     * Returns a list of all cached pokemons types.
     * @return a list of all cached pokemons types.
     * ^^^ Same remark as for getAllPokemonNames ^^^
     */
    public ArrayList<PokemonTypeModel> getAllPokemonTypes() {
        return new ArrayList(_allPokemonTypes.values());
    }
    
    /**
     * Retrieves the PokemonModel instance by its name.
     * 
     * @param pokemonName the name of the searched pokemon.
     * @return the PokemonModel instance.
     * @throws RuntimeException if no pokemon with such name has been found.
     */
    public PokemonModel getPokemonByName(String pokemonName) {
        PokemonModel result = _allPokemons.get(pokemonName);
        if(result == null) {
            throw new RuntimeException("No pokemon with such name: \"" + pokemonName + "\"");
        }
        return result;
    }
    
    /**
     * Retrieves the pokemonTypeModel instance by its name.
     * 
     * @param typeName the name of the searched type.
     * @return the PokemonTypeModel instance.
     * @throws RuntimeException if no type with such name has been found.
     */
    public PokemonTypeModel getPokemonTypeByTypeName(String typeName) {
        PokemonTypeModel result = _allPokemonTypes.get(typeName);
        if(result == null) {
            throw new RuntimeException("No pokemon type with such name: \"" + typeName + "\"");
        }
        return result;
    }
    
    /**
     * Returns the number of loaded pokemon.
     * @return the number of loaded pokemon.
     */
    public int getNumberLoadedPokemons(){
        return _allPokemons.size();
    }
}
