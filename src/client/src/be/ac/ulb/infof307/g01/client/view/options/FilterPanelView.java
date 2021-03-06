package be.ac.ulb.infof307.g01.client.view.options;

import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Holds all filter panel tabs.
 * Each filter panel tab has a different view allowing the user to
 * use filters in different ways.
 */
public class FilterPanelView extends TabPane {
    
    public FilterPanelView() {
        super();
        initStyle();
    } 

    private void initStyle() {
        setSide(Side.LEFT);
    }
    
    public void addTab(final String newTabName, final Tab newTab) {
        newTab.setText(newTabName);
        getTabs().add(newTab);
    }
    
}
