package be.ac.ulb.infof307.g01.common.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * Represents a user.
 * A user has a username, password and e-mail.
 * The sendable keyword indicates the object can be serialized and sent 
 * over the network.
 */
@XmlRootElement
public class UserSendableModel {
    
    private String _username;
    private String _password;
    private String _email;
    
    /**
     * Default constructor.
     * Required by Jersey.
     */
    public UserSendableModel() { }

    public UserSendableModel(String username, String password) {
        this(username, "", password);
    }
    
    public UserSendableModel(String username, String email, String password) {
        _username = username;
        _password = cryptPassword(password);
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
    
    public String cryptPassword(String password) {
        String res = password;
        try {
            MessageDigest algo = MessageDigest.getInstance("MD5");
            res = new String(algo.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, 
                null, "MD5 algorithm doesn't exist, skip encrypt ! (" + ex + ")");
        }
        return res;
    }

    public void setUsername(String username) {
        this._username = username;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public void setEmail(String email) {
        this._email = email;
    }
    
}
