package be.ac.ulb.infof307.g01.controller;

import be.ac.ulb.infof307.g01.view.LoginPopUp;

/**
 * Singleton class managing the authentication of the user,
 * and storing its user name.
 */
public class AuthenticationController {
	private static AuthenticationController _instance = null;
	private String _username;
	private LoginPopUp _loginPopUp;
	
	/** 
	 *  Make the constructor private, as this class is a singleton.
	 */
	private AuthenticationController() {
		createLoginPopUp();
	}
	
	private void createLoginPopUp() {
		_loginPopUp = new LoginPopUp(this);
	}
	
	/**
	 * Try to authenticate with the given user name.
	 * For now, there is no check in database, any user is accepted.
	 * @param username The user name.
	 * @throws IllegalArgumentException If the user name is empty.
	 */
	public void authenticate(String username) {
		if(username.isEmpty()) {
			throw new IllegalArgumentException("Username can not be empty");
		}
		_username = username;
		_loginPopUp.close();
	}
	
	public String getUsername() {
		return _username;
	}
	
	public static AuthenticationController getInstance() {
		if(_instance == null) {
			_instance = new AuthenticationController();
		}
		return _instance;
	}
}
