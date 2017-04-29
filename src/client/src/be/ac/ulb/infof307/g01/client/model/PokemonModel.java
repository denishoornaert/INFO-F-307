package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import java.util.NoSuchElementException;

/** Model of a pokemon.
 * A pokemon represents the generic pokemon entity, such as "Pikachu". A pokemon
 * spotted on the map is represented by MarkerModel.
 */

public class PokemonModel extends PokemonSendableModel {
    
    private static String _pathPrefix;
    
    public PokemonModel(String name, String imagePath) {
        // TODO query to get the corresponding type.
        this(name, imagePath, PokemonTypeModel.getPokemonTypeByTypeName("NONE"));
    }
    
    public PokemonModel(String name, String imageName, PokemonTypeModel type) {
        this(name, imageName, new PokemonTypeModel[] {type});
    }
    
    public PokemonModel(String name, String imageName, PokemonTypeModel type1, 
            PokemonTypeModel type2) {
        this(name, imageName, new PokemonTypeModel[] {type1, type2});
    }
    
    private PokemonModel(String name, String imageName, PokemonTypeModel[] type_array) {
        if(_allPokemon.containsKey(name)) {
            throw new IllegalStateException("Pokemon " + name + 
                    " already created");
        }
        _name = name;
        _type = type_array;
        _imageName = imageName;
        _pathPrefix = ClientConfiguration.getInstance().getSpritesPath();
        _allPokemon.put(name, this);
    }
    
    @Override
    public String getImagePath() {
        return _pathPrefix + super.getImagePath();
    }
    
    
    /////////////////// STATIC /////////////////////
    
    /**
     * Return the pokemonModel instance by name
     * 
     * @param name the desired name
     * @return the PokemonModel instance or null if not found
     */
    public static PokemonModel getPokemonByName(String name) {
        PokemonSendableModel res = _allPokemon.get(name);
        if(res == null || !(res instanceof PokemonModel)) {
            throw new NoSuchElementException("[[" + _allPokemon.size()+ "]] No "
                + "pokemon found with the following name: " + name);
        }
        return (PokemonModel) res;
    }

}
