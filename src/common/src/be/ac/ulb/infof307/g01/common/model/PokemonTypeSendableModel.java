package be.ac.ulb.infof307.g01.common.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * Represents a pokemon type.
 * A pokemon type characterizes its abilities as well as some of its
 * strengths and weaknesses.
 * The sendable keyword indicates the object can be serialized and sent 
 * over the network
 */
@XmlRootElement
public class PokemonTypeSendableModel {
    protected String _typeName;
    
    /**
     * Default constructor.
     * Required by Jersey.
     */
    public PokemonTypeSendableModel() {
    }
    
    public PokemonTypeSendableModel(PokemonTypeSendableModel other) {
        _typeName = other._typeName;
    }
    
    public PokemonTypeSendableModel(String typeName) {
        _typeName = typeName.toUpperCase();
    }
    
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
        final PokemonTypeSendableModel other = (PokemonTypeSendableModel) obj;
        if (!Objects.equals(this._typeName, other._typeName)) {
            return false;
        }
        return true;
    }
    
}
