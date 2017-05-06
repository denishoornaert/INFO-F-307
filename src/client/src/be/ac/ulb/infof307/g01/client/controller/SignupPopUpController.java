package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.SignupPopUp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class managing the authentication of the user,
 * and storing its user name.
 */
public class SignupPopUpController {
    
    private final SignupPopUp _signup;
	
    public SignupPopUpController() {
        _signup = new SignupPopUp(this);
    }
    
    public void submit(String email, String user, String password, boolean terms) {
        try {
            UserController.getInstance().register(email, user, password, terms);
            cancel();
        } catch(IllegalArgumentException error) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, error.getMessage());
            _signup.showError(error.getMessage());
        }
    }
    
    public void cancel() {
        _signup.close();
    }

    public void openTermsAndconditionPopUp() {
        new TermsAndConditionsPopUpController();
    }
}