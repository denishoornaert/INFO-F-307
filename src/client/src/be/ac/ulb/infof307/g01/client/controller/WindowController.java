package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.Window;
import be.ac.ulb.infof307.g01.client.Main;
import javafx.scene.layout.BorderPane;

/**
 * Class that aims to manage the different widgets that will be displayed on the window
 * 
 */
public class WindowController {

    private MapController _mapController;
    private PanelController _panelController;
    private boolean _showPanel = false;
    
    private Window _window;
    
    public WindowController() {
        initControllers();
        _window = new Window(this, new BorderPane());
        displayWidgets();
        Main.setScene(_window);
    }
    
    private void initControllers() {
        _mapController = new MapController();
        _panelController = new PanelController();
    }

    private void displayWidgets() {
        _window.placeWidget(_mapController.getView());
        _window.placeButton();
    }
    
    public void buttonPressed() {
        _showPanel = !_showPanel;
        if(_showPanel) {
            _window.showPanel(_panelController.getView());
        }
        else {
            _window.hidePanel();
        }
    }
    
}