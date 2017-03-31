package be.ac.ulb.infof307.g01;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * TODO: add description
 */
public class PokemonModel {

    private static HashMap<String, PokemonModel> _allPokemon = new HashMap<>();
    private static final String _pathPrefix = "assets" + File.separator + "sprites" + File.separator;
    private final int _typeNumberMax = 2; // TODO : 
    private final String _name;
    private final PokemonTypeModel[] _type;
    private final String _pathImage;
    
    public PokemonModel(String name, String imagePath) {
        // TODO query to get the corresponding type.
        this(name, imagePath, PokemonTypeModel.getPokemonTypeByTypeName("NONE"));
    }
    
    public PokemonModel(String name, String imagePath, PokemonTypeModel... type_array) {
        if(_allPokemon.containsKey(name)) {
            throw new IllegalStateException("Pokemon " + name + 
                    " already created");
        }
        _name = name;
        _type = type_array;
        _pathImage = _pathPrefix + imagePath;
        
        _allPokemon.put(name, this);
    }
    
    /**
     * Return the name of the Pokemon
     * @return the name of the Pokemon
     */
    public String getName() {
        return _name;
    }
    
    /**
     * Return the list of types assigned to the pokemon
     * @return a list of types assigned to the pokemon
     */
    public PokemonTypeModel[] getTypes() {
        return _type;
    }
    
    /**
     * Return the path of the sprite of the Pokemon
     * @return the path of the sprite of the Pokemon
     */
    public String getImagePath() {
        return _pathImage;
    }
    
    /**
     * Test if two pokemon have same name and type
     * 
     * @param otherPokemon the pokemon which must be compared
     * @return True if it's the same pokemon
     */
    public boolean equals(PokemonModel otherPokemon) {
        return otherPokemon._name.equals(_name) && 
                Arrays.equals(otherPokemon._type, _type);
    }
    
    /////////////////// STATIC /////////////////////
    
    /**
     * Return the pokemonModel instance by name
     * 
     * @param name the desired name
     * @return the PokemonModel instance or null if not found
     */
    public static PokemonModel getPokemonByName(String name) {
        final PokemonModel res = _allPokemon.get(name);
        if(res == null) {
            throw new NoSuchElementException("[[" + _allPokemon.size()+ "]] No pokemon found with the following name: " + name);
        }
        return res;
    }
    
    public static ArrayList<PokemonModel> getAllPokemon() {
        return new ArrayList<>(_allPokemon.values());
    }
    
    public static ArrayList<String> getAllPokemonName() {
        return new ArrayList<>(_allPokemon.keySet());
    }
    
    public static void clearAllPokemon() {
        _allPokemon.clear();
    }
    
    /**
     * Get the number of loaded pokemon
     * 
     * @return number of pokemon 
     * @TODO renommer avec un nom plus logique, par exemple getNumberLoadedPokemons
     */
    public static int getSizePokemonModel(){
        return _allPokemon.size();
    }

}
