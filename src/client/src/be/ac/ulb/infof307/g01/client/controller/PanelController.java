package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.PanelView;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            new SigninPopUpController();
        } catch (InstantiationException ex) {
            Logger.getLogger(PanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openSignUp() {
        try {
            new SignupPopUpController();
        } catch (InstantiationException ex) {
            Logger.getLogger(PanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public PanelView getView() {
        return _panelView;
    }
    
}