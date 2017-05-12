package be.ac.ulb.infof307.g01.client.controller.options;

import be.ac.ulb.infof307.g01.client.controller.app.FilterPanelController;
import be.ac.ulb.infof307.g01.client.controller.app.UserController;
import be.ac.ulb.infof307.g01.client.controller.map.MarkerController;
import be.ac.ulb.infof307.g01.client.view.options.PanelView;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Groupe01
 */
public class PanelController {

    private final PanelView _panelView;
    private FilterPanelController _filterPanelController;
    
    public PanelController(MarkerController markerController) {
        _panelView = new PanelView(this);
        _filterPanelController = new FilterPanelController(markerController);
        _panelView.setWidget(_filterPanelController.getView());
    }
    
    public void setUser() {
        String username = UserController.getInstance().getUsername();
        String email = UserController.getInstance().getEmail();
        _panelView.setUser(username,email);
    }
    
    public void openSignin() {
        try {
            new SigninPopUpController(this);
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