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
    
    public void openLogin() {
        // Create Sign In controller
        SigninPopUpController.getInstance();
        // TODO : Change panelView Aspect
    }
    
    public void openSignIn() {
        // Create Sign Up controller
        SignupPopUpController.getInstance();
    }
    
}
