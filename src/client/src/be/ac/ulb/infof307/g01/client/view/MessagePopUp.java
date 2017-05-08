package be.ac.ulb.infof307.g01.client.view;

import be.ac.ulb.infof307.g01.client.controller.MessagePopupController;
import java.util.logging.Level;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class MessagePopUp extends PopUp {
    private final Label _message;
    private final Button _closeButton;
    private final VBox _vbox;

    public MessagePopUp(MessagePopupController controller, Level severity, String message) {
        super(controller);
        _message = new Label(message);
        _vbox = new VBox();
        _closeButton = getCloseButton("Dismiss", "danger");
        show();
    }
    
    
    private void placeWidgets() {
        _vbox.getChildren().addAll(_message, _closeButton);
        add(_vbox);
    }
    
}
