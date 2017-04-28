package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.PanelView;

/**
 *
 * @author Groupe01
 */
public class PanelController {
    
    private final PanelView _panelView; 
    
    public PanelController() {
        _panelView = new PanelView(this);
    }
    
    private void setUser() {
        // TODO : add user to the panel
    }
    
    public void openLogin() {
        // Create Sign In controller
        new SigninPopUpController();
        // TODO : Change panelView Aspect
    }
    
    public void openSignUp() {
        // Create Sign Up controller
        new SignupPopUpController();
        
    }
    
    public PanelView getView() {
        return _panelView;
    }
    
}
