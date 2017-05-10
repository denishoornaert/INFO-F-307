package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.AbstractPopUp;

/**
 * Abstarct class that amis to manage all the controllers linked to pop-up view.
 * Typically, this class will be implicitly called when a PopUpController will 
 * be instantiated.
 * 
 * @author Groupe01
 * 
 */

public abstract class AbstractPopUpController {
    
    private static boolean _aPopUpIsOpen = false;
    
    public AbstractPopUpController() throws InstantiationException {
        // if another pop-up is instanciated and if the pop-up can't be
        // insanciated along another pop-up.
        if(_aPopUpIsOpen && !acceptMultiplePopUps()) {
            throw new InstantiationException();
        }
        _aPopUpIsOpen = true;
    }
    
    protected boolean acceptMultiplePopUps() {
        return false;
    }
    
    public void close(AbstractPopUp widget) {
        // set to false only if the popUpConroller does not care about being only. 
        if(!acceptMultiplePopUps()) {
            _aPopUpIsOpen = false;
        }
        widget.close();
    }
    
}
