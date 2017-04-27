/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.SignupPopUp;

/**
 *
 * @author Groupe01
 */
public class SignupPopUpController {
    private static SignupPopUpController _instance = null;
    private String _username;
    private String _password;
    private SignupPopUp _logupPopUp;
	
    /** 
     *  Make the constructor private, as this class is a singleton.
    */
    private SignupPopUpController() {
        createLogupPopUp();
    }
	
    private void createLogupPopUp() {
        _logupPopUp = new SignupPopUp(/*this*/);
    }
	
    public void authenticate(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username or Password can not be empty");
        }
        _username = username;
        _password = password;
        _logupPopUp.close();
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
