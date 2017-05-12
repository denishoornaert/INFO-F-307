package be.ac.ulb.infof307.g01.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Groupe 1
 */
@XmlRootElement
public class FilterSendableModel {
    
    private String _name;
    private String _expression;
    
    public FilterSendableModel() {} // don't remove (see Jersey)
    
    public FilterSendableModel(String name, String filter) {
        _expression = filter;
        _name = name;
    }
    
    /**
     * @return the expression
     */
    public String getExpression() {
        return _expression;
    }

    /**
     * @param expression the expression to set
     */
    public void setExpression(String expression) {
        _expression = expression;
    }

    /**
     * @return the name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        _name = name;
    }
    
}
