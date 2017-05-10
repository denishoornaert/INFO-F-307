package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.MessagePopUp;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MessagePopUpController extends AbstractPopUpController {

    private static final Logger LOG = Logger.getLogger(MessagePopUpController.class.getName());
    
    public MessagePopUpController(Level severity, String message) throws InstantiationException {
        super();
        new MessagePopUp(this, severity, message);
    }
    
    public static void createPopUpOrLog(Level severity, String message){
        try {
            new MessagePopUpController(severity, message);
        } catch (InstantiationException ex) {
                LOG.log(Level.WARNING, "This error couldn't be shown with a popup: {0}",
                        message);
        }
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
