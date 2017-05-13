package be.ac.ulb.infof307.g01.client.controller.options;

import be.ac.ulb.infof307.g01.client.controller.app.ServerQueryController;
import be.ac.ulb.infof307.g01.client.model.filter.IdentityFilterOperationModel;
import be.ac.ulb.infof307.g01.client.controller.map.MarkerController;
import be.ac.ulb.infof307.g01.client.model.map.MarkerModel;
import be.ac.ulb.infof307.g01.client.model.map.PokemonCache;
import be.ac.ulb.infof307.g01.client.view.options.AdvancedFilterPanelView;
import be.ac.ulb.infof307.g01.client.view.options.BasicFilterPanelView;
import be.ac.ulb.infof307.g01.client.view.options.FilterPanelView;
import be.ac.ulb.infof307.g01.client.view.options.SavedFilterPanelView;
import be.ac.ulb.infof307.g01.common.model.FilterSendableModel;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the filter panel, used to filter Markers displayed on the map.
 * Filters are logical expressions used to determine if a marker should be
 * displayed or hidden on the map. The filter panel allows users to create
 * their own filters by selecting basic options or by writing its own
 * textual expressions.
 * Filters can be saved to the server's database with a descriptive name.
 */
public class FilterPanelController {
    
    private final FilterPanelView _filterPanelView;
    private SavedFilterPanelView _savedTab;
    private BasicFilterPanelView _basicFilter;
    private AdvancedFilterPanelView _advancedFilter;
    private static final String SAVED_EXPRESSION_LABEL = "Saved filter";
    private static final String BASIC_FILTER_LABEL = "Basic filter";
    private static final String ADVANCED_FILTER_LABEL = "Advanced filter";
    private final MarkerController _markerController;
    private HashMap<String, String> _savedFilters;
    
    public FilterPanelController(MarkerController markerController) {
        _filterPanelView = new FilterPanelView(this);
        _markerController = markerController;
        initTabs();
        placeTabs();
    }
    
    private void initTabs() {
        _savedTab = new SavedFilterPanelView(this);
        initSavedTab();
        _basicFilter = new BasicFilterPanelView(this);
        _basicFilter.setComboBoxesContent(PokemonCache.getInstance().getAllPokemonTypesString());
        _advancedFilter = new AdvancedFilterPanelView(this);
    }
    
    private void initSavedTab() {
        _savedFilters = new HashMap<>();
        List<FilterSendableModel> allFilterModels = ServerQueryController.getInstance().getAllFilter();
        for(FilterSendableModel filter : allFilterModels) {
            _savedFilters.put(filter.getName(), filter.getExpression());
        }
        _savedTab.setSavedFilters(_savedFilters.keySet());
    }
    
    private void placeTabs() {
        _filterPanelView.addTab(SAVED_EXPRESSION_LABEL, _savedTab);
        _filterPanelView.addTab(BASIC_FILTER_LABEL, _basicFilter);
        _filterPanelView.addTab(ADVANCED_FILTER_LABEL, _advancedFilter);
    }
    
    /**
     * Helper function, converts a boolean value to a textual logical operator.
     * @param value the boolean value to convert
     * @return "ID" if value is true, "NOT" otherwise
     */
    private String booleanToString(boolean value) {
        return (value) ? "ID" : "NOT";
    }
    
    /**
     * Transforms a set of parameters into a textual filter.
     * //TODO complete JavaDoc
     * @param notName
     * @param name
     * @param notType1
     * @param type1
     * @param notType2
     * @param type2
     * @param andIsSelected
     * @param orIsSelected
     * @return 
     */
    private String getRequest(boolean notName, String name, boolean notType1, 
            String type1, boolean notType2, String type2, boolean andIsSelected, 
            boolean orIsSelected) { // TODO denis "or" is not used
        boolean somethingBefore = false;
        String expression = (andIsSelected) ? "AND" : "OR";
        expression += "(";
        if(!name.isEmpty()) {
            expression += booleanToString(notName)+"(NAME("+name+"))";
            somethingBefore = true;
        }
        if(type1 != null) {
            expression += (somethingBefore) ? "," : "";
            expression += booleanToString(notType1)+"(TYPE("+type1+"))";
            somethingBefore = true;
        }
        if(type2 != null) {
            expression += (somethingBefore) ? "," : "";
            expression += booleanToString(notType2)+"(TYPE("+type2+")),";
        }
        expression += ")";
        return expression;
    }
    
    /**
     * @return the associated JavaFX view
     */
    public FilterPanelView getView() {
        return _filterPanelView;
    }

    /**
     * Applies a filter to the map's markers, according to the filter expression.
     * Calls MarkerController.showHideMarkers().
     * @param expression the textual expression representing the filter rules
     */
    public void applyFilter(String expression) {
        HashSet<MarkerModel> allMarkers = _markerController.getAllMarkers();
        HashSet<MarkerModel> res = allMarkers;
        try {
            IdentityFilterOperationModel filterExpression = new IdentityFilterOperationModel(expression);
            res = filterExpression.evaluateFilter(allMarkers);
        } catch (ParseException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
        }
        _markerController.displaySelectMarkers(res);
    }
    
    /**
     * Saves a filter expression and its name description to the database.
     * @param expressionName the descriptive name of the expression to save
     * @param expression the filtering expression to save
     */
    public void saveFilter(String expressionName, String expression) {
        // TODO manage correctly ! to corretly the error message
        if(!expressionName.isEmpty()) {
            FilterSendableModel filter = new FilterSendableModel(expressionName, expression);
            try {
                ServerQueryController.getInstance().insertFilter(filter);
            } catch (InvalidParameterException error) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, error.getMessage());
            }
        }
        else {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Field empty!");
        }
    }
    
    /**
     * Applies a filter to the map's markers, according to the parameters.
     * Calls FilterPanelController.applyFilter() with an expression that 
     * matches the given parameters.
     * TODO complete JavaDoc.
     * @param notName
     * @param name
     * @param notType1
     * @param type1
     * @param notType2
     * @param type2
     * @param andIsSelected
     * @param orIsSelected 
     */
    public void applyFilter(boolean notName, String name, boolean notType1, 
            String type1, boolean notType2, String type2, boolean andIsSelected, 
            boolean orIsSelected) {
        String expression = getRequest(notName, name, notType1, type1, notType2, 
                type2, andIsSelected, orIsSelected);
        applyFilter(expression);
    }

    /**
     * Saves a filter expression and its name description to the database.
     * Calls FilterPanelController.saveFilter() with an expression that
     * matches the given parameters.
     * TODO complete JavaDoc.
     * @param expressionName
     * @param notName
     * @param name
     * @param notType1
     * @param type1
     * @param notType2
     * @param type2
     * @param andIsSelected
     * @param orIsSelected 
     */
    public void saveFilter(String expressionName, boolean notName, String name, 
            boolean notType1, String type1, boolean notType2, String type2, 
            boolean andIsSelected, boolean orIsSelected) {
        String expression = getRequest(notName, name, notType1, type1, notType2, 
                type2, andIsSelected, orIsSelected);
        saveFilter(expressionName, expression);
    }

    /**
     * Retrieves a named filter from the saved filters and applies it.
     * @param name the descriptive name of the filter to apply
     */
    public void applyFilterByName(String name) {
        String expression = _savedFilters.get(name);
        if(expression == null) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, 
                    "Could not get filter with name: {0} (array: {1})", 
                    new Object[]{name, _savedFilters});
        } else {
            applyFilter(expression);
        }
    }
    
}
