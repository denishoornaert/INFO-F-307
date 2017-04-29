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
    
    public void setUser() {
        // TODO : add user to the panel
        String username = UserController.getInstance().getUsername();
        String email = UserController.getInstance().getEmail();
        _panelView.setUser(username,email);
    }
    
    public void openSignin() {
        // Create Sign In controller
        new SigninPopUpController();
    }
    
    public void openSignUp() {
        // Create Sign Up controller
        new SignupPopUpController();
    }
    
    public PanelView getView() {
        return _panelView;
    }
    
}