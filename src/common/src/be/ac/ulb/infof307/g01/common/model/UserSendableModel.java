package be.ac.ulb.infof307.g01.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Groupe01
 */

@XmlRootElement
public class UserSendableModel {
    
    protected String _username;
    protected String _password;
    protected String _email;
    
    public UserSendableModel() { } // Do not remove !

    public UserSendableModel(String username, String password) {
        this(username, "", password);
    }
    
    public UserSendableModel(String username, String email, String password) {
        _username = username;
        _password = password;
        _email = email;
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
    
}
