package be.ac.ulb.infof307.g01.client.controller;

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
        // if no other pop-up is instanciated and if the pop-up can't be
        // insanciated along another pop-up.
        if(_aPopUpIsOpen && !acceptMultiplePopUps()) {
            throw new InstantiationException();
        }
    }
    
    protected boolean acceptMultiplePopUps() {
        return false;
    }
    
    // TODO make sure this method is overrided in every child.
    protected void close() {
        _aPopUpIsOpen = false;
    }
    
}
