package be.ac.ulb.infof307.g01.client.controller.options;

import be.ac.ulb.infof307.g01.client.controller.app.PopUpController;
import be.ac.ulb.infof307.g01.client.controller.app.UserController;
import be.ac.ulb.infof307.g01.client.view.options.SigninPopUp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class managing the authentication of the user,
 * and storing its user name.
 */
public class SigninPopUpController extends PopUpController {
    
    private final SigninPopUp _signinPopUp;
    private final PanelController _panelController;
    
    /**
     *  Make the constructor private, as this class is a singleton.
     * @param panel left panel
     * @throws InstantiationException popup allready open
     */
    public SigninPopUpController(PanelController panel) throws InstantiationException {
        super();
        _signinPopUp = new SigninPopUp(this);
        _panelController = panel;
    }
    
    /**
     * Try to authenticate with the given user name.
     * For now, there is no check in database, any user is accepted.
     * @param username The user name.
     * @param password The password
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