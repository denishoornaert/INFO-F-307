package be.ac.ulb.infof307.g01.client.controller.options;

import be.ac.ulb.infof307.g01.client.controller.app.ServerQueryController;
import be.ac.ulb.infof307.g01.client.controller.map.MarkerController;
import be.ac.ulb.infof307.g01.client.model.filter.AbstractFilterExpressionModel;
import be.ac.ulb.infof307.g01.client.model.filter.BasicFilterParametersModel;
import be.ac.ulb.infof307.g01.client.model.map.MarkerModel;
import be.ac.ulb.infof307.g01.client.model.map.PokemonCache;
import be.ac.ulb.infof307.g01.client.view.options.AbstractFilterPanelView;
import be.ac.ulb.infof307.g01.client.view.options.AdvancedFilterPanelView;
import be.ac.ulb.infof307.g01.client.view.options.BasicFilterPanelView;
import be.ac.ulb.infof307.g01.client.view.options.FilterPanelView;
import be.ac.ulb.infof307.g01.client.view.options.SavedFilterPanelView;
import be.ac.ulb.infof307.g01.common.model.FilterSendableModel;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.ArrayList;
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
    
    private static final String SAVED_EXPRESSION_LABEL = "Saved filter";
    private static final String BASIC_FILTER_LABEL = "Basic filter";
    private static final String ADVANCED_FILTER_LABEL = "Advanced filter";
    
    private final FilterPanelView _filterPanelView;
    private final MarkerController _markerController;
    private SavedFilterPanelView _savedTab;
    private BasicFilterPanelView _basicFilter;
    private AdvancedFilterPanelView _advancedFilter;
    private HashMap<String, String> _savedFilters;
    
    public FilterPanelController(MarkerController markerController) {
        _filterPanelView = new FilterPanelView();
        _markerController = markerController;
        initTabs();
        placeTabs();
    }
    
    private void initTabs() {
        _savedTab = new SavedFilterPanelView(this);
        initSavedTab();
        _basicFilter = new BasicFilterPanelView(this);
        ArrayList<String> allPokemonTypeSelectable = PokemonCache.getInstance().getAllPokemonTypesString();
        allPokemonTypeSelectable.add(" ");
        _basicFilter.setComboBoxesContent(allPokemonTypeSelectable);
        _advancedFilter = new AdvancedFilterPanelView(this);
    }
    
    private void initSavedTab() {
        _savedFilters = new HashMap<>();
        List<FilterSendableModel> allFilterModels = ServerQueryController.getInstance().getAllFilter();
        for(FilterSendableModel filter : allFilterModels) {
            _savedFilters.put(filter.getName(), filter.getExpression());
        }
        _savedTab.addSavedFilters(_savedFilters.keySet());
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
     * 
     * @param filterParameters The set of parameters of the basic filter
     * @return the expression converted to a String
     */
    private String getRequest(BasicFilterParametersModel filterParameters) {
        boolean somethingBefore = false;
        String expression = (filterParameters.isAndSelected()) ? "AND" : "OR";
        expression += "(";
        if(!filterParameters.getName().isEmpty()) {
            expression += booleanToString(filterParameters.notName())+"(NAME("+filterParameters.getName()+"))";
            somethingBefore = true;
        }
        if(filterParameters.getType1() != null || !" ".equals(filterParameters.getType1())) {
            expression += (somethingBefore) ? "," : "";
            expression += booleanToString(filterParameters.notType1())+"(TYPE("+filterParameters.getType1()+"))";
            somethingBefore = true;
        }
        if(filterParameters.getType2() != null || !" ".equals(filterParameters.getType2())) {
            expression += (somethingBefore) ? "," : "";
            expression += booleanToString(filterParameters.notType2())+"(TYPE("+filterParameters.getType2()+"))";
        }
        expression += ")";
        return expression;
    }
    
    private void addNewlySavedFilter(FilterSendableModel filter) {
        _savedFilters.put(filter.getName(), filter.getExpression());
        _savedTab.addSavedFilters(_savedFilters.keySet());
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
     * 
     * @param expression the textual expression representing the filter rules
     * @param originFilterPanel the origin panel filter
     */
    public void applyFilter(String expression, AbstractFilterPanelView originFilterPanel) {
        HashSet<MarkerModel> allMarkers = _markerController.getAllMarkers();
        HashSet<MarkerModel> res = allMarkers;
        if(originFilterPanel != null) {
            originFilterPanel.showError("");
        }
        
        try {
            AbstractFilterExpressionModel filter = AbstractFilterExpressionModel.parse(expression);
            res = filter.evaluateFilter(allMarkers);
        } catch (ParseException ex) {
            if(originFilterPanel != null) {
                originFilterPanel.showError("Filter not valid");
            } else {
                Logger.getLogger(getClass().getName()).log(Level.WARNING, ex.getMessage());
            }
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
                addNewlySavedFilter(filter);
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
     * @param filterParameters The set of parameters of the basic filter
     * @param originFilterPanel
     */
    public void applyFilter(BasicFilterParametersModel filterParameters, AbstractFilterPanelView originFilterPanel) {
        final String expression = getRequest(filterParameters);
        applyFilter(expression, originFilterPanel);
    }

    /**
     * Saves a filter expression and its name description to the database.
     * Calls FilterPanelController.saveFilter() with an expression that
     * matches the given parameters.
     * TODO complete JavaDoc.
     * @param expressionName The name of the filter to save
     * @param filterParameters The set of parameters of the basic filter
     */
    public void saveFilter(String expressionName, BasicFilterParametersModel filterParameters) {
        final String expression = getRequest(filterParameters);
        saveFilter(expressionName, expression);
    }

    /**
     * Retrieves a named filter from the saved filters and applies it.
     * 
     * @param name the descriptive name of the filter to apply
     * @param originFilterPanel filter panel who this method is call
     */
    public void applyFilterByName(String name, AbstractFilterPanelView originFilterPanel) {
        String expression = _savedFilters.get(name);
        if(expression == null) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, 
                    "Could not get filter with name: {0} (array: {1})", 
                    new Object[]{name, _savedFilters});
        } else {
            applyFilter(expression, originFilterPanel);
        }
    }
    
}
