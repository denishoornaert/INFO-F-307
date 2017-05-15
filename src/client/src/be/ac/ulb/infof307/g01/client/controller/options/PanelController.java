package be.ac.ulb.infof307.g01.client.controller.options;

import be.ac.ulb.infof307.g01.client.controller.app.UserController;
import be.ac.ulb.infof307.g01.client.controller.map.MarkerController;
import be.ac.ulb.infof307.g01.client.view.options.PanelView;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates and controls the panel view that contains the application options.
 */
public class PanelController {

    private final PanelView _panelView;
    private final FilterPanelController _filterPanelController;
    
    public PanelController(MarkerController markerController) {
        _panelView = new PanelView(this);
        _filterPanelController = new FilterPanelController(markerController);
        _panelView.setWidget(_filterPanelController.getView());
    }
    
    /**
     * Updates the user information displayed in the panel view according
     * to ClientConfiguration.
     */
    public void setUser() {
        String username = UserController.getInstance().getUsername();
        String email = UserController.getInstance().getEmail();
        _panelView.setUser(username,email);
    }
    
    /**
     * Opens a signin popup allowing the user to authenticate himself.
     * If another exclusive popup is open, does nothing.
     */
    public void openSignin() {
        try {
            new SigninPopUpController(this);
        } catch (InstantiationException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
        }
    }
    
    /**
     * Opens a signup popup allowing the user to create an account.
     * If another exclusive popup is open, does nothing.
     */
    public void openSignUp() {
        try {
            new SignupPopUpController();
        } catch (InstantiationException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
        }
    }
    
    public void resetResearch() {
        _filterPanelController.applyFilter("", null);
    }
    
    public PanelView getView() {
        return _panelView;
    }
}