package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.SignupPopUp;
import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class managing the authentication of the user,
 * and storing its user name.
 */
public class SignupPopUpController extends PopUpController {
    
    private final SignupPopUp _signup;
	
    public SignupPopUpController() throws InstantiationException {
        super();
        _signup = new SignupPopUp(this);
    }
    
    public void submit(String email, String user, String password, boolean terms) {
        try {
            UserController.getInstance().register(email, user, password, terms);
            close(_signup);
        } catch(InvalidParameterException error) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, error.getMessage());
            _signup.showError(error.getMessage());
        }
    }

    public void openTermsAndconditionPopUp() {
        try {
            new TermsAndConditionsPopUpController();
        } catch (InstantiationException ex) {
            Logger.getLogger(SignupPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}