package be.ac.ulb.infof307.g01.client.view.app;

import be.ac.ulb.infof307.g01.client.controller.app.MessagePopUpController;
import java.util.logging.Level;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class MessagePopUp extends AbstractPopUp {
    private final String _message;
    private final Level _severity;
    private Label _messageLabel;
    private Button _closeButton;
    private VBox _vbox;

    public MessagePopUp(MessagePopUpController controller, Level severity, String message) {
        super(controller);
        _message = severity.getLocalizedName() + ": " + message;
        _severity = severity;
        initWidgets();
        placeWidgets();
        setStyle();
        show();
    }
    
    private void initWidgets() {
        _messageLabel = new Label(_message);
        _vbox = new VBox();
        _closeButton = getCloseButton("Dismiss", "danger");
    }
    
    
    private void placeWidgets() {
        _vbox.setAlignment(Pos.CENTER);
        _vbox.getChildren().addAll(_messageLabel, _closeButton);
        add(_vbox);
    }

    private void setStyle() {
        _vbox.setSpacing(10);
        setSize(250, 50);
        
        _closeButton.getStyleClass().remove("danger");
        // Change the CSS class of the button, according to the severity of the message
        String  closeButtonCssClass;
        switch(_severity.toString()) {
            case "WARNING":
                closeButtonCssClass = "warning";
                break;
            case "ERROR":
                closeButtonCssClass = "danger";
                break;
            case "INFO":
            default:
                closeButtonCssClass = "info";
                
        }
        _closeButton.getStyleClass().add(closeButtonCssClass);
    }
    
}
