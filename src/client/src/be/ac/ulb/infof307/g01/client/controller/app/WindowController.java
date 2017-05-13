package be.ac.ulb.infof307.g01.client.controller.app;

import be.ac.ulb.infof307.g01.client.controller.options.PanelController;
import be.ac.ulb.infof307.g01.client.controller.map.MapController;
import be.ac.ulb.infof307.g01.client.view.app.Window;
import be.ac.ulb.infof307.g01.client.Main;
import be.ac.ulb.infof307.g01.client.model.app.ClientConfiguration;
import javafx.scene.layout.BorderPane;

/**
 * Manages the main window's widgets and appearance.
 */
public class WindowController {

    private MapController _mapController;
    private PanelController _panelController;
    private boolean _panelIsDisplayed = false;
    
    private final Window _window;
    
    public WindowController() {
        initControllers();
        _window = new Window(this, new BorderPane());
        _window.getStylesheets().add(ClientConfiguration.getInstance().getStyleFileName());
        displayWidgets();
        Main.setScene(_window);
    }
    
    /**
     * Initializes the map and panel controller.
     */
    private void initControllers() {
        _mapController = new MapController();
        _panelController = new PanelController(_mapController.getMarkerController());
    }

    /**
     * Displays the main widgets.
     */
    private void displayWidgets() {
        _window.placeWidget(_mapController.getView());
        _window.placeButton();
        // A button click simulation is required as it seems that some css
        // problems occur when both the google map and the css are
        // simultaneously loaded
        _window.simulateCLick();
    }
    
    /**
     * Switches the options panels appearance (hidden or displayed).
     */
    public void switchPanel() {
        _panelIsDisplayed = !_panelIsDisplayed;
        if(_panelIsDisplayed) {
            _window.showPanel(_panelController.getView());
        }
        else {
            _window.hidePanel();
        }
    }
    
}
