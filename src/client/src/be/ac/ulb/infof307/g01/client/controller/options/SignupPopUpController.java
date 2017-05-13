package be.ac.ulb.infof307.g01.client.controller.options;

import be.ac.ulb.infof307.g01.client.controller.app.PopUpController;
import be.ac.ulb.infof307.g01.client.controller.app.UserController;
import be.ac.ulb.infof307.g01.client.view.options.SignupPopUp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates and controls a sign-up popup that allows the user to create an account.
 */
public class SignupPopUpController extends PopUpController {
    
    private final SignupPopUp _signup;
	
    public SignupPopUpController() throws InstantiationException {
        super();
        _signup = new SignupPopUp(this);
    }
    
    /**
     * Submits the provided user information in order to create his account.
     * @param email the user's email
     * @param username the user's username
     * @param password the user's password
     * @param acceptedTerms indicates if the user accepted the terms of usage
     */
    public void submit(String email, String username, String password, boolean acceptedTerms) {
        try {
            UserController.getInstance().register(email, username, password, acceptedTerms);
            close(_signup);
        } catch(IllegalArgumentException error) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, error.getMessage());
            _signup.showError(error.getMessage());
        }
    }

    /**
     * Creates a popup displaying the terms and conditions of the application.
     */
    public void openTermsAndconditionPopUp() {
        try {
            new TermsAndConditionsPopUpController();
        } catch (InstantiationException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
        }
    }
}