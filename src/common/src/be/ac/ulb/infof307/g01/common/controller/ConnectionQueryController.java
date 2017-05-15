package be.ac.ulb.infof307.g01.common.controller;

import be.ac.ulb.infof307.g01.common.model.UserSendableModel;

/**
 * Interface for the connection-related, network-oriented queries.
 * A Query Controller defines an interface for client and server calls, 
 * that may or may not have different implementation for each.
 */
public interface ConnectionQueryController {

    public void signin(UserSendableModel userSendableModel);
    public void signup(UserSendableModel userSendableModel);
    
}
