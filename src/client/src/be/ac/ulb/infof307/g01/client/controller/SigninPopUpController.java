package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.SigninPopUp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class managing the authentication of the user,
 * and storing its user name.
 */
public class SigninPopUpController extends AbstractPopUpController {
    
    private final SigninPopUp _signinPopUp;

    /** 
     *  Make the constructor private, as this class is a singleton.
    * @throws java.lang.InstantiationException
     */
    public SigninPopUpController() throws InstantiationException {
        super();
        _signinPopUp = new SigninPopUp(this);
    }

    /**
     * Try to authenticate with the given user name.
     * For now, there is no check in database, any user is accepted.
     * @param username The user name.
     * @param password The password
     */
    public void authenticate(String username, String password) {
        try {
            UserController.getInstance().authenticate(username, password);
            close(_signinPopUp);
        } catch(IllegalArgumentException error) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, 
                    "Signin message: {0}", error.getMessage());
            _signinPopUp.showError(error.getMessage());
        }
    }

}
