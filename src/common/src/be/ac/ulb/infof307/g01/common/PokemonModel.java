package be.ac.ulb.infof307.g01.common;

import java.util.HashMap;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class PokemonModel {
    
    private static HashMap<String, PokemonModel> _allPokemon = new HashMap<>();
    private String _name;
    private PokemonTypeModel[] _type;
    private String _imageName;

    /**
     * @return the _allPokemon
     */
    public static HashMap<String, PokemonModel> getAllPokemon() {
        return _allPokemon;
    }

    /**
     * @param aAllPokemon the _allPokemon to set
     */
    public static void setAllPokemon(HashMap<String, PokemonModel> aAllPokemon) {
        _allPokemon = aAllPokemon;
    }

    /**
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param _name the _name to set
     */
    public void setName(String _name) {
        this._name = _name;
    }

    /**
     * @return the _type
     */
    public PokemonTypeModel[] getType() {
        return _type;
    }

    /**
     * @param _type the _type to set
     */
    public void setType(PokemonTypeModel[] _type) {
        this._type = _type;
    }

    /**
     * @return the _imageName
     */
    public String getImageName() {
        return _imageName;
    }

    /**
     * @param _imageName the _imageName to set
     */
    public void setImageName(String _imageName) {
        this._imageName = _imageName;
    }
    
}
