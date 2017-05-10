package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.FilterPanelView;

/**
 *
 * Class that manages the filter panel.
 * @author Groupe01
 */
public class FilterPanelController {
    
    private FilterPanelView _filterPanelView;
    
    public FilterPanelController() {
        _filterPanelView = new FilterPanelView(this);
    }
    
    public FilterPanelView getView() {
        return _filterPanelView;
    }

    public void applyFilter(String expression) {
        // TODO @Denis : construct FilterController with Robin stuff. 
    }

    public void applyFilter(boolean notName, String name, boolean notType1, String type1, boolean notType2, String type2, boolean andIsSelected, boolean orIsSelected) {
        // TODO @Denis : generate expression and then :
        System.out.println(notName + " " + name + " " + notType1 + " " + type1 + " " + notType2 + " " + type2 + " " + andIsSelected + " " + orIsSelected);
        applyFilter("");
    }
    
}
