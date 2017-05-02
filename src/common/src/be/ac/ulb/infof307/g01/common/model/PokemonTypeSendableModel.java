package be.ac.ulb.infof307.g01.common.model;

import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Groupe 1
 */
@XmlRootElement
public class PokemonTypeSendableModel {
    
    /**
     * HashMap mapping the name (String) of the type to its only instance in the
     * application
     */
    protected static HashMap<String, PokemonTypeSendableModel> _allPokemonTypes = new HashMap<>();
    protected String _typeName;
    
    public PokemonTypeSendableModel() { } // Must exist
    
    public PokemonTypeSendableModel(PokemonTypeSendableModel other) {
        _typeName = other._typeName;
        _allPokemonTypes.put(_typeName, this);
    }
    
    public PokemonTypeSendableModel(String typeName) {
        typeName = typeName.toUpperCase();
        if(_allPokemonTypes.containsKey(typeName)) {
            throw new IllegalStateException("PokemonType " + typeName + 
                    " already created");
        }
        
        _typeName = typeName;
        _allPokemonTypes.put(_typeName, this);
    }
    
    
    /**
     * Return the name of the type
     * @return the name of the type
     */
    public String getTypeName() {
        return _typeName;
    }

    /**
     * @param _typeName the _typeName to set
     */
    public void setTypeName(String _typeName) {
        this._typeName = _typeName;
    }
    
    @Override
    public String toString() {
        return "PokemonTypeSendableModel(" + _typeName + ")";
    }
    
    /**
     * Return all of the PokemonType created so far
     * @return an ArrayList of PokemonTypeModel instances
     * TODO delete ?
     */
    public static ArrayList<PokemonTypeSendableModel> getAllPokemonTypes() {
        return new ArrayList<>(_allPokemonTypes.values());
    }

    /**
     * @param aAllPokemonTypes the allPokemonTypes to set
     */
    public static void setAllPokemonTypes(HashMap<String, PokemonTypeSendableModel> aAllPokemonTypes) {
        _allPokemonTypes = aAllPokemonTypes;
    }
    
    public static PokemonTypeSendableModel getPokemonTypeByTypeName(String typeName) {
        PokemonTypeSendableModel result = _allPokemonTypes.get(typeName.toUpperCase());
        return result;
    }
    
    /**
     * Remove all existing PokemonType<br>
     * Warning: only for test !
     */
    public static void resetAllPokemonType() {
        _allPokemonTypes.clear();
        new PokemonTypeSendableModel("NONE");
    }
    
    public static PokemonTypeSendableModel getNoneType() {
        PokemonTypeSendableModel noneType = _allPokemonTypes.get("NONE");
        if(noneType == null) {
            noneType = new PokemonTypeSendableModel("NONE");
        }
        return noneType;
    }
    
}
