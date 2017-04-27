package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.SigninPopUp;

/**
 * Singleton class managing the authentication of the user,
 * and storing its user name.
 */
public class SigninPopUpController {
	private SigninPopUp _signinPopUp;
	
	/** 
	 *  Make the constructor private, as this class is a singleton.
	 */
	public SigninPopUpController() {
            _signinPopUp = new SigninPopUp(this);
	}
	
	/**
	 * Try to authenticate with the given user name.
	 * For now, there is no check in database, any user is accepted.
	 * @param username The user name.
	 */
	public void authenticate(String username, String password) {
            (UserController.getInstance()).authenticate(username, password);
	}
        
        /**
         * Close popup
         */
        public void cancel() {
            _signinPopUp.close();
        }
}
