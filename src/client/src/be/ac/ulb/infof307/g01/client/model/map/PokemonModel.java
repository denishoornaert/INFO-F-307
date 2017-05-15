package be.ac.ulb.infof307.g01.client.model.map;

import be.ac.ulb.infof307.g01.client.model.app.ClientConfiguration;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;

/** 
 * Represents a Pokemon.
 * A pokemon is the generic pokemon entity, such as "Pikachu". A pokemon
 * spotted on the map is represented by MarkerModel.
 */
public class PokemonModel extends PokemonSendableModel {
    
    public PokemonModel(final PokemonSendableModel pokemon) {
        super(pokemon);
        // Convert all types to client model instances
        for(int i = 0; i < _types.length; ++i) {
            _types[i] = new PokemonTypeModel(_types[i]);
        }
    }
    
    @Override
    public String getImagePath() {
        return getImagePath(false);
    }
    
    /**
     * Gets the path to the pokemon's image.
     * During test, all folders don't exist, therefore the
     * path to some assets is not the same.
     * 
     * @param isTest true if the path is requested during a test
     * @return the image's path
     */
    public String getImagePath(final boolean isTest) {
        String assetFolder;
        if(isTest) {
            assetFolder = ClientConfiguration.getTestInstance().getSpritePath();
        } else {
            assetFolder = ClientConfiguration.getInstance().getSpritePath();
        }
        
        return assetFolder + super.getImagePath();
    }
}
