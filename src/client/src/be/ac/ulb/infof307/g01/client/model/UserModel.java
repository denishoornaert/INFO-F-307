/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.UserSendableModel;

/**
 *
 * @author Groupe01
 */

public class UserModel extends UserSendableModel{
    private static UserModel _instance = null;
    
    private UserModel() { } // Singleton
    
    public static UserModel getInstance(){
        if (_instance == null) {
            _instance = new UserModel();
        }
        return _instance;
    }
}
