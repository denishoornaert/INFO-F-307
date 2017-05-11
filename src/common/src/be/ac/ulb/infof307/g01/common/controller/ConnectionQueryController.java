package be.ac.ulb.infof307.g01.common.controller;

import be.ac.ulb.infof307.g01.common.model.UserSendableModel;

/**
 *
 * @author Groupe01
 */
public interface ConnectionQueryController {

    public void signin(UserSendableModel userSendableModel);
    public void signup(UserSendableModel userSendableModel);
    
}
