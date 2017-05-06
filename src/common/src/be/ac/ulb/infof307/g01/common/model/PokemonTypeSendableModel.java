package be.ac.ulb.infof307.g01.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Groupe 1
 */
@XmlRootElement
public class PokemonTypeSendableModel {
    protected String _typeName;
    
    /**
     * Constructor, needed by Jersey.
     */
    public PokemonTypeSendableModel() {
    }
    
    public PokemonTypeSendableModel(PokemonTypeSendableModel other) {
        _typeName = other._typeName;
    }
    
    public PokemonTypeSendableModel(String typeName) {
        _typeName = typeName.toUpperCase();
    }
    
    /**
     * Return the name of the type
     * @return the name of the type
     */
    public String getTypeName() {
        return _typeName;
    }
    
    /**
     * Changes the type name.
     * \note This is needed by Jersey, do not remove
     * @param typeName the new name of the type
     */
    public void setTypeName(String typeName) {
        _typeName = typeName;
    }

    @Override
    public String toString() {
        return "PokemonTypeSendableModel(" + _typeName + ")";
    }
    
    public static PokemonTypeSendableModel getNoneType() {
        return new PokemonTypeSendableModel("NONE");
    }
    
}
