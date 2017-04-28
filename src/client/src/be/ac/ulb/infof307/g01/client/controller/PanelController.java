package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.Main;
import be.ac.ulb.infof307.g01.client.view.PanelView;

/**
 *
 * @author Groupe01
 */
public class PanelController {
    private static PanelController _instance = null;
    private final PanelView _panelView; 
    
    private PanelController() {
        _panelView = new PanelView(this);
        Main.getBorderPane().setLeft(_panelView);
    }
    
    public void setUser() {
        // TODO : add user to the panel
        String username = UserController.getInstance().getUsername();
        String email = UserController.getInstance().getEmail();
        _panelView.setUser(username,email);
    }
    
    public void openLogin() {
        // Create Sign In controller
        new SigninPopUpController();
    }
    
    public void openSignUp() {
        // Create Sign Up controller
        new SignupPopUpController();
    }
    
    public static PanelController getInstance() {
        if (_instance == null) {
            _instance = new PanelController();
        }
        return _instance;
    }
    
}
