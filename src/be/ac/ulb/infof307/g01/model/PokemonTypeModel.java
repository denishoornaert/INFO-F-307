package be.ac.ulb.infof307.g01.model;

import java.util.ArrayList;
import java.util.HashMap;

/** Model of a Pokemon type.
 * The Pokemon type is the element of the pokemon, such as Eletric for Pikachu.
 */
public class PokemonTypeModel {
    
    /**
     * HashMap mapping the name (String) of the type to its only instance in the
     * application
     */
    private static HashMap<String, PokemonTypeModel> _allPokemonTypes = new HashMap<>();
    private final String _typeName;
    
    static {
        _allPokemonTypes.put("NONE", new PokemonTypeModel("NONE"));
    }
    
    /**
     * Create a new PokemonType
     * @param typeName the name of the brand new type
     * @throws IllegalStateException if type already exists
     * @TODO Ce constructeur devrait être privé, puisque getPokemonTypeByTypeName
     * sert deja de factory. Seule PokemonTypeModel devrait pouvoir appeler le constructeur
     */
    public PokemonTypeModel(String typeName) {
        typeName = typeName.toUpperCase();
        if(_allPokemonTypes.containsKey(typeName)) {
            throw new IllegalStateException("PokemonType " + typeName + 
                    " already created");
        }
        
        _typeName = typeName;
        _allPokemonTypes.put(_typeName, this);
    }
    
    /**
     * Return the name of the type
     * @return the name of the type
     */
    public String getTypeName() {
        return _typeName;
    }
    
    /////////////// STATIC ////////////////
    
    /**
     * Return the pokemonTypeModel instance by name
     * 
     * @param typeName the desired name
     * @return the PokemonTypeModel instance or null if not found
     */
    public static PokemonTypeModel getPokemonTypeByTypeName(String typeName) {
        return _allPokemonTypes.get(typeName.toUpperCase());
    }
    
    /**
     * Return all of the PokemonType created so far
     * @return an ArrayList of PokemonTypeModel instances
     */
    public static ArrayList<PokemonTypeModel> getAllPokemonTypes() {
        return new ArrayList<>(_allPokemonTypes.values());
    }
    
    /**
     * Remove all existing PokemonType (and juste add NONE type)<br />
     * Warning: only for test !
     */
    public static void resetAllPokemonType() {
        _allPokemonTypes.clear();
        _allPokemonTypes.put("NONE", new PokemonTypeModel("NONE"));
    }
        
        
    @Override
    public String toString() {
        return "PokemonTypeModel(" + _typeName + ")";
    }
    
    /*NORMAL,FIRE,WATER,ELECTRIC,GRASS,ICE,FIGHTING,POISON,GROUND,FLYING,PSYCHIC,
    BUG,ROCK,GHOST,DRAGON,DARK,STEEL,FAIRY,NONE*/
}
