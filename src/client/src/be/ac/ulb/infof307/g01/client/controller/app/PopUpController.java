package be.ac.ulb.infof307.g01.client.controller.app;

import be.ac.ulb.infof307.g01.client.view.app.AbstractPopUp;

/**
 * Abstract class that manages the controllers linked to pop-up views.
 * Popup controllers must inherit from this class and override the default
 * behavior if necessary.
 */
public class PopUpController {
    
    private static boolean _aPopUpIsOpen = false;
    
    /**
     * Tries to create a new instance of the class.
     * @throws InstantiationException if another popup is already open and
     * doesn't allow other popups to be open at the same time
     */
    public PopUpController() throws InstantiationException {
        if(_aPopUpIsOpen && !acceptMultiplePopUps()) {
            throw new InstantiationException();
        }
        _aPopUpIsOpen = true;
    }
    
    /**
     * Default behavior of popups does not allow other popups to be open
     * at the same time.
     * Override this method in child classes to change this behavior.
     * @return false
     */
    protected boolean acceptMultiplePopUps() {
        return false;
    }
    
    /**
     * Closes the provided AbstractPopUp instance.
     * @param widget the instance to close
     */
    public void close(AbstractPopUp widget) {
        // set to false only if the popUpConroller accepts multiple popups
        if(!acceptMultiplePopUps()) {
            _aPopUpIsOpen = false;
        }
        widget.close();
    }
    
}
