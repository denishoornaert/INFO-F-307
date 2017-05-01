package be.ac.ulb.infof307.g01.common.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            Logger.getLogger(UserSendableModel.class.getName()).log(Level.WARNING, 
                null, "MD5 algorithm doesn't exist, skip encrypt ! (" + ex + ")");
        }
        return res;
    }
    
}
