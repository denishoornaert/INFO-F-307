package be.ac.ulb.infof307.g01.client.controller.app;

import be.ac.ulb.infof307.g01.client.controller.filter.IdentityFilterOperationController;
import be.ac.ulb.infof307.g01.client.controller.map.MarkerController;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.model.PokemonCache;
import be.ac.ulb.infof307.g01.client.view.AdvancedFilterPanelView;
import be.ac.ulb.infof307.g01.client.view.BasicFilterPanelView;
import be.ac.ulb.infof307.g01.client.view.FilterPanelView;
import be.ac.ulb.infof307.g01.client.view.SavedFilterPanelView;
import be.ac.ulb.infof307.g01.common.model.FilterSendableModel;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private final String _savedExpressionLabel = "Saved filter";
    private final String _basicFilterLabel = "Basic filter";
    private final String _advancedFilterLabel = "Advanced filter";
    private final MarkerController _markerController;
    
    public FilterPanelController(MarkerController markerController) {
        _filterPanelView = new FilterPanelView(this);
        _markerController = markerController;
        initTabs();
        placeTabs();
    }
    
    private void initTabs() {
        _savedTab = new SavedFilterPanelView(this);
        _basicFilter = new BasicFilterPanelView(this);
        _basicFilter.setComboBoxesContent(PokemonCache.getInstance().getAllPokemonTypesString());
        _advancedFilter = new AdvancedFilterPanelView(this);
    }
    
    private void placeTabs() {
        _filterPanelView.addTab(_savedExpressionLabel, _savedTab);
        _filterPanelView.addTab(_basicFilterLabel, _basicFilter);
        _filterPanelView.addTab(_advancedFilterLabel, _advancedFilter);
    }
    
    private String booleanToString(boolean value) {
        return (value) ? "ID" : "NOT";
    }
    
    private String getRequest(boolean notName, String name, boolean notType1, String type1, boolean notType2, String type2, boolean andIsSelected, boolean orIsSelected) {
        String expression = (andIsSelected) ? "AND" : "OR";
        expression += "("+booleanToString(notName)+"(NAME("+name+")),";
        expression += booleanToString(notType1)+"(TYPE("+type1+")),";
        expression += booleanToString(notType1)+"(TYPE("+type1+"))";
        return expression;
    }
    
    public FilterPanelView getView() {
        return _filterPanelView;
    }

    public void applyFilter(String expression) {
        System.out.println(expression);
        try {
            IdentityFilterOperationController filterExpression = new IdentityFilterOperationController(expression);
            HashSet<MarkerModel> allMarkers = _markerController.getAllMarkers();
            HashSet<MarkerModel> res = filterExpression.evaluateFilter(allMarkers);
            _markerController.showHideMarker(res);
        } catch (ParseException ex) {
            Logger.getLogger(FilterPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // TODo manage correctly !
    public void saveFilter(String expressionName, String expression) {
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

    public void applyFilter(boolean notName, String name, boolean notType1, String type1, boolean notType2, String type2, boolean andIsSelected, boolean orIsSelected) {
        String expression = getRequest(notName, name, notType1, type1, notType2, type2, andIsSelected, orIsSelected);
        applyFilter(expression);
    }

    public void saveFilter(String expressionName, boolean notName, String name, boolean notType1, String type1, boolean notType2, String type2, boolean andIsSelected, boolean orIsSelected) {
        String expression = getRequest(notName, name, notType1, type1, notType2, type2, andIsSelected, orIsSelected);
        saveFilter(expressionName, expression);
    }
    
}
