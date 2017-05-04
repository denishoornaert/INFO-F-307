package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;

/** Model of a pokemon.
 * A pokemon represents the generic pokemon entity, such as "Pikachu". A pokemon
 * spotted on the map is represented by MarkerModel.
 */

public class PokemonModel extends PokemonSendableModel {
    
    private static final String PATH_PREFIX = ClientConfiguration.getInstance().getSpritePath();
    
    public PokemonModel(PokemonSendableModel pokemon) {
        super(pokemon);
        // Convert all types to client model instances
        for(int i = 0; i < _type.length; ++i) {
            _type[i] = new PokemonTypeModel(_type[i]);
        }
    }
    
    @Override
    public String getImagePath() {
        return PATH_PREFIX + super.getImagePath();
    }
}
