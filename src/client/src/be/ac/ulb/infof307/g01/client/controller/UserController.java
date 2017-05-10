package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.common.controller.ConnectionQueryController;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Groupe 01
 */
public class UserController {
    
    private static UserController _instance = null;
    private UserSendableModel _user;
    private final ConnectionQueryController _connection;
    private boolean _isConnected = false;
    
    private UserController() {
        _connection = ServerQueryController.getInstance();
        // The following line is normal (DO NOT TOUCH ! I'm talking to you @theo :p)
        // This is normale because due to Jersey we can't modify the trivial
        // constructor and therefore we need to write this wierd instruction.
        _user = new UserSendableModel("", "", "");
    }
    
    /**
     * Try to authenticate with the given user name.
     * For now, there is no check in database, any user is accepted.
     * @param username The user name.
     * @param password The password.
    */
    public void authenticate(String username, String password) throws InvalidParameterException {
        // TODO get email from db
        if(username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("All fields are required");
        }
        UserSendableModel temporaryProfil = new UserSendableModel(username, password);
        _connection.signin(temporaryProfil);
        _user = temporaryProfil;
        Logger.getLogger(getClass().getName()).log(Level.INFO, 
                    "User {0} is login !", username);
        _isConnected = true;
    }
    
    /**
     * 
     * @param username the username
     * @param email the email
     * @param password of the user
     * @param terms are accepted
     */
    public void register(String email, String username, String password, 
            boolean terms) throws InvalidParameterException {
        if(email.isEmpty() || username.isEmpty() || password.isEmpty() || !terms) {
            throw new IllegalArgumentException("All fields are required");
        }
        UserSendableModel temporaryProfil = new UserSendableModel(username, email, password);
        _connection.signup(temporaryProfil);
        Logger.getLogger(getClass().getName()).log(Level.INFO, 
                "Your can check your mails and then login.");
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
