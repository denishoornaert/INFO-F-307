/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.client.model;

/** Model of the User. A User contains private information of the User
 * (id,username,email,password).
 * 
 */
//TODO Remis : add extend UserModel..(common).
public class UserModel {
    private final int _id;
    private final String _username;
    private final String _email;
    private final String _password;
    private static UserModel _thisUser = null;
    
    /**
     * Creat the User 
     * @param id the id of the User in the database
     * @param username the username of the User
     * @param email the e-mail of the user
     * @param password the user password
     */
    
    private UserModel(int id,String username,String email,String password){
        _id = id;
        _username = username;
        _email = email;
        _password = password; // not a good idéa
    }
    /**
     * Create the instance of the User 
     * @param id the id of the User in the database
     * @param username the username of the User
     * @param email the e-mail of the user
     * @param password the user password
     */
    public static void initUser(int id,String username,String email,String password){
        if(_thisUser == null){
            _thisUser = new UserModel(id,username,email,password);
        }
    }
    
    /**
     * Return the instance of UserModel
     * @return the instance of the UserModel
     */
    public static UserModel getInstance(){
        return _thisUser;
    }
    
    /**
     * Return username of the instance
     * @return the username of the UserModel
     */
    public String getUsername(){
        return _username;
    }
    
    /**
     * Return password of the instance
     * @return the password of the UserModel
     */
    public String getPassword(){
        return _password;
    }
    
    /**
     * Return email of the instance
     * @return the email of the UserModel
     */
    public String getEmail(){
        return _email;
    }
    
    /**
     * Return id of the instance
     * @return the id of the UserModel
     */
    public int getId(){
        return _id;
    }
}
