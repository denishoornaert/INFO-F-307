package be.ac.ulb.infof307.g01.client.controller.options;

import be.ac.ulb.infof307.g01.client.controller.app.PopUpController;
import be.ac.ulb.infof307.g01.client.controller.app.UserController;
import be.ac.ulb.infof307.g01.client.view.options.SigninPopUp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates and controls a sign-in popup that allows the user to authenticate himself.
 * Holds a reference to the PanelController, which allows it to update the 
 * displayed user information.
 */
public class SigninPopUpController extends PopUpController {
    
    private final SigninPopUp _signinPopUp;
    private final PanelController _panelController;
    
    public SigninPopUpController(PanelController panel) throws InstantiationException {
        super();
        _signinPopUp = new SigninPopUp(this);
        _panelController = panel;
    }
    
    /**
     * Tries to authenticate the user with the provided information.
     * If the authentication fails, displays an error message in the popup.
     * @param username the user's username
     * @param password the user's password
     */
    public void authenticate(String username, String password)  {
        try {
            UserController.getInstance().authenticate(username, password);
            _panelController.setUser();
            close(_signinPopUp);
        } catch(IllegalArgumentException error) {
            Logger.getLogger(getClass().getName()).log(Level.INFO,
                    "Signin message: {0}", error.getMessage());
            _signinPopUp.showError(error.getMessage());
        }
    }
    
}
