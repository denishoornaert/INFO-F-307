/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.common.model;

/**
 *
 * @author Groupe01
 */
public interface ConnectionQueryModel {

    public boolean signin(UserSendableModel userSendableModel);
    public boolean signup(UserSendableModel userSendableModel);
    
}
