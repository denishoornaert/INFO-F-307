package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.SignupPopUp;

/**
 * Singleton class managing the authentication of the user,
 * and storing its user name.
 */
public class SignupPopUpController {
	private static SignupPopUpController _instance = null;
	private String _username;
        private String _password;
	private SignupPopUp _loginPopUp;
	
	/** 
	 *  Make the constructor private, as this class is a singleton.
	 */
	private SignupPopUpController() {
            createSignUpPopUp();
	}
	
	private void createSignUpPopUp() {
            _loginPopUp = new SignupPopUp(/*this*/);
	}
	
	/**
	 * Try to authenticate with the given user name.
	 * For now, there is no check in database, any user is accepted.
	 * @param username The user name.
	 * @throws IllegalArgumentException If the user name is empty.
	 */
	public void authenticate(String username, String password) {
            if(username.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Username or Password can not be empty");
            }
            _username = username;
            _password = password;
            _loginPopUp.close();
	}
	
	public String getUsername() {
            return _username;
	}
        
        public String getPassword() {
            return _password;
        }
	
	public static SignupPopUpController getInstance() {
            if(_instance == null) {
                    _instance = new SignupPopUpController();
            }
            return _instance;
	}
}
