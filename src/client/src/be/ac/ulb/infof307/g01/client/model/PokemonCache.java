package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.model.PokemonTypeModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton class which holds the client pokemon information sent by the server.
 * This information consists of PokemonModel and PokemonTypeModel instances.
 * Since that data is not subject to change, we just need to store it
 * once, and all related requests can be answered locally.
 * The singleton instance is filled by ServerQueryController when the latter is
 * constructed.
 */
public class PokemonCache {
    /** Singleton instance. */
    private static PokemonCache _instance = null;
    
    /** All registered pokemons so far, sorted by name. */
    private final Map<String, PokemonModel> _allPokemons = new HashMap<>();
    
    /** All registered pokemon types so far, sorted by name. */
    private final Map<String, PokemonTypeModel> _allPokemonTypes = new HashMap<>();

    private PokemonCache() {
    }
    
    public static PokemonCache getInstance() {
        if(_instance == null) {
            _instance = new PokemonCache();
        }
        return _instance;
    }
    
    /**
     * Replaces the cache of pokemons with the given one.
     * @param allPokemons the new list of pokemons used to replace the previous
     * one
     */
    public void loadAllPokemons(List<PokemonSendableModel> allPokemons) {
        _allPokemons.clear();
        for (PokemonSendableModel pokemon : allPokemons) {
            _allPokemons.put(pokemon.getName(), new PokemonModel(pokemon));
        }
    }
    
    /**
     * Replaces the cache of pokemon types with the given one.
     * @param allPokemonTypes the new list of pokemon types used to replace the
     * previous one.
     */
    public void loadAllPokemonTypes(List<PokemonTypeSendableModel> allPokemonTypes) {
        _allPokemonTypes.clear();
        for (PokemonTypeSendableModel pokemonType : allPokemonTypes) {
            _allPokemonTypes.put(pokemonType.getTypeName().toUpperCase(), new PokemonTypeModel(pokemonType));
        }
    }
        
    /**
     * @return a list of all names of all cached pokemons
     */
    public ArrayList<String> getAllPokemonNames() {
        return new ArrayList<>(_allPokemons.keySet());
    }
    
    /**
     * @return a list of all cached pokemons types
     */
    public ArrayList<PokemonTypeModel> getAllPokemonTypes() {
        return new ArrayList<>(_allPokemonTypes.values());
    }
    
    /**
     * @return a list of all cached pokemon type names, as strings
     */
    public ArrayList<String> getAllPokemonTypesString() {
        ArrayList<PokemonTypeModel> tmp = getAllPokemonTypes();
        ArrayList<String> pokemonTypesString = new ArrayList<>();
        for(PokemonTypeModel pokemonType : tmp) {
            pokemonTypesString.add(pokemonType.getTypeName());
        }
        return pokemonTypesString;
    }
    
    /**
     * Retrieves the PokemonModel instance from its name.
     * 
     * @param pokemonName the name of the searched pokemon
     * @return the PokemonModel instance
     * @throws RuntimeException if no pokemon with such name has been found
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
     * @return the number of loaded pokemon.
     */
    public int getNumberLoadedPokemons(){
        return _allPokemons.size();
    }
}
