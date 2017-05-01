package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.SignupPopUp;

/**
 * Singleton class managing the authentication of the user,
 * and storing its user name.
 */
public class SignupPopUpController {
    
    private SignupPopUp _signup;
	
    public SignupPopUpController() {
        _signup = new SignupPopUp(this);
    }
    
    /**
     * 
     * @param email user email
     * @param user username
     */
    public void submit(String email, String user, String password, boolean terms) {
        UserController.getInstance().register(email, user, password, terms);
    }
    
    public void cancel() {
        _signup.close();
    }

    public void openTermsAndconditionPopUp() {
        new TermsAndConditionsPopUpController();
    }
}