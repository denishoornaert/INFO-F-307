package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.common.model.ConnectionQueryModel;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Groupe 01
 */
public class UserController {
    
    private static UserController _instance = null;
    private UserSendableModel _user;
    private final ConnectionQueryModel _connection;
    private boolean _isConnected = false;
    
    private UserController() {
        _connection = (ConnectionQueryModel) ServerQueryController.getInstance();
    }
    
    /**
     * Try to authenticate with the given user name.
     * For now, there is no check in database, any user is accepted.
     * @param username The user name.
     * @param password The password.
    */
    public void authenticate(String username, String password) throws IllegalArgumentException {
        if(username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("All fields are required");
        }
        UserSendableModel temporaryProfil = new UserSendableModel(username, password);
        _isConnected = _connection.signin(temporaryProfil);
        if(!_isConnected) {
            throw new IllegalArgumentException("there's something wrong.");
        } else {
            _user = temporaryProfil;
            Logger.getLogger(getClass().getName()).log(Level.INFO, 
                    "User {0} is login !", username);
        }
    }
    
    /**
     * 
     * @param username the username
     * @param email the email
     * @param password of the user
     * @param terms are accepted
     */
    public void register(String email, String username, String password, 
            boolean terms) throws IllegalArgumentException {
        if(email.isEmpty() || username.isEmpty() || password.isEmpty() || !terms) {
            throw new IllegalArgumentException("All fields are required");
        }
        UserSendableModel temporaryProfil = new UserSendableModel(username, email, password);
        boolean successfullySignup = _connection.signup(temporaryProfil);
        if(!successfullySignup) {
            throw new IllegalArgumentException("User name or email already taken.");
        } else {
            throw new IllegalArgumentException("Your can check your mails and then login.");
        }
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

    public boolean isConnected() {
        return _isConnected;
    }
}
