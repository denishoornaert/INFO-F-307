package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.common.model.ConnectionQueryModel;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;

/**
 *
 * @author Groupe 01
 */
public class UserController {
    
    private static UserController _instance = null;
    private UserSendableModel _user;
    private final ConnectionQueryModel _connection;
    
    private UserController() {
        this(true);
    }
    
    private UserController(boolean initConnection) {
        if(initConnection) {
            _connection = (ConnectionQueryModel) ServerQueryController.getInstance();
        } else {
            _connection = null;
        }
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
        if(_connection != null) {
            boolean successfullySignin = _connection.signin(temporaryProfil);
            if(!successfullySignin) {
                throw new IllegalArgumentException("User name already taken.");
            } else {
                _user = temporaryProfil;
            }
        }
    }
    
    /**
     * 
     * @param username the username
     * @param email the email
     * @param password of the user
     * @param terms are accepted
     */
    public void register(String email, String username, String password, boolean terms) {
        if(email.isEmpty() || username.isEmpty() || password.isEmpty() || !terms) {
            throw new IllegalArgumentException("All fields are required");
        }
        UserSendableModel temporaryUser = new UserSendableModel(username, email, password);
        
        if(_connection != null) {
            _connection.signup(temporaryUser);
        }
    }
    
    public static UserController getTestInstance() {
        if(_instance == null) {
            _instance = new UserController(false);
        }
        return _instance;
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
