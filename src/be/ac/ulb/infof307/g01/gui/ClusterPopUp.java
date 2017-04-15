package be.ac.ulb.infof307.g01.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * Pop-up showed when the user clicks on a pokemon cluster.
 */
public class ClusterPopUp extends PopUp {

    private VBox _vbox;
    private Label _message;
    private Button _closeButton;
    
    public ClusterPopUp() {
        super();
        initWidgets();
        placeWidgets();
        setStyle();
        show();
    }
    
    private void initWidgets() {
        _vbox = new VBox();
        initMessageLabel();
        initCloseButton();
    }
    
    private void initMessageLabel() {
        _message = new Label("You have clicked on a pokemon cluster. To see "
                + "which pokmon is contained in the pokemon cluster please zoom"
                + ".");
        _message.setMaxWidth(Region.USE_PREF_SIZE);
        _message.setWrapText(true);
    }
    
    private void initCloseButton() {
        _closeButton = getCloseButton("ok", "primary");
    }

    private void placeWidgets() {
        _vbox.getChildren().addAll(_message, _closeButton);
        super.add(_vbox);
    }

    private void setStyle() {
        _vbox.setAlignment(Pos.CENTER);
        _vbox.setSpacing(10);
    }
    
}
