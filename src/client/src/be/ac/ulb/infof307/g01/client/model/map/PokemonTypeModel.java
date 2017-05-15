package be.ac.ulb.infof307.g01.client.model.map;

import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;

/** 
 * Represents a Pokemon type.
 * The Pokemon type is the element of the pokemon, such as "Eletric" for Pikachu.
 */
public class PokemonTypeModel extends PokemonTypeSendableModel {
    
    public PokemonTypeModel(final PokemonTypeSendableModel pokemonType) {
        super(pokemonType);
    }
    
    /**
     * Creates a new PokemonType
     * @param typeName the name of the brand new type
     * @throws IllegalStateException if type already exists
     */
    public PokemonTypeModel(final String typeName) {
        super(typeName);
    }
}
