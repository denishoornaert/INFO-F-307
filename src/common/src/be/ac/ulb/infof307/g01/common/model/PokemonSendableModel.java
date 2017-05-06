package be.ac.ulb.infof307.g01.common.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PokemonSendableModel {    
    protected String _name;
    protected PokemonTypeSendableModel[] _type;
    protected String _imageName;
    
    public PokemonSendableModel() { } // Must exist
    
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
        _type = new PokemonTypeSendableModel[2];
        _type[0] = pokemonTypeOne;
        _type[1] = pokemonTypeTwo;        
    }
    
    public PokemonSendableModel(PokemonSendableModel other) {
        this(other._name, other._imageName, other._type[0], other._type[1]);
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
    public void setName(String _name) {
        this._name = _name;
    }

    /**
     * Return the list of types assigned to the pokemon
     * @return a list of types assigned to the pokemon
     */
    public PokemonTypeSendableModel[] getType() {
        return _type;
    }

    /**
     * @param _type the _type to set
     */
    public void setType(PokemonTypeSendableModel[] _type) {
        this._type = _type;
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
    public void setImagePath(String _imageName) {
        this._imageName = _imageName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
