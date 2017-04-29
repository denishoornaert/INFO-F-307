package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;

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
        super(typeName);
    }
    
    
    /////////////// STATIC ////////////////
    
    /**
     * Return the pokemonTypeModel instance by name
     * 
     * @param typeName the desired name
     * @return the PokemonTypeModel instance or null if not found
     */
    public static PokemonTypeModel getPokemonTypeByTypeName(String typeName) {
        PokemonTypeSendableModel result = _allPokemonTypes.get(typeName.toUpperCase());
        if(result instanceof PokemonTypeModel) {
            return (PokemonTypeModel) result;
        }
        return null;
    }
    
}
