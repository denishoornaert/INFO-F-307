package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;

/** Model of a pokemon.
 * A pokemon represents the generic pokemon entity, such as "Pikachu". A pokemon
 * spotted on the map is represented by MarkerModel.
 */

public class PokemonModel extends PokemonSendableModel {
    
    private static String _pathPrefix;
    
    public PokemonModel(PokemonSendableModel pokemon) {
        super(pokemon);
        // Convert all types to client model instances
        for(int i = 0; i < _type.length; ++i) {
            _type[i] = new PokemonTypeModel(_type[i]);
        }
        _pathPrefix = ClientConfiguration.getInstance().getSpritePath();
    }
    
    @Override
    public String getImagePath() {
        return _pathPrefix + super.getImagePath();
    }
}
