package be.ac.ulb.infof307.g01.client.controller.app;

import be.ac.ulb.infof307.g01.client.view.AdvancedFilterPanelView;
import be.ac.ulb.infof307.g01.client.view.BasicFilterPanelView;
import be.ac.ulb.infof307.g01.client.view.FilterPanelView;
import be.ac.ulb.infof307.g01.client.view.SavedFilterPanelView;

/**
 *
 * Class that manages the filter panel.
 * @author Groupe01
 */
public class FilterPanelController {
    
    private final FilterPanelView _filterPanelView;
    private SavedFilterPanelView _savedTab;
    private BasicFilterPanelView _basicFilter;
    private AdvancedFilterPanelView _advancedFilter;
    private static final String SAVED_EXPRESSION_LABEL = "Saved filter";
    private static final String BASIC_FILTER_LABEL = "Basic filter";
    private static final String ADVANCED_FILTER_LABEL = "Advanced filter";
    
    public FilterPanelController() {
        _filterPanelView = new FilterPanelView(this);
        initTabs();
        placeTabs();
    }
    
    private void initTabs() {
        _savedTab = new SavedFilterPanelView(this);
        _basicFilter = new BasicFilterPanelView(this);
        _advancedFilter = new AdvancedFilterPanelView(this);
    }
    
    private void placeTabs() {
        _filterPanelView.addTab(SAVED_EXPRESSION_LABEL, _savedTab);
        _filterPanelView.addTab(BASIC_FILTER_LABEL, _basicFilter);
        _filterPanelView.addTab(ADVANCED_FILTER_LABEL, _advancedFilter);
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
