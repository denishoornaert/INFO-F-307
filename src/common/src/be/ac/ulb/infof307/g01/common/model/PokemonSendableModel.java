package be.ac.ulb.infof307.g01.common.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PokemonSendableModel {    
    protected String _name;
    protected PokemonTypeSendableModel[] _types;
    protected String _imageName;
    
    public PokemonSendableModel() { } // Must exist
    
    public PokemonSendableModel(final String pokemonName, final String imagePath, 
            final PokemonTypeSendableModel pokemonType) {
        this(pokemonName, imagePath, pokemonType, 
            PokemonTypeSendableModel.getNoneType());
    }
    
    public PokemonSendableModel(final String pokemonName, final String imagePath, 
            final PokemonTypeSendableModel pokemonTypeOne, 
            final PokemonTypeSendableModel pokemonTypeTwo) {
        _name = pokemonName;
        _imageName = imagePath;
        _types = new PokemonTypeSendableModel[2];
        _types[0] = pokemonTypeOne;
        _types[1] = pokemonTypeTwo;        
    }
    
    public PokemonSendableModel(final PokemonSendableModel other) {
        this(other._name, other._imageName, other._types[0], other._types[1]);
    }
    
    /**
     * Return the name of the Pokemon
     * @return the name of the Pokemon
     */
    public String getName() {
        return _name;
    }

    /**
     * @param _name the _name to set
     */
    public void setName(final String _name) {
        this._name = _name;
    }

    /**
     * Return the list of types assigned to the pokemon
     * @return a list of types assigned to the pokemon
     */
    public PokemonTypeSendableModel[] getTypes() {
        return _types.clone();
    }
    
    public String[] getTypeNames() {
        String[] typeNames = new String[_types.length];
        for(int i = 0; i < _types.length; ++i) {
            typeNames[i] = _types[i].getTypeName();
        }
        return typeNames;
    }

    /**
     * @param _type the _types to set
     */
    public void setTypes(final PokemonTypeSendableModel[] _type) {
        this._types = _type;
    }

    /**
     * Return the path of the sprite of the Pokemon
     * @return the path of the sprite of the Pokemon
     */
    public String getImagePath() {
        return _imageName;
    }

    /**
     * @param _imageName the _imageName to set
     */
    public void setImagePath(final String _imageName) {
        this._imageName = _imageName;
    }
    
    @Override
    public boolean equals(final Object obj) {
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
