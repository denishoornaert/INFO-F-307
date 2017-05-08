package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.MessagePopUp;
import java.util.logging.Level;


public class MessagePopupController extends AbstractPopUpController {
    
    public MessagePopupController(Level severity, String message) throws InstantiationException {
        super();
        new MessagePopUp(this, severity, message);
    }
    
    /**
     * We accept that a message popup is shown above another popup, this may be
     * necessary.
     * @return true
     */
    @Override
    protected boolean acceptMultiplePopUps() {
        return true;
    }
}
