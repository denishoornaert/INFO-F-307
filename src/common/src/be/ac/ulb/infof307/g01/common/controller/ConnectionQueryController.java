/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.common.controller;

import be.ac.ulb.infof307.g01.common.model.UserSendableModel;

/**
 *
 * @author Groupe01
 */
public interface ConnectionQueryController {

    public boolean signin(UserSendableModel userSendableModel);
    public boolean signup(UserSendableModel userSendableModel);
    
}
