package be.ac.ulb.infof307.g01.common.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * Represents a pokemon.
 * A pokemon has a name, two types and a corresponding image.
 * The sendable keyword indicates the object can be serialized and sent 
 * over the network
 */
@XmlRootElement
public class PokemonSendableModel {    
    protected String _name;
    protected PokemonTypeSendableModel[] _types;
    protected String _imageName;
    
    /**
     * Default constructor.
     * Required by Jersey.
     */
    public PokemonSendableModel() {}
    
    public PokemonSendableModel(String pokemonName, String imagePath, 
            PokemonTypeSendableModel pokemonType) {
        this(pokemonName, imagePath, pokemonType, 
            PokemonTypeSendableModel.getNoneType());
    }
    
    public PokemonSendableModel(String pokemonName, String imagePath, 
            PokemonTypeSendableModel pokemonTypeOne, 
            PokemonTypeSendableModel pokemonTypeTwo) {
        _name = pokemonName;
        _imageName = imagePath;
        _types = new PokemonTypeSendableModel[2];
        _types[0] = pokemonTypeOne;
        _types[1] = pokemonTypeTwo;        
    }
    
    public PokemonSendableModel(PokemonSendableModel other) {
        this(other._name, other._imageName, other._types[0], other._types[1]);
    }
    
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    /**
     * Returns the pokemon's two types.
     * @return an array of types
     */
    public PokemonTypeSendableModel[] getTypes() {
        return _types.clone();
    }
    
    /**
     * Returns the pokemon's two type names.
     * @return an array of Strings
     */
    public String[] getTypeNames() {
        String[] typeNames = new String[_types.length];
        for(int i = 0; i < _types.length; ++i) {
            typeNames[i] = _types[i].getTypeName();
        }
        return typeNames;
    }

    /**
     * Assigns the pokemon's types.
     * @param types the types to set
     */
    public void setTypes(PokemonTypeSendableModel[] types) {
        this._types = types;
    }

    /**
     * Return the path to the pokemon's image.
     * @return an image path
     */
    public String getImagePath() {
        return _imageName;
    }

    /**
     * @param imageName the new pokemon's image name
     */
    public void setImagePath(String imageName) {
        this._imageName = imageName;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PokemonSendableModel other = (PokemonSendableModel) obj;
        if (!Objects.equals(this._name, other._name)) {
            return false;
        }
        return true;
    }
}
