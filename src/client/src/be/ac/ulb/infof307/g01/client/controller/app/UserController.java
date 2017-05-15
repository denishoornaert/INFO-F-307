package be.ac.ulb.infof307.g01.client.controller.app;

import be.ac.ulb.infof307.g01.common.controller.ConnectionQueryController;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class that provides user-related information to other classes.
 */
public class UserController {
    
    private static UserController _instance = null;
    private UserSendableModel _user;
    private final ConnectionQueryController _connection;
    private boolean _isConnected = false;
    
    public static UserController getInstance() {
        if(_instance == null) {
            _instance = new UserController();
        }
        return _instance;
    }
    
    private UserController() {
        _connection = ServerQueryController.getInstance();
        // The following line is normal (DO NOT TOUCH ! I'm talking to you @theo :p)
        // This is normale because due to Jersey we can't modify the trivial
        // constructor and therefore we need to write this wierd instruction.
        _user = new UserSendableModel("", "", ""); //REQUIRED by Jersey
    }
    
    /**
     * Tries to authenticate the user with the given username and password.
     * @param username the user's username
     * @param password the user's password
    */
    public void authenticate(final String username, final String password)
            throws InvalidParameterException {
        if(username.isEmpty() || password.isEmpty()) {
            throw new InvalidParameterException("All fields are required");
        }
        final UserSendableModel temporaryProfil = new UserSendableModel(username, password);
        _connection.signin(temporaryProfil);
        _user = temporaryProfil;
        Logger.getLogger(getClass().getName()).log(Level.INFO, 
                "User {0} ({1}) is logged in !", 
                new Object[]{username, _user.getEmail()});
        _isConnected = true;
    }
    
    /**
     * Tries to register a new user with the provided user information.
     * @param username the user's username
     * @param email the user's email
     * @param password the user's password
     * @param terms indicates if the user has accepted the terms of usage
     */
    public void register(final String email, final String username, 
            final String password, final boolean terms) throws InvalidParameterException {
        if(email.isEmpty() || username.isEmpty() || password.isEmpty() || !terms) {
            throw new IllegalArgumentException("All fields are required");
        }
        final UserSendableModel temporaryProfil = new UserSendableModel(username, email, password);
        _connection.signup(temporaryProfil);
        Logger.getLogger(getClass().getName()).log(Level.INFO, 
                "Your can check your mails and then login.");
    }

    public void logout() {
        _user = new UserSendableModel("","","");
        _isConnected = false;
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
