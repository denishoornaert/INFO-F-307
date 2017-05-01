/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    protected boolean _signin;
    
    public UserSendableModel() { } // Do not remove !
    
    public void signin(String user, String password) {
        _username = user;
        _password = password;
        _signin = true;
    }
    
    public void signup(String user, String email) {
        _username = user;
        _email = email;
        _signin = false;
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
    
    public boolean getEvent() {
        return _signin;
    }
    
}
