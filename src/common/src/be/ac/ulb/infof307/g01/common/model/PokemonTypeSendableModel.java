package be.ac.ulb.infof307.g01.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Groupe 1
 */
@XmlRootElement
public class PokemonTypeSendableModel {
    protected String _typeName;
    
    public PokemonTypeSendableModel() { } // Must exist
    
    public PokemonTypeSendableModel(PokemonTypeSendableModel other) {
        _typeName = other._typeName;
    }
    
    public PokemonTypeSendableModel(String typeName) {
        typeName = typeName.toUpperCase();
        _typeName = typeName;
    }
    
    
    /**
     * Return the name of the type
     * @return the name of the type
     */
    public String getTypeName() {
        return _typeName;
    }
    
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
