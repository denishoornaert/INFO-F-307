package be.ac.ulb.infof307.g01.client.model.filter;

/**
 * Holds all the parameters of a basic filter in one unique place.
 * This class avoids code redundancy when transmitting information between
 * BasicFilterPanelView and FilterPanelController.
 */
public class BasicFilterParametersModel {
    /**
     * True if we will NOT this name, false otherwhise.
     */
    final boolean _notName;
    
    /**
     *  string that we are looking for.
     */
    final String _name;
    
    /**
     * True if we will NOT the type1, false otherwhise.
     */
    final boolean _notType1;
    
    /**
     * The first type that we are looking for.
     */
    final String _type1;
    
    /**
     * True if we will NOT the type2, false otherwhise.
     */
    final boolean _notType2;
    
    /**
     * The second type that we are looking for.
     */
    final String _type2;
    
    /**
     * True if we must apply a logical AND, false if we must apply a logical OR.
     */
    final boolean _andIsSelected;

    public BasicFilterParametersModel(final boolean notName, final String name,
            final boolean notType1, final String type1, final boolean notType2,
            final String type2, final boolean andIsSelected) {
        _notName = notName;
        _name = name;
        _notType1 = notType1;
        _type1 = type1;
        _notType2 = notType2;
        _type2 = type2;
        _andIsSelected = andIsSelected;
    }

    public boolean notName() {
        return _notName;
    }

    public String getName() {
        return _name;
    }

    public boolean notType1() {
        return _notType1;
    }

    public String getType1() {
        return _type1;
    }

    public boolean notType2() {
        return _notType2;
    }

    public String getType2() {
        return _type2;
    }

    public boolean isAndSelected() {
        return _andIsSelected;
    }
    
}
