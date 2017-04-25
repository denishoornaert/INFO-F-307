/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.controller;

import be.ac.ulb.infof307.g01.view.PanelView;

/**
 *
 * @author Groupe01
 */
public class PanelController {
    
    private final PanelView _panelView; 
    
    public PanelController() {
        _panelView = new PanelView(this);
    }
    
    public void openLogin() {
        // Create Sign In controller
        SigninPopUpController.getInstance();
        // TODO : Change panelView Aspect
    }
    
    public void openSignIn() {
        // Create Sign Up controller
        
    }
    
}
