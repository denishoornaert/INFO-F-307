package be.ac.ulb.infof307.g01.client.controller.app;

import be.ac.ulb.infof307.g01.client.view.app.MessagePopUp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessagePopUpController extends PopUpController {

    private static final Logger LOG = Logger.getLogger(MessagePopUpController.class.getName());
    
    /**
     * Creates a MessagePopUpController, with its corresponding PopUp view.
     * @param severity the importance of the popup message
     * @param message the message to display in the popup
     * @throws InstantiationException if another open popup prevented this one
     * from being opened.
     */
    public MessagePopUpController(Level severity, String message) throws InstantiationException {
        super();
        new MessagePopUp(this, severity, message);
    }
    
    /**
     * Static method allowing to create instances of the class while handling
     * the InstantiationException thrown when the corresponding PopUp view
     * could not be created.
     * @param severity the importance of the popup message
     * @param message the message to display in the popup
     */
    public static void createPopUpOrLog(Level severity, String message){
        try {
            new MessagePopUpController(severity, message);
        } catch (InstantiationException ex) {
                LOG.log(Level.WARNING, "This error couldn't be shown with a popup: {0}",
                        message);
        }
    }
    
    /**
     * Overrides the parent's behavior to accept that other popups be created
     * while this one is open.
     * @return true
     */
    @Override
    protected boolean acceptMultiplePopUps() {
        return true;
    }
}
