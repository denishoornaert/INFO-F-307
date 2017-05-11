package be.ac.ulb.infof307.g01.client.view;

import be.ac.ulb.infof307.g01.client.controller.FilterPanelController;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
/**
 *
 * @author Groupe01
 */
public class FilterPanelView extends TabPane {
    
    public FilterPanelView(FilterPanelController controller) {
        super();
        initStyle();
    } 

    private void initStyle() {
        setSide(Side.LEFT);
    }
    
    public void addTab(String newTabName ,Tab newTab) {
        newTab.setText(newTabName);
        getTabs().add(newTab);
    }
    
}
