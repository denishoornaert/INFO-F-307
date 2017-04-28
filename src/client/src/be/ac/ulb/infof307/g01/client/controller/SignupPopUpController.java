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
     * @param password user password
     */
    public void submit(String email, String user, String password) {
        UserController.getInstance().register(email, user, password);
        PanelController.getInstance().setUser();
    }
    
    public void cancel() {
        _signup.close();
    }
}
