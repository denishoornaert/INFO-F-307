package be.ac.ulb.infof307.g01.client.view;

import be.ac.ulb.infof307.g01.client.controller.app.FilterPanelController;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Holds all filter panel tabs.
 * Each filter panel tab has a different view allowing the user to
 * use filters in different ways.
 */
public class FilterPanelView extends TabPane {
    
    public FilterPanelView(FilterPanelController controller) {
        super();
        initStyle();
    } 

    private void initStyle() {
        setSide(Side.LEFT);
    }
    
    public void addTab(String newTabName, Tab newTab) {
        newTab.setText(newTabName);
        getTabs().add(newTab);
    }
    
}
