package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.PanelView;

/**
 *
 * @author Groupe01
 */
public class PanelController {

    private final PanelView _panelView; 
    
    private SigninPopUpController _signIn;
    private SignupPopUpController _signUp;
    
    public PanelController() {
        _panelView = new PanelView(this);
    }
    
    public void setUser() {
        // TODO : add user to the panel
        String username = UserController.getInstance().getUsername();
        String email = UserController.getInstance().getEmail();
        _panelView.setUser(username,email);
    }
    
    public void openLogin() {
        // Create Sign In controller
        _signIn = new SigninPopUpController();
    }
    
    public void openSignUp() {
        // Create Sign Up controller
        _signUp = new SignupPopUpController();
    }
    
    public PanelView getView() {
        return _panelView;
    }
    
}
