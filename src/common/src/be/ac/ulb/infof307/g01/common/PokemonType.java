package be.ac.ulb.infof307.g01.common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Groupe 1
 */
@XmlRootElement
public class PokemonType {
    
    private int _id;
    private String _name;

    /**
     * @return the _id
     */
    public int getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(int _id) {
        this._id = _id;
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
    
    @Override
    public String toString() {
        return "ID=" + _id + ";NAME=" + _name;
    }

}
