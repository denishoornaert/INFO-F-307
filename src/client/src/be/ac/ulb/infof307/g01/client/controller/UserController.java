/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.common.model.ConnectionQueryModel;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;

/**
 *
 * @author Nathan
 */
public class UserController {
    
    private static UserController _instance = null;
    private UserSendableModel _user;
    private final ConnectionQueryModel _connection;
    
    private UserController() {
        _connection = (ConnectionQueryModel) ServerQueryController.getInstance();
    }
    
    /**
     * Try to authenticate with the given user name.
     * For now, there is no check in database, any user is accepted.
     * @param username The user name.
     * @param password The password.
    */
    public void authenticate(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("All fields are required");
        }
        UserSendableModel temporaryProfil = new UserSendableModel(username, password);
        boolean successfullySignin = _connection.signin(temporaryProfil);
        if(!successfullySignin) {
            throw new IllegalArgumentException("User name already taken.");
        }
    }
    
    /**
     * 
     * @param email the email
     * @param username the username
     */
    public void register(String email, String username, String password, boolean terms) {
        if(email.isEmpty() || username.isEmpty() || password.isEmpty() || !terms) {
            throw new IllegalArgumentException("All fields are required");
        }
        UserSendableModel temporaryProfil = new UserSendableModel(username, email, password);
        _connection.signup(temporaryProfil);
    }
    
    public static UserController getInstance() {
        if(_instance == null) {
            _instance = new UserController();
        }
        return _instance;
    }

    public String getEmail() {
        return _user.getEmail();
    }

    public String getUsername() {
        return _user.getUsername();
    }

    public String getPassword() {
        return _user.getPassword();
    }
}
