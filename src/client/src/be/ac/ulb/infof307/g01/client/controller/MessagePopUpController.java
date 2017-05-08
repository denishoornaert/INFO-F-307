package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.MessagePopUp;
import java.util.logging.Level;


public class MessagePopUpController extends AbstractPopUpController {
    
    public MessagePopUpController(Level severity, String message) throws InstantiationException {
        super();
        // We don't use the view afterwards, just instanciate it.
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
