package be.ac.ulb.infof307.g01.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/** 
 * Represents a marker filter, allowing the user to display markers
 * based on their properties.
 * Filters have a string expression using a prefix syntax with logical
 * operators, as well as a descriptive name used to improve readability.
 * The sendable keyword indicates the object can be serialized and sent 
 * over the network
 */
@XmlRootElement
public class FilterSendableModel {
    
    private String _name;
    private String _expression;
    
    /**
     * Default constructor.
     * Required by Jersey.
     */
    public FilterSendableModel() {}
    
    public FilterSendableModel(final String name, final String filter) {
        _expression = filter;
        _name = name;
    }
    
    public String getExpression() {
        return _expression;
    }

    /**
     * @param expression the expression to set
     */
    public void setExpression(final String expression) {
        _expression = expression;
    }

    public String getName() {
        return _name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        _name = name;
    }
    
}
