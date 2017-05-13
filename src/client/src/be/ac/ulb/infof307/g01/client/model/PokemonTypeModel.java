package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;

/** 
 * Represents a Pokemon type.
 * The Pokemon type is the element of the pokemon, such as "Eletric" for Pikachu.
 */
public class PokemonTypeModel extends PokemonTypeSendableModel {
    
    public PokemonTypeModel(PokemonTypeSendableModel pokemonType) {
        super(pokemonType);
    }
    
    /**
     * Creates a new PokemonType
     * @param typeName the name of the brand new type
     * @throws IllegalStateException if type already exists
     */
    public PokemonTypeModel(String typeName) {
        super(typeName);
    }
}
