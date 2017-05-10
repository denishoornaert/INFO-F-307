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
    
}
