package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.PokemonTypeSendableModel;

/** Model of a Pokemon type.
 * The Pokemon type is the element of the pokemon, such as Eletric for Pikachu.
 */
public class PokemonTypeModel extends PokemonTypeSendableModel {
    
    static {
        _allPokemonTypes.put("NONE", new PokemonTypeModel("NONE"));
    }
    
    /**
     * Create a new PokemonType
     * @param typeName the name of the brand new type
     * @throws IllegalStateException if type already exists
     * TODO Ce constructeur devrait être privé, puisque getPokemonTypeByTypeName
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
    
    
    /////////////// STATIC ////////////////
    
    /**
     * Return the pokemonTypeModel instance by name
     * 
     * @param typeName the desired name
     * @return the PokemonTypeModel instance or null if not found
     */
    public static PokemonTypeSendableModel getPokemonTypeByTypeName(String typeName) {
        return _allPokemonTypes.get(typeName.toUpperCase());
    }
    
    /**
     * Remove all existing PokemonType (and juste add NONE type)<br>
     * Warning: only for test !
     */
    public static void resetAllPokemonType() {
        _allPokemonTypes.clear();
        _allPokemonTypes.put("NONE", new PokemonTypeModel("NONE"));
    }
    
}
