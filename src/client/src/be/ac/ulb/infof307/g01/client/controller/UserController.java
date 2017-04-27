/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.client.controller;

/**
 *
 * @author Nathan
 */
public class UserController {
    private static UserController _instance = null;
    private String _username;
    private String _password;
    private String _email;
    
    /**
     * Private controller : Singleton
     */
    private UserController() {
        _username = null;
        _password = null;
        _email = null;
    }
    
    public String getUsername() {
        return _username;
    }
    public String getPassword() {
        return _password;
    }
    public String getEmail() {
        return _email;
    }
    
    /**
     * Try to authenticate with the given user name.
     * For now, there is no check in database, any user is accepted.
     * @param username The user name.
     * @param password The password.
    */
    public void authenticate(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username or Password can not be empty");
        }
        _username = username;
        _password = password;
        // _email = getEmailFromDB -> TODO
    }
    
    /**
     * 
     * @param email the email
     * @param username the username
     * @param password the password
     */
    public void register(String email, String username, String password) {
        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("All fields are required");
        }
        _email = email;
        _username = username;
        _password = password;
    }
    
    public static UserController getInstance() {
        if(_instance == null) {
            _instance = new UserController();
        }
        return _instance;
    }
}
