package be.ac.ulb.infof307.g01.client.view.options;

import be.ac.ulb.infof307.g01.client.controller.options.TermsAndConditionsPopUpController;
import be.ac.ulb.infof307.g01.client.view.app.AbstractPopUp;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author hoornaert
 */
public class TermsAndConditionsPopUp extends AbstractPopUp {

    VBox _vbox;
    TextArea _text;
    Button _closeButton;
    
    public TermsAndConditionsPopUp(TermsAndConditionsPopUpController controller) {
        super(controller);
        initWidgets();
        placeWidgets();
        initSyle();
        setSize(300, 300);
        show();
    }

    private void initWidgets() {
        _vbox = new VBox();
        _text = new TextArea();
        _text.setEditable(false);
        _text.setWrapText(true);
        _closeButton = getCloseButton("quit", "danger");
    }

    private void placeWidgets() {
        _vbox.getChildren().addAll(_text, _closeButton);
        add(_vbox);
    }
    
    private void initSyle() {
        _vbox.setSpacing(10);
        _vbox.setAlignment(Pos.CENTER);
        VBox.setVgrow(_text, Priority.ALWAYS);
        _text.setMaxWidth(Double.MAX_VALUE);
    }
    
    public void setText(String text) {
        _text.setText(text);
    }
    
}
