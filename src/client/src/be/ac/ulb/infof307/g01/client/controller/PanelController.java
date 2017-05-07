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
        String username = UserController.getInstance().getUsername();
        String email = UserController.getInstance().getEmail();
        _panelView.setUser(username,email);
    }
    
    public void openSignin() {
        new SigninPopUpController(this);
    }
    
    public void openSignUp() {
        new SignupPopUpController();
    }
    
    public PanelView getView() {
        return _panelView;
    }
    
}